package com.chimericdream.blocktraders.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class PrismaticHoneycombItem extends VillagerConversionItem {
    public static final String ID = "prismatic_honeycomb";

    public PrismaticHoneycombItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    public String getVillagerProfession() {
        return "bt_beekeeper";
    }
}
