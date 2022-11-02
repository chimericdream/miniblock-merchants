package com.chimericdream.blocktraders;

import com.chimericdream.blocktraders.client.render.VillagerConversionItemRenderer;
import com.chimericdream.blocktraders.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;

@Environment(EnvType.CLIENT)
public class BlockTradersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.SOAKED_VILLAGER_PLUSHIE, new VillagerConversionItemRenderer());
//        LOGGER.info("[minekea] Initializing client code");
//
//        Keybindings.initialize();
//
//        ITEMS.initializeClient();
//
//        FluidClientHandlers.setupHandlers();
//
//        for (MinekeaBlockCategory category : BLOCK_CATEGORIES) {
//            category.initializeClient();
//        }
//
//        for (ModCompatLayer mod : MinekeaMod.OTHER_MODS) {
//            mod.initializeClient();
//        }
    }
}
