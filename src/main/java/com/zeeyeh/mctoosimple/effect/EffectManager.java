package com.zeeyeh.mctoosimple.effect;

import com.zeeyeh.mctoosimple.GlobalConfig;
import com.zeeyeh.mctoosimple.utils.ListenerUtil;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author Leon_Keiran
 * @description 脱水药水效果
 * @date 2024/6/27 上午11:03
 * @github <a href="https://github.com/bmlingqi">https://github.com/bmlingqi</a>
 */
public class EffectManager {
    public static DeferredRegister<MobEffect> POTIONS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, GlobalConfig.MOD_ID);
    public static final RegistryObject<MobEffect> DEHYDRATION = POTIONS.register("dehydration", () -> new DehydrationEffect(
            MobEffectCategory.HARMFUL,
            0x26B64C
    ));

    public static void register() {
        POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
