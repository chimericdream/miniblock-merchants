package com.chimericdream.miniblockmerchants.item;

import com.chimericdream.miniblockmerchants.ModInfo;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

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
    public static final VillagerConversionItem STABILIZED_EXPLOSION = new StabilizedExplosionItem();
    public static final VillagerConversionItem UNUSUALLY_DENSE_ROCK = new UnusuallyDenseRockItem();
    public static final VillagerConversionItem WAGYU_BEEF = new WagyuBeefItem();

    public void init() {
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, AncientShellItem.ID), ANCIENT_SHELL);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, BookOfRitualsItem.ID), BOOK_OF_RITUALS);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, BuddingCactusItem.ID), BUDDING_CACTUS);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, CrystalPhialItem.ID), CRYSTAL_PHIAL);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, CultivatedSaplingItem.ID), CULTIVATED_SAPLING);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, DrenchedScoreSheetItem.ID), DRENCHED_SCORE_SHEET);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, EnchantedRedDeliciousItem.ID), ENCHANTED_RED_DELICIOUS);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, EndlessBookshelfItem.ID), ENDLESS_BOOKSHELF);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, FineThreadItem.ID), FINE_THREAD);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, ForgottenScrapMetalItem.ID), FORGOTTEN_SCRAP_METAL);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, FragrantFlowerItem.ID), FRAGRANT_FLOWER);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, GalileanSpyglassItem.ID), GALILEAN_SPYGLASS);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, MastercraftedIronItem.ID), MASTERCRAFTED_IRON);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, MixologyStationItem.ID), MIXOLOGY_STATION);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, OvergrownCarrotItem.ID), OVERGROWN_CARROT);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, PrismaticHoneycombItem.ID), PRISMATIC_HONEYCOMB);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, PureGoldItem.ID), PURE_GOLD);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, RadiatingRedstoneItem.ID), RADIATING_REDSTONE);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, RottingRecyclingBinItem.ID), ROTTING_RECYCLING_BIN);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, SculptingClayItem.ID), SCULPTING_CLAY);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, ShimmeringWheatItem.ID), SHIMMERING_WHEAT);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, SoakedVillagerPlushieItem.ID), SOAKED_VILLAGER_PLUSHIE);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, SparklingBlazePowderItem.ID), SPARKLING_BLAZE_POWDER);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, StabilizedExplosionItem.ID), STABILIZED_EXPLOSION);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, UnusuallyDenseRockItem.ID), UNUSUALLY_DENSE_ROCK);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, WagyuBeefItem.ID), WAGYU_BEEF);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            content.addAfter(
                Items.DRAGON_EGG,
                ANCIENT_SHELL,
                BOOK_OF_RITUALS,
                BUDDING_CACTUS,
                CRYSTAL_PHIAL,
                CULTIVATED_SAPLING,
                DRENCHED_SCORE_SHEET,
                ENCHANTED_RED_DELICIOUS,
                ENDLESS_BOOKSHELF,
                FINE_THREAD,
                FORGOTTEN_SCRAP_METAL,
                FRAGRANT_FLOWER,
                GALILEAN_SPYGLASS,
                MASTERCRAFTED_IRON,
                MIXOLOGY_STATION,
                OVERGROWN_CARROT,
                PRISMATIC_HONEYCOMB,
                PURE_GOLD,
                RADIATING_REDSTONE,
                ROTTING_RECYCLING_BIN,
                SCULPTING_CLAY,
                SHIMMERING_WHEAT,
                SOAKED_VILLAGER_PLUSHIE,
                SPARKLING_BLAZE_POWDER,
                STABILIZED_EXPLOSION,
                UNUSUALLY_DENSE_ROCK,
                WAGYU_BEEF
            );
        });
    }
}
