package io.github.caleb67.modulartools.datagen;

import io.github.caleb67.modulartools.ModularTools;
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
import org.apache.commons.lang3.IntegerRange;
import org.apache.commons.lang3.function.TriConsumer;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModularToolsEnglishLangProvider extends FabricLanguageProvider {
    protected ModularToolsEnglishLangProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        // Specifying en_us is optional, as it's the default language code
        super(dataOutput, "en_us", registryLookup);
    }
    
    private static final List<Map.Entry<MaterialBehavior, Translation>> MATERIAL_TRANSLATIONS = List.of(
        Map.entry(MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR, new Translation("Wood", Optional.empty(), false)),
        Map.entry(MaterialBehaviors.IRON_MATERIAL_BEHAVIOR, new Translation("Iron", Optional.empty(), false)),
        Map.entry(MaterialBehaviors.STONE_MATERIAL_BEHAVIOR, new Translation("Stone", Optional.empty(), false)),
        Map.entry(MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR, new Translation("Copper", Optional.of("Reach"), true)),
        Map.entry(MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR, new Translation("Gold", Optional.of("Fast"), true)),
        Map.entry(MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR, new Translation("Blaze", Optional.of("Smelting"), false)),
        Map.entry(MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR, new Translation("Emerald", Optional.of("Fortune"), true)),
        Map.entry(MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR, new Translation("Prismarine", Optional.of("Aquatic"), true)),
        Map.entry(MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR, new Translation("Lapis", Optional.of("Amplifier"), true)),
        Map.entry(MaterialBehaviors.QUARTZ_MATERIAL_BEHAVIOR, new Translation("Quartz", Optional.of("Silk Touch"), false)),
        Map.entry(MaterialBehaviors.DIAMOND_MATERIAL_BEHAVIOR, new Translation("Diamond", Optional.of("Durable"), true)),
        Map.entry(MaterialBehaviors.ECHO_MATERIAL_BEHAVIOR, new Translation("Echo", Optional.of("Echoing"), false)),
        Map.entry(MaterialBehaviors.REDSTONE_MATERIAL_BEHAVIOR, new Translation("Redstone", Optional.of("Smart"), false)),
        Map.entry(MaterialBehaviors.NETHERITE_MATERIAL_BEHAVIOR, new Translation("Netherite", Optional.of("Heavy Duty"), true))
    );
    
    private static final Map<HeadType, String> HEAD_TYPES = Map.of(
        new HeadType.Pickaxe(), "Pickaxe", new HeadType.Shovel(), "Shovel", new HeadType.Axe(), "Axe",
        new HeadType.Sword(), "Sword", new HeadType.Hoe(), "Hoe"
    );
    
    record Translation(String name, Optional<String> effectName, boolean hasLevels) {}
    
    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider holderLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(TranslationUtil.makePartUnknown(), "Unknown");
        
        BiConsumer<MaterialBehavior, Translation> effectTranslations = (material, translation) -> {
            HEAD_TYPES.forEach((headType, headName) -> translationBuilder.add(
                TranslationUtil.makeToolDescId(material.key, headType), translation.name+" "+headName)
            );
            translationBuilder.add(TranslationUtil.makePartDescId(material.key, Part.ROD), translation.name + " Rod");
            translationBuilder.add(TranslationUtil.makePartDescId(material.key, Part.TRIM), translation.name + " Trim");
            translation.effectName.ifPresent(effectName -> {
                translationBuilder.add(TranslationUtil.makeEffectDescId(material.key, 1), translation.hasLevels ? effectName + " I" : effectName);
                translationBuilder.add(TranslationUtil.makeEffectDescId(material.key, 2), translation.hasLevels ? effectName + " II" : effectName);
                translationBuilder.add(TranslationUtil.makeEffectDescId(material.key, 3), translation.hasLevels ? effectName + " III" : effectName);
                translationBuilder.add(TranslationUtil.makeEffectDescId(material.key, 4), translation.hasLevels ? effectName + " IV" : effectName);
            });
        };
        
        MATERIAL_TRANSLATIONS.forEach(entry -> effectTranslations.accept(entry.getKey(), entry.getValue()));
        
        
        translationBuilder.add(Items.PICKAXE_TOOL_TEMPLATE, "Pickaxe Template");
        translationBuilder.add(Items.SHOVEL_TOOL_TEMPLATE, "Shovel Template");
        translationBuilder.add(Items.AXE_TOOL_TEMPLATE, "Axe Template");
        translationBuilder.add(Items.SWORD_TOOL_TEMPLATE, "Sword Template");
        translationBuilder.add(Items.HOE_TOOL_TEMPLATE, "Hoe Template");
        
        translationBuilder.add(Items.BASE_PICKAXE_TOOL, "Unknown Type Pickaxe");
        translationBuilder.add(Items.BASE_SHOVEL_TOOL, "Unknown Type Shovel");
        translationBuilder.add(Items.BASE_AXE_TOOL, "Unknown Type Axe");
        translationBuilder.add(Items.BASE_SWORD_TOOL, "Unknown Type Sword");
        translationBuilder.add(Items.BASE_HOE_TOOL, "Unknown Type Hoe");
        
        translationBuilder
            .add(Util.makeDescriptionId("menu", Identifier.fromNamespaceAndPath(ModularTools.MODID, "forge")), "Forge");
        
        translationBuilder.add("creativeTab." + ModularTools.MODID, "Modular Tools");
    }
}
