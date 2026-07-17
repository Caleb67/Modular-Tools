package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.tool.BaseMaterialBehavior;
import io.github.caleb67.modulartools.tool.HeadType;
import io.github.caleb67.modulartools.tool.MaterialFunctionContext;
import io.github.caleb67.modulartools.tool.Part;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipOperation;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DamageResistant;

import java.util.Optional;
import java.util.stream.Stream;

public class NetheriteMaterialBehavior extends BaseMaterialBehavior {
    public NetheriteMaterialBehavior(Properties properties) {
        super(properties);
    }
    
    @Override public Optional<MaterialEffectTooltipOperation> getEffectTooltip(ItemStack itemStack, int numTimes) {
        return Optional.of(((_, _, _, builder, _) -> builder.accept(
            Component.translatable(TranslationUtil.makeEffectDescId(this.key, numTimes))
                     .withStyle(this.getEffectFormatting())
        )));
    }
    
    @Override public void onCreation(MaterialFunctionContext context, Part part, HeadType type, ItemStack itemStack) {
        if (context.hasSeen(this.key)) return;
        var damageTypes = context.registryAccess.lookupOrThrow(Registries.DAMAGE_TYPE);
        var types = Stream.concat(
            damageTypes.getOrThrow(DamageTypeTags.IS_FIRE).stream(),
            damageTypes.getOrThrow(DamageTypeTags.IS_EXPLOSION).stream()
        ).toList();
        itemStack.set(
                DataComponents.DAMAGE_RESISTANT,
                new DamageResistant(HolderSet.direct(types))
        );
    }
}
