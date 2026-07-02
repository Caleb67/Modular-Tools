package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import io.github.caleb67.modulartools.tool.Part;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipOperation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class LapisMaterialBehavior extends MaterialBehavior {
    public LapisMaterialBehavior(Properties properties) {
        super(properties);
    }
    
    public static int getAmplifierAmount(ItemStack itemStack) {
        var head = Part.HEAD.getMaterial(itemStack);
        var rod = Part.ROD.getMaterial(itemStack);
        var trim = Part.TRIM.getMaterial(itemStack);
        if (head.isEmpty() || rod.isEmpty() || trim.isEmpty()) return 1;
        
        return (head.get() instanceof LapisMaterialBehavior ? 2 : 1)
            *(rod.get() instanceof LapisMaterialBehavior ? 2 : 1)
            *(trim.get() instanceof LapisMaterialBehavior ? 2 : 1);
    }
    
    public static int getRealLevel(ItemStack itemStack) {
        var head = Part.HEAD.getMaterial(itemStack);
        var rod = Part.ROD.getMaterial(itemStack);
        var trim = Part.TRIM.getMaterial(itemStack);
        if (head.isEmpty() || rod.isEmpty() || trim.isEmpty()) return 1;
        
        return (head.get() instanceof LapisMaterialBehavior ? 1 : 0)
            + (rod.get() instanceof LapisMaterialBehavior ? 1 : 0)
            + (trim.get() instanceof LapisMaterialBehavior ? 1 : 0);
    }
    
    public Optional<MaterialEffectTooltipOperation> getEffectTooltip(ItemStack itemStack, int numTimes) {
        var num = getRealLevel(itemStack);
        return Optional.of((collector, context,
                            display, builder, tooltipFlag) -> {
            builder.accept(
                Component.translatable(TranslationUtil.makeEffectDescId(this.key, num))
                         .withStyle(this.getEffectFormatting())
            );
        });
    }
}
