package io.github.caleb67.modulartools.client;

import io.github.caleb67.modulartools.register.MenuTypes;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class ModularToolsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(MenuTypes.FORGE, ForgeScreen::new);
    }
}
