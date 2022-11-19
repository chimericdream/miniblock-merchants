package com.chimericdream.miniblockmerchants.item;

import com.chimericdream.miniblockmerchants.ModInfo;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

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
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, UnusuallyDenseRockItem.ID), UNUSUALLY_DENSE_ROCK);
        Registry.register(Registries.ITEM, new Identifier(ModInfo.MOD_ID, WagyuBeefItem.ID), WAGYU_BEEF);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAll(List.of(
                ANCIENT_SHELL.getDefaultStack(),
                BOOK_OF_RITUALS.getDefaultStack(),
                BUDDING_CACTUS.getDefaultStack(),
                CRYSTAL_PHIAL.getDefaultStack(),
                CULTIVATED_SAPLING.getDefaultStack(),
                DRENCHED_SCORE_SHEET.getDefaultStack(),
                ENCHANTED_RED_DELICIOUS.getDefaultStack(),
                ENDLESS_BOOKSHELF.getDefaultStack(),
                FINE_THREAD.getDefaultStack(),
                FORGOTTEN_SCRAP_METAL.getDefaultStack(),
                FRAGRANT_FLOWER.getDefaultStack(),
                GALILEAN_SPYGLASS.getDefaultStack(),
                MASTERCRAFTED_IRON.getDefaultStack(),
                MIXOLOGY_STATION.getDefaultStack(),
                OVERGROWN_CARROT.getDefaultStack(),
                PRISMATIC_HONEYCOMB.getDefaultStack(),
                PURE_GOLD.getDefaultStack(),
                RADIATING_REDSTONE.getDefaultStack(),
                ROTTING_RECYCLING_BIN.getDefaultStack(),
                SCULPTING_CLAY.getDefaultStack(),
                SHIMMERING_WHEAT.getDefaultStack(),
                SOAKED_VILLAGER_PLUSHIE.getDefaultStack(),
                SPARKLING_BLAZE_POWDER.getDefaultStack(),
                UNUSUALLY_DENSE_ROCK.getDefaultStack(),
                WAGYU_BEEF.getDefaultStack()
            ));
        });
    }
}
