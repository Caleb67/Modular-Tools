package io.github.caleb67.modulartools.network;

import io.github.caleb67.modulartools.ModularTools;
import io.github.caleb67.modulartools.client.ForgeHammerButton;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ClientboundForgeToolResultPayload(boolean result) implements CustomPacketPayload {
    public static final Identifier FORGE_TOOL_RESULT_PAYLOAD_ID = Identifier.fromNamespaceAndPath(ModularTools.MODID, "forge_tool_result");

    public static final CustomPacketPayload.Type<ClientboundForgeToolResultPayload> TYPE = new CustomPacketPayload.Type<>(FORGE_TOOL_RESULT_PAYLOAD_ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundForgeToolResultPayload> CODEC = StreamCodec
            .composite(ByteBufCodecs.BOOL, ClientboundForgeToolResultPayload::result, ClientboundForgeToolResultPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    // !TODO play sound effects
    public static void handle(ClientboundForgeToolResultPayload packet, SoundManager manager) {
        if (packet.result()) ForgeHammerButton.playHammeringSound(manager);
        else ForgeHammerButton.playFailHammeringSound(manager);
    }
}