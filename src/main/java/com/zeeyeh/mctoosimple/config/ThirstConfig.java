package com.zeeyeh.mctoosimple.config;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * @author Leon_Keiran
 * @description 口渴值配置
 * @date 2024/6/27 下午1:23
 * @github <a href="https://github.com/bmlingqi">https://github.com/bmlingqi</a>
 */
public class ThirstConfig {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.ConfigValue<Integer> MAX_THIRST_VALUE;
    public static ForgeConfigSpec.ConfigValue<Integer> DRINK_MILK_VALUE;
    public static ForgeConfigSpec.ConfigValue<Integer> DRINK_POTION_VALUE;
    public static ForgeConfigSpec.ConfigValue<Float> TICK_REMOVE_PROBABILITY_VALUE;
    public static ForgeConfigSpec.ConfigValue<Integer> TICK_REMOVE_THIRST_VALUE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("General settings")
                .push("general");
        MAX_THIRST_VALUE = builder.comment("Maximum thirst value")
                        .define("max_thirst", 40);
        DRINK_MILK_VALUE = builder.comment("How much does drinking milk increase thirst")
                        .define("milk_value", 1);
        DRINK_POTION_VALUE = builder.comment("Drink water bottle to increase the amount of thirst")
                        .define("potion_value", 2);
        TICK_REMOVE_PROBABILITY_VALUE = builder.comment("Drink water bottle to increase the amount of thirst")
                        .define("potion_value", 0.002f);
        TICK_REMOVE_THIRST_VALUE = builder.comment("How many thirst points are deducted from each tick, if appropriate")
                        .define("remove_thirst_value", 1);
        builder.pop();
        COMMON_CONFIG = builder.build();
    }
}
