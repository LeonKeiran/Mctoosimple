package com.zeeyeh.mctoosimple;

import com.mojang.logging.LogUtils;
import com.zeeyeh.mctoosimple.client.gui.ThirstGuiEventHandler;
import com.zeeyeh.mctoosimple.config.ThirstConfig;
import com.zeeyeh.mctoosimple.effect.EffectManager;
import com.zeeyeh.mctoosimple.networking.MTSNetworking;
import com.zeeyeh.mctoosimple.utils.ListenerUtil;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import java.util.stream.Collectors;

/**
 * 模组入口
 * @author Leon_Keiran
 * @date 2024/6/26 下午8:03
 * @github <a href="https://github.com/bmlingqi">https://github.com/bmlingqi</a>
 */
@Mod(GlobalConfig.MOD_ID)
public class Mctoosimple
{
    public static final Logger LOGGER = LogUtils.getLogger();

    public Mctoosimple()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ThirstConfig.COMMON_CONFIG);
        ListenerUtil.registerListener(this::setup);
        ListenerUtil.registerListener(this::setupClient);
        ListenerUtil.registerListener(this::enqueueIMC);
        ListenerUtil.registerListener(this::processIMC);
        ListenerUtil.registerListener(this::processIMC);
        EffectManager.register();
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setupClient(final FMLClientSetupEvent event) {
        ThirstGuiEventHandler.register();
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(MTSNetworking::register);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo("mctoosimple", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
        {
            LOGGER.info("HELLO from Register Block");
        }
    }
}
