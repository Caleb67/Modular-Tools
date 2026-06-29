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

    public static final GoldMaterialBehavior GOLD_MATERIAL_BEHAVIOR = register(
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
    public static final BlazeMaterialBehavior BLAZE_MATERIAL_BEHAVIOR = register(
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
    public static final EmeraldMaterialBehavior EMERALD_MATERIAL_BEHAVIOR = register(
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
    public static final CopperMaterialBehavior COPPER_MATERIAL_BEHAVIOR = register(
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

    public static final PrismarineMaterialBehavior PRISMARINE_MATERIAL_BEHAVIOR = register(
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

    public static final LapisMaterialBehavior LAPIS_MATERIAL_BEHAVIOR = register(
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

    public static final QuartzMaterialBehavior QUARTZ_MATERIAL_BEHAVIOR = register(
            "quartz_material_behavior",
            QuartzMaterialBehavior::new,
            new MaterialBehavior.Properties()
                    .toolMaterial(ToolMaterial.IRON)
                    .setAttributesForHeadType(new HeadType.Pickaxe(), 1.0F, -2.8F)
                    .setAttributesForHeadType(new HeadType.Shovel(), 1.5F, -3.0F)
                    .setAttributesForHeadType(new HeadType.Axe(), 7.0F, -3.2F)
                    .setAttributesForHeadType(new HeadType.Sword(), 3.0F, -2.4F)
                    .setFormatting(ChatFormatting.YELLOW)
                    .setEffectFormatting(ChatFormatting.WHITE, ChatFormatting.ITALIC)
    );

    public static final DiamondMaterialBehavior DIAMOND_MATERIAL_BEHAVIOR = register(
            "diamond_material_behavior",
            DiamondMaterialBehavior::new,
            new MaterialBehavior.Properties()
                    .toolMaterial(ToolMaterial.DIAMOND)
                    .setAttributesForHeadType(new HeadType.Pickaxe(), 1.0F, -2.8F)
                    .setAttributesForHeadType(new HeadType.Shovel(), 1.5F, -3.0F)
                    .setAttributesForHeadType(new HeadType.Axe(), 7.0F, -3.2F)
                    .setAttributesForHeadType(new HeadType.Sword(), 3.0F, -2.4F)
                    .setFormatting(ChatFormatting.AQUA)
                    .setEffectFormatting(ChatFormatting.AQUA, ChatFormatting.BOLD)
    );

    public static final DragonMaterialBehavior DRAGON_MATERIAL_BEHAVIOR = register(
            "dragon_material_behavior",
            DragonMaterialBehavior::new,
            new MaterialBehavior.Properties()
                    .toolMaterial(ToolMaterial.NETHERITE)
                    .setAttributesForHeadType(new HeadType.Pickaxe(), 1.0F, -2.8F)
                    .setAttributesForHeadType(new HeadType.Shovel(), 1.5F, -3.0F)
                    .setAttributesForHeadType(new HeadType.Axe(), 7.0F, -3.2F)
                    .setAttributesForHeadType(new HeadType.Sword(), 3.0F, -2.4F)
                    .setFormatting(ChatFormatting.LIGHT_PURPLE)
                    .setEffectFormatting(ChatFormatting.DARK_PURPLE, ChatFormatting.OBFUSCATED)
    );


    private static <T extends MaterialBehavior> T register(String name,
                                                           Function<MaterialBehavior.Properties, T> factory,
                                                           MaterialBehavior.Properties properties) {
        var key = ResourceKey.create(ModularToolsRegistries.MATERIAL_BEHAVIOR.key(), Identifier.fromNamespaceAndPath(ModularTools.MODID, name));
        var material_behavior = factory.apply(properties.setId(key));
        return Registry.register(ModularToolsRegistries.MATERIAL_BEHAVIOR, key, material_behavior);
    }

    public static void load() {}
}
