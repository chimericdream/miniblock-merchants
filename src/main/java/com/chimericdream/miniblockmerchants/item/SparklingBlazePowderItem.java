package com.chimericdream.miniblockmerchants.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class SparklingBlazePowderItem extends VillagerConversionItem {
    public static final String ID = "sparkling_blaze_powder";

    public SparklingBlazePowderItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    public String getVillagerProfession() {
        return "mm_netherographer";
    }
}