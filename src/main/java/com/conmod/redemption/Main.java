package com.conmod.redemption;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
// import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.conlib.config.Config;

// import java.util.stream.Collectors;

import com.conlib.registry.ModRegister;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("redemption")
public class Main
{
    // Directly reference a log4j logger.
    public static final Logger LOG = LogManager.getLogger();
    public static String MOD_ID = "redemption";

    public Main() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.CONFIG, MOD_ID + ".toml");
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        Mod_Config.Init();
        Config.loadConfig(Config.CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + ".toml").toString());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {      
        // some preinit code
        // LOG.info("HELLO FROM PREINIT");
        // LOG.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

        // try {
        //     LOG.info("SENDING INFO.");
        //     Client.Send("TEST STRING.");
        // }
        // catch (Exception e) {
        //     LOG.warn("################");
        //     LOG.warn(e);
        // }
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        // LOG.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        // InterModComms.sendTo("redemption", "helloworld", () -> { LOG.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        // LOG.info("Got IMC {}", event.getIMCStream().
        //         map(m->m.getMessageSupplier().get()).
        //         collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        // LOG.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOG.info("####################################");

            ModRegister.Init();
            
            ModBlocks.Init();
            ModItems.Init();
        }
    }
}
