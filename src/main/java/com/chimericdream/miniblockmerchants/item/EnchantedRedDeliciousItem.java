package com.chimericdream.miniblockmerchants.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class EnchantedRedDeliciousItem extends VillagerConversionItem {
    public static final String ID = "enchanted_red_delicious";

    public EnchantedRedDeliciousItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    public String getVillagerProfession() {
        return "mm_pomologist";
    }
}
