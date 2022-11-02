package com.chimericdream.blocktraders.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class DrenchedScoreSheetItem extends VillagerConversionItem {
    public static final String ID = "drenched_score_sheet";

    public DrenchedScoreSheetItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    protected String getVillagerProfession() {
        return "bt_gamemaster";
    }
}
