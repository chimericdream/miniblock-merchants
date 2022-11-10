package com.chimericdream.miniblockmerchants.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class OvergrownCarrotItem extends VillagerConversionItem {
    public static final String ID = "overgrown_carrot";

    public OvergrownCarrotItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    public String getVillagerProfession() {
        return "mm_olericulturist";
    }
}
