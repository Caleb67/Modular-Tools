package io.github.caleb67.modulartools.register;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.ModularToolsRegistries;
import io.github.caleb67.modulartools.content.materials.*;
import io.github.caleb67.modulartools.tool.HeadType;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ToolMaterial;

import java.util.function.Function;

public class MaterialBehaviors {
    public static final MaterialBehavior WOOD_MATERIAL_BEHAVIOR = register(
            "wood_material_behavior",
            MaterialBehavior::new,
            new MaterialBehavior.Properties()
                    .toolMaterial(ToolMaterial.WOOD)
                    .setAttributesForHeadType(new HeadType.Pickaxe(), 1.0F, -2.8F)
                    .setAttributesForHeadType(new HeadType.Shovel(), 1.5F, -3.0F)
                    .setAttributesForHeadType(new HeadType.Axe(), 7.0F, -3.2F)
                    .setAttributesForHeadType(new HeadType.Sword(), 3.0F, -2.4F)
                    .setFormatting(ChatFormatting.WHITE)

    );
    public static final MaterialBehavior STONE_MATERIAL_BEHAVIOR = register(
            "stone_material_behavior",
            MaterialBehavior::new,
            new MaterialBehavior.Properties()
                    .toolMaterial(ToolMaterial.STONE)
                    .setAttributesForHeadType(new HeadType.Pickaxe(), 1.0F, -2.8F)
                    .setAttributesForHeadType(new HeadType.Shovel(), 1.5F, -3.0F)
                    .setAttributesForHeadType(new HeadType.Axe(), 7.0F, -3.2F)
                    .setAttributesForHeadType(new HeadType.Sword(), 3.0F, -2.4F)
                    .setFormatting(ChatFormatting.WHITE)
    );

    public static final MaterialBehavior GOLD_MATERIAL_BEHAVIOR = register(
            "gold_material_behavior",
            GoldMaterialBehavior::new,
            new MaterialBehavior.Properties()
                    .toolMaterial(ToolMaterial.GOLD)
                    .setAttributesForHeadType(new HeadType.Pickaxe(), 1.0F, -2.8F)
                    .setAttributesForHeadType(new HeadType.Shovel(), 1.5F, -3.0F)
                    .setAttributesForHeadType(new HeadType.Axe(), 7.0F, -3.2F)
                    .setAttributesForHeadType(new HeadType.Sword(), 3.0F, -2.4F)
                    .setFormatting(ChatFormatting.YELLOW)
                    .setEffectFormatting(ChatFormatting.GOLD, ChatFormatting.ITALIC)
    );
    public static final MaterialBehavior BLAZE_MATERIAL_BEHAVIOR = register(
            "blaze_material_behavior",
            BlazeMaterialBehavior::new,
            new MaterialBehavior.Properties()
                    .toolMaterial(ToolMaterial.DIAMOND)
                    .setAttributesForHeadType(new HeadType.Pickaxe(), 1.0F, -2.8F)
                    .setAttributesForHeadType(new HeadType.Shovel(), 1.5F, -3.0F)
                    .setAttributesForHeadType(new HeadType.Axe(), 7.0F, -3.2F)
                    .setAttributesForHeadType(new HeadType.Sword(), 3.0F, -2.4F)
                    .setFormatting(ChatFormatting.AQUA)
                    .setEffectFormatting(ChatFormatting.DARK_RED, ChatFormatting.BOLD)
    );
    public static final MaterialBehavior EMERALD_MATERIAL_BEHAVIOR = register(
            "emerald_material_behavior",
            EmeraldMaterialBehavior::new,
            new MaterialBehavior.Properties()
                    .toolMaterial(ToolMaterial.IRON)
                    .setAttributesForHeadType(new HeadType.Pickaxe(), 1.0F, -2.8F)
                    .setAttributesForHeadType(new HeadType.Shovel(), 1.5F, -3.0F)
                    .setAttributesForHeadType(new HeadType.Axe(), 7.0F, -3.2F)
                    .setAttributesForHeadType(new HeadType.Sword(), 3.0F, -2.4F)
                    .setFormatting(ChatFormatting.YELLOW)
                    .setEffectFormatting(ChatFormatting.GREEN)
    );
    public static final MaterialBehavior IRON_MATERIAL_BEHAVIOR = register(
            "iron_material_behavior",
            MaterialBehavior::new,
            new MaterialBehavior.Properties()
                    .toolMaterial(ToolMaterial.IRON)
                    .setAttributesForHeadType(new HeadType.Pickaxe(), 1.0F, -2.8F)
                    .setAttributesForHeadType(new HeadType.Shovel(), 1.5F, -3.0F)
                    .setAttributesForHeadType(new HeadType.Axe(), 7.0F, -3.2F)
                    .setAttributesForHeadType(new HeadType.Sword(), 3.0F, -2.4F)
                    .setFormatting(ChatFormatting.YELLOW)
    );
    public static final MaterialBehavior COPPER_MATERIAL_BEHAVIOR = register(
            "copper_material_behavior",
            CopperMaterialBehavior::new,
            new MaterialBehavior.Properties()
                    .toolMaterial(ToolMaterial.COPPER)
                    .setAttributesForHeadType(new HeadType.Pickaxe(), 1.0F, -2.8F)
                    .setAttributesForHeadType(new HeadType.Shovel(), 1.5F, -3.0F)
                    .setAttributesForHeadType(new HeadType.Axe(), 7.0F, -3.2F)
                    .setAttributesForHeadType(new HeadType.Sword(), 3.0F, -2.4F)
                    .setFormatting(ChatFormatting.WHITE)
                    .setEffectFormatting(ChatFormatting.RED)
    );

    public static final MaterialBehavior PRISMARINE_MATERIAL_BEHAVIOR = register(
            "prismarine_material_behavior",
            PrismarineMaterialBehavior::new,
            new MaterialBehavior.Properties()
                    .toolMaterial(ToolMaterial.DIAMOND)
                    .setAttributesForHeadType(new HeadType.Pickaxe(), 1.0F, -2.8F)
                    .setAttributesForHeadType(new HeadType.Shovel(), 1.5F, -3.0F)
                    .setAttributesForHeadType(new HeadType.Axe(), 7.0F, -3.2F)
                    .setAttributesForHeadType(new HeadType.Sword(), 3.0F, -2.4F)
                    .setFormatting(ChatFormatting.WHITE)
                    .setEffectFormatting(ChatFormatting.DARK_AQUA)
    );

    public static final MaterialBehavior LAPIS_MATERIAL_BEHAVIOR = register(
            "lapis_material_behavior",
            LapisMaterialBehavior::new,
            new MaterialBehavior.Properties()
                    .toolMaterial(ToolMaterial.STONE)
                    .setAttributesForHeadType(new HeadType.Pickaxe(), 1.0F, -2.8F)
                    .setAttributesForHeadType(new HeadType.Shovel(), 1.5F, -3.0F)
                    .setAttributesForHeadType(new HeadType.Axe(), 7.0F, -3.2F)
                    .setAttributesForHeadType(new HeadType.Sword(), 3.0F, -2.4F)
                    .setFormatting(ChatFormatting.WHITE)
                    .setEffectFormatting(ChatFormatting.BLUE)
    );


    private static MaterialBehavior register(String name, Function<MaterialBehavior.Properties, MaterialBehavior> factory, MaterialBehavior.Properties properties) {
        var key = ResourceKey.create(ModularToolsRegistries.MATERIAL_BEHAVIOR.key(), Identifier.fromNamespaceAndPath(ModularTools.MODID, name));
        var material_behavior = factory.apply(properties.setId(key));
        return Registry.register(ModularToolsRegistries.MATERIAL_BEHAVIOR, key, material_behavior);
    }

    public static void load() {}
}
