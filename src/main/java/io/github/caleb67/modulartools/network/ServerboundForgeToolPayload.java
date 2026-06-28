package io.github.caleb67.modulartools.network;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.content.blocks.ForgeMenu;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public record ServerboundForgeToolPayload(int containerId) implements CustomPacketPayload {
    public static final Identifier FORGE_TOOL_PAYLOAD_ID = Identifier.fromNamespaceAndPath(ModularTools.MODID, "forge_tool");

    public static final CustomPacketPayload.Type<ServerboundForgeToolPayload> TYPE = new CustomPacketPayload.Type<>(FORGE_TOOL_PAYLOAD_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, ServerboundForgeToolPayload> CODEC = StreamCodec
            .composite(ByteBufCodecs.CONTAINER_ID, ServerboundForgeToolPayload::containerId, ServerboundForgeToolPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    // !TODO play sound effects
    public static void handle(ServerboundForgeToolPayload packet, ServerPlayer player, ServerLevel level) {
        if (player.containerMenu instanceof ForgeMenu fm && fm.containerId == packet.containerId()) {
            boolean result = fm.attemptForge(level);
            ClientboundForgeToolResultPayload payload = new ClientboundForgeToolResultPayload(result);
            ServerPlayNetworking.send(player, payload);
        }
    }
}
