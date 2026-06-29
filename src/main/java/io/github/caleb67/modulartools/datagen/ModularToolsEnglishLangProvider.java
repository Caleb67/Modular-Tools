package io.github.caleb67.modulartools.datagen;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.content.materials.EmeraldMaterialBehavior;
import io.github.caleb67.modulartools.register.Items;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import io.github.caleb67.modulartools.tool.HeadType;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import io.github.caleb67.modulartools.tool.Part;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import org.apache.commons.lang3.function.TriConsumer;
import org.apache.commons.lang3.function.TriFunction;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModularToolsEnglishLangProvider extends FabricLanguageProvider {
    protected ModularToolsEnglishLangProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        // Specifying en_us is optional, as it's the default language code
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider holderLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(TranslationUtil.makePartUnknown(), "Unknown");

        TriConsumer<MaterialBehavior, String, Boolean> effectTranslations = (material, name, hasLevels) -> {
            translationBuilder.add(TranslationUtil.makeEffectDescId(material.key, 1), hasLevels ? name+" I" : name);
            translationBuilder.add(TranslationUtil.makeEffectDescId(material.key, 2), hasLevels ? name+" II" : name);
            translationBuilder.add(TranslationUtil.makeEffectDescId(material.key, 3), hasLevels ? name+" III" : name);
            translationBuilder.add(TranslationUtil.makeEffectDescId(material.key, 4), hasLevels ? name+" IV" : name);
        };
        effectTranslations.accept(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR, "Reach", true);
        effectTranslations.accept(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR, "Fast", true);
        effectTranslations.accept(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR, "Smelting", false);
        effectTranslations.accept(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR, "Fortune", true);
        effectTranslations.accept(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR, "Aquatic", true);
        effectTranslations.accept(MaterialBehaviors.QUARTZ_MATERIAL_BEHAVIOR, "Silk Touch", false);
        effectTranslations.accept(MaterialBehaviors.DIAMOND_MATERIAL_BEHAVIOR, "Durable", true);
        effectTranslations.accept(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR, "Amplifier", true);
        effectTranslations.accept(MaterialBehaviors.DRAGON_MATERIAL_BEHAVIOR, "Third Eye", true);

        BiConsumer<MaterialBehavior, String> toolTranslations = (material, name) -> {
            translationBuilder.add(TranslationUtil.makeToolDescId(material.key, new HeadType.Pickaxe()), name+" Pickaxe");
            translationBuilder.add(TranslationUtil.makeToolDescId(material.key, new HeadType.Shovel()), name+" Shovel");
            translationBuilder.add(TranslationUtil.makeToolDescId(material.key, new HeadType.Axe()), name+" Axe");
            translationBuilder.add(TranslationUtil.makeToolDescId(material.key, new HeadType.Sword()), name+" Sword");
            translationBuilder.add(TranslationUtil.makePartDescId(material.key, Part.ROD), name+" Rod");
            translationBuilder.add(TranslationUtil.makePartDescId(material.key, Part.TRIM), name+" Trim");
        };
        toolTranslations.accept(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR, "Wood");
        toolTranslations.accept(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR, "Iron");
        toolTranslations.accept(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR, "Stone");
        toolTranslations.accept(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR, "Copper");
        toolTranslations.accept(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR, "Gold");
        toolTranslations.accept(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR, "Blaze");
        toolTranslations.accept(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR, "Emerald");
        toolTranslations.accept(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR, "Prismarine");
        toolTranslations.accept(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR, "Lapis");
        toolTranslations.accept(MaterialBehaviors.QUARTZ_MATERIAL_BEHAVIOR, "Quartz");
        toolTranslations.accept(MaterialBehaviors.DIAMOND_MATERIAL_BEHAVIOR, "Diamond");
        toolTranslations.accept(MaterialBehaviors.DRAGON_MATERIAL_BEHAVIOR, "Dragon");

        translationBuilder.add(Items.PICKAXE_TOOL_TEMPLATE, "Pickaxe Template");
        translationBuilder.add(Items.SHOVEL_TOOL_TEMPLATE, "Shovel Template");
        translationBuilder.add(Items.AXE_TOOL_TEMPLATE, "Axe Template");
        translationBuilder.add(Items.SWORD_TOOL_TEMPLATE, "Sword Template");

        translationBuilder
                .add(Util.makeDescriptionId("menu", Identifier.fromNamespaceAndPath(ModularTools.MODID, "forge")), "Forge");

        translationBuilder.add("creativeTab."+ModularTools.MODID, "Modular Tools");
    }
}
