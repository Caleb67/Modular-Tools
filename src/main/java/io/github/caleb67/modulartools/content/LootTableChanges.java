package io.github.caleb67.modulartools.content;

import io.github.caleb67.modulartools.content.materials.BlazeMaterialBehavior;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LootTableChanges {
    public static final ArrayList<LootTableEvents.ModifyDrops> changes = new ArrayList<>();
    public static final RecipeManager.CachedCheck<@NotNull SingleRecipeInput, ? extends AbstractCookingRecipe> SMELTING_CHECK = RecipeManager.createCheck(
        RecipeType.SMELTING);
    
    public static void load() {
        changes.add(BlazeMaterialBehavior.SMELTING_BEHAVIOR);
        
    }
    
    
}
