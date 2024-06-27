package com.zeeyeh.mctoosimple.api;

import com.zeeyeh.mctoosimple.client.ThirstData;
import com.zeeyeh.mctoosimple.config.ThirstConfig;
import com.zeeyeh.mctoosimple.networking.MTSNetworking;
import com.zeeyeh.mctoosimple.networking.packet.ThirstDataS2CPacket;

/**
 * @author Leon_Keiran
 * @description 口渴值帮助类
 * @date 2024/6/27 下午1:20
 * @github <a href="https://github.com/bmlingqi">https://github.com/bmlingqi</a>
 */
public class ThirstHelper {

    public static int getThirst(int amount) {
        return ThirstData.getThirst();
    }

    public static void addThirst(int amount) {
        int thirst = ThirstData.getThirst();
        thirst += amount;
        MTSNetworking.sendToServer(MTSNetworking.THIRST_CHANNEL, new ThirstDataS2CPacket(thirst));
    }

    public static void removeThirst(int amount) {
        int thirst = ThirstData.getThirst();
        thirst -= amount;
        MTSNetworking.sendToServer(MTSNetworking.THIRST_CHANNEL, new ThirstDataS2CPacket(thirst));
    }

    public static int getConfigMaxThirstValue() {
        return ThirstConfig.MAX_THIRST_VALUE.get();
    }

    public static int getConfigMilkValue() {
        return ThirstConfig.DRINK_MILK_VALUE.get();
    }

    public static int getConfigPotionValue() {
        return ThirstConfig.DRINK_POTION_VALUE.get();
    }

    public static int getConfigRemoveThirstValue() {
        return ThirstConfig.TICK_REMOVE_THIRST_VALUE.get();
    }

    public static float getConfigRemoveProbabilityValue() {
        return ThirstConfig.TICK_REMOVE_PROBABILITY_VALUE.get();
    }
}
