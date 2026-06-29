package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.ModularToolsRegistries;
import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.tool.AbstractModularToolItem;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipOperation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class LapisMaterialBehavior extends MaterialBehavior {
    public LapisMaterialBehavior(Properties properties) {
        super(properties);
    }

    public static int getAmplifierAmount(ItemStack stack) {
        var head = AbstractModularToolItem.getToolHead(stack);
        var rod = AbstractModularToolItem.getToolRod(stack);
        var trim = AbstractModularToolItem.getToolTrim(stack);
        if (head.isEmpty() || rod.isEmpty() || trim.isEmpty())
            return 1;
        // !TODO log this at some point

        int level = 1;
        if (ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(head.get()).value() instanceof LapisMaterialBehavior) level = level * 2;
        if (ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(rod.get()).value() instanceof LapisMaterialBehavior) level = level * 2;
        if (ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(trim.get()).value() instanceof LapisMaterialBehavior) level = level * 2;

        return level;
    }

    public static int getRealLevel(ItemStack stack) {
        var head = AbstractModularToolItem.getToolHead(stack);
        var rod = AbstractModularToolItem.getToolRod(stack);
        var trim = AbstractModularToolItem.getToolTrim(stack);
        if (head.isEmpty() || rod.isEmpty() || trim.isEmpty())
            return 0;

        int level = 0;
        if (ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(head.get()).value() instanceof LapisMaterialBehavior) level += 1;
        if (ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(rod.get()).value() instanceof LapisMaterialBehavior) level += 1;
        if (ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(trim.get()).value() instanceof LapisMaterialBehavior) level += 1;

        return level;
    }

    public Optional<MaterialEffectTooltipOperation> getEffectTooltip(ItemStack itemStack, int numTimes) {
        var num = getRealLevel(itemStack);
        return Optional.of((executor, context,
                            display, builder, tooltipFlag) -> {
            builder.accept(
                    Component.translatable(TranslationUtil.makeEffectDescId(this.key, num))
                            .withStyle(this.getEffectFormatting())
            );
        });
    }
}
