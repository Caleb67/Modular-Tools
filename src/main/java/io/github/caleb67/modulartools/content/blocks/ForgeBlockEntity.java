package io.github.caleb67.modulartools.content.blocks;

import io.github.caleb67.modulartools.register.BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ForgeBlockEntity extends BlockEntity {
    public ForgeBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntities.FORGE_BLOCK_ENTITY, pos, state);
    }
    
    public static void tick(Level level, BlockPos pos, BlockState state, ForgeBlockEntity entity) {
        if (level.isClientSide()) return;
        if (!ForgeBlock.satisfiesConditions(pos, (ServerLevel) level)) {
            level.setBlockAndUpdate(pos, Blocks.CRAFTING_TABLE.defaultBlockState());
        }
    }
}
