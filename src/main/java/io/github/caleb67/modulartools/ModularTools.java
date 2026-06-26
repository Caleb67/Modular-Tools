package io.github.caleb67.modulartools;

import io.github.caleb67.modulartools.content.EndTickEvents;
import io.github.caleb67.modulartools.content.LootTableChanges;
import io.github.caleb67.modulartools.register.Events;
import io.github.caleb67.modulartools.register.Items;
import io.github.caleb67.modulartools.register.MTDataComponents;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import net.fabricmc.api.ModInitializer;
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
        Items.load();
        LootTableChanges.load();
        EndTickEvents.load();
        Events.load();
    }
}
