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
        BlockTradersMod.LOGGER.info("[blocktraders] Initializing client code");

        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.ANCIENT_SHELL, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.BOOK_OF_RITUALS, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.BUDDING_CACTUS, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.CRYSTAL_PHIAL, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.CULTIVATED_SAPLING, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.DRENCHED_SCORE_SHEET, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.ENCHANTED_RED_DELICIOUS, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.ENDLESS_BOOKSHELF, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.FINE_THREAD, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.FORGOTTEN_SCRAP_METAL, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.FRAGRANT_FLOWER, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.GALILEAN_SPYGLASS, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.MASTERCRAFTED_IRON, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.MIXOLOGY_STATION, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.OVERGROWN_CARROT, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.PRISMATIC_HONEYCOMB, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.PURE_GOLD, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.RADIATING_REDSTONE, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.ROTTING_RECYCLING_BIN, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.SCULPTING_CLAY, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.SHIMMERING_WHEAT, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.SOAKED_VILLAGER_PLUSHIE, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.SPARKLING_BLAZE_POWDER, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.UNUSUALLY_DENSE_ROCK, new VillagerConversionItemRenderer());
        BuiltinItemRendererRegistry.INSTANCE.register(ModItems.WAGYU_BEEF, new VillagerConversionItemRenderer());
    }
}
