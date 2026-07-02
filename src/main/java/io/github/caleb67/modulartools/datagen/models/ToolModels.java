package io.github.caleb67.modulartools.datagen.models;

import io.github.caleb67.modulartools.register.Items;
import io.github.caleb67.modulartools.register.MTDataComponents;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import io.github.caleb67.modulartools.tool.AbstractModularToolItem;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.select.ComponentContents;
import net.minecraft.resources.ResourceKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToolModels {
    private static final List<Map.Entry<MaterialBehavior, String>> materialBehaviors = List.of(
        Map.entry(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR, "blaze"),
        Map.entry(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR, "iron"),
        Map.entry(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR, "prismarine"),
        Map.entry(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR, "gold"),
        Map.entry(MaterialBehaviors.DIAMOND_MATERIAL_BEHAVIOR, "diamond"),
        Map.entry(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR, "lapis"),
        Map.entry(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR, "emerald"),
        Map.entry(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR, "copper"),
        Map.entry(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR, "stone"),
        Map.entry(MaterialBehaviors.QUARTZ_MATERIAL_BEHAVIOR, "quartz"),
        Map.entry(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR, "wood"),
        Map.entry(MaterialBehaviors.ECHO_MATERIAL_BEHAVIOR, "echo"),
        Map.entry(MaterialBehaviors.REDSTONE_MATERIAL_BEHAVIOR, "redstone"),
        Map.entry(MaterialBehaviors.NETHERITE_MATERIAL_BEHAVIOR, "netherite")
    );
    
    public static void generateItemModels(ItemModelGenerators itemModelGenerators) {
        var pickaxe_heads = makePickaxeToolHeadModels(itemModelGenerators);
        var pickaxe_trims = makePickaxeToolTrimModels(itemModelGenerators);
        var shovel_heads = makeShovelToolHeadModels(itemModelGenerators);
        var shovel_trims = makeShovelToolTrimModels(itemModelGenerators);
        var axe_heads = makeAxeToolHeadModels(itemModelGenerators);
        var axe_trims = makeAxeToolTrimModels(itemModelGenerators);
        var sword_heads = makeSwordToolHeadModels(itemModelGenerators);
        var sword_trims = makeSwordToolTrimModels(itemModelGenerators);
        
        var rods = makeRodModels(itemModelGenerators);
        var sword_rods = makeSwordRodModels(itemModelGenerators);
        
        generateToolModel(itemModelGenerators, Items.BASE_PICKAXE_TOOL, pickaxe_heads, rods, pickaxe_trims);
        generateToolModel(itemModelGenerators, Items.BASE_SHOVEL_TOOL, shovel_heads, rods, shovel_trims);
        generateToolModel(itemModelGenerators, Items.BASE_AXE_TOOL, axe_heads, rods, axe_trims);
        generateToolModel(itemModelGenerators, Items.BASE_SWORD_TOOL, sword_heads, sword_rods, sword_trims);
    }
    
    private static void generateToolModel(ItemModelGenerators itemModelGenerators, AbstractModularToolItem tool,
                                          Map<ResourceKey<MaterialBehavior>, ItemModel.Unbaked> heads,
                                          Map<ResourceKey<MaterialBehavior>, ItemModel.Unbaked> rods,
                                          Map<ResourceKey<MaterialBehavior>, ItemModel.Unbaked> trims) {
        itemModelGenerators.itemModelOutput.accept(tool,
            ItemModelUtils.composite(
                ItemModelUtils.select(new ComponentContents<>(MTDataComponents.MODULAR_TOOL_ROD),
                    makeMaterialSwitch(rods)),
                ItemModelUtils.select(new ComponentContents<>(MTDataComponents.MODULAR_TOOL_HEAD),
                    makeMaterialSwitch(heads)),
                ItemModelUtils.select(new ComponentContents<>(MTDataComponents.MODULAR_TOOL_TRIM),
                    makeMaterialSwitch(trims))
            )
        );
    }
    
    private static List<SelectItemModel.SwitchCase<ResourceKey<MaterialBehavior>>> makeMaterialSwitch(
        Map<ResourceKey<MaterialBehavior>, ItemModel.Unbaked> options) {
        var list = new ArrayList<SelectItemModel.SwitchCase<ResourceKey<MaterialBehavior>>>();
        for (var entry : materialBehaviors)
            list.add(
                ItemModelUtils.when(entry.getKey().key, options.get(entry.getKey().key))
            );
        return list;
    }
    
    private static HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked> makePickaxeToolHeadModels(ItemModelGenerators itemModelGenerators) {
        var out = new HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked>();
        for (var entry : materialBehaviors)
            out.put(entry.getKey().key,
                makeToolHeadModel(itemModelGenerators, Items.BASE_PICKAXE_TOOL, entry.getValue()));
        return out;
    }
    
    private static HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked> makePickaxeToolTrimModels(ItemModelGenerators itemModelGenerators) {
        var out = new HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked>();
        for (var entry : materialBehaviors)
            out.put(entry.getKey().key,
                makeToolTrimModel(itemModelGenerators, Items.BASE_PICKAXE_TOOL, entry.getValue()));
        return out;
    }
    
    private static HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked> makeShovelToolHeadModels(ItemModelGenerators itemModelGenerators) {
        var out = new HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked>();
        for (var entry : materialBehaviors)
            out.put(entry.getKey().key,
                makeToolHeadModel(itemModelGenerators, Items.BASE_SHOVEL_TOOL, entry.getValue()));
        return out;
    }
    
    private static HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked> makeShovelToolTrimModels(ItemModelGenerators itemModelGenerators) {
        var out = new HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked>();
        for (var entry : materialBehaviors)
            out.put(entry.getKey().key,
                makeToolTrimModel(itemModelGenerators, Items.BASE_SHOVEL_TOOL, entry.getValue()));
        return out;
    }
    
    private static HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked> makeAxeToolHeadModels(ItemModelGenerators itemModelGenerators) {
        var out = new HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked>();
        for (var entry : materialBehaviors)
            out.put(entry.getKey().key, makeToolHeadModel(itemModelGenerators, Items.BASE_AXE_TOOL, entry.getValue()));
        return out;
    }
    
    private static HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked> makeAxeToolTrimModels(ItemModelGenerators itemModelGenerators) {
        var out = new HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked>();
        for (var entry : materialBehaviors)
            out.put(entry.getKey().key, makeToolTrimModel(itemModelGenerators, Items.BASE_AXE_TOOL, entry.getValue()));
        return out;
    }
    
    private static HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked> makeSwordToolHeadModels(ItemModelGenerators itemModelGenerators) {
        var out = new HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked>();
        for (var entry : materialBehaviors)
            out.put(entry.getKey().key,
                makeToolHeadModel(itemModelGenerators, Items.BASE_SWORD_TOOL, entry.getValue()));
        return out;
    }
    
    private static HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked> makeSwordToolTrimModels(ItemModelGenerators itemModelGenerators) {
        var out = new HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked>();
        for (var entry : materialBehaviors)
            out.put(entry.getKey().key,
                makeToolTrimModel(itemModelGenerators, Items.BASE_SWORD_TOOL, entry.getValue()));
        return out;
    }
    
    private static HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked> makeRodModels(ItemModelGenerators itemModelGenerators) {
        var out = new HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked>();
        for (var entry : materialBehaviors)
            out.put(entry.getKey().key, makeRodModel(itemModelGenerators, entry.getValue()));
        return out;
    }
    
    private static HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked> makeSwordRodModels(ItemModelGenerators itemModelGenerators) {
        var out = new HashMap<ResourceKey<MaterialBehavior>, ItemModel.Unbaked>();
        for (var entry : materialBehaviors)
            out.put(entry.getKey().key, makeSwordRodModel(itemModelGenerators, entry.getValue()));
        return out;
    }
    
    private static ItemModel.Unbaked makeRodModel(ItemModelGenerators itemModelGenerators, String material) {
        return ItemModelUtils
            .plainModel(
                itemModelGenerators.createFlatItemModel(Items.ROD, "_" + material,
                    ModelTemplates.FLAT_HANDHELD_ITEM));
    }
    
    private static ItemModel.Unbaked makeSwordRodModel(ItemModelGenerators itemModelGenerators, String material) {
        return ItemModelUtils
            .plainModel(itemModelGenerators.createFlatItemModel(Items.ROD, "_sword_" + material,
                ModelTemplates.FLAT_HANDHELD_ITEM));
    }
    
    private static ItemModel.Unbaked makeToolHeadModel(ItemModelGenerators itemModelGenerators, AbstractModularToolItem tool, String material) {
        return ItemModelUtils
            .plainModel(itemModelGenerators.createFlatItemModel(tool, "_" + material + "_head",
                ModelTemplates.FLAT_HANDHELD_ITEM));
    }
    
    private static ItemModel.Unbaked makeToolTrimModel(ItemModelGenerators itemModelGenerators, AbstractModularToolItem tool, String material) {
        return ItemModelUtils
            .plainModel(itemModelGenerators.createFlatItemModel(tool, "_" + material + "_trim",
                ModelTemplates.FLAT_HANDHELD_ITEM));
    }
}
