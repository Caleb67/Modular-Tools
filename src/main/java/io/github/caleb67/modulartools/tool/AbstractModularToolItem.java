package io.github.caleb67.modulartools.tool;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.ModularToolsRegistries;
import io.github.caleb67.modulartools.content.materials.DiamondMaterialBehavior;
import io.github.caleb67.modulartools.content.materials.EmeraldMaterialBehavior;
import io.github.caleb67.modulartools.content.materials.QuartzMaterialBehavior;
import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.register.MTDataComponents;
import io.github.caleb67.modulartools.tool.tooltip.ToolEffectTooltipExecutor;
import io.github.caleb67.modulartools.tool.tooltip.ToolEffectTooltipOperation;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public abstract class AbstractModularToolItem extends Item {
    public static final Identifier BASE_ATTACK_SPEED = Identifier.fromNamespaceAndPath(ModularTools.MODID, "base_attack_speed");
    public static final Identifier BASE_ATTACK_DAMAGE = Identifier.fromNamespaceAndPath(ModularTools.MODID, "base_attack_damage");

    public AbstractModularToolItem(Properties properties) {
        super(properties);
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
        var tool_head = getToolHead(itemStack);
        var tool_rod = getToolRod(itemStack);
        var tool_trim = getToolTrim(itemStack);

        tool_rod.ifPresentOrElse(
                rod -> ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(rod).value()
                        .appendPartTooltip(Part.ROD, context, display, builder, tooltipFlag),
                () -> builder.accept(Component.translatable(TranslationUtil.makePartUnknown()))
        );
        tool_trim.ifPresentOrElse(
                trim -> ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(trim).value()
                        .appendPartTooltip(Part.TRIM, context, display, builder, tooltipFlag),
                () -> builder.accept(Component.translatable(TranslationUtil.makePartUnknown()))
        );

        ArrayList<ToolEffectTooltipOperation> operations = new ArrayList<>(3);
        ToolEffectTooltipExecutor executor = new ToolEffectTooltipExecutor();

        if (tool_head.isEmpty() || tool_rod.isEmpty() || tool_trim.isEmpty())
            builder.accept(Component.translatable(TranslationUtil.makePartUnknown()));
        else operations = executor.add(tool_head.get())
                .add(tool_rod.get())
                .add(tool_trim.get())
                .complete(itemStack, context, display, builder, tooltipFlag);

        if (!operations.isEmpty()) {
            builder.accept(Component.literal("————————").withStyle(ChatFormatting.GRAY, ChatFormatting.BOLD));
            operations.forEach(operation -> operation.apply(executor, context, display, builder, tooltipFlag));
        }
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
        var tool_head = getToolHead(itemStack);
        var tool_rod = getToolRod(itemStack);
        var tool_trim = getToolTrim(itemStack);

        if (tool_head.isEmpty() || tool_rod.isEmpty() || tool_trim.isEmpty())
            return super.mineBlock(itemStack, level, state, pos, owner);
        // !TODO log this at some point

        var head = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_head.get()).value();
        var rod = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_rod.get()).value();
        var trim = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_trim.get()).value();

        if (!head.mineBlock(Part.HEAD, this.getHeadType(), itemStack, level, state, pos, owner)) return false;
        if (!rod.mineBlock(Part.ROD, new HeadType.NotApplicable(), itemStack, level, state, pos, owner)) return false;
        if (!trim.mineBlock(Part.TRIM, new HeadType.NotApplicable(), itemStack, level, state, pos, owner)) return false;
        return true;
    }


    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity mob, LivingEntity attacker) {
        if (!DiamondMaterialBehavior.shouldNotDamage(itemStack, attacker.getRandom()))
            itemStack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);

        var tool_head = getToolHead(itemStack);
        var tool_rod = getToolRod(itemStack);
        var tool_trim = getToolTrim(itemStack);

        if (tool_head.isEmpty() || tool_rod.isEmpty() || tool_trim.isEmpty())
            super.hurtEnemy(itemStack, mob, attacker);
        // !TODO log this at some point

        var head = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_head.get()).value();
        var rod = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_rod.get()).value();
        var trim = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_trim.get()).value();

        head.hurtEnemy(Part.HEAD, this.getHeadType(), itemStack, mob, attacker);
        rod.hurtEnemy(Part.ROD, new HeadType.NotApplicable(), itemStack, mob, attacker);
        trim.hurtEnemy(Part.HEAD, new HeadType.NotApplicable(), itemStack, mob, attacker);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack itemStack, BlockState state) {
        var tool_head = getToolHead(itemStack);
        if (tool_head.isEmpty())
            return super.isCorrectToolForDrops(itemStack, state);
        // !TODO log this at some point

        var head = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_head.get()).value();
        return head.isCorrectToolForDrops(this.getHeadType(), itemStack, state);
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState state) {
        var tool_head = getToolHead(itemStack);
        var tool_rod = getToolRod(itemStack);
        var tool_trim = getToolTrim(itemStack);

        if (tool_head.isEmpty() || tool_rod.isEmpty() || tool_trim.isEmpty())
            return super.getDestroySpeed(itemStack, state);
        // !TODO log this at some point

        var head = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_head.get()).value();
        var rod = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_rod.get()).value();
        var trim = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_trim.get()).value();

        var head_speed = this.getHeadType().getTool(head.material).getMiningSpeed(state) * head.getDestroySpeed(Part.HEAD, this.getHeadType(), itemStack, state);
        var rod_speed = this.getHeadType().getTool(rod.material).getMiningSpeed(state) * rod.getDestroySpeed(Part.ROD, new HeadType.NotApplicable(), itemStack, state);
        var trim_speed = this.getHeadType().getTool(trim.material).getMiningSpeed(state) * trim.getDestroySpeed(Part.TRIM, new HeadType.NotApplicable(), itemStack, state);
        return head_speed + rod_speed + trim_speed;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        itemStack.set(net.minecraft.core.component.DataComponents.MAX_DAMAGE, findMaxDamage(itemStack));
        EmeraldMaterialBehavior.testAndApply(itemStack, level);
        QuartzMaterialBehavior.testAndApply(itemStack, level);
        super.inventoryTick(itemStack, level, owner, slot);
    }

    protected double getSumAttributesOfParts(BiFunction<Part, HeadType, Float> head,
                                           BiFunction<Part, HeadType, Float> rod,
                                           BiFunction<Part, HeadType, Float> trim,
                                           double divisor, ItemStack itemStack) {
        var vhead = head.apply(Part.HEAD, this.getHeadType()) / divisor;
        var vrod = rod.apply(Part.ROD, this.getHeadType()) / divisor;
        var vtrim = trim.apply(Part.TRIM, this.getHeadType()) / divisor;
        var sum = vhead + vrod + vtrim;
        return sum;
    }

    public final void updateAttackAttributes(ItemStack itemStack, Entity owner) {
        if (!(owner instanceof ServerPlayer player)) return;
        var attack_speed_attr = player.getAttribute(Attributes.ATTACK_SPEED);
        var attack_damage_attr = player.getAttribute(Attributes.ATTACK_DAMAGE);

        var tool_head = getToolHead(itemStack);
        var tool_rod = getToolRod(itemStack);
        var tool_trim = getToolTrim(itemStack);
        if (tool_head.isEmpty() || tool_rod.isEmpty() || tool_trim.isEmpty())
            return;
        // !TODO log this at some point
        var head = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_head.get()).value();
        var rod = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_rod.get()).value();
        var trim = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_trim.get()).value();

        attack_speed_attr.addOrReplacePermanentModifier(
                new AttributeModifier(
                        BASE_ATTACK_SPEED,
                        this.getSumAttributesOfParts(
                                (part, type) -> head.getAttackSpeed(part, type, itemStack),
                                (part, type) -> rod.getAttackSpeed(part, type, itemStack),
                                (part, type) -> trim.getAttackSpeed(part, type, itemStack),
                                3, itemStack
                        ),
                        AttributeModifier.Operation.ADD_VALUE
                )
        );
        attack_damage_attr.addOrReplacePermanentModifier(
                new AttributeModifier(
                        BASE_ATTACK_DAMAGE,
                        this.getSumAttributesOfParts(
                                (part, type) -> head.getAttackDamage(part, type, itemStack),
                                (part, type) -> rod.getAttackDamage(part, type, itemStack),
                                (part, type) -> trim.getAttackDamage(part, type, itemStack),
                                3, itemStack
                        ),
                        AttributeModifier.Operation.ADD_VALUE
                )
        );
    }

    public int findMaxDamage(ItemStack itemStack) {
        var tool_head = getToolHead(itemStack);
        var tool_rod = getToolRod(itemStack);
        var tool_trim = getToolTrim(itemStack);

        if (tool_head.isEmpty() || tool_rod.isEmpty() || tool_trim.isEmpty())
            return 0;
        // !TODO log this at some point

        double head = (ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_head.get()).value().material.durability() / 1.5);
        double rod =(ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_rod.get()).value().material.durability() / 1.5);
        double trim = (ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(tool_trim.get()).value().material.durability() / 1.5);
        return (int) (Math.ceil(head) + Math.ceil(rod) + Math.ceil(trim));
    }
}
