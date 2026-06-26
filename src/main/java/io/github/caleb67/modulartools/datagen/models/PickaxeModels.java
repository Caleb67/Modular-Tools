package io.github.caleb67.modulartools.datagen.models;

import io.github.caleb67.modulartools.register.Items;
import io.github.caleb67.modulartools.register.MTDataComponents;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.select.ComponentContents;

public class PickaxeModels {
    public static void generateItemModels(ItemModelGenerators itemModelGenerators) {
        ItemModel.Unbaked woodPickaxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_PICKAXE_TOOL, "_wood_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked stonePickaxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_PICKAXE_TOOL, "_stone_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked goldPickaxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_PICKAXE_TOOL, "_gold_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked blazePickaxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_PICKAXE_TOOL, "_blaze_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked emeraldPickaxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_PICKAXE_TOOL, "_emerald_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked ironPickaxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_PICKAXE_TOOL, "_iron_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked copperPickaxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_PICKAXE_TOOL, "_copper_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked prismarinePickaxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_PICKAXE_TOOL, "_prismarine_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked lapisPickaxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_PICKAXE_TOOL, "_lapis_head", ModelTemplates.FLAT_HANDHELD_ITEM));

        ItemModel.Unbaked woodPickaxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_pickaxe_wood", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked stonePickaxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_pickaxe_stone", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked goldPickaxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_pickaxe_gold", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked blazePickaxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_pickaxe_blaze", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked emeraldPickaxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_pickaxe_emerald", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked ironPickaxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_pickaxe_iron", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked copperPickaxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_pickaxe_copper", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked prismarinePickaxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_pickaxe_prismarine", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked lapisPickaxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_pickaxe_lapis", ModelTemplates.FLAT_HANDHELD_ITEM));

        itemModelGenerators.itemModelOutput.accept(
                Items.BASE_PICKAXE_TOOL,
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
                                ItemModelUtils.when(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, woodPickaxeHead),
                                ItemModelUtils.when(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, stonePickaxeHead),
                                ItemModelUtils.when(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, goldPickaxeHead),
                                ItemModelUtils.when(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, blazePickaxeHead),
                                ItemModelUtils.when(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, emeraldPickaxeHead),
                                ItemModelUtils.when(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, ironPickaxeHead),
                                ItemModelUtils.when(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, copperPickaxeHead),
                                ItemModelUtils.when(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, prismarinePickaxeHead),
                                ItemModelUtils.when(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, lapisPickaxeHead)
                        ),
                        ItemModelUtils.select(new ComponentContents<>(MTDataComponents.MODULAR_TOOL_TRIM),
                                ItemModelUtils.when(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, woodPickaxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, stonePickaxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, goldPickaxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, blazePickaxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, emeraldPickaxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, ironPickaxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, copperPickaxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, prismarinePickaxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, lapisPickaxeToolTrim)
                        )
                )
        );
    }
}
