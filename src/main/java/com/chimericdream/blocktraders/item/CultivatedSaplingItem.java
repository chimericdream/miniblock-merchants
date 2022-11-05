package com.chimericdream.blocktraders.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class CultivatedSaplingItem extends VillagerConversionItem {
    public static final String ID = "cultivated_sapling";

    public CultivatedSaplingItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    public String getVillagerProfession() {
        return "bt_arboriculturalist";
    }
}
