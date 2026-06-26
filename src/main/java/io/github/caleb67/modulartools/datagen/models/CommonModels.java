package io.github.caleb67.modulartools.datagen.models;

import io.github.caleb67.modulartools.register.Items;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;

public class CommonModels {
    public static CommonModels commonModels = null;

    public final ItemModel.Unbaked woodToolRod;
    public final ItemModel.Unbaked stoneToolRod;
    public final ItemModel.Unbaked goldToolRod;
    public final ItemModel.Unbaked blazeToolRod;
    public final ItemModel.Unbaked emeraldToolRod;
    public final ItemModel.Unbaked ironToolRod;
    public final ItemModel.Unbaked copperToolRod;
    public final ItemModel.Unbaked prismarineToolRod;
    public final ItemModel.Unbaked lapisToolRod;
    private CommonModels(ItemModelGenerators itemModelGenerators) {
        woodToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_wood", ModelTemplates.FLAT_HANDHELD_ITEM));
        stoneToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_stone", ModelTemplates.FLAT_HANDHELD_ITEM));
        goldToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_gold", ModelTemplates.FLAT_HANDHELD_ITEM));
        blazeToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_blaze", ModelTemplates.FLAT_HANDHELD_ITEM));
        emeraldToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_emerald", ModelTemplates.FLAT_HANDHELD_ITEM));
        ironToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_iron", ModelTemplates.FLAT_HANDHELD_ITEM));
        copperToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_copper", ModelTemplates.FLAT_HANDHELD_ITEM));
        prismarineToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_prismarine", ModelTemplates.FLAT_HANDHELD_ITEM));
        lapisToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_lapis", ModelTemplates.FLAT_HANDHELD_ITEM));
    }

    public static void load(ItemModelGenerators itemModelGenerators) {
        commonModels = new CommonModels(itemModelGenerators);
    }
}
