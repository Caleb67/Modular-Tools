package io.github.caleb67.modulartools;

import io.github.caleb67.modulartools.content.EndTickEvents;
import io.github.caleb67.modulartools.content.LootTableChanges;
import io.github.caleb67.modulartools.register.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModularTools implements ModInitializer {
    public static final String MODID = "modulartools";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);



    @Override
    public void onInitialize() {
        ModularToolsRegistries.load();
        MaterialBehaviors.load();
        MTDataComponents.load();
        MenuTypes.load();
        Payloads.load();
        Items.load();
        Blocks.load();
        BlockEntities.load();
        LootTableChanges.load();
        EndTickEvents.load();
        Events.load();
        CreativeTabs.load();
    }
}
