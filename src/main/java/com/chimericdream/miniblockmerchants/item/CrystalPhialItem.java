package com.chimericdream.miniblockmerchants.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class CrystalPhialItem extends VillagerConversionItem {
    public static final String ID = "crystal_phial";

    public CrystalPhialItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    public String getVillagerProfession() {
        return "mm_alchemist";
    }
}
