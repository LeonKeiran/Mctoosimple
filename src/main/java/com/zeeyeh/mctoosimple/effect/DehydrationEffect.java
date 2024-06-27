package com.zeeyeh.mctoosimple.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author Leon_Keiran
 * @description 脱水效果
 * @date 2024/6/27 下午12:19
 * @github <a href="https://github.com/bmlingqi">https://github.com/bmlingqi</a>
 */
public class DehydrationEffect extends MobEffect {
    protected DehydrationEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        pAmplifier++;
        Random random = new Random();
        int i = random.nextInt();
        if (pLivingEntity instanceof Player player) {
            player.setSpeed(player.getSpeed() - 0.01F * i);
            player.hurt(DamageSource.MAGIC, 1.0F);

        }
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }
}
