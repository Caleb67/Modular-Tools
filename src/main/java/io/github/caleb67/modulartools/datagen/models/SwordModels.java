package io.github.caleb67.modulartools.datagen.models;

import io.github.caleb67.modulartools.register.Items;
import io.github.caleb67.modulartools.register.MTDataComponents;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.select.ComponentContents;

public class SwordModels {
    public static void generateItemModels(ItemModelGenerators itemModelGenerators) {
        ItemModel.Unbaked woodSwordHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SWORD_TOOL, "_wood_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked stoneSwordHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SWORD_TOOL, "_stone_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked goldSwordHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SWORD_TOOL, "_gold_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked blazeSwordHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SWORD_TOOL, "_blaze_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked emeraldSwordHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SWORD_TOOL, "_emerald_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked ironSwordHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SWORD_TOOL, "_iron_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked copperSwordHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SWORD_TOOL, "_copper_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked prismarineSwordHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SWORD_TOOL, "_prismarine_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked lapisSwordHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SWORD_TOOL, "_lapis_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked quartzSwordHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_SWORD_TOOL, "_quartz_head", ModelTemplates.FLAT_HANDHELD_ITEM));

        ItemModel.Unbaked woodSwordToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_sword_wood", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked stoneSwordToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_sword_stone", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked goldSwordToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_sword_gold", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked blazeSwordToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_sword_blaze", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked emeraldSwordToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_sword_emerald", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked ironSwordToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_sword_iron", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked copperSwordToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_sword_copper", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked prismarineSwordToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_sword_prismarine", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked lapisSwordToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_sword_lapis", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked quartzSwordToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_sword_quartz", ModelTemplates.FLAT_HANDHELD_ITEM));

        itemModelGenerators.itemModelOutput.accept(
                Items.BASE_SWORD_TOOL,
                ItemModelUtils.composite(
                        ItemModelUtils.select(new ComponentContents<>(MTDataComponents.MODULAR_TOOL_ROD),
                                ItemModelUtils.when(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.woodSwordToolRod),
                                ItemModelUtils.when(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.stoneSwordToolRod),
                                ItemModelUtils.when(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.goldSwordToolRod),
                                ItemModelUtils.when(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.blazeSwordToolRod),
                                ItemModelUtils.when(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.emeraldSwordToolRod),
                                ItemModelUtils.when(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.ironSwordToolRod),
                                ItemModelUtils.when(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.copperSwordToolRod),
                                ItemModelUtils.when(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.prismarineSwordToolRod),
                                ItemModelUtils.when(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.lapisSwordToolRod),
                                ItemModelUtils.when(MaterialBehaviors.QUARTZ_MATERIAL_BEHAVIOR.key, CommonModels.commonModels.quartzSwordToolRod)
                        ),
                        ItemModelUtils.select(new ComponentContents<>(MTDataComponents.MODULAR_TOOL_HEAD),
                                ItemModelUtils.when(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, woodSwordHead),
                                ItemModelUtils.when(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, stoneSwordHead),
                                ItemModelUtils.when(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, goldSwordHead),
                                ItemModelUtils.when(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, blazeSwordHead),
                                ItemModelUtils.when(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, emeraldSwordHead),
                                ItemModelUtils.when(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, ironSwordHead),
                                ItemModelUtils.when(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, copperSwordHead),
                                ItemModelUtils.when(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, prismarineSwordHead),
                                ItemModelUtils.when(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, lapisSwordHead),
                                ItemModelUtils.when(MaterialBehaviors.QUARTZ_MATERIAL_BEHAVIOR.key, quartzSwordHead)
                        ),
                        ItemModelUtils.select(new ComponentContents<>(MTDataComponents.MODULAR_TOOL_TRIM),
                                ItemModelUtils.when(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, woodSwordToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, stoneSwordToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, goldSwordToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, blazeSwordToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, emeraldSwordToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, ironSwordToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, copperSwordToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, prismarineSwordToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, lapisSwordToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.QUARTZ_MATERIAL_BEHAVIOR.key, quartzSwordToolTrim)
                        )
                )
        );
    }
}

