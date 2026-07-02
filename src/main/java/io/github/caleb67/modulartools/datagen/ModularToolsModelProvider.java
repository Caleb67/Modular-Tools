package io.github.caleb67.modulartools.datagen;

import io.github.caleb67.modulartools.datagen.models.ToolModels;
import io.github.caleb67.modulartools.register.Blocks;
import io.github.caleb67.modulartools.register.Items;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.world.level.block.Block;

public class ModularToolsModelProvider extends FabricModelProvider {
    public ModularToolsModelProvider(FabricPackOutput output) {
        super(output);
    }
    
    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createCraftingTableLike(Blocks.FORGE, Blocks.FORGE, this::forgeBlockMapping);
    }
    
    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        ToolModels.generateItemModels(itemModelGenerators);
        itemModelGenerators.generateFlatItem(Items.PICKAXE_TOOL_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Items.SHOVEL_TOOL_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Items.AXE_TOOL_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Items.SWORD_TOOL_TEMPLATE, ModelTemplates.FLAT_ITEM);
    }
    
    @Override
    public String getName() {
        return "ModularToolsModelProvider";
    }
    
    public TextureMapping forgeBlockMapping(Block block, Block block2) {
        return new TextureMapping()
            .put(TextureSlot.UP, new Material(ModelLocationUtils.getModelLocation(block, "_top")))
            .put(TextureSlot.DOWN, new Material(ModelLocationUtils.getModelLocation(block2, "_bottom")))
            .put(TextureSlot.NORTH, new Material(ModelLocationUtils.getModelLocation(block, "_side")))
            .put(TextureSlot.EAST, new Material(ModelLocationUtils.getModelLocation(block, "_side")))
            .put(TextureSlot.SOUTH, new Material(ModelLocationUtils.getModelLocation(block, "_side")))
            .put(TextureSlot.WEST, new Material(ModelLocationUtils.getModelLocation(block, "_side")))
            .put(TextureSlot.PARTICLE, new Material(ModelLocationUtils.getModelLocation(block, "_side")));
    }
}
