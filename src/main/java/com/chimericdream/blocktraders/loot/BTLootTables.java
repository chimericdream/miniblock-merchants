package com.chimericdream.blocktraders.loot;

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
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            // Only modify built-in loot tables and leave data pack loot tables untouched by checking the source.
            if (!source.isBuiltin()) {
                return;
            }

            List<LootPool.Builder> poolBuilders = new ArrayList<>();

            if (CARROT_LOOT_TABLE_ID.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.OVERGROWN_CARROT, 512));
            }

            if (CLAY_LOOT_TABLE_ID.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.SCULPTING_CLAY, 256));
            }

            if (OAK_LEAVES_TABLE_ID.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.ENCHANTED_RED_DELICIOUS, 4096));
            }

            if (REDSTONE_ORE_TABLE_ID.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.RADIATING_REDSTONE, 256));
            }

            if (SPRUCE_LEAVES_TABLE_ID.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.CULTIVATED_SAPLING, 4096));
            }

            if (WHEAT_TABLE_ID.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.SHIMMERING_WHEAT, 512));
            }

            if (LootTables.FISHING_TREASURE_GAMEPLAY.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.FORGOTTEN_SCRAP_METAL, 24));
                poolBuilders.add(makeBuilder(ModItems.SOAKED_VILLAGER_PLUSHIE, 24));
                poolBuilders.add(makeBuilder(ModItems.DRENCHED_SCORE_SHEET, 24));
                poolBuilders.add(makeBuilder(ModItems.ROTTING_RECYCLING_BIN, 24));
                poolBuilders.add(makeBuilder(ModItems.CRYSTAL_PHIAL, 24));
                poolBuilders.add(makeBuilder(ModItems.MIXOLOGY_STATION, 24));
            }

            if (LootTables.VILLAGE_ARMORER_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.MASTERCRAFTED_IRON, 2));
            }

            if (LootTables.VILLAGE_BUTCHER_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.WAGYU_BEEF, 2));
            }

            if (LootTables.VILLAGE_SHEPARD_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.FINE_THREAD, 2));
            }

            if (LootTables.ABANDONED_MINESHAFT_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.UNUSUALLY_DENSE_ROCK, 12));
            }

            if (LootTables.DESERT_PYRAMID_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.BUDDING_CACTUS, 12));
            }

            if (LootTables.IGLOO_CHEST_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.GALILEAN_SPYGLASS, 1));
            }

            if (LootTables.JUNGLE_TEMPLE_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.PRISMATIC_HONEYCOMB, 1));
            }

            if (LootTables.PILLAGER_OUTPOST_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.FRAGRANT_FLOWER, 2));
            }

            if (LootTables.RUINED_PORTAL_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.SPARKLING_BLAZE_POWDER, 4));
            }

            if (LootTables.SIMPLE_DUNGEON_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.PURE_GOLD, 4));
            }

            if (LootTables.STRONGHOLD_LIBRARY_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.ENDLESS_BOOKSHELF, 2));
                poolBuilders.add(makeBuilder(ModItems.BOOK_OF_RITUALS, 2));
            }

            if (LootTables.UNDERWATER_RUIN_BIG_CHEST.equals(id)) {
                poolBuilders.add(makeBuilder(ModItems.ANCIENT_SHELL, 2));
            }

            for (LootPool.Builder builder : poolBuilders) {
                tableBuilder.pool(builder);
            }
        });
    }
}
