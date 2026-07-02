package io.github.caleb67.modulartools.register;

import io.github.caleb67.modulartools.content.blocks.ForgeMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class MenuTypes {
    public static final MenuType<ForgeMenu> FORGE = register("forge", ForgeMenu::new);
    
    public static <T extends AbstractContainerMenu> MenuType<T> register(
        String name,
        MenuType.MenuSupplier<T> constructor
    ) {
        return Registry.register(BuiltInRegistries.MENU, name, new MenuType<>(constructor, FeatureFlagSet.of()));
    }
    
    public static void load() {}
}
