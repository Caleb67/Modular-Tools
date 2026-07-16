package io.github.caleb67.modulartools.gametest.material;

import io.github.caleb67.modulartools.gametest.base.BaseMaterialBehaviorTest;
import io.github.caleb67.modulartools.gametest.base.Helper;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import io.github.caleb67.modulartools.tool.AbstractModularToolItem;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.enchantment.Enchantments;

public final class TestEmerald extends BaseMaterialBehaviorTest {
    @GameTest
    public void testInventoryTick(GameTestHelper context, AbstractModularToolItem tool,
                                  ServerPlayer player, int slot, int level) {
        
        var stack = player.getInventory().getItem(slot);
        Helper.expectEnchantment(context, Enchantments.FORTUNE, stack, level);
        Helper.expectEnchantment(context, Enchantments.LOOTING, stack, level);
    }
    
    @Override protected MaterialBehavior getMaterial() {
        return MaterialBehaviors.EMERALD_MATERIAL_BEHAVIOR;
    }
}

