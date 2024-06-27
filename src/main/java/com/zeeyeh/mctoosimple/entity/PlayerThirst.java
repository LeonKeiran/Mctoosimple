package com.zeeyeh.mctoosimple.entity;

import com.zeeyeh.mctoosimple.config.ThirstConfig;
import net.minecraft.nbt.CompoundTag;

/**
 * @author Leon_Keiran
 * @description
 * @date 2024/6/26 下午11:36
 * @github <a href="https://github.com/bmlingqi">https://github.com/bmlingqi</a>
 */
public class PlayerThirst {
    private int thirst;
    public static final int MIN = 0;

    public int getThirst() {
        return thirst;
    }

    public void addThirst(int amount) {
        this.thirst = Math.min(thirst + amount, ThirstConfig.MAX_THIRST_VALUE.get());
    }

    public void removeThirst(int amount) {
        this.thirst = Math.max(thirst - amount, MIN);
    }

    public void of(PlayerThirst source) {
        this.thirst = source.thirst;
    }

    public void save(CompoundTag nbt) {
        nbt.putInt("thirst", thirst);
    }

    public void of(CompoundTag nbt) {
        thirst = nbt.getInt("thirst");
    }
}
