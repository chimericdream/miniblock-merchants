package com.chimericdream.miniblockmerchants.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class MastercraftedIronItem extends VillagerConversionItem {
    public static final String ID = "mastercrafted_iron";

    public MastercraftedIronItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    public String getVillagerProfession() {
        return "mm_blacksmith";
    }
}
