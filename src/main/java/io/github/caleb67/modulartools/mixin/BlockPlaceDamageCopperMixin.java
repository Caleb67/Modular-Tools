package io.github.caleb67.modulartools.mixin;

import io.github.caleb67.modulartools.tool.AbstractModularToolItem;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public abstract class BlockPlaceDamageCopperMixin implements ItemLike, FeatureElement, FabricItem {
    @Inject(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;consume(ILnet/minecraft/world/entity/LivingEntity;)V"))
    public void place(BlockPlaceContext placeContext, CallbackInfoReturnable<InteractionResult> cir) {
        var stack = placeContext.getPlayer().getMainHandItem();
        if (stack.getItem() instanceof AbstractModularToolItem) {
            stack.hurtAndBreak(1, placeContext.getPlayer(), InteractionHand.MAIN_HAND);
        }
    }
}
