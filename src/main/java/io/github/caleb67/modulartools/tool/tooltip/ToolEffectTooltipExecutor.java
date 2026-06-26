package io.github.caleb67.modulartools.tool.tooltip;

import io.github.caleb67.modulartools.ModularToolsRegistries;
import io.github.caleb67.modulartools.content.materials.LapisMaterialBehavior;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class ToolEffectTooltipExecutor {
    private HashMap<Identifier, Integer> seen = new HashMap<>();
    public ToolEffectTooltipExecutor add(ResourceKey<MaterialBehavior> behavior) {
        var cur_num_visits = this.seen.getOrDefault(behavior.identifier(), 0);
        this.seen.put(behavior.identifier(), cur_num_visits + 1);
        return this;
    }
    public ArrayList<ToolEffectTooltipOperation> complete(ItemStack itemStack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        ArrayList<ToolEffectTooltipOperation> out = new ArrayList<>(3);
        final var amplifier = LapisMaterialBehavior.getAmplifierAmount(itemStack);
        this.seen.forEach(((identifier, numTimes) -> {
            var key = ResourceKey.create(ModularToolsRegistries.MATERIAL_BEHAVIOR.key(), identifier);
            numTimes = numTimes * amplifier;
            var operation = ModularToolsRegistries.MATERIAL_BEHAVIOR.getOrThrow(key).value().getEffectTooltip(itemStack, numTimes);
            operation.ifPresent(out::add);
        }));
        return out;
    }
}
