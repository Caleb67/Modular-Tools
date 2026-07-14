package io.github.caleb67.modulartools.register;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.ModularToolsRegistries;
import io.github.caleb67.modulartools.content.materials.*;
import io.github.caleb67.modulartools.tool.BaseMaterialBehavior;
import io.github.caleb67.modulartools.tool.HeadType;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Items;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MaterialBehaviors {
    public static final MaterialBehavior WOOD_MATERIAL_BEHAVIOR = register(
        "wood_material_behavior",
        BaseMaterialBehavior::new,
        new MaterialBehavior.Properties()
            .toolMaterial(ToolMaterial.WOOD)
            .setRepairItems(() -> setFromHolderSet(BuiltInRegistries.ITEM.getOrThrow(ItemTags.PLANKS)))
            .setFormatting(ChatFormatting.WHITE)
    
    );
    public static final MaterialBehavior STONE_MATERIAL_BEHAVIOR = register(
        "stone_material_behavior",
        BaseMaterialBehavior::new,
        new MaterialBehavior.Properties()
            .toolMaterial(ToolMaterial.STONE)
            .setRepairItems(() -> setFromHolderSet(BuiltInRegistries.ITEM.getOrThrow(ItemTags.STONE_TOOL_MATERIALS)))
            .setFormatting(ChatFormatting.WHITE)
    );
    
    public static final GoldMaterialBehavior GOLD_MATERIAL_BEHAVIOR = register(
        "gold_material_behavior",
        GoldMaterialBehavior::new,
        new MaterialBehavior.Properties()
            .toolMaterial(ToolMaterial.GOLD)
            .setRepairItems(() -> Set.of(Items.GOLD_INGOT))
            .setFormatting(ChatFormatting.YELLOW)
            .setEffectFormatting(ChatFormatting.GOLD, ChatFormatting.ITALIC)
    );
    public static final BlazeMaterialBehavior BLAZE_MATERIAL_BEHAVIOR = register(
        "blaze_material_behavior",
        BlazeMaterialBehavior::new,
        new MaterialBehavior.Properties()
            .toolMaterial(ToolMaterial.DIAMOND)
            .setRepairItems(() -> Set.of(Items.BLAZE_ROD))
            .setFormatting(ChatFormatting.AQUA)
            .setEffectFormatting(ChatFormatting.DARK_RED, ChatFormatting.BOLD)
    );
    public static final EmeraldMaterialBehavior EMERALD_MATERIAL_BEHAVIOR = register(
        "emerald_material_behavior",
        EmeraldMaterialBehavior::new,
        new MaterialBehavior.Properties()
            .toolMaterial(ToolMaterial.IRON)
            .setRepairItems(() -> Set.of(Items.EMERALD))
            .setFormatting(ChatFormatting.YELLOW)
            .setEffectFormatting(ChatFormatting.GREEN)
    );
    public static final MaterialBehavior IRON_MATERIAL_BEHAVIOR = register(
        "iron_material_behavior",
        BaseMaterialBehavior::new,
        new MaterialBehavior.Properties()
            .toolMaterial(ToolMaterial.IRON)
            .setRepairItems(() -> Set.of(Items.IRON_INGOT))
            .setFormatting(ChatFormatting.YELLOW)
    );
    public static final CopperMaterialBehavior COPPER_MATERIAL_BEHAVIOR = register(
        "copper_material_behavior",
        CopperMaterialBehavior::new,
        new MaterialBehavior.Properties()
            .toolMaterial(ToolMaterial.COPPER)
            .setRepairItems(() -> Set.of(Items.COPPER_INGOT))
            .setFormatting(ChatFormatting.WHITE)
            .setEffectFormatting(ChatFormatting.RED)
    );
    
    public static final PrismarineMaterialBehavior PRISMARINE_MATERIAL_BEHAVIOR = register(
        "prismarine_material_behavior",
        PrismarineMaterialBehavior::new,
        new MaterialBehavior.Properties()
            .toolMaterial(ToolMaterial.DIAMOND)
            .setRepairItems(() -> Set.of(Items.PRISMARINE_SHARD))
            .setFormatting(ChatFormatting.WHITE)
            .setEffectFormatting(ChatFormatting.DARK_AQUA)
    );
    
    public static final LapisMaterialBehavior LAPIS_MATERIAL_BEHAVIOR = register(
        "lapis_material_behavior",
        LapisMaterialBehavior::new,
        new MaterialBehavior.Properties()
            .toolMaterial(ToolMaterial.STONE)
            .setRepairItems(() -> Set.of(Items.LAPIS_LAZULI))
            .setFormatting(ChatFormatting.WHITE)
            .setEffectFormatting(ChatFormatting.BLUE)
    );
    
    public static final QuartzMaterialBehavior QUARTZ_MATERIAL_BEHAVIOR = register(
        "quartz_material_behavior",
        QuartzMaterialBehavior::new,
        new MaterialBehavior.Properties()
            .toolMaterial(ToolMaterial.IRON)
            .setRepairItems(() -> Set.of(Items.QUARTZ))
            .setFormatting(ChatFormatting.YELLOW)
            .setEffectFormatting(ChatFormatting.WHITE, ChatFormatting.ITALIC)
    );
    
    public static final DiamondMaterialBehavior DIAMOND_MATERIAL_BEHAVIOR = register(
        "diamond_material_behavior",
        DiamondMaterialBehavior::new,
        new MaterialBehavior.Properties()
            .toolMaterial(ToolMaterial.DIAMOND)
            .setRepairItems(() -> Set.of(Items.DIAMOND))
            .setFormatting(ChatFormatting.AQUA)
            .setEffectFormatting(ChatFormatting.AQUA, ChatFormatting.BOLD)
    );
    
    public static final EchoMaterialBehavior ECHO_MATERIAL_BEHAVIOR = register(
        "echo_material_behavior",
        EchoMaterialBehavior::new,
        new MaterialBehavior.Properties()
            .toolMaterial(ToolMaterial.NETHERITE)
            .setRepairItems(() -> Set.of(Items.ECHO_SHARD))
            .setFormatting(ChatFormatting.LIGHT_PURPLE)
            .setEffectFormatting(ChatFormatting.DARK_AQUA, ChatFormatting.ITALIC)
    );
    
    public static final RedstoneMaterialBehavior REDSTONE_MATERIAL_BEHAVIOR = register(
        "redstone_material_behavior",
        RedstoneMaterialBehavior::new,
        new MaterialBehavior.Properties()
            .toolMaterial(ToolMaterial.STONE)
            .setRepairItems(() -> Set.of(Items.REDSTONE))
            .setFormatting(ChatFormatting.YELLOW)
            .setEffectFormatting(ChatFormatting.DARK_RED, ChatFormatting.ITALIC)
    );
    
    public static final NetheriteMaterialBehavior NETHERITE_MATERIAL_BEHAVIOR = register(
        "netherite_material_behavior",
        NetheriteMaterialBehavior::new,
        new MaterialBehavior.Properties()
            .toolMaterial(ToolMaterial.NETHERITE)
            .setRepairItems(() -> Set.of(Items.NETHERITE_INGOT))
            .setAttributesForHeadType(new HeadType.Hoe(), -4.0F, 0.0F)
            .setFormatting(ChatFormatting.LIGHT_PURPLE)
            .setEffectFormatting(ChatFormatting.LIGHT_PURPLE, ChatFormatting.BOLD)
    );
    
    
    private static <T extends MaterialBehavior> T register(String name,
                                                           Function<MaterialBehavior.Properties, T> factory,
                                                           MaterialBehavior.Properties properties) {
        var key = ResourceKey.create(ModularToolsRegistries.MATERIAL_BEHAVIOR.key(),
            Identifier.fromNamespaceAndPath(ModularTools.MODID, name));
        var material_behavior = factory.apply(properties.setId(key));
        return Registry.register(ModularToolsRegistries.MATERIAL_BEHAVIOR, key, material_behavior);
    }
    
    public static void load() {}
    
    private static <T> Set<T> setFromHolderSet(HolderSet<T> holderSet) {
        return holderSet.stream()
                        .map(Holder::value)
                        .collect(Collectors.toSet());
    }
}
