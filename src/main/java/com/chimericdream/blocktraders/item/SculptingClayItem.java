package com.chimericdream.blocktraders.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class SculptingClayItem extends VillagerConversionItem {
    public static final String ID = "sculpting_clay";

    public SculptingClayItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    protected String getVillagerProfession() {
        return "bt_sculptor";
    }
}
