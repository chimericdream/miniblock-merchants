package com.chimericdream.blocktraders.config;

import com.chimericdream.blocktraders.BlockTradersMod;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "blocktraders")
@Config.Gui.Background("minecraft:textures/block/composter_side.png")
public class BlockTradersConfig implements ConfigData {
    public String reset = "";

    public int ancientShellChance = Defaults.ANCIENT_SHELL_CHANCE;
    public int bookOfRitualsChance = Defaults.BOOK_OF_RITUALS_CHANCE;
    public int buddingCactusChance = Defaults.BUDDING_CACTUS_CHANCE;
    public int crystalPhialChance = Defaults.CRYSTAL_PHIAL_CHANCE;
    public int cultivatedSaplingChance = Defaults.CULTIVATED_SAPLING_CHANCE;
    public int drenchedScoreSheetChance = Defaults.DRENCHED_SCORE_SHEET_CHANCE;
    public int enchantedRedDeliciousChance = Defaults.ENCHANTED_RED_DELICIOUS_CHANCE;
    public int endlessBookshelfChance = Defaults.ENDLESS_BOOKSHELF_CHANCE;
    public int fineThreadChance = Defaults.FINE_THREAD_CHANCE;
    public int forgottenScrapMetalChance = Defaults.FORGOTTEN_SCRAP_METAL_CHANCE;
    public int fragrantFlowerChance = Defaults.FRAGRANT_FLOWER_CHANCE;
    public int galileanSpyglassChance = Defaults.GALILEAN_SPYGLASS_CHANCE;
    public int mastercraftedIronChance = Defaults.MASTERCRAFTED_IRON_CHANCE;
    public int mixologyStationChance = Defaults.MIXOLOGY_STATION_CHANCE;
    public int overgrownCarrotChance = Defaults.OVERGROWN_CARROT_CHANCE;
    public int prismaticHoneycombChance = Defaults.PRISMATIC_HONEYCOMB_CHANCE;
    public int pureGoldChance = Defaults.PURE_GOLD_CHANCE;
    public int radiatingRedstoneChance = Defaults.RADIATING_REDSTONE_CHANCE;
    public int rottingRecyclingBinChance = Defaults.ROTTING_RECYCLING_BIN_CHANCE;
    public int sculptingClayChance = Defaults.SCULPTING_CLAY_CHANCE;
    public int shimmeringWheatChance = Defaults.SHIMMERING_WHEAT_CHANCE;
    public int soakedVillagerPlushieChance = Defaults.SOAKED_VILLAGER_PLUSHIE_CHANCE;
    public int sparklingBlazePowderChance = Defaults.SPARKLING_BLAZE_POWDER_CHANCE;
    public int unusuallyDenseRockChance = Defaults.UNUSUALLY_DENSE_ROCK_CHANCE;
    public int wagyuBeefChance = Defaults.WAGYU_BEEF_CHANCE;

    public void validatePostLoad() {
        if (this.ancientShellChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'ancientShellChance'! Resetting to default.");
            this.ancientShellChance = Defaults.ANCIENT_SHELL_CHANCE;
        }

        if (this.bookOfRitualsChance < 0) {
            this.bookOfRitualsChance = Defaults.BOOK_OF_RITUALS_CHANCE;
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'bookOfRitualsChance'! Resetting to default.");
        }

        if (this.buddingCactusChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'buddingCactusChance'! Resetting to default.");
            this.buddingCactusChance = Defaults.BUDDING_CACTUS_CHANCE;
        }

        if (this.crystalPhialChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'crystalPhialChance'! Resetting to default.");
            this.crystalPhialChance = Defaults.CRYSTAL_PHIAL_CHANCE;
        }

        if (this.cultivatedSaplingChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'cultivatedSaplingChance'! Resetting to default.");
            this.cultivatedSaplingChance = Defaults.CULTIVATED_SAPLING_CHANCE;
        }

        if (this.drenchedScoreSheetChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'drenchedScoreSheetChance'! Resetting to default.");
            this.drenchedScoreSheetChance = Defaults.DRENCHED_SCORE_SHEET_CHANCE;
        }

        if (this.enchantedRedDeliciousChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'enchantedRedDeliciousChance'! Resetting to default.");
            this.enchantedRedDeliciousChance = Defaults.ENCHANTED_RED_DELICIOUS_CHANCE;
        }

        if (this.endlessBookshelfChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'endlessBookshelfChance'! Resetting to default.");
            this.endlessBookshelfChance = Defaults.ENDLESS_BOOKSHELF_CHANCE;
        }

        if (this.fineThreadChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'fineThreadChance'! Resetting to default.");
            this.fineThreadChance = Defaults.FINE_THREAD_CHANCE;
        }

        if (this.forgottenScrapMetalChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'forgottenScrapMetalChance'! Resetting to default.");
            this.forgottenScrapMetalChance = Defaults.FORGOTTEN_SCRAP_METAL_CHANCE;
        }

        if (this.fragrantFlowerChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'fragrantFlowerChance'! Resetting to default.");
            this.fragrantFlowerChance = Defaults.FRAGRANT_FLOWER_CHANCE;
        }

        if (this.galileanSpyglassChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'galileanSpyglassChance'! Resetting to default.");
            this.galileanSpyglassChance = Defaults.GALILEAN_SPYGLASS_CHANCE;
        }

        if (this.mastercraftedIronChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'mastercraftedIronChance'! Resetting to default.");
            this.mastercraftedIronChance = Defaults.MASTERCRAFTED_IRON_CHANCE;
        }

        if (this.mixologyStationChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'mixologyStationChance'! Resetting to default.");
            this.mixologyStationChance = Defaults.MIXOLOGY_STATION_CHANCE;
        }

        if (this.overgrownCarrotChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'overgrownCarrotChance'! Resetting to default.");
            this.overgrownCarrotChance = Defaults.OVERGROWN_CARROT_CHANCE;
        }

        if (this.prismaticHoneycombChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'prismaticHoneycombChance'! Resetting to default.");
            this.prismaticHoneycombChance = Defaults.PRISMATIC_HONEYCOMB_CHANCE;
        }

        if (this.pureGoldChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'pureGoldChance'! Resetting to default.");
            this.pureGoldChance = Defaults.PURE_GOLD_CHANCE;
        }

        if (this.radiatingRedstoneChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'radiatingRedstoneChance'! Resetting to default.");
            this.radiatingRedstoneChance = Defaults.RADIATING_REDSTONE_CHANCE;
        }

        if (this.rottingRecyclingBinChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'rottingRecyclingBinChance'! Resetting to default.");
            this.rottingRecyclingBinChance = Defaults.ROTTING_RECYCLING_BIN_CHANCE;
        }

        if (this.sculptingClayChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'sculptingClayChance'! Resetting to default.");
            this.sculptingClayChance = Defaults.SCULPTING_CLAY_CHANCE;
        }

        if (this.shimmeringWheatChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'shimmeringWheatChance'! Resetting to default.");
            this.shimmeringWheatChance = Defaults.SHIMMERING_WHEAT_CHANCE;
        }

        if (this.soakedVillagerPlushieChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'soakedVillagerPlushieChance'! Resetting to default.");
            this.soakedVillagerPlushieChance = Defaults.SOAKED_VILLAGER_PLUSHIE_CHANCE;
        }

        if (this.sparklingBlazePowderChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'sparklingBlazePowderChance'! Resetting to default.");
            this.sparklingBlazePowderChance = Defaults.SPARKLING_BLAZE_POWDER_CHANCE;
        }

        if (this.unusuallyDenseRockChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'unusuallyDenseRockChance'! Resetting to default.");
            this.unusuallyDenseRockChance = Defaults.UNUSUALLY_DENSE_ROCK_CHANCE;
        }

        if (this.wagyuBeefChance < 0) {
            BlockTradersMod.LOGGER.info("[config] Invalid value found for 'wagyuBeefChance'! Resetting to default.");
            this.wagyuBeefChance = Defaults.WAGYU_BEEF_CHANCE;
        }
    }

    public static class Defaults {
        public static int ANCIENT_SHELL_CHANCE = 2;
        public static int BOOK_OF_RITUALS_CHANCE = 2;
        public static int BUDDING_CACTUS_CHANCE = 12;
        public static int CRYSTAL_PHIAL_CHANCE = 24;
        public static int CULTIVATED_SAPLING_CHANCE = 4096;
        public static int DRENCHED_SCORE_SHEET_CHANCE = 24;
        public static int ENCHANTED_RED_DELICIOUS_CHANCE = 4096;
        public static int ENDLESS_BOOKSHELF_CHANCE = 2;
        public static int FINE_THREAD_CHANCE = 2;
        public static int FORGOTTEN_SCRAP_METAL_CHANCE = 24;
        public static int FRAGRANT_FLOWER_CHANCE = 2;
        public static int GALILEAN_SPYGLASS_CHANCE = 1;
        public static int MASTERCRAFTED_IRON_CHANCE = 2;
        public static int MIXOLOGY_STATION_CHANCE = 24;
        public static int OVERGROWN_CARROT_CHANCE = 512;
        public static int PRISMATIC_HONEYCOMB_CHANCE = 1;
        public static int PURE_GOLD_CHANCE = 4;
        public static int RADIATING_REDSTONE_CHANCE = 256;
        public static int ROTTING_RECYCLING_BIN_CHANCE = 24;
        public static int SCULPTING_CLAY_CHANCE = 256;
        public static int SHIMMERING_WHEAT_CHANCE = 512;
        public static int SOAKED_VILLAGER_PLUSHIE_CHANCE = 24;
        public static int SPARKLING_BLAZE_POWDER_CHANCE = 4;
        public static int UNUSUALLY_DENSE_ROCK_CHANCE = 12;
        public static int WAGYU_BEEF_CHANCE = 2;
    }
}
