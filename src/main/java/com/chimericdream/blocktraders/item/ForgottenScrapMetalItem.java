package com.chimericdream.blocktraders.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class ForgottenScrapMetalItem extends VillagerConversionItem {
    public static final String ID = "forgotten_scrap_metal";

    public ForgottenScrapMetalItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    public String getVillagerProfession() {
        return "bt_steampunker";
    }
}
