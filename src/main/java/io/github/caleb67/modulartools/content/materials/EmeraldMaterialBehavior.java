package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.tool.BaseMaterialBehavior;
import io.github.caleb67.modulartools.tool.MaterialFunctionContext;
import io.github.caleb67.modulartools.tool.Part;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipOperation;
import io.github.caleb67.modulartools.util.Tests;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class EmeraldMaterialBehavior extends BaseMaterialBehavior {
    
    public EmeraldMaterialBehavior(Properties properties) {
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
    public void inventoryTick(MaterialFunctionContext context, ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if (context.hasSeen(this.key)) return;
        
        final int enchant_level = (
            (context.head instanceof EmeraldMaterialBehavior ? 1 : 0)
            + (context.rod instanceof EmeraldMaterialBehavior ? 1 : 0)
            + (context.trim instanceof EmeraldMaterialBehavior ? 1 : 0)
        ) * LapisMaterialBehavior.getAmplifierAmount(itemStack);
        
        var fortune = getEnchantment(context.registryAccess, Enchantments.FORTUNE).orElseThrow();
        var looting = getEnchantment(context.registryAccess, Enchantments.LOOTING).orElseThrow();
        
        var item_enchantments = new ItemEnchantments.Mutable(itemStack.getEnchantments());
        
        if (enchant_level > 0) {
            item_enchantments.set(fortune, enchant_level);
            item_enchantments.set(looting, enchant_level);
        }
        EnchantmentHelper.setEnchantments(itemStack, item_enchantments.toImmutable());
    }
    
    @Override public void removeEffects(MaterialFunctionContext context, Entity owner, ItemStack itemStack) {
        var fortune = getEnchantment(context.registryAccess, Enchantments.FORTUNE).orElseThrow();
        var looting = getEnchantment(context.registryAccess, Enchantments.LOOTING).orElseThrow();
        
        var item_enchantments = new ItemEnchantments.Mutable(itemStack.getEnchantments());
        item_enchantments.removeIf(Tests.in(fortune, looting));
        EnchantmentHelper.setEnchantments(itemStack, item_enchantments.toImmutable());
    }
}
