package com.zeeyeh.mctoosimple.event;

import com.zeeyeh.mctoosimple.GlobalConfig;
import com.zeeyeh.mctoosimple.client.ThirstData;
import com.zeeyeh.mctoosimple.config.ThirstConfig;
import com.zeeyeh.mctoosimple.networking.MTSNetworking;
import com.zeeyeh.mctoosimple.networking.packet.ThirstDataS2CPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

/**
 * @author Leon_Keiran
 * @description 玩家事件
 * @date 2024/6/26 下午11:32
 * @github <a href="https://github.com/bmlingqi">https://github.com/bmlingqi</a>
 */
@Mod.EventBusSubscriber(modid = GlobalConfig.MOD_ID)
public class MTSPlayerEvent {

    @SubscribeEvent
    public static void onPlayerLoad(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getPlayer();
        CompoundTag nbt = player.getPersistentData();
        ThirstData.setThirst(nbt.getInt(GlobalConfig.MOD_ID + "_thirst"));
    }

    @SubscribeEvent
    public static void onPlayerSave(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getPlayer();
        CompoundTag nbt = player.getPersistentData();
        nbt.putInt(GlobalConfig.MOD_ID + "_thirst", ThirstData.getThirst());
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side != LogicalSide.SERVER) {
            return;
        }
        int thirst = ThirstData.getThirst();
        // 口渴值较低给予缓慢移动
        if (thirst == 0) {
            event.player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 5, true, true));
        } else if (thirst <= 5) {
            event.player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 4, true, true));
        } else if (thirst <= 10) {
            event.player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 3, true, true));
        } else if (thirst <= 15) {
            event.player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 2, true, true));
        } else if (thirst <= 20) {
            event.player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 5, 1, true, true));
        }
//        if (thirst > 0 && new Random().nextFloat() < 0.002f) {
        if (thirst > 0 && new Random().nextFloat() < ThirstConfig.TICK_REMOVE_PROBABILITY_VALUE.get()) {
            thirst -= ThirstConfig.TICK_REMOVE_THIRST_VALUE.get();
            MTSNetworking.sendToClient(MTSNetworking.THIRST_CHANNEL, new ThirstDataS2CPacket(thirst), (ServerPlayer) event.player);
        }
    }
}
