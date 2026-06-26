package io.github.caleb67.modulartools.tool.tooltip;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

@FunctionalInterface
public interface ToolEffectTooltipOperation {
    void apply(ToolEffectTooltipExecutor visitor, Item.TooltipContext context,
               TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag);
}
