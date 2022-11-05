package com.chimericdream.blocktraders.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class GalileanSpyglassItem extends VillagerConversionItem {
    public static final String ID = "galilean_spyglass";

    public GalileanSpyglassItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    public String getVillagerProfession() {
        return "bt_astronomer";
    }
}
