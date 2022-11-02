package com.chimericdream.blocktraders.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class FragrantFlowerItem extends VillagerConversionItem {
    public static final String ID = "fragrant_flower";

    public FragrantFlowerItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    protected String getVillagerProfession() {
        return "bt_horticulturist";
    }
}
