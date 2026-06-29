package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.ModularToolsRegistries;
import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.register.MTDataComponents;
import io.github.caleb67.modulartools.tool.InventoryTickContext;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import io.github.caleb67.modulartools.tool.tooltip.ToolEffectTooltipOperation;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class QuartzMaterialBehavior extends MaterialBehavior {
    public QuartzMaterialBehavior(Properties properties) {
        super(properties);
    }

    public Optional<ToolEffectTooltipOperation> getEffectTooltip(ItemStack itemStack, int numTimes) {
        return Optional.of((executor, context,
                            display, builder, tooltipFlag) -> {
            builder.accept(
                    Component.translatable(TranslationUtil.makeEffectDescId(this.key, numTimes))
                            .withStyle(this.getEffectFormatting())
            );
        });
    }

    @Override
    public void inventoryTick(InventoryTickContext context, ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if (context.hasSeen(this.key)) return;
        testAndApply(itemStack, level);
    }

    public static void testAndApply(ItemStack itemStack, ServerLevel level) {
        var modular_tool_head = itemStack.get(MTDataComponents.MODULAR_TOOL_HEAD);
        var modular_tool_rod = itemStack.get(MTDataComponents.MODULAR_TOOL_ROD);
        var modular_tool_trim = itemStack.get(MTDataComponents.MODULAR_TOOL_TRIM);

        if (modular_tool_head == null ||
                modular_tool_rod == null ||
                modular_tool_trim == null) {
            return;
            // !TODO log this at some point
        }

        var head = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(modular_tool_head).value();
        var rod = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(modular_tool_rod).value();
        var trim = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(modular_tool_trim).value();

        boolean should_enchant;
        if (head instanceof QuartzMaterialBehavior ||
            rod instanceof QuartzMaterialBehavior ||
            trim instanceof QuartzMaterialBehavior) {
            should_enchant = true;
        } else {
            should_enchant = false;
        }

        Holder<Enchantment> silk_touch = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH);

        var enchantments = itemStack.getEnchantments();
        var item_enchantments = new ItemEnchantments.Mutable(enchantments);

        item_enchantments.removeIf(enchantmentHolder -> enchantmentHolder.value() == silk_touch.value());
        if (should_enchant) {
            item_enchantments.set(silk_touch, 1);
        }
        EnchantmentHelper.setEnchantments(itemStack,item_enchantments.toImmutable());
    }
}
