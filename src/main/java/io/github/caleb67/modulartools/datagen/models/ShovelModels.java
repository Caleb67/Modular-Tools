package io.github.caleb67.modulartools.datagen.models;

import io.github.caleb67.modulartools.register.Items;
import io.github.caleb67.modulartools.register.MTDataComponents;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.select.ComponentContents;

public class ShovelModels {

    public static void generateItemModels(ItemModelGenerators itemModelGenerators) {
        ItemModel.Unbaked woodShovelHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SHOVEL_TOOL, "_wood_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked stoneShovelHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SHOVEL_TOOL, "_stone_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked goldShovelHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SHOVEL_TOOL, "_gold_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked blazeShovelHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SHOVEL_TOOL, "_blaze_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked emeraldShovelHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SHOVEL_TOOL, "_emerald_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked ironShovelHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SHOVEL_TOOL, "_iron_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked copperShovelHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SHOVEL_TOOL, "_copper_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked prismarineShovelHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SHOVEL_TOOL, "_prismarine_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked lapisShovelHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SHOVEL_TOOL, "_lapis_head", ModelTemplates.FLAT_HANDHELD_ITEM));


        ItemModel.Unbaked woodShovelToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_shovel_wood", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked stoneShovelToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_shovel_stone", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked goldShovelToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_shovel_gold", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked blazeShovelToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_shovel_blaze", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked emeraldShovelToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_shovel_emerald", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked ironShovelToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_shovel_iron", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked copperShovelToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_shovel_copper", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked prismarineShovelToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_shovel_prismarine", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked lapisShovelToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_shovel_lapis", ModelTemplates.FLAT_HANDHELD_ITEM));

        itemModelGenerators.itemModelOutput.accept(
                Items.BASE_SHOVEL_TOOL,
                ItemModelUtils.composite(
                        ItemModelUtils.select(new ComponentContents<>(MTDataComponents.MODULAR_TOOL_ROD),
                                ItemModelUtils.when(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.woodToolRod),
                                ItemModelUtils.when(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.stoneToolRod),
                                ItemModelUtils.when(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.goldToolRod),
                                ItemModelUtils.when(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.blazeToolRod),
                                ItemModelUtils.when(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.emeraldToolRod),
                                ItemModelUtils.when(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.ironToolRod),
                                ItemModelUtils.when(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.copperToolRod),
                                ItemModelUtils.when(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.prismarineToolRod),
                                ItemModelUtils.when(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.lapisToolRod)
                        ),
                        ItemModelUtils.select(new ComponentContents<>(MTDataComponents.MODULAR_TOOL_HEAD),
                                ItemModelUtils.when(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, woodShovelHead),
                                ItemModelUtils.when(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, stoneShovelHead),
                                ItemModelUtils.when(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, goldShovelHead),
                                ItemModelUtils.when(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, blazeShovelHead),
                                ItemModelUtils.when(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, emeraldShovelHead),
                                ItemModelUtils.when(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, ironShovelHead),
                                ItemModelUtils.when(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, copperShovelHead),
                                ItemModelUtils.when(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, prismarineShovelHead),
                                ItemModelUtils.when(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, lapisShovelHead)
                        ),
                        ItemModelUtils.select(new ComponentContents<>(MTDataComponents.MODULAR_TOOL_TRIM),
                                ItemModelUtils.when(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, woodShovelToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, stoneShovelToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, goldShovelToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, blazeShovelToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, emeraldShovelToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, ironShovelToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, copperShovelToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, prismarineShovelToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, lapisShovelToolTrim)
                        )
                )
        );
    }
}
