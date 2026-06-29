package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.ModularToolsRegistries;
import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.register.MTDataComponents;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipOperation;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class DiamondMaterialBehavior extends MaterialBehavior {
    public DiamondMaterialBehavior(Properties properties) {
        super(properties);
    }

    public Optional<MaterialEffectTooltipOperation> getEffectTooltip(ItemStack itemStack, int numTimes) {
        return Optional.of((executor, context,
                            display, builder, tooltipFlag) -> {
            builder.accept(
                    Component.translatable(TranslationUtil.makeEffectDescId(this.key, numTimes))
                            .withStyle(this.getEffectFormatting())
            );
        });
    }

    public static boolean shouldNotDamage(ItemStack itemStack, RandomSource randomSource) {
        var modular_tool_head = itemStack.get(MTDataComponents.MODULAR_TOOL_HEAD);
        var modular_tool_rod = itemStack.get(MTDataComponents.MODULAR_TOOL_ROD);
        var modular_tool_trim = itemStack.get(MTDataComponents.MODULAR_TOOL_TRIM);

        if (modular_tool_head == null ||
                modular_tool_rod == null ||
                modular_tool_trim == null) {
            return true;
            // !TODO log this at some point
        }

        var head = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(modular_tool_head).value();
        var rod = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(modular_tool_rod).value();
        var trim = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(modular_tool_trim).value();

        int count = 0;
        if (head instanceof DiamondMaterialBehavior)
            count++;
        if (rod instanceof DiamondMaterialBehavior)
            count++;
        if (trim instanceof DiamondMaterialBehavior)
            count++;
        count = count * LapisMaterialBehavior.getAmplifierAmount(itemStack);

        return randomSource.nextInt(6) < count;
    }
}
