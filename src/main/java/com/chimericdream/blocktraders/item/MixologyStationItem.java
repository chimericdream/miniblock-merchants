package com.chimericdream.blocktraders.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class MixologyStationItem extends VillagerConversionItem {
    public static final String ID = "mixology_station";

    public MixologyStationItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    protected String getVillagerProfession() {
        return "bt_bartender";
    }
}
