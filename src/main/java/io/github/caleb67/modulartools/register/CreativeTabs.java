package io.github.caleb67.modulartools.register;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.ModularToolsRegistries;
import io.github.caleb67.modulartools.tool.AbstractModularToolItem;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTabOutput;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import org.apache.logging.log4j.util.TriConsumer;

public class CreativeTabs {
    public static final ResourceKey<CreativeModeTab> MODULARTOOLS_CREATIVE_TAB_KEY = ResourceKey.create(
        BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(ModularTools.MODID, "creative_tab")
    );
    
    public static final CreativeModeTab MODULARTOOLS_CREATIVE_TAB = FabricCreativeModeTab.builder()
                                                                                         .icon(
                                                                                             () -> Items.BASE_PICKAXE_TOOL.getDefaultInstance())
                                                                                         .title(Component.translatable(
                                                                                             "creativeTab." + ModularTools.MODID))
                                                                                         .displayItems((_, _) -> {})
                                                                                         .build();
    
    private static void acceptItems(CreativeModeTab tab, FabricCreativeModeTabOutput output) {
        output.accept(Items.PICKAXE_TOOL_TEMPLATE);
        output.accept(Items.SHOVEL_TOOL_TEMPLATE);
        output.accept(Items.AXE_TOOL_TEMPLATE);
        output.accept(Items.SWORD_TOOL_TEMPLATE);
        output.accept(Items.HOE_TOOL_TEMPLATE);
        
        TriConsumer<AbstractModularToolItem, Iterable<MaterialBehavior>, CreativeModeTab.Output>
            acceptForTool = (tool, materials, tabout) -> {
            var stack = tool.getDefaultInstance();
            ;
            for (var material : materials) {
                if (!material.hasHeadTypeAttributes(tool.getHeadType())) continue;
                stack = stack.copy();
                stack.set(MTDataComponents.MODULAR_TOOL_HEAD, material.key);
                stack.set(MTDataComponents.MODULAR_TOOL_ROD, material.key);
                stack.set(MTDataComponents.MODULAR_TOOL_TRIM, material.key);
                tool.onCreation(stack, output.getContext().holders());
                tabout.accept(stack);
                
            }
        };
        
        var materials = ModularToolsRegistries.getAllMaterialBehaviors();
        acceptForTool.accept(Items.BASE_PICKAXE_TOOL, materials, output);
        acceptForTool.accept(Items.BASE_SHOVEL_TOOL, materials, output);
        acceptForTool.accept(Items.BASE_AXE_TOOL, materials, output);
        acceptForTool.accept(Items.BASE_SWORD_TOOL, materials, output);
        acceptForTool.accept(Items.BASE_HOE_TOOL, materials, output);
    }
    
    public static void load() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MODULARTOOLS_CREATIVE_TAB_KEY,
            MODULARTOOLS_CREATIVE_TAB);
        CreativeModeTabEvents.MODIFY_OUTPUT_ALL.register(((tab, output) -> {
            if (tab == MODULARTOOLS_CREATIVE_TAB) acceptItems(MODULARTOOLS_CREATIVE_TAB, output);
        }));
    }
    
    
}
