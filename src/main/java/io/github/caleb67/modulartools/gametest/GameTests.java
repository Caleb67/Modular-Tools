package io.github.caleb67.modulartools.gametest;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.gametest.base.BaseMaterialBehaviorTest;
import io.github.caleb67.modulartools.gametest.material.TestCopper;
import io.github.caleb67.modulartools.gametest.material.TestEmerald;
import io.github.caleb67.modulartools.gametest.material.TestQuartz;
import io.github.caleb67.modulartools.gametest.material.TestRedstone;
import io.github.caleb67.modulartools.register.Items;
import net.fabricmc.fabric.impl.gametest.FabricGameTestRunner;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.gametest.framework.*;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class GameTests {
    private static final ArrayList<ToolsTest> GAME_TESTS = new ArrayList<>();
    private static List<BaseMaterialBehaviorTest> MATERIAL_BEHAVIOR_TESTS = new ArrayList<>();
    
    public static void createTests() {
        MATERIAL_BEHAVIOR_TESTS.addAll(List.of(
            new TestEmerald(), new TestCopper(), new TestQuartz(), new TestRedstone()
        ));
        MATERIAL_BEHAVIOR_TESTS.forEach(gameTest -> {
            var id = gameTest.getTestId();
            createTest("tick_pickaxe_" + id, ctx -> gameTest.invokeInventoryTickTest(ctx, Items.BASE_PICKAXE_TOOL),
                200);
            createTest("misc_pickaxe_" + id, ctx -> gameTest.invokeMiscTest(ctx, Items.BASE_PICKAXE_TOOL), 200);
            createTest("tick_shovel_" + id, ctx -> gameTest.invokeInventoryTickTest(ctx, Items.BASE_SHOVEL_TOOL), 200);
            createTest("misc_shovel_" + id, ctx -> gameTest.invokeMiscTest(ctx, Items.BASE_SHOVEL_TOOL), 200);
            createTest("tick_axe_" + id, ctx -> gameTest.invokeInventoryTickTest(ctx, Items.BASE_AXE_TOOL), 200);
            createTest("misc_axe_" + id, ctx -> gameTest.invokeMiscTest(ctx, Items.BASE_AXE_TOOL), 200);
            createTest("tick_sword_" + id, ctx -> gameTest.invokeInventoryTickTest(ctx, Items.BASE_SWORD_TOOL), 200);
            createTest("misc_sword_" + id, ctx -> gameTest.invokeMiscTest(ctx, Items.BASE_SWORD_TOOL), 200);
            createTest("tick_hoe_" + id, ctx -> gameTest.invokeInventoryTickTest(ctx, Items.BASE_HOE_TOOL), 200);
            createTest("misc_hoe_" + id, ctx -> gameTest.invokeMiscTest(ctx, Items.BASE_HOE_TOOL), 200);
        });
    }
    
    public static void bootstrapInstances(Registry<GameTestInstance> testInstances) {
        if (FabricGameTestRunner.ENABLED) registerTests(testInstances);
    }
    
    private static void createTest(String id, Consumer<GameTestHelper> test, int maxTicks) {
        var instanceKey = createTestInstanceKey(id);
        var functionKey = createTestFunctionKey(id);
        GAME_TESTS.add(new ToolsTest(instanceKey, functionKey, test, maxTicks));
    }
    
    private static void registerTests(Registry<GameTestInstance> testInstances) {
        var lookup = VanillaRegistries.createLookup();
        var batches = lookup.lookupOrThrow(Registries.TEST_ENVIRONMENT);
        GAME_TESTS.forEach(test -> Registry.register(
            testInstances,
            test.instance(),
            new FunctionGameTestInstance(
                test.test(),
                new TestData<>(
                    batches.getOrThrow(GameTestEnvironments.DEFAULT_KEY),
                    Identifier.withDefaultNamespace("empty"),
                    test.maxTicks(), 1, false
                )
            )
        ));
    }
    
    public static void load() {
        createTests();
        GAME_TESTS.forEach(test -> Registry.register(BuiltInRegistries.TEST_FUNCTION, test.test, test.executable));
    }
    
    public static ResourceKey<GameTestInstance> createTestInstanceKey(String id) {
        return ResourceKey.create(Registries.TEST_INSTANCE, Identifier.fromNamespaceAndPath(ModularTools.MODID, id));
    }
    
    public static ResourceKey<Consumer<GameTestHelper>> createTestFunctionKey(String id) {
        return ResourceKey.create(Registries.TEST_FUNCTION, Identifier.fromNamespaceAndPath(ModularTools.MODID, id));
    }
    
    public record ToolsTest(ResourceKey<GameTestInstance> instance,
                            ResourceKey<Consumer<GameTestHelper>> test,
                            Consumer<GameTestHelper> executable,
                            int maxTicks) {}
}
