package io.github.caleb67.modulartools;

import com.mojang.serialization.Lifecycle;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class ModularToolsRegistries {
    public static final Registry<MaterialBehavior> MATERIAL_BEHAVIOR = new MappedRegistry<>(
            ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(ModularTools.MODID, "material_behaviors")),
            Lifecycle.stable()
    );

    public static void load() {}
}
