package com.chimericdream.miniblockmerchants.loot;

import com.chimericdream.miniblockmerchants.config.MiniblockMerchantsConfig;
import com.chimericdream.miniblockmerchants.config.ConfigManager;
import com.chimericdream.miniblockmerchants.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class MMLootTables {
    private static final Identifier CARROT_LOOT_TABLE_ID = Blocks.CARROTS.getLootTableId();
    private static final Identifier CLAY_LOOT_TABLE_ID = Blocks.CLAY.getLootTableId();
    private static final Identifier CREEPER_LOOT_TABLE_ID = EntityType.CREEPER.getLootTableId();
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

    private static void checkVanillaLootTables(Identifier id, List<LootPool.Builder> poolBuilders, MiniblockMerchantsConfig config) {
        if (config.overgrownCarrotChance > 0 && CARROT_LOOT_TABLE_ID.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.OVERGROWN_CARROT, config.overgrownCarrotChance));
        }

        if (config.sculptingClayChance > 0 && CLAY_LOOT_TABLE_ID.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.SCULPTING_CLAY, config.sculptingClayChance));
        }

        if (config.enchantedRedDeliciousChance > 0 && OAK_LEAVES_TABLE_ID.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.ENCHANTED_RED_DELICIOUS, config.enchantedRedDeliciousChance));
        }

        if (config.radiatingRedstoneChance > 0 && REDSTONE_ORE_TABLE_ID.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.RADIATING_REDSTONE, config.radiatingRedstoneChance));
        }

        if (config.cultivatedSaplingChance > 0 && SPRUCE_LEAVES_TABLE_ID.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.CULTIVATED_SAPLING, config.cultivatedSaplingChance));
        }

        if (config.shimmeringWheatChance > 0 && WHEAT_TABLE_ID.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.SHIMMERING_WHEAT, config.shimmeringWheatChance));
        }

        if (LootTables.FISHING_TREASURE_GAMEPLAY.equals(id)) {
            if (config.forgottenScrapMetalChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.FORGOTTEN_SCRAP_METAL, config.forgottenScrapMetalChance));
            }

            if (config.soakedVillagerPlushieChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.SOAKED_VILLAGER_PLUSHIE, config.soakedVillagerPlushieChance));
            }

            if (config.drenchedScoreSheetChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.DRENCHED_SCORE_SHEET, config.drenchedScoreSheetChance));
            }

            if (config.rottingRecyclingBinChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.ROTTING_RECYCLING_BIN, config.rottingRecyclingBinChance));
            }

            if (config.crystalPhialChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.CRYSTAL_PHIAL, config.crystalPhialChance));
            }

            if (config.mixologyStationChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.MIXOLOGY_STATION, config.mixologyStationChance));
            }
        }

        if (config.mastercraftedIronChance > 0 && LootTables.VILLAGE_ARMORER_CHEST.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.MASTERCRAFTED_IRON, config.mastercraftedIronChance));
        }

        if (config.wagyuBeefChance > 0 && LootTables.VILLAGE_BUTCHER_CHEST.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.WAGYU_BEEF, config.wagyuBeefChance));
        }

        if (config.fineThreadChance > 0 && LootTables.VILLAGE_SHEPARD_CHEST.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.FINE_THREAD, config.fineThreadChance));
        }

        if (config.unusuallyDenseRockChance > 0 && LootTables.ABANDONED_MINESHAFT_CHEST.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.UNUSUALLY_DENSE_ROCK, config.unusuallyDenseRockChance));
        }

        if (config.buddingCactusChance > 0 && LootTables.DESERT_PYRAMID_CHEST.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.BUDDING_CACTUS, config.buddingCactusChance));
        }

        if (config.galileanSpyglassChance > 0 && LootTables.IGLOO_CHEST_CHEST.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.GALILEAN_SPYGLASS, config.galileanSpyglassChance));
        }

        if (config.prismaticHoneycombChance > 0 && LootTables.JUNGLE_TEMPLE_CHEST.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.PRISMATIC_HONEYCOMB, config.prismaticHoneycombChance));
        }

        if (config.fragrantFlowerChance > 0 && LootTables.PILLAGER_OUTPOST_CHEST.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.FRAGRANT_FLOWER, config.fragrantFlowerChance));
        }

        if (config.sparklingBlazePowderChance > 0 && LootTables.RUINED_PORTAL_CHEST.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.SPARKLING_BLAZE_POWDER, config.sparklingBlazePowderChance));
        }

        if (config.pureGoldChance > 0 && LootTables.SIMPLE_DUNGEON_CHEST.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.PURE_GOLD, config.pureGoldChance));
        }

        if (LootTables.STRONGHOLD_LIBRARY_CHEST.equals(id)) {
            if (config.endlessBookshelfChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.ENDLESS_BOOKSHELF, config.endlessBookshelfChance));
            }

            if (config.bookOfRitualsChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.BOOK_OF_RITUALS, config.bookOfRitualsChance));
            }
        }

        if (config.ancientShellChance > 0 && LootTables.UNDERWATER_RUIN_BIG_CHEST.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.ANCIENT_SHELL, config.ancientShellChance));
        }

        if (CREEPER_LOOT_TABLE_ID.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.STABILIZED_EXPLOSION, config.stabilizedExplosionChance));
        }
    }

    public static void init() {
        MiniblockMerchantsConfig config = ConfigManager.getConfig();

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            // Only modify built-in loot tables and leave data pack loot tables untouched by checking the source.
            if (!source.isBuiltin()) {
                return;
            }

            List<LootPool.Builder> poolBuilders = new ArrayList<>();

            checkVanillaLootTables(id, poolBuilders, config);

            for (LootPool.Builder builder : poolBuilders) {
                tableBuilder.pool(builder);
            }
        });
    }
}
