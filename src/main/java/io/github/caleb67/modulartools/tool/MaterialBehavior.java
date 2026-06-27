package io.github.caleb67.modulartools.tool;

import io.github.caleb67.modulartools.ModularToolsRegistries;
import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.tool.tooltip.ToolEffectTooltipOperation;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MaterialBehavior {
    public final ToolMaterial material;
    private HashMap<HeadType, Attribute> attribute_map;
    public final ResourceKey<MaterialBehavior> key;
    private Set<Item> items;

    private final Properties properties;

    public MaterialBehavior(Properties properties) {
        this.material = properties.material;
        this.attribute_map = properties.attribute_map;
        this.key = properties.getIdOrThrow();
        this.properties = properties;
        this.items = Set.of();
    }

    public ChatFormatting[] getFormatting() { return this.properties.formatting != null ? this.properties.formatting : new ChatFormatting[]{ChatFormatting.WHITE}; }
    public ChatFormatting[] getEffectFormatting() { return this.properties.effect_formatting != null ? this.properties.effect_formatting : new ChatFormatting[]{ChatFormatting.WHITE}; }

    public List<Item> getItems() { return this.items.stream().toList();}

    public boolean mineBlock(Part part, HeadType type, ItemStack itemStack, Level level,
                             BlockState state, BlockPos pos, LivingEntity owner) {
        if (type instanceof HeadType.NotApplicable) return true;

        Tool tool = type.getTool(this.material);

        if (tool == null) return false;

        if (level.isClientSide() || state.getDestroySpeed(level, pos) <= 0.0F || tool.damagePerBlock() <= 0) return true;

        itemStack.hurtAndBreak(tool.damagePerBlock(), owner, EquipmentSlot.MAINHAND);

        return true;
    }

    public float getDestroySpeed(Part part, HeadType type, ItemStack itemStack, BlockState state) {
        if (type instanceof HeadType.NotApplicable) return 0.0F;
        else return 1.0F;
    }

    public boolean isCorrectToolForDrops(HeadType type, ItemStack itemStack, BlockState state) {
        if (type instanceof HeadType.NotApplicable) return true;

        Attribute attribute = this.attribute_map.get(type);
        if (attribute == null)
            throw new IllegalStateException("No attributes set for head type '" + type.getName() + "' with material '" + this.key.identifier() + "'!");

        return type.getTool(this.material).isCorrectForDrops(state);
    }

    public void appendPartTooltip(Part part, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        builder.accept(
                Component.translatable(TranslationUtil.makePartDescId(this.key, part))
                        .withStyle(this.getFormatting())
        );
    }

    public float getAttackDamage(Part part, HeadType type, ItemStack itemStack) {
        if (type instanceof HeadType.NotApplicable) return 0.0F;

        Attribute attribute = this.attribute_map.get(type);
        if (attribute == null)
            throw new IllegalStateException("No attributes set for head type '" + type.getName() + "' with material '" + this.key + "'!");

        return attribute.baseAttackDamage + this.material.attackDamageBonus();
    }

    public float getAttackSpeed(Part part, HeadType type, ItemStack itemStack) {
        if (type instanceof HeadType.NotApplicable) return 0.0F;

        Attribute attribute = this.attribute_map.get(type);
        if (attribute == null)
            throw new IllegalStateException("No attributes set for head type '" + type.getName() + "' with material '" + this.key + "'!");

        return attribute.baseAttackSpeed;
    }

    public void addValidItems(Set<Item> items) {
        this.items = Stream.concat(this.items.stream(), items.stream()).collect(Collectors.toSet());
    }

    public Optional<ToolEffectTooltipOperation> getEffectTooltip(ItemStack itemStack, int numTimes) {
        return Optional.empty();
    }

    public void addHeadTypeAttributes(HeadType key, float baseAttackDamage, float baseAttackSpeed) {
        if (this.attribute_map.containsKey(key))
            throw new IllegalArgumentException("HeadType '"+key.getName()+"' already has attributes for material '"+this.key.identifier()+"'!");
        else
            this.attribute_map.put(key, new Attribute(baseAttackDamage, baseAttackSpeed));
    }

    public static Set<MaterialBehavior> fromItem(Item item) {
        var materials = ModularToolsRegistries.getAllMaterialBehaviors();
        return StreamSupport
                .stream(materials.spliterator(), false)
                .filter(materialBehavior -> materialBehavior.getItems().contains(item))
                .collect(Collectors.toSet());
    }

    public void hurtEnemy(Part part, HeadType type, ItemStack itemStack, LivingEntity mob, LivingEntity attacker) {}

    public static final class Properties {
        HashMap<HeadType, Attribute> attribute_map;
        ResourceKey<MaterialBehavior> key;
        ToolMaterial material;
        @Nullable ChatFormatting[] formatting;
        @Nullable ChatFormatting[] effect_formatting;

        public Properties() {
            this.attribute_map = new HashMap<HeadType, Attribute>();
            this.key = null;
            this.material = ToolMaterial.WOOD;
            this.formatting = null;
            this.effect_formatting = null;
        }

        public Properties setId(ResourceKey<MaterialBehavior> key) {
            this.key = key;
            return this;
        }

        public Properties setAttributesForHeadType(HeadType type, float baseAttackDamage, float baseAttackSpeed) {
            this.attribute_map.put(type, new Attribute(baseAttackDamage, baseAttackSpeed));
            return this;
        }

        public Properties toolMaterial(ToolMaterial material) {
            this.material = material;
            return this;
        }

        public Properties setFormatting(ChatFormatting... formatting) {
            this.formatting = formatting;
            return this;
        }

        public Properties setEffectFormatting(ChatFormatting... formatting) {
            this.effect_formatting = formatting;
            return this;
        }

        public ResourceKey<MaterialBehavior> getIdOrThrow() {
            if (this.key == null) throw new NullPointerException("MaterialBehavior id not set.");
            return this.key;
        }
    }
    public record Attribute(float baseAttackDamage, float baseAttackSpeed) {}
}
