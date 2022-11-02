package com.chimericdream.blocktraders.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class UnusuallyDenseRockItem extends VillagerConversionItem {
    public static final String ID = "unusually_dense_rock";

    public UnusuallyDenseRockItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    protected String getVillagerProfession() {
        return "bt_petrologist";
    }
}
