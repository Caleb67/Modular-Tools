package io.github.caleb67.modulartools.gametest.base;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import io.github.caleb67.modulartools.tool.AbstractModularToolItem;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;

public abstract class BaseMaterialBehaviorTest {
    
    /**
     *
     * @param context
     * @param tool
     * @param player  has slot 0 selected in hotbar
     * @param slot    always 0
     * @param level
     */
    protected void testInventoryTick(GameTestHelper context, AbstractModularToolItem tool,
                                     ServerPlayer player, int slot, int level) {}
    
    protected abstract MaterialBehavior getMaterial();
    
    public final String getTestId() {
        return getMaterial().key.identifier().getPath() + "_test";
    }
    
    /**
     * tests onTick method for material behavior with toolItem tool
     * succeeds automatically if all tests pass <br>
     * always spawns player at 0, 0, 0 <br>
     * unused parts will be stone material <br>
     * always invokes test starting with: <br>
     * - head at lvl 1, <br>
     * - then head+rod at lvl 2, <br>
     * - head+rod+trim at lvl 3, <br>
     * - and finally head+rod+(lapis trim) at lvl 4
     *
     * @param context The test context
     */
    public void invokeInventoryTickTest(GameTestHelper context, AbstractModularToolItem tool) {
        var material = getMaterial();
        ServerPlayer player = (ServerPlayer) context.makeMockServerPlayer(GameType.SURVIVAL);
        context.succeed();
        player.getInventory().setSelectedSlot(0);
        
        invokeInventoryTick(context, player, tool, material, 0, 0);
        invokeInventoryTick(context, player, tool, material, 0, 1);
        invokeInventoryTick(context, player, tool, material, 0, 2);
        invokeInventoryTick(context, player, tool, material, 0, 3);
        invokeInventoryTick(context, player, tool, material, 0, 4);
        context.succeed();
    }
    
    protected void invokeInventoryTick(GameTestHelper context, ServerPlayer player,
                                       AbstractModularToolItem tool, MaterialBehavior material,
                                       int slot, int expectedLevel) {
        switch (expectedLevel) {
            case 0 -> Helper.setTool(tool, player, slot, MaterialBehaviors.STONE_MATERIAL_BEHAVIOR);
            case 1 -> Helper.setTool(tool, player, slot, material,
                MaterialBehaviors.STONE_MATERIAL_BEHAVIOR, MaterialBehaviors.STONE_MATERIAL_BEHAVIOR);
            case 2 -> Helper.setTool(tool, player, slot, material, material, MaterialBehaviors.STONE_MATERIAL_BEHAVIOR);
            case 3 -> Helper.setTool(tool, player, slot, material, material, material);
            case 4 -> Helper.setTool(tool, player, slot, material, material, MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR);
            default -> throw new IllegalArgumentException("Max material effect level is 4!");
        }
        try {
            player.getInventory().setSelectedSlot(0);
            player.getInventory().tick();
            testInventoryTick(context, tool, player, 0, expectedLevel);
        } catch (Exception e) {
            ModularTools.LOGGER.error(
                "Test failed for testInventoryTick lvl{} '{}', on headtype '{}'",
                expectedLevel, material.key, tool.getHeadType().getName()
            );
            ModularTools.LOGGER.error(e.getMessage());
        }
    }
}
