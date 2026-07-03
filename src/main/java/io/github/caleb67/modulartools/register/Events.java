package io.github.caleb67.modulartools.register;


import io.github.caleb67.modulartools.content.EndTickEvents;
import io.github.caleb67.modulartools.content.LevelLoadEvents;
import io.github.caleb67.modulartools.content.LoadEntityEvents;
import io.github.caleb67.modulartools.content.LootTableChanges;
import io.github.caleb67.modulartools.content.blocks.ForgeMenu;
import io.github.caleb67.modulartools.tool.AbstractModularToolItem;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLevelEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Events {
    public static void load() {
        LootTableChanges.changes.forEach(LootTableEvents.MODIFY_DROPS::register);
        EndTickEvents.changes.forEach(ServerTickEvents.END_SERVER_TICK::register);
        LoadEntityEvents.changes.forEach(ServerEntityEvents.ENTITY_LOAD::register);
        LevelLoadEvents.changes.forEach(ServerLevelEvents.LOAD::register);
        
        ServerTickEvents.END_SERVER_TICK.register(minecraftServer -> {
            minecraftServer.getPlayerList().getPlayers().forEach(serverPlayer -> {
                var attack_speed_attr = serverPlayer.getAttribute(Attributes.ATTACK_SPEED);
                var attack_damage_attr = serverPlayer.getAttribute(Attributes.ATTACK_DAMAGE);
                assert attack_speed_attr != null;
                assert attack_damage_attr != null;
                
                AtomicReference<ItemStack> viable_tool = new AtomicReference<>();
                serverPlayer.getInventory().forEach(itemStack -> {
                    if (ItemStack.isSameItemSameComponents((serverPlayer).getMainHandItem(), itemStack) &&
                        (itemStack.getItem() instanceof AbstractModularToolItem)) {
                        viable_tool.set(itemStack);
                    }
                });
                
                if (viable_tool.get() != null) {
                    var stack = viable_tool.get();
                    ((AbstractModularToolItem) stack.getItem()).updateAttackAttributes(stack, serverPlayer);
                }
                else {
                    attack_speed_attr.removeModifier(AbstractModularToolItem.BASE_ATTACK_SPEED);
                    attack_damage_attr.removeModifier(AbstractModularToolItem.BASE_ATTACK_SPEED);
                }
                
                
                // update Menus if open
                if (serverPlayer.containerMenu instanceof ForgeMenu fm) {
                    fm.updateFuel();
                }
            });
        });
        
        ServerLevelEvents.LOAD.register((server, level) -> {
            MaterialBehaviors.NETHERITE_MATERIAL_BEHAVIOR.load(level);
            MaterialBehaviors.WOOD_MATERIAL_BEHAVIOR.addValidItems(
                setFromHolderSet(BuiltInRegistries.ITEM.getOrThrow(ItemTags.PLANKS))
            );
            MaterialBehaviors.STONE_MATERIAL_BEHAVIOR.addValidItems(
                setFromHolderSet(BuiltInRegistries.ITEM.getOrThrow(ItemTags.STONE_TOOL_MATERIALS))
            );
            MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR.addValidItems(Set.of(Items.COPPER_INGOT));
            MaterialBehaviors.IRON_MATERIAL_BEHAVIOR.addValidItems(Set.of(Items.IRON_INGOT));
            MaterialBehaviors.GOLD_MATERIAL_BEHAVIOR.addValidItems(Set.of(Items.GOLD_INGOT));
            MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR.addValidItems(Set.of(Items.EMERALD));
            MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR.addValidItems(Set.of(Items.LAPIS_LAZULI));
            MaterialBehaviors.BLAZE_MATERIAL_BEHAVIOR.addValidItems(Set.of(Items.BLAZE_ROD));
            MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR.addValidItems(Set.of(Items.PRISMARINE_SHARD));
            MaterialBehaviors.QUARTZ_MATERIAL_BEHAVIOR.addValidItems(Set.of(Items.QUARTZ));
            MaterialBehaviors.DIAMOND_MATERIAL_BEHAVIOR.addValidItems(Set.of(Items.DIAMOND));
            MaterialBehaviors.ECHO_MATERIAL_BEHAVIOR.addValidItems(Set.of(Items.ECHO_SHARD));
            MaterialBehaviors.REDSTONE_MATERIAL_BEHAVIOR.addValidItems(Set.of(Items.REDSTONE));
            MaterialBehaviors.NETHERITE_MATERIAL_BEHAVIOR.addValidItems(Set.of(Items.NETHERITE_INGOT));
        });
    }
    
    private static <T> Set<T> setFromHolderSet(HolderSet<T> holderSet) {
        return holderSet.stream()
                        .map(Holder::value)
                        .collect(Collectors.toSet());
    }
}
