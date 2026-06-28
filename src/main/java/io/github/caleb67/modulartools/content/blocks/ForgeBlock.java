package io.github.caleb67.modulartools.content.blocks;


import io.github.caleb67.modulartools.ModularTools;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Util;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class ForgeBlock extends Block implements EntityBlock {
    public static final Component CONTAINER_NAME = Component.translatable(
            Util.makeDescriptionId("menu", Identifier.fromNamespaceAndPath(ModularTools.MODID, "forge"))
    );

    public ForgeBlock(Properties properties) {
        super(properties);
    }

    @Override protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider(
                (containerId, inventory, player) -> new ForgeMenu(
                        containerId, inventory, ContainerLevelAccess.create(level, pos),
                        new SimpleContainerData(1)
                ),
                CONTAINER_NAME
        );
    }

    @Override protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                                         Player player, BlockHitResult hitResult) {
        if (!level.isClientSide())
            player.openMenu(state.getMenuProvider(level, pos));
        return InteractionResult.SUCCESS;
    }

    @Override public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ForgeBlockEntity(pos, state);
    }

    @Nullable
    @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                            BlockEntityType<T> type) {
        return (level1, pos, state1, entity) -> ForgeBlockEntity.tick(level1, pos, state1, (ForgeBlockEntity) entity);
    }

    public Optional<Map.Entry<BlockPos, BlockPos>> getBlastFurnaces(Level level, BlockPos pos,
                                                                        BlockState state) {
        var furnaces = new ArrayList<BlockPos>();
        for (BlockPos pos1 : getSurrounding(pos)) {
            if (level.getBlockState(pos1).is(Blocks.BLAST_FURNACE)) {
                furnaces.add(pos1);
            }
        }
        if (furnaces.size() == 2) return Optional.of(Map.entry(furnaces.get(0), furnaces.get(1)));
        else return Optional.empty();
    };

    private static ArrayList<BlockPos> getSurrounding(BlockPos pos) {
        ArrayList<BlockPos> positions = new ArrayList<>();
        positions.add(pos.north());
        positions.add(pos.east());
        positions.add(pos.south());
        positions.add(pos.west());
        positions.add(pos.north().east());
        positions.add(pos.north().west());
        positions.add(pos.south().east());
        positions.add(pos.south().west());
        return positions;
    }

    public static boolean satisfiesConditions(BlockPos pos, ServerLevel level) {
        var positions = getSurrounding(pos);

        int anvils = 0;
        int stone = 0;
        int blast_furnaces = 0;
        for (BlockPos p : positions) {
            var state = level.getBlockState(p);
            if (state.getBlock() == Blocks.STONE) stone++;
            if (state.getBlock() == Blocks.ANVIL) anvils++;
            if (state.getBlock() == Blocks.BLAST_FURNACE) blast_furnaces++;
        }

        return (blast_furnaces == 2 && stone == 4 && anvils == 2);
    }
}
