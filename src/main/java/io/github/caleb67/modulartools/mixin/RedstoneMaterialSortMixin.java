package io.github.caleb67.modulartools.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.caleb67.modulartools.content.materials.RedstoneMaterialBehavior;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import io.github.caleb67.modulartools.tool.AbstractModularToolItem;
import io.github.caleb67.modulartools.tool.Part;
import io.github.caleb67.modulartools.util.Tests;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class RedstoneMaterialSortMixin {
    
    @Inject(method = "playerTouch", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/world/entity/player/Inventory;add(Lnet/minecraft/world/item/ItemStack;)Z"))
    public void sortIntoBoxes(Player player, CallbackInfo ci,
                              @Local(name = "itemStack") ItemStack itemStack) {
        var hand_item = player.getMainHandItem();
        if (hand_item.getItem() instanceof AbstractModularToolItem) {
            var head = Part.HEAD.getMaterial(hand_item);
            var rod = Part.ROD.getMaterial(hand_item);
            var trim = Part.TRIM.getMaterial(hand_item);
            if (head.isEmpty() || rod.isEmpty() || trim.isEmpty()) return;
            if (Tests.in(head.get(), rod.get(), trim.get())
                     .test(MaterialBehaviors.REDSTONE_MATERIAL_BEHAVIOR))
                RedstoneMaterialBehavior.apply((ServerPlayer) player, itemStack);
        }
    }
}
