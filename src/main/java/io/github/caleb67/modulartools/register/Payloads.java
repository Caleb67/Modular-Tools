package io.github.caleb67.modulartools.register;

import io.github.caleb67.modulartools.network.ServerboundForgeToolPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class Payloads {
    public static void load() {
        PayloadTypeRegistry.serverboundPlay()
                .register(ServerboundForgeToolPayload.TYPE, ServerboundForgeToolPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(ServerboundForgeToolPayload.TYPE, (payload, context) -> {
            ServerboundForgeToolPayload.handle(payload, context.player(), context.player().level());
        });
    }
}
