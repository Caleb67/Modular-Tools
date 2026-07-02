package io.github.caleb67.modulartools.register;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.content.blocks.ForgeBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class Blocks {
    public static final ForgeBlock FORGE = register(
        "forge",
        ForgeBlock::new,
        BlockBehaviour.Properties.of()
    );
    
    private static <T extends Block> T register(String name,
                                                Function<BlockBehaviour.Properties, T> factory,
                                                BlockBehaviour.Properties properties) {
        var key = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(ModularTools.MODID, name));
        var block = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }
    
    public static void load() {}
}
