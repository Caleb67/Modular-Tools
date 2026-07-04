package io.github.caleb67.modulartools.mixin;

import io.github.caleb67.modulartools.tool.AbstractModularToolItem;
import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ModularToolHurtAndBreakMixin implements DataComponentHolder, ItemInstance, FabricItemStack {
    @Shadow public abstract Item getItem();
    
    @Inject(method = "hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V",
        at = @At(value = "HEAD"), cancellable = true)
    public void ensureDamageAffects(int amount, LivingEntity owner, EquipmentSlot slot, CallbackInfo ci) {
        if (this.getItem() instanceof AbstractModularToolItem) {
            AbstractModularToolItem.hurtAndBreakTool((ItemStack) (Object) this, amount, owner, slot);
            ci.cancel();
        }
    }
    
}
