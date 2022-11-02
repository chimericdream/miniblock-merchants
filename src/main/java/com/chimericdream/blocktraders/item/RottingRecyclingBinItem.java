package com.chimericdream.blocktraders.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class RottingRecyclingBinItem extends VillagerConversionItem {
    public static final String ID = "rotting_recycling_bin";

    public RottingRecyclingBinItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    protected String getVillagerProfession() {
        return "bt_recycler";
    }
}
