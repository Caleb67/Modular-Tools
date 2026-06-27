package io.github.caleb67.modulartools.datagen;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import io.github.caleb67.modulartools.tool.HeadType;
import io.github.caleb67.modulartools.tool.Part;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import java.util.concurrent.CompletableFuture;

public class ModularToolsEnglishLangProvider extends FabricLanguageProvider {
    protected ModularToolsEnglishLangProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        // Specifying en_us is optional, as it's the default language code
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider holderLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(TranslationUtil.makePartUnknown(), "Unknown");

        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, 1), "Reach I");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, 2), "Reach II");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, 3), "Reach III");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, 4), "Reach IV");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, 1), "Fast I");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, 2), "Fast II");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, 3), "Fast III");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, 4), "Fast IV");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, 1), "Smelting");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, 2), "Smelting");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, 3), "Smelting");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, 4), "Smelting");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, 1), "Fortune I");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, 2), "Fortune II");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, 3), "Fortune III");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, 4), "Fortune IV");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, 1), "Aquatic I");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, 2), "Aquatic II");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, 3), "Aquatic III");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, 4), "Aquatic IV");

        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, 1), "Amplifier I");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, 2), "Amplifier II");
        translationBuilder.add(TranslationUtil.makeEffectDescId(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, 3), "Amplifier III");

        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, new HeadType.Pickaxe()), "Wood Pickaxe");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, new HeadType.Pickaxe()), "Stone Pickaxe");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, new HeadType.Pickaxe()), "Gold Pickaxe");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, new HeadType.Pickaxe()), "Blaze Pickaxe");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, new HeadType.Pickaxe()), "Emerald Pickaxe");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, new HeadType.Pickaxe()), "Iron Pickaxe");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, new HeadType.Pickaxe()), "Copper Pickaxe");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, new HeadType.Pickaxe()), "Lapis Pickaxe");

        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, new HeadType.Shovel()), "Wood Shovel");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, new HeadType.Shovel()), "Stone Shovel");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, new HeadType.Shovel()), "Gold Shovel");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, new HeadType.Shovel()), "Blaze Shovel");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, new HeadType.Shovel()), "Emerald Shovel");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, new HeadType.Shovel()), "Iron Shovel");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, new HeadType.Shovel()), "Copper Shovel");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, new HeadType.Shovel()), "Prismarine Shovel");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, new HeadType.Shovel()), "Lapis Shovel");

        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, new HeadType.Axe()), "Wood Axe");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, new HeadType.Axe()), "Stone Axe");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, new HeadType.Axe()), "Gold Axe");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, new HeadType.Axe()), "Blaze Axe");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, new HeadType.Axe()), "Emerald Axe");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, new HeadType.Axe()), "Iron Axe");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, new HeadType.Axe()), "Copper Axe");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, new HeadType.Axe()), "Prismarine Axe");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, new HeadType.Axe()), "Lapis Axe");

        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, new HeadType.Sword()), "Wood Sword");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, new HeadType.Sword()), "Stone Sword");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, new HeadType.Sword()), "Gold Sword");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, new HeadType.Sword()), "Blaze Sword");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, new HeadType.Sword()), "Emerald Sword");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, new HeadType.Sword()), "Iron Sword");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, new HeadType.Sword()), "Copper Sword");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, new HeadType.Sword()), "Prismarine Sword");
        translationBuilder.add(
                TranslationUtil.makeToolDescId(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, new HeadType.Sword()), "Lapis Sword");

        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, Part.ROD), "Wood Rod");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, Part.ROD), "Stone Rod");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, Part.ROD), "Gold Rod");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, Part.ROD), "Blaze Rod");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, Part.ROD), "Emerald Rod");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, Part.ROD), "Iron Rod");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, Part.ROD), "Copper Rod");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, Part.ROD), "Prismarine Rod");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, Part.ROD), "Lapis Rod");

        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key, Part.TRIM), "Wood Trim");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.key, Part.TRIM), "Stone Trim");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.key, Part.TRIM), "Gold Trim");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.key, Part.TRIM), "Blaze Trim");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.key, Part.TRIM), "Emerald Trim");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.key, Part.TRIM), "Iron Trim");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.key, Part.TRIM), "Copper Trim");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.key, Part.TRIM), "Prismarine Trim");
        translationBuilder.add(TranslationUtil.makePartDescId(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.key, Part.TRIM), "Lapis Trim");

        translationBuilder
                .add(Util.makeDescriptionId("menu", Identifier.fromNamespaceAndPath(ModularTools.MODID, "forge")), "Forge");

    }
}
