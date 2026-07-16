package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.tool.BaseMaterialBehavior;
import io.github.caleb67.modulartools.tool.HeadType;
import io.github.caleb67.modulartools.tool.MaterialFunctionContext;
import io.github.caleb67.modulartools.tool.Part;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipOperation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class GoldMaterialBehavior extends BaseMaterialBehavior {
    
    public GoldMaterialBehavior(Properties properties) {
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
    
    @Override
    public float getDestroySpeed(MaterialFunctionContext context, Part part, HeadType type, ItemStack itemStack, BlockState state) {
        if (!(type instanceof HeadType.NotApplicable))
            return super.getDestroySpeed(context, part, type, itemStack, state)
                *LapisMaterialBehavior.getAmplifierAmount(itemStack);
        if (part == Part.ROD) return 3.0F*LapisMaterialBehavior.getAmplifierAmount(itemStack);
        else if (part == Part.TRIM) return 2.0F*LapisMaterialBehavior.getAmplifierAmount(itemStack);
        return 1.0F*LapisMaterialBehavior.getAmplifierAmount(itemStack);
    }
    
    @Override
    public float getAttackSpeed(MaterialFunctionContext context, Part part, HeadType type, ItemStack itemStack) {
        return super.getAttackSpeed(context, part, type, itemStack)
            + (1.0F*LapisMaterialBehavior.getAmplifierAmount(itemStack));
    }
}

