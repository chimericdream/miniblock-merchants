package com.chimericdream.blocktraders.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class AncientShellItem extends VillagerConversionItem {
    public static final String ID = "ancient_shell";

    public AncientShellItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    @Override
    protected String getVillagerProfession() {
        return "bt_oceanographer";
    }
}
