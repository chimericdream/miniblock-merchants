package com.chimericdream.miniblockmerchants.item;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final VillagerConversionItem ANCIENT_SHELL = new AncientShellItem();
    public static final VillagerConversionItem BOOK_OF_RITUALS = new BookOfRitualsItem();
    public static final VillagerConversionItem BUDDING_CACTUS = new BuddingCactusItem();
    public static final VillagerConversionItem CRYSTAL_PHIAL = new CrystalPhialItem();
    public static final VillagerConversionItem CULTIVATED_SAPLING = new CultivatedSaplingItem();
    public static final VillagerConversionItem DRENCHED_SCORE_SHEET = new DrenchedScoreSheetItem();
    public static final VillagerConversionItem ENCHANTED_RED_DELICIOUS = new EnchantedRedDeliciousItem();
    public static final VillagerConversionItem ENDLESS_BOOKSHELF = new EndlessBookshelfItem();
    public static final VillagerConversionItem FINE_THREAD = new FineThreadItem();
    public static final VillagerConversionItem FORGOTTEN_SCRAP_METAL = new ForgottenScrapMetalItem();
    public static final VillagerConversionItem FRAGRANT_FLOWER = new FragrantFlowerItem();
    public static final VillagerConversionItem GALILEAN_SPYGLASS = new GalileanSpyglassItem();
    public static final VillagerConversionItem MASTERCRAFTED_IRON = new MastercraftedIronItem();
    public static final VillagerConversionItem MIXOLOGY_STATION = new MixologyStationItem();
    public static final VillagerConversionItem OVERGROWN_CARROT = new OvergrownCarrotItem();
    public static final VillagerConversionItem PRISMATIC_HONEYCOMB = new PrismaticHoneycombItem();
    public static final VillagerConversionItem PURE_GOLD = new PureGoldItem();
    public static final VillagerConversionItem RADIATING_REDSTONE = new RadiatingRedstoneItem();
    public static final VillagerConversionItem ROTTING_RECYCLING_BIN = new RottingRecyclingBinItem();
    public static final VillagerConversionItem SCULPTING_CLAY = new SculptingClayItem();
    public static final VillagerConversionItem SHIMMERING_WHEAT = new ShimmeringWheatItem();
    public static final VillagerConversionItem SOAKED_VILLAGER_PLUSHIE = new SoakedVillagerPlushieItem();
    public static final VillagerConversionItem SPARKLING_BLAZE_POWDER = new SparklingBlazePowderItem();
    public static final VillagerConversionItem UNUSUALLY_DENSE_ROCK = new UnusuallyDenseRockItem();
    public static final VillagerConversionItem WAGYU_BEEF = new WagyuBeefItem();

    public void init() {
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", AncientShellItem.ID), ANCIENT_SHELL);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", BookOfRitualsItem.ID), BOOK_OF_RITUALS);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", BuddingCactusItem.ID), BUDDING_CACTUS);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", CrystalPhialItem.ID), CRYSTAL_PHIAL);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", CultivatedSaplingItem.ID), CULTIVATED_SAPLING);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", DrenchedScoreSheetItem.ID), DRENCHED_SCORE_SHEET);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", EnchantedRedDeliciousItem.ID), ENCHANTED_RED_DELICIOUS);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", EndlessBookshelfItem.ID), ENDLESS_BOOKSHELF);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", FineThreadItem.ID), FINE_THREAD);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", ForgottenScrapMetalItem.ID), FORGOTTEN_SCRAP_METAL);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", FragrantFlowerItem.ID), FRAGRANT_FLOWER);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", GalileanSpyglassItem.ID), GALILEAN_SPYGLASS);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", MastercraftedIronItem.ID), MASTERCRAFTED_IRON);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", MixologyStationItem.ID), MIXOLOGY_STATION);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", OvergrownCarrotItem.ID), OVERGROWN_CARROT);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", PrismaticHoneycombItem.ID), PRISMATIC_HONEYCOMB);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", PureGoldItem.ID), PURE_GOLD);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", RadiatingRedstoneItem.ID), RADIATING_REDSTONE);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", RottingRecyclingBinItem.ID), ROTTING_RECYCLING_BIN);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", SculptingClayItem.ID), SCULPTING_CLAY);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", ShimmeringWheatItem.ID), SHIMMERING_WHEAT);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", SoakedVillagerPlushieItem.ID), SOAKED_VILLAGER_PLUSHIE);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", SparklingBlazePowderItem.ID), SPARKLING_BLAZE_POWDER);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", UnusuallyDenseRockItem.ID), UNUSUALLY_DENSE_ROCK);
        Registry.register(Registry.ITEM, new Identifier("miniblockmerchants", WagyuBeefItem.ID), WAGYU_BEEF);
    }
}
