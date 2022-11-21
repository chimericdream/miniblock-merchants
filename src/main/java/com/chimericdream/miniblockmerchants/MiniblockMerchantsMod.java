package com.chimericdream.miniblockmerchants;

import com.chimericdream.miniblockmerchants.config.ConfigManager;
import com.chimericdream.miniblockmerchants.item.ModItems;
import com.chimericdream.miniblockmerchants.loot.MMLootTables;
import com.chimericdream.miniblockmerchants.registry.MMProfessions;
import com.chimericdream.miniblockmerchants.registry.MMStats;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ModInfo.MOD_ID)
public class MiniblockMerchantsMod {
    public static final Logger LOGGER = LogUtils.getLogger();

    public MiniblockMerchantsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ConfigManager.registerAutoConfig();
        modEventBus.addListener(this::commonSetup);

        ModItems.init(modEventBus);

		MMProfessions.init(modEventBus);
		MMProfessions.populateTrades();

        MMStats.init(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, LootTableLoadEvent.class, MMLootTables::listen);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = ModInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MiniblockMerchantsClient.init(Minecraft.getInstance());
        }
    }

//	static {
//		ConfigManager.registerAutoConfig();
//	}
//
//	@Override
//	public void onInitialize() {
//		MMProfessions.init();
//		MMProfessions.populateTrades();
//
//		MMLootTables.init();
//		MMStats.init();
//	}
}
