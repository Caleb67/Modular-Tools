package io.github.caleb67.modulartools;

import io.github.caleb67.modulartools.content.EndTickEvents;
import io.github.caleb67.modulartools.content.LevelLoadEvents;
import io.github.caleb67.modulartools.content.LoadEntityEvents;
import io.github.caleb67.modulartools.content.LootTableChanges;
import io.github.caleb67.modulartools.register.*;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.caleb67.modulartools.gametest.GameTests;
import net.fabricmc.fabric.impl.gametest.FabricGameTestRunner;

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
        LoadEntityEvents.load();
        LevelLoadEvents.load();
        Events.load();
        CreativeTabs.load();
        
        if (FabricGameTestRunner.ENABLED) {
            GameTests.load();
        }
    }
}
