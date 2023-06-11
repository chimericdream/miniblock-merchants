package com.chimericdream.miniblockmerchants.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

import java.util.Objects;
import java.util.function.Consumer;

import static com.chimericdream.miniblockmerchants.config.MiniblockMerchantsConfig.*;

public class ConfigManager {
    private static ConfigHolder<MiniblockMerchantsConfig> holder;

    public static final Consumer<MiniblockMerchantsConfig> DEFAULT = (config) -> {
        config.reset = "Erase to reset";
        config.ancientShellChance = Defaults.ANCIENT_SHELL_CHANCE;
        config.bookOfRitualsChance = Defaults.BOOK_OF_RITUALS_CHANCE;
        config.buddingCactusChance = Defaults.BUDDING_CACTUS_CHANCE;
        config.crystalPhialChance = Defaults.CRYSTAL_PHIAL_CHANCE;
        config.cultivatedSaplingChance = Defaults.CULTIVATED_SAPLING_CHANCE;
        config.drenchedScoreSheetChance = Defaults.DRENCHED_SCORE_SHEET_CHANCE;
        config.enchantedRedDeliciousChance = Defaults.ENCHANTED_RED_DELICIOUS_CHANCE;
        config.endlessBookshelfChance = Defaults.ENDLESS_BOOKSHELF_CHANCE;
        config.fineThreadChance = Defaults.FINE_THREAD_CHANCE;
        config.forgottenScrapMetalChance = Defaults.FORGOTTEN_SCRAP_METAL_CHANCE;
        config.fragrantFlowerChance = Defaults.FRAGRANT_FLOWER_CHANCE;
        config.galileanSpyglassChance = Defaults.GALILEAN_SPYGLASS_CHANCE;
        config.mastercraftedIronChance = Defaults.MASTERCRAFTED_IRON_CHANCE;
        config.mixologyStationChance = Defaults.MIXOLOGY_STATION_CHANCE;
        config.overgrownCarrotChance = Defaults.OVERGROWN_CARROT_CHANCE;
        config.prismaticHoneycombChance = Defaults.PRISMATIC_HONEYCOMB_CHANCE;
        config.pureGoldChance = Defaults.PURE_GOLD_CHANCE;
        config.radiatingRedstoneChance = Defaults.RADIATING_REDSTONE_CHANCE;
        config.rottingRecyclingBinChance = Defaults.ROTTING_RECYCLING_BIN_CHANCE;
        config.sculptingClayChance = Defaults.SCULPTING_CLAY_CHANCE;
        config.shimmeringWheatChance = Defaults.SHIMMERING_WHEAT_CHANCE;
        config.soakedVillagerPlushieChance = Defaults.SOAKED_VILLAGER_PLUSHIE_CHANCE;
        config.sparklingBlazePowderChance = Defaults.SPARKLING_BLAZE_POWDER_CHANCE;
        config.stabilizedExplosionChance = Defaults.STABILIZED_EXPLOSION_CHANCE;
        config.unusuallyDenseRockChance = Defaults.UNUSUALLY_DENSE_ROCK_CHANCE;
        config.wagyuBeefChance = Defaults.WAGYU_BEEF_CHANCE;
    };

    public static void registerAutoConfig() {
        if (holder != null) {
            throw new IllegalStateException("Configuration already registered");
        }

        holder = AutoConfig.register(MiniblockMerchantsConfig.class, JanksonConfigSerializer::new);

        if (holder.getConfig().reset == null || Objects.equals(holder.getConfig().reset, "")) {
            DEFAULT.accept(holder.getConfig());
        }

        holder.save();
    }

    public static MiniblockMerchantsConfig getConfig() {
        if (holder == null) {
            return new MiniblockMerchantsConfig();
        }

        return holder.getConfig();
    }

    public static void load() {
        if (holder == null) {
            registerAutoConfig();
        }

        holder.load();
    }

    public static void save() {
        if (holder == null) {
            registerAutoConfig();
        }

        holder.save();
    }
}
