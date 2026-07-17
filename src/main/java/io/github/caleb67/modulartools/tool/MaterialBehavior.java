package io.github.caleb67.modulartools.tool;

import io.github.caleb67.modulartools.ModularToolsRegistries;
import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipOperation;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class MaterialBehavior {
    public final ToolMaterial material;
    private HashMap<HeadType, MaterialStats> attribute_map;
    public final ResourceKey<MaterialBehavior> key;
    
    private final Properties properties;
    
    public MaterialBehavior(Properties properties) {
        this.material = properties.material;
        this.attribute_map = properties.attribute_map;
        this.key = properties.getIdOrThrow();
        this.properties = properties;
    }
    
    public ChatFormatting[] getFormatting() {
        return this.properties.formatting != null
            ? this.properties.formatting
            : new ChatFormatting[]{ChatFormatting.WHITE};
    }
    
    public ChatFormatting[] getEffectFormatting() {
        return this.properties.effect_formatting != null
            ? this.properties.effect_formatting
            : new ChatFormatting[]{ChatFormatting.WHITE};
    }
    
    public Set<Item> getItems() {return Collections.unmodifiableSet(this.properties.itemSupplier.get());}
    
    public boolean mineBlock(MaterialFunctionContext context, Part part, HeadType type, ItemStack itemStack, Level level,
                             BlockState state, BlockPos pos, LivingEntity owner) {
        if (type instanceof HeadType.NotApplicable) return true;
        
        Tool tool = type.getTool(this.material);
        
        if (tool == null) return false;
        
        if (level.isClientSide() || state.getDestroySpeed(level, pos) <= 0.0F || tool.damagePerBlock() <= 0)
            return true;
        
        AbstractModularToolItem.hurtAndBreakTool(itemStack, 1, owner, EquipmentSlot.MAINHAND);
        
        return true;
    }
    
    public float getDestroySpeed(MaterialFunctionContext context, Part part, HeadType type, ItemStack itemStack, BlockState state) {
        if (type instanceof HeadType.NotApplicable) return 0.0F;
        else return 1.0F;
    }
    
    public void inventoryTick(MaterialFunctionContext context, ItemStack itemStack, ServerLevel level,
                              Entity owner, @Nullable EquipmentSlot slot) {}
    
    public boolean isCorrectToolForDrops(MaterialFunctionContext context, HeadType type, ItemStack itemStack, BlockState state) {
        if (type instanceof HeadType.NotApplicable) return true;
        
        MaterialStats materialStats = this.attribute_map.get(type);
        if (materialStats == null)
            throw new IllegalStateException(
                "No attributes set for head type '" + type.getName() + "' with material '" + this.key.identifier() + "'!");
        
        return type.getTool(this.material).isCorrectForDrops(state);
    }
    
    public void appendPartTooltip(Part part, Item.TooltipContext context, TooltipDisplay display,
                                  Consumer<Component> builder, TooltipFlag tooltipFlag) {
        builder.accept(
            Component.translatable(TranslationUtil.makePartDescId(this.key, part))
                     .withStyle(this.getFormatting())
        );
    }
    
    public Optional<MaterialEffectTooltipOperation> getEffectTooltip(ItemStack itemStack, int numTimes) {
        return Optional.empty();
    }
    
    public float getAttackDamage(MaterialFunctionContext context, Part part, HeadType type, ItemStack itemStack) {
        if (type instanceof HeadType.NotApplicable) return 0.0F;
        
        MaterialStats materialStats = this.attribute_map.get(type);
        if (materialStats == null)
            throw new IllegalStateException(
                "No attributes set for head type '" + type.getName() + "' with material '" + this.key + "'!");
        
        return materialStats.baseAttackDamage + this.material.attackDamageBonus();
    }
    
    public float getAttackSpeed(MaterialFunctionContext context, Part part, HeadType type, ItemStack itemStack) {
        if (type instanceof HeadType.NotApplicable) return 0.0F;
        
        MaterialStats materialStats = this.attribute_map.get(type);
        if (materialStats == null)
            throw new IllegalStateException(
                "No attributes set for head type '" + type.getName() + "' with material '" + this.key + "'!");
        
        return materialStats.baseAttackSpeed;
    }
    
    protected abstract Set<Item> validItemsToRepair();
    
    public void addHeadTypeAttributes(HeadType type, float baseAttackDamage, float baseAttackSpeed) {
        if (this.attribute_map.containsKey(type))
            throw new IllegalArgumentException(
                "HeadType '" + type.getName() + "' already has attributes for material '" + this.key.identifier() + "'!");
        else
            this.attribute_map.put(type, new MaterialStats(baseAttackDamage, baseAttackSpeed));
    }
    
    public boolean hasHeadTypeAttributes(HeadType type) {
        MaterialStats materialStats = this.attribute_map.get(type);
        return materialStats != null;
    }
    
    public static Set<MaterialBehavior> fromItem(Item item) {
        var materials = ModularToolsRegistries.getAllMaterialBehaviors();
        return StreamSupport
            .stream(materials.spliterator(), false)
            .filter(materialBehavior -> materialBehavior.getItems().contains(item))
            .collect(Collectors.toUnmodifiableSet());
    }
    
    public void hurtEnemy(MaterialFunctionContext context, Part part, HeadType type, ItemStack itemStack, LivingEntity mob, LivingEntity attacker) {}
    
    public void onCreation(MaterialFunctionContext context, Part part, HeadType type,
                           ItemStack itemStack) {
    }
    
    public void removeEffects(MaterialFunctionContext context, Entity owner, ItemStack itemStack) {}
    
    public static final class Properties {
        HashMap<HeadType, MaterialStats> attribute_map;
        ResourceKey<MaterialBehavior> key;
        ToolMaterial material;
        @Nullable ChatFormatting[] formatting;
        @Nullable ChatFormatting[] effect_formatting;
        Supplier<Set<Item>> itemSupplier;
        
        public Properties() {
            this.attribute_map = new HashMap<HeadType, MaterialStats>();
            this.key = null;
            this.material = ToolMaterial.WOOD;
            this.formatting = null;
            this.effect_formatting = null;
            this.itemSupplier = Collections::emptySet;
        }
        
        public Properties setId(ResourceKey<MaterialBehavior> key) {
            this.key = key;
            return this;
        }
        
        public Properties setAttributesForHeadType(HeadType type, float baseAttackDamage, float baseAttackSpeed) {
            if (this.attribute_map.containsKey(type)) return this;
            this.attribute_map.put(type, new MaterialStats(baseAttackDamage, baseAttackSpeed));
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
        
        public Properties setRepairItems(Supplier<Set<Item>> itemSupplier) {
            this.itemSupplier = itemSupplier;
            return this;
        }
    }
    
    public record MaterialStats(float baseAttackDamage, float baseAttackSpeed) {}
    
    public static Optional<Holder.Reference<Enchantment>> getEnchantment(HolderLookup.Provider registryAccess, ResourceKey<Enchantment> enchantment) {
        return registryAccess.lookupOrThrow(Registries.ENCHANTMENT).get(enchantment);
    }
}
