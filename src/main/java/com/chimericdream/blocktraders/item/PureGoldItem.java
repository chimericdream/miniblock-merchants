package com.chimericdream.blocktraders.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class PureGoldItem extends VillagerConversionItem {
    public static final String ID = "pure_gold";

    public PureGoldItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    protected String getVillagerProfession() {
        return "bt_mineralogist";
    }
}
