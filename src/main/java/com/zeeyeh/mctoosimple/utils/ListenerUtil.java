package com.zeeyeh.mctoosimple.utils;

import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.function.Consumer;

/**
 * @author Leon_Keiran
 * @description 事件工具类
 * @date 2024/6/26 下午9:05
 * @github <a href="https://github.com/bmlingqi">https://github.com/bmlingqi</a>
 */
public class ListenerUtil {

    public static <T extends Event> void registerListener(Consumer<T> listener) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(listener);
    }
}
