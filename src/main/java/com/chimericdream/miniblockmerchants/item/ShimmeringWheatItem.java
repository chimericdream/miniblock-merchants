package com.chimericdream.miniblockmerchants.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class ShimmeringWheatItem extends VillagerConversionItem {
    public static final String ID = "shimmering_wheat";

    public ShimmeringWheatItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    public String getVillagerProfession() {
        return "mm_baker";
    }
}
