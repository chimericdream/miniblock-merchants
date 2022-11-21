package com.chimericdream.miniblockmerchants.loot;

import com.chimericdream.miniblockmerchants.config.MiniblockMerchantsConfig;
import com.chimericdream.miniblockmerchants.config.ConfigManager;
import com.chimericdream.miniblockmerchants.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraftforge.event.LootTableLoadEvent;

import java.util.ArrayList;
import java.util.List;

public class MMLootTables {
    private static final ResourceLocation CARROT_LOOT_TABLE_ID = Blocks.CARROTS.getLootTable();
    private static final ResourceLocation CLAY_LOOT_TABLE_ID = Blocks.CLAY.getLootTable();
    private static final ResourceLocation OAK_LEAVES_TABLE_ID = Blocks.OAK_LEAVES.getLootTable();
    private static final ResourceLocation REDSTONE_ORE_TABLE_ID = Blocks.REDSTONE_ORE.getLootTable();
    private static final ResourceLocation SPRUCE_LEAVES_TABLE_ID = Blocks.SPRUCE_LEAVES.getLootTable();
    private static final ResourceLocation WHEAT_TABLE_ID = Blocks.WHEAT.getLootTable();

    private static LootPool.Builder makeBuilder(Item item, int chance) {
        LootPool.Builder builder = LootPool.lootPool()
            .add(LootItem.lootTableItem(item));

        if (chance == 1) {
            return builder;
        }

        return builder.add(LootItem.lootTableItem(Items.AIR).setWeight(chance - 1));
    }

    private static void checkVanillaLootTables(ResourceLocation id, List<LootPool.Builder> poolBuilders, MiniblockMerchantsConfig config) {
        if (config.overgrownCarrotChance > 0 && CARROT_LOOT_TABLE_ID.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.OVERGROWN_CARROT.get(), config.overgrownCarrotChance));
        }

        if (config.sculptingClayChance > 0 && CLAY_LOOT_TABLE_ID.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.SCULPTING_CLAY.get(), config.sculptingClayChance));
        }

        if (config.enchantedRedDeliciousChance > 0 && OAK_LEAVES_TABLE_ID.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.ENCHANTED_RED_DELICIOUS.get(), config.enchantedRedDeliciousChance));
        }

        if (config.radiatingRedstoneChance > 0 && REDSTONE_ORE_TABLE_ID.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.RADIATING_REDSTONE.get(), config.radiatingRedstoneChance));
        }

        if (config.cultivatedSaplingChance > 0 && SPRUCE_LEAVES_TABLE_ID.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.CULTIVATED_SAPLING.get(), config.cultivatedSaplingChance));
        }

        if (config.shimmeringWheatChance > 0 && WHEAT_TABLE_ID.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.SHIMMERING_WHEAT.get(), config.shimmeringWheatChance));
        }

        if (BuiltInLootTables.FISHING_TREASURE.equals(id)) {
            if (config.forgottenScrapMetalChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.FORGOTTEN_SCRAP_METAL.get(), config.forgottenScrapMetalChance));
            }

            if (config.soakedVillagerPlushieChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.SOAKED_VILLAGER_PLUSHIE.get(), config.soakedVillagerPlushieChance));
            }

            if (config.drenchedScoreSheetChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.DRENCHED_SCORE_SHEET.get(), config.drenchedScoreSheetChance));
            }

            if (config.rottingRecyclingBinChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.ROTTING_RECYCLING_BIN.get(), config.rottingRecyclingBinChance));
            }

            if (config.crystalPhialChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.CRYSTAL_PHIAL.get(), config.crystalPhialChance));
            }

            if (config.mixologyStationChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.MIXOLOGY_STATION.get(), config.mixologyStationChance));
            }
        }

        if (config.mastercraftedIronChance > 0 && BuiltInLootTables.VILLAGE_ARMORER.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.MASTERCRAFTED_IRON.get(), config.mastercraftedIronChance));
        }

        if (config.wagyuBeefChance > 0 && BuiltInLootTables.VILLAGE_BUTCHER.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.WAGYU_BEEF.get(), config.wagyuBeefChance));
        }

        if (config.fineThreadChance > 0 && BuiltInLootTables.VILLAGE_SHEPHERD.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.FINE_THREAD.get(), config.fineThreadChance));
        }

        if (config.unusuallyDenseRockChance > 0 && BuiltInLootTables.ABANDONED_MINESHAFT.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.UNUSUALLY_DENSE_ROCK.get(), config.unusuallyDenseRockChance));
        }

        if (config.buddingCactusChance > 0 && BuiltInLootTables.DESERT_PYRAMID.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.BUDDING_CACTUS.get(), config.buddingCactusChance));
        }

        if (config.galileanSpyglassChance > 0 && BuiltInLootTables.IGLOO_CHEST.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.GALILEAN_SPYGLASS.get(), config.galileanSpyglassChance));
        }

        if (config.prismaticHoneycombChance > 0 && BuiltInLootTables.JUNGLE_TEMPLE.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.PRISMATIC_HONEYCOMB.get(), config.prismaticHoneycombChance));
        }

        if (config.fragrantFlowerChance > 0 && BuiltInLootTables.PILLAGER_OUTPOST.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.FRAGRANT_FLOWER.get(), config.fragrantFlowerChance));
        }

        if (config.sparklingBlazePowderChance > 0 && BuiltInLootTables.RUINED_PORTAL.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.SPARKLING_BLAZE_POWDER.get(), config.sparklingBlazePowderChance));
        }

        if (config.pureGoldChance > 0 && BuiltInLootTables.SIMPLE_DUNGEON.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.PURE_GOLD.get(), config.pureGoldChance));
        }

        if (BuiltInLootTables.STRONGHOLD_LIBRARY.equals(id)) {
            if (config.endlessBookshelfChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.ENDLESS_BOOKSHELF.get(), config.endlessBookshelfChance));
            }

            if (config.bookOfRitualsChance > 0) {
                poolBuilders.add(makeBuilder(ModItems.BOOK_OF_RITUALS.get(), config.bookOfRitualsChance));
            }
        }

        if (config.ancientShellChance > 0 && BuiltInLootTables.UNDERWATER_RUIN_BIG.equals(id)) {
            poolBuilders.add(makeBuilder(ModItems.ANCIENT_SHELL.get(), config.ancientShellChance));
        }
    }

    public static void listen(LootTableLoadEvent event) {
        MiniblockMerchantsConfig config = ConfigManager.getConfig();

        LootTable table = event.getTable();
        ResourceLocation tableId = table.getLootTableId();

        List<LootPool.Builder> poolBuilders = new ArrayList<>();

        checkVanillaLootTables(tableId, poolBuilders, config);

        for (LootPool.Builder builder : poolBuilders) {
            table.addPool(builder.build());
        }

        event.setTable(table);
    }
}
