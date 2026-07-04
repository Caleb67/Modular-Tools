package io.github.caleb67.modulartools.register;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.tool.AbstractModularToolItem;
import io.github.caleb67.modulartools.tool.HeadType;
import io.github.caleb67.modulartools.tool.ToolTemplateItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.function.Function;

public class Items {
    
    public static final AbstractModularToolItem BASE_PICKAXE_TOOL = register(
        "base_pickaxe_tool",
        properties -> new AbstractModularToolItem(properties) {
            @Override public @NotNull HeadType getHeadType() {return new HeadType.Pickaxe();}
        },
        new Item.Properties()
    );
    public static final Item PICKAXE_TOOL_TEMPLATE = register(
        "pickaxe_tool_template",
        properties -> new ToolTemplateItem(properties, BASE_PICKAXE_TOOL),
        new Item.Properties()
    );
    
    public static final AbstractModularToolItem BASE_SHOVEL_TOOL = register(
        "base_shovel_tool",
        properties -> new AbstractModularToolItem(properties) {
            @Override public @NotNull HeadType getHeadType() {return new HeadType.Shovel();}
            
            @Override public @NonNull InteractionResult useOn(UseOnContext context) {
                var result = net.minecraft.world.item.Items.NETHERITE_SHOVEL.useOn(context);
                return result == InteractionResult.PASS ? super.useOn(context) : result;
            }
        },
        new Item.Properties()
    );
    public static final Item SHOVEL_TOOL_TEMPLATE = register(
        "shovel_tool_template",
        properties -> new ToolTemplateItem(properties, BASE_SHOVEL_TOOL),
        new Item.Properties()
    );
    
    public static final AbstractModularToolItem BASE_AXE_TOOL = register(
        "base_axe_tool",
        properties -> new AbstractModularToolItem(properties) {
            @Override
            public @NotNull HeadType getHeadType() {return new HeadType.Axe();}
            
            @Override public @NonNull InteractionResult useOn(UseOnContext context) {
                var result = net.minecraft.world.item.Items.NETHERITE_AXE.useOn(context);
                return result == InteractionResult.PASS ? super.useOn(context) : result;
            }
        },
        new Item.Properties()
    );
    public static final Item AXE_TOOL_TEMPLATE = register(
        "axe_tool_template",
        properties -> new ToolTemplateItem(properties, BASE_AXE_TOOL),
        new Item.Properties()
    );
    
    public static final AbstractModularToolItem BASE_SWORD_TOOL = register(
        "base_sword_tool",
        properties -> new AbstractModularToolItem(properties) {
            @Override public @NotNull HeadType getHeadType() {return new HeadType.Sword();}
        },
        new Item.Properties()
    );
    public static final Item SWORD_TOOL_TEMPLATE = register(
        "sword_tool_template",
        properties -> new ToolTemplateItem(properties, BASE_SWORD_TOOL),
        new Item.Properties()
    );
    
    public static final AbstractModularToolItem BASE_HOE_TOOL = register(
        "base_hoe_tool",
        properties -> new AbstractModularToolItem(properties) {
            @Override public @NotNull HeadType getHeadType() {return new HeadType.Hoe();}
            
            @Override public @NonNull InteractionResult useOn(@NonNull UseOnContext context) {
                var result = net.minecraft.world.item.Items.NETHERITE_HOE.useOn(context);
                return result == InteractionResult.PASS ? super.useOn(context) : result;
            }
        },
        new Item.Properties()
    );
    public static final Item HOE_TOOL_TEMPLATE = register(
        "hoe_tool_template",
        properties -> new ToolTemplateItem(properties, BASE_HOE_TOOL),
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
    
    public static void load() {}
    
    ;
}
