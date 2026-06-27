package io.github.caleb67.modulartools.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModularToolsRecipeProvider extends FabricRecipeProvider {
    public ModularToolsRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);
                shapeless(RecipeCategory.TOOLS, io.github.caleb67.modulartools.register.Items.PICKAXE_TOOL_TEMPLATE)
                        .requires(Items.BOOK)
                        .requires(Items.WOODEN_PICKAXE)
                        .unlockedBy(getHasName(Items.WOODEN_PICKAXE), has(Items.WOODEN_PICKAXE))
                        .save(output);
                shapeless(RecipeCategory.TOOLS, io.github.caleb67.modulartools.register.Items.SHOVEL_TOOL_TEMPLATE)
                        .requires(Items.BOOK)
                        .requires(Items.WOODEN_SHOVEL)
                        .unlockedBy(getHasName(Items.WOODEN_SHOVEL), has(Items.WOODEN_SHOVEL))
                        .save(output);
                shapeless(RecipeCategory.TOOLS, io.github.caleb67.modulartools.register.Items.AXE_TOOL_TEMPLATE)
                        .requires(Items.BOOK)
                        .requires(Items.WOODEN_AXE)
                        .unlockedBy(getHasName(Items.WOODEN_AXE), has(Items.WOODEN_AXE))
                        .save(output);
                shapeless(RecipeCategory.TOOLS, io.github.caleb67.modulartools.register.Items.SWORD_TOOL_TEMPLATE)
                        .requires(Items.BOOK)
                        .requires(Items.WOODEN_SWORD)
                        .unlockedBy(getHasName(Items.WOODEN_SWORD), has(Items.WOODEN_SWORD))
                        .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return "ModularToolsRecipeProvider";
    }
}
