package com.chimericdream.blocktraders.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class SoakedVillagerPlushieItem extends VillagerConversionItem {
    public static final String ID = "soaked_villager_plushie";

    public SoakedVillagerPlushieItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    protected String getVillagerProfession() {
        return "bt_plushie_maniac";
    }
}