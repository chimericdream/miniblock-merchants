package com.chimericdream.miniblockmerchants.item;

import com.chimericdream.miniblockmerchants.ModInfo;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModInfo.MOD_ID);

    public static final RegistryObject<VillagerConversionItem> ANCIENT_SHELL = ITEMS.register(AncientShellItem.ID, AncientShellItem::new);
    public static final RegistryObject<VillagerConversionItem> BOOK_OF_RITUALS = ITEMS.register(BookOfRitualsItem.ID, BookOfRitualsItem::new);
    public static final RegistryObject<VillagerConversionItem> BUDDING_CACTUS = ITEMS.register(BuddingCactusItem.ID, BuddingCactusItem::new);
    public static final RegistryObject<VillagerConversionItem> CRYSTAL_PHIAL = ITEMS.register(CrystalPhialItem.ID, CrystalPhialItem::new);
    public static final RegistryObject<VillagerConversionItem> CULTIVATED_SAPLING = ITEMS.register(CultivatedSaplingItem.ID, CultivatedSaplingItem::new);
    public static final RegistryObject<VillagerConversionItem> DRENCHED_SCORE_SHEET = ITEMS.register(DrenchedScoreSheetItem.ID, DrenchedScoreSheetItem::new);
    public static final RegistryObject<VillagerConversionItem> ENCHANTED_RED_DELICIOUS = ITEMS.register(EnchantedRedDeliciousItem.ID, EnchantedRedDeliciousItem::new);
    public static final RegistryObject<VillagerConversionItem> ENDLESS_BOOKSHELF = ITEMS.register(EndlessBookshelfItem.ID, EndlessBookshelfItem::new);
    public static final RegistryObject<VillagerConversionItem> FINE_THREAD = ITEMS.register(FineThreadItem.ID, FineThreadItem::new);
    public static final RegistryObject<VillagerConversionItem> FORGOTTEN_SCRAP_METAL = ITEMS.register(ForgottenScrapMetalItem.ID, ForgottenScrapMetalItem::new);
    public static final RegistryObject<VillagerConversionItem> FRAGRANT_FLOWER = ITEMS.register(FragrantFlowerItem.ID, FragrantFlowerItem::new);
    public static final RegistryObject<VillagerConversionItem> GALILEAN_SPYGLASS = ITEMS.register(GalileanSpyglassItem.ID, GalileanSpyglassItem::new);
    public static final RegistryObject<VillagerConversionItem> MASTERCRAFTED_IRON = ITEMS.register(MastercraftedIronItem.ID, MastercraftedIronItem::new);
    public static final RegistryObject<VillagerConversionItem> MIXOLOGY_STATION = ITEMS.register(MixologyStationItem.ID, MixologyStationItem::new);
    public static final RegistryObject<VillagerConversionItem> OVERGROWN_CARROT = ITEMS.register(OvergrownCarrotItem.ID, OvergrownCarrotItem::new);
    public static final RegistryObject<VillagerConversionItem> PRISMATIC_HONEYCOMB = ITEMS.register(PrismaticHoneycombItem.ID, PrismaticHoneycombItem::new);
    public static final RegistryObject<VillagerConversionItem> PURE_GOLD = ITEMS.register(PureGoldItem.ID, PureGoldItem::new);
    public static final RegistryObject<VillagerConversionItem> RADIATING_REDSTONE = ITEMS.register(RadiatingRedstoneItem.ID, RadiatingRedstoneItem::new);
    public static final RegistryObject<VillagerConversionItem> ROTTING_RECYCLING_BIN = ITEMS.register(RottingRecyclingBinItem.ID, RottingRecyclingBinItem::new);
    public static final RegistryObject<VillagerConversionItem> SCULPTING_CLAY = ITEMS.register(SculptingClayItem.ID, SculptingClayItem::new);
    public static final RegistryObject<VillagerConversionItem> SHIMMERING_WHEAT = ITEMS.register(ShimmeringWheatItem.ID, ShimmeringWheatItem::new);
    public static final RegistryObject<VillagerConversionItem> SOAKED_VILLAGER_PLUSHIE = ITEMS.register(SoakedVillagerPlushieItem.ID, SoakedVillagerPlushieItem::new);
    public static final RegistryObject<VillagerConversionItem> SPARKLING_BLAZE_POWDER = ITEMS.register(SparklingBlazePowderItem.ID, SparklingBlazePowderItem::new);
    public static final RegistryObject<VillagerConversionItem> UNUSUALLY_DENSE_ROCK = ITEMS.register(UnusuallyDenseRockItem.ID, UnusuallyDenseRockItem::new);
    public static final RegistryObject<VillagerConversionItem> WAGYU_BEEF = ITEMS.register(WagyuBeefItem.ID, WagyuBeefItem::new);

    public static void init(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
