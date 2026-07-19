package io.github.caleb67.modulartools.gametest.material;

import io.github.caleb67.modulartools.content.materials.RedstoneMaterialBehavior;
import io.github.caleb67.modulartools.gametest.base.BaseMaterialBehaviorTest;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import io.github.caleb67.modulartools.tool.AbstractModularToolItem;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import net.minecraft.core.component.DataComponents;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.Blocks;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class TestRedstone extends BaseMaterialBehaviorTest {
    @Override protected void testMisc(GameTestHelper context, AbstractModularToolItem tool,
                                      ServerPlayer player, int slot, int level) {
        var pickupStack = new ItemStack(Blocks.DIRT.asItem(), 64);
        
        assertPickUp(context, player, pickupStack, true, "(no box)");
        clearInventory(context, player, pickupStack);
        
        player.getInventory().setItem(3, new ItemStack(Blocks.SHULKER_BOX, 1));
        var box = player.getInventory().getItem(3);
        
        assertPickUp(context, player, pickupStack, true, "(empty box)");
        clearInventory(context, player, pickupStack);
        
        box.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(List.of(new ItemStack(Blocks.STONE))));
        assertPickUp(context, player, pickupStack, true, "(mismatched box)");
        clearInventory(context, player, pickupStack);
        
        box.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(List.of(pickupStack.copyWithCount(2))));
        assertPickUp(context, player, pickupStack, false, "(matching box)");
        var sum = getSum(context, box);
        context.assertTrue(sum == (64 + 2), "Expected 66 items in the box! (matching box)");
        clearInventory(context, player, pickupStack);
        
        box.set(DataComponents.CONTAINER,
            ItemContainerContents.fromItems(Collections.nCopies(27, pickupStack.copyWithCount(63))));
        assertPickUp(context, player, pickupStack, true, "(matching box almost full)");
        sum = getSum(context, box);
        context.assertTrue(sum == (64*27), "Expected full box! (matching box almost full)");
        context.assertTrue(getRemaining(context, player, pickupStack) == (64 - 27),
            "Expected 37 to be picked up! (matching box almost full)");
    }
    
    @Override protected MaterialBehavior getMaterial() {
        return MaterialBehaviors.REDSTONE_MATERIAL_BEHAVIOR;
    }
    
    private int getSum(GameTestHelper context, ItemStack shulkerBox) {
        var sum = shulkerBox.get(DataComponents.CONTAINER)
                            .allItemsCopyStream()
                            .map(ItemStack::getCount)
                            .reduce(Integer::sum);
        context.assertTrue(sum.isPresent(), "Expected items in the box!");
        return sum.orElseThrow();
    }
    
    private int getRemaining(GameTestHelper context, ServerPlayer player, ItemStack of) {
        AtomicInteger i = new AtomicInteger();
        player.getInventory().forEach(itemStack -> {
            if (itemStack.is(of.getItem())) {
                i.addAndGet(itemStack.count());
            }
        });
        return i.intValue();
    }
    
    private void assertPickUp(GameTestHelper context, ServerPlayer player,
                              ItemStack pickup, boolean shouldPickUp, String errorDetails) {
        var picked_up = pickedUp(player, pickup);
        if (shouldPickUp) {
            context.assertTrue(picked_up, "Expected player to pick up stack! " + errorDetails);
            
        }
        else
            context.assertFalse(picked_up, "Expected stack to go in box! " + errorDetails);
    }
    
    private void clearInventory(GameTestHelper context, ServerPlayer player, ItemStack of) {
        player.getInventory().forEach(stack -> {
            if (stack.is(of.getItem())) {
                stack.setCount(0);
            }
        });
    }
    
    private boolean pickedUp(ServerPlayer player, ItemStack pickup) {
        var pickup_copy = pickup.copy();
        RedstoneMaterialBehavior.apply(player, pickup_copy);
        if (!pickup_copy.isEmpty())
            player.getInventory().add(pickup_copy);
        return player.getInventory().contains(pickup);
    }
}
