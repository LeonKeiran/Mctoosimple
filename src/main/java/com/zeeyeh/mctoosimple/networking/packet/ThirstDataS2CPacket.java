package com.zeeyeh.mctoosimple.networking.packet;

import com.google.common.graph.Network;
import com.zeeyeh.mctoosimple.client.ThirstData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @author Leon_Keiran
 * @description
 * @date 2024/6/26 下午11:21
 * @github <a href="https://github.com/bmlingqi">https://github.com/bmlingqi</a>
 */
public class ThirstDataS2CPacket {
    private final int thirst;

    public ThirstDataS2CPacket(int thirst) {
        this.thirst = thirst;
    }

    public ThirstDataS2CPacket(FriendlyByteBuf buffer) {
        this.thirst = buffer.readInt();
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeInt(thirst);
    }

    public boolean handler(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ThirstData.setThirst(thirst);
        });
        return true;
    }
}
