package io.github.caleb67.modulartools.gametest.material;

import io.github.caleb67.modulartools.content.materials.CopperMaterialBehavior;
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

public final class TestCopper extends BaseMaterialBehaviorTest {
    @GameTest
    public void testInventoryTick(GameTestHelper context, AbstractModularToolItem tool,
                                  ServerPlayer player, int slot, int level) {
        try {testInHand(context, player, level);} catch (GameTestAssertException e) {
            throw Helper.specify(context, e, "(in mainhand)");
        }
        try {testNotInHand(context, player, level);} catch (GameTestAssertException e) {
            throw Helper.specify(context, e, "(not in mainhand)");
        }
    }
    
    private void testInHand(GameTestHelper context, ServerPlayer player, int level) {
        player.getInventory().setSelectedSlot(0);
        CopperMaterialBehavior.testAndApply(player.getSlot(0).get(), player);
        Helper.expectAttributeModifier(context,
            Attributes.BLOCK_INTERACTION_RANGE, CopperMaterialBehavior.INCREASE_BLOCK_REACH,
            player, level, 2*level
        );
        Helper.expectAttributeModifier(context,
            Attributes.ENTITY_INTERACTION_RANGE, CopperMaterialBehavior.INCREASE_ENTITY_REACH,
            player, level, 2*level
        );
    }
    
    private void testNotInHand(GameTestHelper context, ServerPlayer player, int level) {
        player.getInventory().setSelectedSlot(1);
        CopperMaterialBehavior.testAndApply(player.getSlot(0).get(), player);
        Helper.expectAttributeModifier(context,
            Attributes.BLOCK_INTERACTION_RANGE, CopperMaterialBehavior.INCREASE_BLOCK_REACH,
            player, 0, 0
        );
        Helper.expectAttributeModifier(context,
            Attributes.ENTITY_INTERACTION_RANGE, CopperMaterialBehavior.INCREASE_ENTITY_REACH,
            player, 0, 0
        );
    }
    
    @Override protected MaterialBehavior getMaterial() {
        return MaterialBehaviors.COPPER_MATERIAL_BEHAVIOR;
    }
}

