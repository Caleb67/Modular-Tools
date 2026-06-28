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
    public final ItemModel.Unbaked quartzToolRod;
    public final ItemModel.Unbaked diamondToolRod;

    public final ItemModel.Unbaked woodSwordToolRod;
    public final ItemModel.Unbaked stoneSwordToolRod;
    public final ItemModel.Unbaked goldSwordToolRod;
    public final ItemModel.Unbaked blazeSwordToolRod;
    public final ItemModel.Unbaked emeraldSwordToolRod;
    public final ItemModel.Unbaked ironSwordToolRod;
    public final ItemModel.Unbaked copperSwordToolRod;
    public final ItemModel.Unbaked prismarineSwordToolRod;
    public final ItemModel.Unbaked lapisSwordToolRod;
    public final ItemModel.Unbaked quartzSwordToolRod;
    public final ItemModel.Unbaked diamondSwordToolRod;

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
        quartzToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_quartz", ModelTemplates.FLAT_HANDHELD_ITEM));
        diamondToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_diamond", ModelTemplates.FLAT_HANDHELD_ITEM));

        woodSwordToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_sword_wood", ModelTemplates.FLAT_HANDHELD_ITEM));
        stoneSwordToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_sword_stone", ModelTemplates.FLAT_HANDHELD_ITEM));
        goldSwordToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_sword_gold", ModelTemplates.FLAT_HANDHELD_ITEM));
        blazeSwordToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_sword_blaze", ModelTemplates.FLAT_HANDHELD_ITEM));
        emeraldSwordToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_sword_emerald", ModelTemplates.FLAT_HANDHELD_ITEM));
        ironSwordToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_sword_iron", ModelTemplates.FLAT_HANDHELD_ITEM));
        copperSwordToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_sword_copper", ModelTemplates.FLAT_HANDHELD_ITEM));
        prismarineSwordToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_sword_prismarine", ModelTemplates.FLAT_HANDHELD_ITEM));
        lapisSwordToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_sword_lapis", ModelTemplates.FLAT_HANDHELD_ITEM));
        quartzSwordToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_sword_quartz", ModelTemplates.FLAT_HANDHELD_ITEM));
        diamondSwordToolRod = ItemModelUtils.plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_sword_diamond", ModelTemplates.FLAT_HANDHELD_ITEM));
    }

    public static void load(ItemModelGenerators itemModelGenerators) {
        commonModels = new CommonModels(itemModelGenerators);
    }
}
