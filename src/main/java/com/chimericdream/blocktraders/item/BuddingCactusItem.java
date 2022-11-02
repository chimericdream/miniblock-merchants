package com.chimericdream.blocktraders.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class BuddingCactusItem extends VillagerConversionItem {
    public static final String ID = "budding_cactus";

    public BuddingCactusItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    protected String getVillagerProfession() {
        return "bt_eremologist";
    }
}
