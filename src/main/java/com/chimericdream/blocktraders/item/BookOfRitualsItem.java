package com.chimericdream.blocktraders.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class BookOfRitualsItem extends VillagerConversionItem {
    public static final String ID = "book_of_rituals";

    public BookOfRitualsItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    public String getVillagerProfession() {
        return "bt_ritualist";
    }
}
