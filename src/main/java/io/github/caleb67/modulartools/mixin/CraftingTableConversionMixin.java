package io.github.caleb67.modulartools.mixin;

import io.github.caleb67.modulartools.content.blocks.ForgeBlock;
import io.github.caleb67.modulartools.register.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingTableBlock.class)
public abstract class CraftingTableConversionMixin extends Block {
    protected CraftingTableConversionMixin(Properties properties) {
        super(properties);
    }
    
    @Inject(method = "useWithoutItem", at = @At(value = "HEAD"), cancellable = true)
    public void becomeForge(BlockState state, Level level, BlockPos pos, Player player,
                            BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (level.isClientSide()) return;
        if (ForgeBlock.satisfiesConditions(pos, (ServerLevel) level)) {
            level.setBlockAndUpdate(pos, Blocks.FORGE.defaultBlockState());
            cir.setReturnValue(InteractionResult.SUCCESS);
            cir.cancel();
        }
    }
}
