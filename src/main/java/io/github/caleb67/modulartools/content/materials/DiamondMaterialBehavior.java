package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import io.github.caleb67.modulartools.tool.Part;
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
        return Optional.of((collector, context,
                            display, builder, tooltipFlag) -> {
            builder.accept(
                Component.translatable(TranslationUtil.makeEffectDescId(this.key, numTimes))
                         .withStyle(this.getEffectFormatting())
            );
        });
    }
    
    public static boolean shouldNotDamage(ItemStack itemStack, RandomSource randomSource) {
        var head = Part.HEAD.getMaterial(itemStack);
        var rod = Part.ROD.getMaterial(itemStack);
        var trim = Part.TRIM.getMaterial(itemStack);
        if (head.isEmpty() || rod.isEmpty() || trim.isEmpty()) return true;
        
        int count = (head.get() instanceof DiamondMaterialBehavior ? 1 : 0)
            + (rod.get() instanceof DiamondMaterialBehavior ? 1 : 0)
            + (trim.get() instanceof DiamondMaterialBehavior ? 1 : 0);
        count = count*LapisMaterialBehavior.getAmplifierAmount(itemStack);
        return randomSource.nextInt(6) < count;
    }
}
