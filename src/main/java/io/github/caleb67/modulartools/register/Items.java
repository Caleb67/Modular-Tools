package io.github.caleb67.modulartools.register;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.tool.*;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTabOutput;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.TooltipDisplay;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Items {

    public static final AbstractModularToolItem BASE_PICKAXE_TOOL = register(
            "base_pickaxe_tool",
            properties -> new AbstractModularToolItem(properties) {
                @Override public @NotNull HeadType getHeadType() {return new HeadType.Pickaxe();}
            },
            new Item.Properties().stacksTo(1)
                    .durability(ToolMaterial.WOOD.durability())
                    .component(MTDataComponents.MODULAR_TOOL_HEAD, MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key)
                    .component(MTDataComponents.MODULAR_TOOL_ROD, MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key)
                    .component(MTDataComponents.MODULAR_TOOL_TRIM, MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key)
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false)
                    .component(
                            DataComponents.TOOLTIP_DISPLAY,
                            new TooltipDisplay(false, new LinkedHashSet<>(List.of(DataComponents.ENCHANTMENTS)))
                    )
    );
    public static final Item PICKAXE_TOOL_TEMPLATE = register(
            "pickaxe_tool_template",
            properties -> new ToolTemplateItem(properties, (AbstractModularToolItem) BASE_PICKAXE_TOOL),
            new Item.Properties()
    );

    public static final AbstractModularToolItem BASE_SHOVEL_TOOL = register(
            "base_shovel_tool",
            properties -> new AbstractModularToolItem(properties) {
                @Override public @NotNull HeadType getHeadType() {return new HeadType.Shovel();}
            },
            new Item.Properties().stacksTo(1)
                    .durability(ToolMaterial.WOOD.durability())
                    .component(MTDataComponents.MODULAR_TOOL_HEAD, MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key)
                    .component(MTDataComponents.MODULAR_TOOL_ROD, MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key)
                    .component(MTDataComponents.MODULAR_TOOL_TRIM, MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key)
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false)
                    .component(
                            DataComponents.TOOLTIP_DISPLAY,
                            new TooltipDisplay(false, new LinkedHashSet<>(List.of(DataComponents.ENCHANTMENTS)))
                    )
    );
    public static final Item SHOVEL_TOOL_TEMPLATE = register(
            "shovel_tool_template",
            properties -> new ToolTemplateItem(properties, (AbstractModularToolItem) BASE_SHOVEL_TOOL),
            new Item.Properties()
    );

    public static final BaseAxeItem BASE_AXE_TOOL = register(
            "base_axe_tool",
            properties -> new BaseAxeItem(properties),
            new Item.Properties().stacksTo(1)
                    .durability(ToolMaterial.WOOD.durability())
                    .component(MTDataComponents.MODULAR_TOOL_HEAD, MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key)
                    .component(MTDataComponents.MODULAR_TOOL_ROD, MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key)
                    .component(MTDataComponents.MODULAR_TOOL_TRIM, MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key)
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false)
                    .component(
                            DataComponents.TOOLTIP_DISPLAY,
                            new TooltipDisplay(false, new LinkedHashSet<>(List.of(DataComponents.ENCHANTMENTS)))
                    )
    );
    public static final Item AXE_TOOL_TEMPLATE = register(
            "axe_tool_template",
            properties -> new ToolTemplateItem(properties, (AbstractModularToolItem) BASE_AXE_TOOL),
            new Item.Properties()
    );

    public static final AbstractModularToolItem BASE_SWORD_TOOL = register(
            "base_sword_tool",
            properties -> new AbstractModularToolItem(properties) {
                @Override public @NotNull HeadType getHeadType() {return new HeadType.Sword();}
            },
            new Item.Properties().stacksTo(1)
                    .durability(ToolMaterial.WOOD.durability())
                    .component(MTDataComponents.MODULAR_TOOL_HEAD, MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key)
                    .component(MTDataComponents.MODULAR_TOOL_ROD, MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key)
                    .component(MTDataComponents.MODULAR_TOOL_TRIM, MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.key)
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, false)
                    .component(
                            DataComponents.TOOLTIP_DISPLAY,
                            new TooltipDisplay(false, new LinkedHashSet<>(List.of(DataComponents.ENCHANTMENTS)))
                    )
    );
    public static final Item SWORD_TOOL_TEMPLATE = register(
            "sword_tool_template",
            properties -> new ToolTemplateItem(properties, (AbstractModularToolItem) BASE_SWORD_TOOL),
            new Item.Properties()
    );


    public static final Item ROD = register(
            "rod",
            Item::new,
            new Item.Properties().stacksTo(1)
    );
    public static final Item TRIM = register(
            "trim",
            Item::new,
            new Item.Properties().stacksTo(1)
    );

    private static <T extends Item> T register(String name,
                                 Function<Item.Properties, T> factory,
                                 Item.Properties properties) {
        var key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ModularTools.MODID, name));
        var item = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static void load() {};
}
