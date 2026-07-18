package io.github.caleb67.modulartools.gametest.material;

import io.github.caleb67.modulartools.content.materials.CopperMaterialBehavior;
import io.github.caleb67.modulartools.content.materials.PrismarineMaterialBehavior;
import io.github.caleb67.modulartools.gametest.base.BaseMaterialBehaviorTest;
import io.github.caleb67.modulartools.gametest.base.Helper;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import io.github.caleb67.modulartools.tool.AbstractModularToolItem;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;

public final class TestPrismarine extends BaseMaterialBehaviorTest {
    @GameTest
    public void testInventoryTick(GameTestHelper context, AbstractModularToolItem tool,
                                  ServerPlayer player, int slot, int level) {
        try {testInHand(context, player, level);} catch (GameTestAssertException e) {
            throw Helper.specify(context, e, "(in mainhand)");
        }
        try {testNotInHand(context, player);} catch (GameTestAssertException e) {
            throw Helper.specify(context, e, "(not in mainhand)");
        }
    }
    
    private void testInHand(GameTestHelper context, ServerPlayer player, int level) {
        player.getInventory().setSelectedSlot(0);
        player.getInventory().tick();
        Helper.expectAttributeModifier(context,
            Attributes.SUBMERGED_MINING_SPEED, PrismarineMaterialBehavior.SUBMERGED_MINING_INCREASE,
            player, level, ((double)level)/3
        );
        Helper.expectAttributeModifier(context,
            Attributes.OXYGEN_BONUS, PrismarineMaterialBehavior.OXYGEN_BONUS_INCREASE,
            player, level, 10*level
        );
    }
    
    private void testNotInHand(GameTestHelper context, ServerPlayer player) {
        player.getInventory().setSelectedSlot(1);
        player.getInventory().tick();
        Helper.expectAttributeModifier(context,
            Attributes.SUBMERGED_MINING_SPEED, PrismarineMaterialBehavior.SUBMERGED_MINING_INCREASE,
            player, 0, 0
        );
        Helper.expectAttributeModifier(context,
            Attributes.OXYGEN_BONUS, PrismarineMaterialBehavior.OXYGEN_BONUS_INCREASE,
            player, 0, 0
        );
    }
    
    @Override protected MaterialBehavior getMaterial() {
        return MaterialBehaviors.PRISMARINE_MATERIAL_BEHAVIOR;
    }
}

