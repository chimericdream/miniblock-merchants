package com.chimericdream.miniblockmerchants.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class BuddingCactusItem extends VillagerConversionItem {
    public static final String ID = "budding_cactus";

    public BuddingCactusItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    public String getVillagerProfession() {
        return "mm_eremologist";
    }
}
