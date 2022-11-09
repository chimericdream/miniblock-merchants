package com.chimericdream.blocktraders.loot;

import com.chimericdream.blocktraders.config.BlockTradersConfig;
import com.chimericdream.blocktraders.config.ConfigManager;
import com.chimericdream.blocktraders.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class BTLootTables {
    private static final Identifier CARROT_LOOT_TABLE_ID = Blocks.CARROTS.getLootTableId();
    private static final Identifier CLAY_LOOT_TABLE_ID = Blocks.CLAY.getLootTableId();
    private static final Identifier OAK_LEAVES_TABLE_ID = Blocks.OAK_LEAVES.getLootTableId();
    private static final Identifier REDSTONE_ORE_TABLE_ID = Blocks.REDSTONE_ORE.getLootTableId();
    private static final Identifier SPRUCE_LEAVES_TABLE_ID = Blocks.SPRUCE_LEAVES.getLootTableId();
    private static final Identifier WHEAT_TABLE_ID = Blocks.WHEAT.getLootTableId();

    private static LootPool.Builder makeBuilder(Item item, int chance) {
        LootPool.Builder builder = LootPool.builder()
            .with(ItemEntry.builder(item));

        if (chance == 1) {
            return builder;
        }

        return builder.with(ItemEntry.builder(Items.AIR).weight(chance - 1));
    }

    public static void init() {
        BlockTradersConfig config = ConfigManager.getConfig();

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            // Only modify built-in loot tables and leave data pack loot tables untouched by checking the source.
            if (!source.isBuiltin()) {
                return;
            }

            List<LootPool.Builder> poolBuilders = new ArrayList<>();

            if (CARROT_LOOT_TABLE_ID.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.OVERGROWN_CARROT, config.overgrownCarrotChance));
            }

            if (CLAY_LOOT_TABLE_ID.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.SCULPTING_CLAY, config.sculptingClayChance));
            }

            if (OAK_LEAVES_TABLE_ID.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.ENCHANTED_RED_DELICIOUS, config.enchantedRedDeliciousChance));
            }

            if (REDSTONE_ORE_TABLE_ID.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.RADIATING_REDSTONE, config.radiatingRedstoneChance));
            }

            if (SPRUCE_LEAVES_TABLE_ID.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.CULTIVATED_SAPLING, config.cultivatedSaplingChance));
            }

            if (WHEAT_TABLE_ID.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.SHIMMERING_WHEAT, config.shimmeringWheatChance));
            }

            if (LootTables.FISHING_TREASURE_GAMEPLAY.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.FORGOTTEN_SCRAP_METAL, config.forgottenScrapMetalChance));
                poolBuilders.add(makeBuilder(ModItems.SOAKED_VILLAGER_PLUSHIE, config.soakedVillagerPlushieChance));
                poolBuilders.add(makeBuilder(ModItems.DRENCHED_SCORE_SHEET, config.drenchedScoreSheetChance));
                poolBuilders.add(makeBuilder(ModItems.ROTTING_RECYCLING_BIN, config.rottingRecyclingBinChance));
                poolBuilders.add(makeBuilder(ModItems.CRYSTAL_PHIAL, config.crystalPhialChance));
                poolBuilders.add(makeBuilder(ModItems.MIXOLOGY_STATION, config.mixologyStationChance));
            }

            if (LootTables.VILLAGE_ARMORER_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.MASTERCRAFTED_IRON,  config.mastercraftedIronChance));
            }

            if (LootTables.VILLAGE_BUTCHER_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.WAGYU_BEEF, config.wagyuBeefChance));
            }

            if (LootTables.VILLAGE_SHEPARD_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.FINE_THREAD, config.fineThreadChance));
            }

            if (LootTables.ABANDONED_MINESHAFT_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.UNUSUALLY_DENSE_ROCK, config.unusuallyDenseRockChance));
            }

            if (LootTables.DESERT_PYRAMID_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.BUDDING_CACTUS, config.buddingCactusChance));
            }

            if (LootTables.IGLOO_CHEST_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.GALILEAN_SPYGLASS, config.galileanSpyglassChance));
            }

            if (LootTables.JUNGLE_TEMPLE_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.PRISMATIC_HONEYCOMB, config.prismaticHoneycombChance));
            }

            if (LootTables.PILLAGER_OUTPOST_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.FRAGRANT_FLOWER, config.fragrantFlowerChance));
            }

            if (LootTables.RUINED_PORTAL_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.SPARKLING_BLAZE_POWDER, config.sparklingBlazePowderChance));
            }

            if (LootTables.SIMPLE_DUNGEON_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.PURE_GOLD, config.pureGoldChance));
            }

            if (LootTables.STRONGHOLD_LIBRARY_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.ENDLESS_BOOKSHELF, config.endlessBookshelfChance));
                poolBuilders.add(makeBuilder(ModItems.BOOK_OF_RITUALS, config.bookOfRitualsChance));
            }

            if (LootTables.UNDERWATER_RUIN_BIG_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.ANCIENT_SHELL, config.ancientShellChance));
            }

            for (LootPool.Builder builder : poolBuilders) {
                tableBuilder.pool(builder);
            }
        });
    }
}
