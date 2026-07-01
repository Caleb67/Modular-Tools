package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.tool.InventoryTickContext;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import io.github.caleb67.modulartools.tool.Part;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipOperation;
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

        boolean should_enchant = head.get() instanceof QuartzMaterialBehavior ||
                rod.get() instanceof QuartzMaterialBehavior ||
                trim.get() instanceof QuartzMaterialBehavior;

        Holder<Enchantment> silk_touch = level.registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH);

        var item_enchantments = new ItemEnchantments.Mutable(itemStack.getEnchantments());
        item_enchantments.removeIf(enchantmentHolder -> enchantmentHolder.value() == silk_touch.value());

        if (should_enchant) item_enchantments.set(silk_touch, 1);
        EnchantmentHelper.setEnchantments(itemStack,item_enchantments.toImmutable());
    }
}
