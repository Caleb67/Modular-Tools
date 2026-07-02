package io.github.caleb67.modulartools.datagen;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.tool.HeadType;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import io.github.caleb67.modulartools.tool.Part;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;

public class TranslationUtil {
    public static String makePartDescId(ResourceKey<MaterialBehavior> material, Part part) {
        return Util.makeDescriptionId("tool_description", material.identifier()) + "." + part.getName();
    }
    
    public static String makeToolDescId(ResourceKey<MaterialBehavior> head_material, HeadType type) {
        return Util.makeDescriptionId("tool_description", head_material.identifier())
            + "." + type.getName().getNamespace()
            + "." + type.getName().getPath().replace('/', '.');
    }
    
    public static String makeEffectDescId(ResourceKey<MaterialBehavior> material, int level) {
        return Util.makeDescriptionId("part_effect", material.identifier()) + "." + level;
    }
    
    public static String makePartUnknown() {
        return "tool_description." + ModularTools.MODID + ".unknown";
    }
}
