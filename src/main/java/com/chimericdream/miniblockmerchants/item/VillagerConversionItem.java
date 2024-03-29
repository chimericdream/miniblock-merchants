package com.chimericdream.miniblockmerchants.item;

import com.chimericdream.miniblockmerchants.util.TextHelpers;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

abstract public class VillagerConversionItem extends Item {
    public VillagerConversionItem() {
        super(new FabricItemSettings());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(TextHelpers.getTooltip("tooltip.conversion_item.lore"));
    }

    abstract public String getVillagerProfession();
}
