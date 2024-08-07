package com.chimericdream.miniblockmerchants.item;

import com.chimericdream.miniblockmerchants.util.TextHelpers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

abstract public class VillagerConversionItem extends Item {
    public VillagerConversionItem() {
        super(new Item.Settings());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(TextHelpers.getTooltip("tooltip.conversion_item.lore"));
    }

    abstract public String getVillagerProfession();
}
