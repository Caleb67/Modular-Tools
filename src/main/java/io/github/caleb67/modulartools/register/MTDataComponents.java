package io.github.caleb67.modulartools.register;

import io.github.caleb67.modulartools.ModularToolsRegistries;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import static io.github.caleb67.modulartools.ModularTools.MODID;


public class MTDataComponents {
    public static final DataComponentType<ResourceKey<MaterialBehavior>> MODULAR_TOOL_HEAD = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(MODID, "modular_tool_head"),
            DataComponentType.<ResourceKey<MaterialBehavior>>builder().persistent(ResourceKey.codec(ModularToolsRegistries.MATERIAL_BEHAVIOR.key())).build()
    );

    public static final DataComponentType<ResourceKey<MaterialBehavior>> MODULAR_TOOL_ROD = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(MODID, "modular_tool_rod"),
            DataComponentType.<ResourceKey<MaterialBehavior>>builder().persistent(ResourceKey.codec(ModularToolsRegistries.MATERIAL_BEHAVIOR.key())).build()
    );

    public static final DataComponentType<ResourceKey<MaterialBehavior>> MODULAR_TOOL_TRIM = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(MODID, "modular_tool_trim"),
            DataComponentType.<ResourceKey<MaterialBehavior>>builder().persistent(ResourceKey.codec(ModularToolsRegistries.MATERIAL_BEHAVIOR.key())).build()
    );

    public static void load() {};
}
