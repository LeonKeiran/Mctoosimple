package com.zeeyeh.mctoosimple.networking;

import com.zeeyeh.mctoosimple.GlobalConfig;
import com.zeeyeh.mctoosimple.networking.packet.ThirstDataS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * @author Leon_Keiran
 * @description
 * @date 2024/6/26 下午11:18
 * @github <a href="https://github.com/bmlingqi">https://github.com/bmlingqi</a>
 */
public class MTSNetworking {
    public static SimpleChannel THIRST_CHANNEL;
    public static final String VERSION = "1.0";
    public static void register() {
        THIRST_CHANNEL = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(GlobalConfig.MOD_ID, "thirst_channel"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        THIRST_CHANNEL.messageBuilder(ThirstDataS2CPacket.class, 0)
                .encoder(ThirstDataS2CPacket::toBytes)
                .decoder(ThirstDataS2CPacket::new)
                .consumer(ThirstDataS2CPacket::handler)
                .add();
    }

    public static <M> void sendToServer(SimpleChannel channel, M message) {
        channel.sendToServer(message);
    }

    public static <M> void sendToClient(SimpleChannel channel, M message, ServerPlayer player) {
        channel.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <M> void sendToClients(SimpleChannel channel, M message, ServerPlayer player) {
        channel.send(PacketDistributor.ALL.noArg(), message);
    }
}
