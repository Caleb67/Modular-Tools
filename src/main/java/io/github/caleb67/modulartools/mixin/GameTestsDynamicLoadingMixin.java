package io.github.caleb67.modulartools.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.caleb67.modulartools.gametest.GameTests;
import net.fabricmc.fabric.impl.gametest.FabricGameTestModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.gametest.framework.GameTestInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FabricGameTestModInitializer.class)
public class GameTestsDynamicLoadingMixin {
    @Inject(method = "registerDynamicEntries", at = @At(value = "TAIL"))
    private static void registerTestInstances(CallbackInfo ci,
                                              @Local(name = "testInstances") Registry<GameTestInstance> testInstances) {
        GameTests.bootstrapInstances(testInstances);
    }
}
