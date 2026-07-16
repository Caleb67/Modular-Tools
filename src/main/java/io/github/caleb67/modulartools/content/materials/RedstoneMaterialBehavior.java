package io.github.caleb67.modulartools.content.materials;

import io.github.caleb67.modulartools.datagen.TranslationUtil;
import io.github.caleb67.modulartools.tool.BaseMaterialBehavior;
import io.github.caleb67.modulartools.tool.tooltip.MaterialEffectTooltipOperation;
import io.github.caleb67.modulartools.util.MethodChain;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemContainerContents;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class RedstoneMaterialBehavior extends BaseMaterialBehavior {
    public RedstoneMaterialBehavior(Properties properties) {
        super(properties);
    }
    
    public static void apply(ServerPlayer player, ItemStack itemStack) {
        var inv = player.getInventory();
        var possible_boxes = new ArrayList<ItemStack>();
        
        inv.forEach(stack -> {
            if (!stack.is(Items.SHULKER_BOX)) return;
            if (!stack.has(DataComponents.CONTAINER)) return;
            if (stack.get(DataComponents.CONTAINER)
                     .nonEmptyItemCopyStream()
                     .anyMatch(stk -> stk.is(itemStack.getItem())))
                possible_boxes.add(stack);
        });
        
        possible_boxes.forEach(box -> {
            var container = new ArrayList<>(box.get(DataComponents.CONTAINER).allItemsCopyStream().toList());
            for (int i = 27 - container.size(); i > 0; i--)
                container.add(ItemStack.EMPTY);
            
            var new_container = container
                .stream()
                .map(stk -> {
                    if (stk.isEmpty()) {
                        stk = new ItemStack(
                            itemStack.getItem().builtInRegistryHolder(), 0,
                            itemStack.getComponentsPatch()
                        );
                        tryAddToStack(stk, itemStack);
                    }
                    else if (ItemStack.isSameItemSameComponents(stk, itemStack))
                        tryAddToStack(stk, itemStack);
                    return stk;
                 }).toList();
            
            box.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(new_container));
        });
    }
    
    protected static void tryAddToStack(ItemStack destinationStack, ItemStack sourceStack) {
        if (destinationStack.isEmpty()) {
            destinationStack.setCount(sourceStack.getCount());
            sourceStack.setCount(0);
        }
        else {
            var dest_capacity = destinationStack.getMaxStackSize() - destinationStack.getCount();
            var amount_to_move = Math.min(dest_capacity, sourceStack.getCount());
            
            sourceStack.shrink(amount_to_move);
            destinationStack.grow(amount_to_move);
        }
    }
    
    @Override public Optional<MaterialEffectTooltipOperation> getEffectTooltip(ItemStack itemStack, int numTimes) {
        return Optional.of(((collector, context, display, builder, tooltipFlag) -> {
            builder.accept(
                Component.translatable(TranslationUtil.makeEffectDescId(this.key, numTimes))
                         .withStyle(this.getEffectFormatting())
            );
        }));
    }
}
