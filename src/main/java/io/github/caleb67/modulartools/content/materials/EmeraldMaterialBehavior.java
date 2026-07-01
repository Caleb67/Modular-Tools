package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.tool.InventoryTickContext;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import io.github.caleb67.modulartools.tool.Part;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipOperation;
import io.github.caleb67.modulartools.util.MethodChain;
import io.github.caleb67.modulartools.util.Tests;
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

public class EmeraldMaterialBehavior extends MaterialBehavior {

    public EmeraldMaterialBehavior(Properties properties) {
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

    @Override
    public void inventoryTick(InventoryTickContext context, ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if (context.hasSeen(this.key)) return;
        testAndApply(itemStack, level);
    }

    protected static void testAndApply(ItemStack itemStack, ServerLevel level) {
        var head = Part.HEAD.getMaterial(itemStack);
        var rod = Part.ROD.getMaterial(itemStack);
        var trim = Part.TRIM.getMaterial(itemStack);
        if (head.isEmpty() || rod.isEmpty() || trim.isEmpty()) return;

        final int enchant_level = ((head.get() instanceof EmeraldMaterialBehavior ? 1 : 0)
                + (rod.get() instanceof EmeraldMaterialBehavior ? 1 : 0)
                + (trim.get() instanceof EmeraldMaterialBehavior ? 1 : 0))
                * LapisMaterialBehavior.getAmplifierAmount(itemStack);

        Holder<Enchantment> fortune = level.registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE);
        Holder<Enchantment> looting = level.registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.LOOTING);

        var item_enchantments = new ItemEnchantments.Mutable(itemStack.getEnchantments());
        item_enchantments.removeIf(Tests.in(fortune, looting));
        if (enchant_level > 0) new MethodChain<>(item_enchantments)
                    .and(ItemEnchantments.Mutable::set, fortune, enchant_level)
                    .and(ItemEnchantments.Mutable::set, looting, enchant_level);
        EnchantmentHelper.setEnchantments(itemStack,item_enchantments.toImmutable());
    }
}
