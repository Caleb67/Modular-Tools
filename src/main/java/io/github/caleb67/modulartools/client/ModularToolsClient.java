package io.github.caleb67.modulartools.client;

import io.github.caleb67.modulartools.gametest.GameTests;
import io.github.caleb67.modulartools.register.MenuTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screens.MenuScreens;

import net.fabricmc.fabric.impl.gametest.FabricGameTestRunner;


public class ModularToolsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(MenuTypes.FORGE, ForgeScreen::new);
        
        if (FabricGameTestRunner.ENABLED || FabricLoader.getInstance().isDevelopmentEnvironment()) {
            GameTests.load();
        }
    }
}
