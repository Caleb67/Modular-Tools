package io.github.caleb67.modulartools.datagen.models;

import io.github.caleb67.modulartools.register.Items;
import io.github.caleb67.modulartools.register.MTDataComponents;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.select.ComponentContents;

public class AxeModels {

    public static void generateItemModels(ItemModelGenerators itemModelGenerators) {
        ItemModel.Unbaked woodAxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_AXE_TOOL, "_wood_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked stoneAxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_AXE_TOOL, "_stone_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked goldAxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_AXE_TOOL, "_gold_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked blazeAxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_AXE_TOOL, "_blaze_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked emeraldAxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_AXE_TOOL, "_emerald_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked ironAxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_AXE_TOOL, "_iron_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked copperAxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_AXE_TOOL, "_copper_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked prismarineAxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_AXE_TOOL, "_prismarine_head", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked lapisAxeHead = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.BASE_AXE_TOOL, "_lapis_head", ModelTemplates.FLAT_HANDHELD_ITEM));


        ItemModel.Unbaked woodAxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_axe_wood", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked stoneAxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_axe_stone", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked goldAxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_axe_gold", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked blazeAxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_axe_blaze", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked emeraldAxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_axe_emerald", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked ironAxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_axe_iron", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked copperAxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_axe_copper", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked prismarineAxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_axe_prismarine", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked lapisAxeToolTrim = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.TRIM, "_axe_lapis", ModelTemplates.FLAT_HANDHELD_ITEM));

        itemModelGenerators.itemModelOutput.accept(
                Items.BASE_AXE_TOOL,
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
                                ItemModelUtils.when(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, woodAxeHead),
                                ItemModelUtils.when(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, stoneAxeHead),
                                ItemModelUtils.when(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, goldAxeHead),
                                ItemModelUtils.when(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, blazeAxeHead),
                                ItemModelUtils.when(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, emeraldAxeHead),
                                ItemModelUtils.when(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, ironAxeHead),
                                ItemModelUtils.when(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, copperAxeHead),
                                ItemModelUtils.when(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, prismarineAxeHead),
                                ItemModelUtils.when(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, lapisAxeHead)
                        ),
                        ItemModelUtils.select(new ComponentContents<>(MTDataComponents.MODULAR_TOOL_TRIM),
                                ItemModelUtils.when(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, woodAxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, stoneAxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, goldAxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, blazeAxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, emeraldAxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, ironAxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, copperAxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, prismarineAxeToolTrim),
                                ItemModelUtils.when(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, lapisAxeToolTrim)
                        )
                )
        );
    }
}
