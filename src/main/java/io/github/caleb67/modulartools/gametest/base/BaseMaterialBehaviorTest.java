package io.github.caleb67.modulartools.gametest.base;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.register.MaterialBehaviors;
import io.github.caleb67.modulartools.tool.AbstractModularToolItem;
import io.github.caleb67.modulartools.tool.MaterialBehavior;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import org.apache.commons.lang3.function.TriConsumer;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

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
    
    protected void testMisc(GameTestHelper context, AbstractModularToolItem tool, ServerPlayer player, int slot, int level) {}
    
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
    public final void invokeInventoryTickTest(GameTestHelper context, AbstractModularToolItem tool) {
        var material = getMaterial();
        ServerPlayer player = (ServerPlayer) context.makeMockServerPlayer(GameType.SURVIVAL);
        player.getInventory().setSelectedSlot(0);
        
        BiConsumer<GameTestHelper, Integer> invTickTest = (ctx, lvl) -> {
            player.getInventory().setSelectedSlot(0);
            player.getInventory().tick();
            testInventoryTick(ctx, tool, player, 0, lvl);
            player.getInventory().clearContent();
        };
        var name = "testInventoryTick";
        invokeTest(name, context, invTickTest, player, tool, material, 0, 0);
        invokeTest(name, context, invTickTest, player, tool, material, 0, 1);
        invokeTest(name, context, invTickTest, player, tool, material, 0, 2);
        invokeTest(name, context, invTickTest, player, tool, material, 0, 3);
        invokeTest(name, context, invTickTest, player, tool, material, 0, 4);
        context.succeed();
    }
    
    public final void invokeMiscTest(GameTestHelper context, AbstractModularToolItem tool) {
        var material = getMaterial();
        ServerPlayer player = (ServerPlayer) context.makeMockServerPlayer(GameType.SURVIVAL);
        player.getInventory().setSelectedSlot(0);
        
        BiConsumer<GameTestHelper, Integer> miscTest = (ctx, lvl) -> {
            testMisc(ctx, tool, player, 0, lvl);
        };
        var name = "testMisc";
        invokeTest(name, context, miscTest, player, tool, material, 0, 4);
        invokeTest(name, context, miscTest, player, tool, material, 0, 0);
        invokeTest(name, context, miscTest, player, tool, material, 0, 1);
        invokeTest(name, context, miscTest, player, tool, material, 0, 2);
        invokeTest(name, context, miscTest, player, tool, material, 0, 3);
        context.succeed();
    }
    
    protected final void invokeTest(String name, GameTestHelper context,
                              BiConsumer<GameTestHelper, Integer> function,
                              ServerPlayer player, AbstractModularToolItem tool,
                              MaterialBehavior material, int slot, int level) {
        switch (level) {
            case 0 -> Helper.setTool(tool, player, slot, MaterialBehaviors.STONE_MATERIAL_BEHAVIOR);
            case 1 -> Helper.setTool(tool, player, slot, material,
                MaterialBehaviors.STONE_MATERIAL_BEHAVIOR, MaterialBehaviors.STONE_MATERIAL_BEHAVIOR);
            case 2 -> Helper.setTool(tool, player, slot, material, material, MaterialBehaviors.STONE_MATERIAL_BEHAVIOR);
            case 3 -> Helper.setTool(tool, player, slot, material, material, material);
            case 4 -> Helper.setTool(tool, player, slot, material, material, MaterialBehaviors.LAPIS_MATERIAL_BEHAVIOR);
            default -> throw new IllegalArgumentException("Max material effect level is 4!");
        }
        try {
            function.accept(context, level);
        } catch (GameTestAssertException e) {
            ModularTools.LOGGER.error(
                "Test failed for {} lvl{} '{}', on headtype '{}'",
                name, level, material.key, tool.getHeadType().getName()
            );
            context.testInfo.fail(e.getDescription());
        }
    }
}
