package io.github.caleb67.modulartools.datagen;

import io.github.caleb67.modulartools.datagen.models.*;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;

public class ModularToolsModelProvider extends FabricModelProvider {
    public ModularToolsModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        CommonModels.load(itemModelGenerators);
        PickaxeModels.generateItemModels(itemModelGenerators);
        ShovelModels.generateItemModels(itemModelGenerators);
        AxeModels.generateItemModels(itemModelGenerators);
        SwordModels.generateItemModels(itemModelGenerators);
    }

    @Override
    public String getName() {
        return "ModularToolsModelProvider";
    }
}
