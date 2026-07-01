package io.github.caleb67.modulartools.tool;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.ModularToolsRegistries;
import io.github.caleb67.modulartools.content.materials.DiamondMaterialBehavior;
import io.github.caleb67.modulartools.content.materials.EchoMaterialBehavior;
import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.register.MTDataComponents;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipCollector;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public abstract class AbstractModularToolItem extends Item {
    public static final Identifier BASE_ATTACK_SPEED = Identifier.fromNamespaceAndPath(ModularTools.MODID, "base_attack_speed");
    public static final Identifier BASE_ATTACK_DAMAGE = Identifier.fromNamespaceAndPath(ModularTools.MODID, "base_attack_damage");

    public AbstractModularToolItem(Properties properties) {
        super(properties.stacksTo(1)
                .durability(ToolMaterial.WOOD.durability())
                .component(MTDataComponents.MODULAR_TOOL_HEAD, MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key)
                .component(MTDataComponents.MODULAR_TOOL_ROD, MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key)
                .component(MTDataComponents.MODULAR_TOOL_TRIM, MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key)
                .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false)
                .component(
                        DataComponents.TOOLTIP_DISPLAY,
                        new TooltipDisplay(false, new LinkedHashSet<>(List.of(DataComponents.ENCHANTMENTS)))
                )
        );
    }

    @Override
    public @NotNull Component getName(ItemStack itemStack) {
        var tool_head = getToolHead(itemStack);
        if (tool_head.isPresent())
            return Component.translatable(TranslationUtil.makeToolDescId(tool_head.get(), this.getHeadType()))
                    .withStyle(ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_head.get()).value().getFormatting());
        else
            return super.getName(itemStack);
    }

    public abstract @NotNull HeadType getHeadType();

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context,
                                TooltipDisplay display, Consumer<Component> builder,
                                TooltipFlag tooltipFlag) {
        var tool_head = Part.HEAD.getMaterial(itemStack);
        var tool_rod = Part.ROD.getMaterial(itemStack);
        var tool_trim = Part.TRIM.getMaterial(itemStack);
        tool_rod.ifPresentOrElse(
                rod -> rod.appendPartTooltip(Part.ROD, context, display, builder, tooltipFlag),
                () -> builder.accept(Component.translatable(TranslationUtil.makePartUnknown()))
        );
        tool_trim.ifPresentOrElse(
                trim -> trim.appendPartTooltip(Part.TRIM, context, display, builder, tooltipFlag),
                () -> builder.accept(Component.translatable(TranslationUtil.makePartUnknown()))
        );
        if (tool_head.isEmpty() || tool_rod.isEmpty() || tool_trim.isEmpty()) return;

        MaterialEffectTooltipCollector collector = new MaterialEffectTooltipCollector();
        var operations = collector.add(tool_head.get().key)
                .add(tool_rod.get().key)
                .add(tool_trim.get().key)
                .complete(itemStack, context, display, builder, tooltipFlag);
        if (!operations.isEmpty())
            builder.accept(Component.literal("————————").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));
        operations.forEach(operation -> operation.apply(collector, context, display, builder, tooltipFlag));
    }

    public static Optional<ResourceKey<MaterialBehavior>> getToolHead(ItemInstance item) {
        var modular_tool_head = item.get(MTDataComponents.MODULAR_TOOL_HEAD);
        return modular_tool_head != null ? Optional.of(modular_tool_head) : Optional.empty();
    }
    public static Optional<ResourceKey<MaterialBehavior>> getToolRod(ItemInstance item) {
        var modular_tool_rod = item.get(MTDataComponents.MODULAR_TOOL_ROD);
        return modular_tool_rod != null ? Optional.of(modular_tool_rod) : Optional.empty();
    }
    public static Optional<ResourceKey<MaterialBehavior>> getToolTrim(ItemInstance item) {
        var modular_tool_trim = item.get(MTDataComponents.MODULAR_TOOL_TRIM);
        return modular_tool_trim != null ? Optional.of(modular_tool_trim) : Optional.empty();
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {
        return super.useOn(context);
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState state, BlockPos pos, LivingEntity owner) {
        var head = Part.HEAD.getMaterial(itemStack);
        var rod = Part.ROD.getMaterial(itemStack);
        var trim = Part.TRIM.getMaterial(itemStack);
        if (head.isEmpty() || rod.isEmpty() || trim.isEmpty())
            return super.mineBlock(itemStack, level, state, pos, owner);

        if (!head.get().mineBlock(Part.HEAD, this.getHeadType(), itemStack, level, state, pos, owner)) return false;
        if (!rod.get().mineBlock(Part.ROD, new HeadType.NotApplicable(), itemStack, level, state, pos, owner)) return false;
        if (!trim.get().mineBlock(Part.TRIM, new HeadType.NotApplicable(), itemStack, level, state, pos, owner)) return false;
        return true;
    }


    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity mob, LivingEntity attacker) {
        hurtAndBreakTool(itemStack, 1, attacker, EquipmentSlot.MAINHAND);
        var head = Part.HEAD.getMaterial(itemStack);
        var rod = Part.ROD.getMaterial(itemStack);
        var trim = Part.TRIM.getMaterial(itemStack);
        if (head.isEmpty() || rod.isEmpty() || trim.isEmpty()) {
            super.hurtEnemy(itemStack, mob, attacker); return;
        }

        head.get().hurtEnemy(Part.HEAD, this.getHeadType(), itemStack, mob, attacker);
        rod.get().hurtEnemy(Part.ROD, new HeadType.NotApplicable(), itemStack, mob, attacker);
        trim.get().hurtEnemy(Part.HEAD, new HeadType.NotApplicable(), itemStack, mob, attacker);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack itemStack, BlockState state) {
        var head = Part.HEAD.getMaterial(itemStack);
        return head
                .map(materialBehavior -> materialBehavior.isCorrectToolForDrops(this.getHeadType(), itemStack, state))
                .orElseGet(() -> super.isCorrectToolForDrops(itemStack, state));
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState state) {
        var head = Part.HEAD.getMaterial(itemStack);
        var rod = Part.ROD.getMaterial(itemStack);
        var trim = Part.TRIM.getMaterial(itemStack);
        if (head.isEmpty() || rod.isEmpty() || trim.isEmpty())
            return super.getDestroySpeed(itemStack, state);

        var head_speed = this.getHeadType().getTool(head.get().material).getMiningSpeed(state)
                * head.get().getDestroySpeed(Part.HEAD, this.getHeadType(), itemStack, state);
        var rod_speed = this.getHeadType().getTool(rod.get().material).getMiningSpeed(state)
                * rod.get().getDestroySpeed(Part.ROD, new HeadType.NotApplicable(), itemStack, state);
        var trim_speed = this.getHeadType().getTool(trim.get().material).getMiningSpeed(state)
                * trim.get().getDestroySpeed(Part.TRIM, new HeadType.NotApplicable(), itemStack, state);
        return head_speed + rod_speed + trim_speed;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        var head = Part.HEAD.getMaterial(itemStack);
        var rod = Part.ROD.getMaterial(itemStack);
        var trim = Part.TRIM.getMaterial(itemStack);
        if (head.isEmpty() || rod.isEmpty() || trim.isEmpty()) {
            super.inventoryTick(itemStack, level, owner, slot);
            return;
        }
        InventoryTickContext context = new InventoryTickContext();

        head.get().inventoryTick(context, itemStack, level, owner, slot);
        context.add(head.get().key);
        rod.get().inventoryTick(context, itemStack, level, owner, slot);
        context.add(rod.get().key);
        trim.get().inventoryTick(context, itemStack, level, owner, slot);
        context.add(trim.get().key);

        itemStack.set(net.minecraft.core.component.DataComponents.MAX_DAMAGE, findMaxDamage(itemStack));
        super.inventoryTick(itemStack, level, owner, slot);
    }

    public double getSumAttributesOfParts(BiFunction<Part, HeadType, Float> head,
                                           BiFunction<Part, HeadType, Float> rod,
                                           BiFunction<Part, HeadType, Float> trim,
                                           double divisor) {
        var vhead = head.apply(Part.HEAD, this.getHeadType()) / divisor;
        var vrod = rod.apply(Part.ROD, this.getHeadType()) / divisor;
        var vtrim = trim.apply(Part.TRIM, this.getHeadType()) / divisor;
        return vhead + vrod + vtrim;
    }

    public final void updateAttackAttributes(ItemStack itemStack, Entity owner) {
        if (!(owner instanceof ServerPlayer player)) return;
        var attack_speed_attr = player.getAttribute(Attributes.ATTACK_SPEED);
        var attack_damage_attr = player.getAttribute(Attributes.ATTACK_DAMAGE);

        var head = Part.HEAD.getMaterial(itemStack);
        var rod = Part.ROD.getMaterial(itemStack);
        var trim = Part.TRIM.getMaterial(itemStack);
        if (head.isEmpty() || rod.isEmpty() || trim.isEmpty())
            return;
        // !TODO log this at some point

        attack_speed_attr.addOrReplacePermanentModifier(
                new AttributeModifier(
                        BASE_ATTACK_SPEED,
                        this.getSumAttributesOfParts(
                                (part, type) -> head.get().getAttackSpeed(part, type, itemStack),
                                (part, type) -> rod.get().getAttackSpeed(part, type, itemStack),
                                (part, type) -> trim.get().getAttackSpeed(part, type, itemStack),
                                3
                        ),
                        AttributeModifier.Operation.ADD_VALUE
                )
        );
        attack_damage_attr.addOrReplacePermanentModifier(
                new AttributeModifier(
                        BASE_ATTACK_DAMAGE,
                        this.getSumAttributesOfParts(
                                (part, type) -> head.get().getAttackDamage(part, type, itemStack),
                                (part, type) -> rod.get().getAttackDamage(part, type, itemStack),
                                (part, type) -> trim.get().getAttackDamage(part, type, itemStack),
                                3
                        ),
                        AttributeModifier.Operation.ADD_VALUE
                )
        );
    }

    public int findMaxDamage(ItemStack itemStack) {
        var head = Part.HEAD.getMaterial(itemStack);
        var rod = Part.ROD.getMaterial(itemStack);
        var trim = Part.TRIM.getMaterial(itemStack);
        if (head.isEmpty() || rod.isEmpty() || trim.isEmpty())
            return 0;

        var sum =this.getSumAttributesOfParts(
                (_, _) -> (float) head.get().material.durability(),
                (_, _) -> (float) rod.get().material.durability(),
                (_, _) -> (float) trim.get().material.durability(),
                1.5
        );
        return (int) (Math.ceil(sum));
    }

    public static void hurtAndBreakTool(ItemStack itemStack, int amount, LivingEntity attacker, EquipmentSlot slot) {
        if (DiamondMaterialBehavior.shouldNotDamage(itemStack, attacker.getRandom())) return;
        if (EchoMaterialBehavior.shouldNotDamage(itemStack)) return;
        itemStack.hurtAndBreak(amount, attacker, slot);
    }
}
