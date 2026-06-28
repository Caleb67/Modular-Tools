package io.github.caleb67.modulartools.register;

import io.github.caleb67.modulartools.network.ClientboundForgeToolResultPayload;
import io.github.caleb67.modulartools.network.ServerboundForgeToolPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class Payloads {
    public static void load() {
        PayloadTypeRegistry.serverboundPlay()
                .register(ServerboundForgeToolPayload.TYPE, ServerboundForgeToolPayload.CODEC);
        PayloadTypeRegistry.clientboundPlay()
                .register(ClientboundForgeToolResultPayload.TYPE, ClientboundForgeToolResultPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(ServerboundForgeToolPayload.TYPE, (payload, context) -> {
            ServerboundForgeToolPayload.handle(payload, context.player(), context.player().level());
        });

        ClientPlayNetworking.registerGlobalReceiver(ClientboundForgeToolResultPayload.TYPE, (payload, context) -> {
            ClientboundForgeToolResultPayload.handle(payload, context.client().getSoundManager());
        });
    }
}
