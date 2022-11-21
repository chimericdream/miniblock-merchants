package com.chimericdream.miniblockmerchants.item;

import com.chimericdream.miniblockmerchants.MiniblockMerchantsClient;
import com.chimericdream.miniblockmerchants.util.TextHelpers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

abstract public class VillagerConversionItem extends Item {
    public VillagerConversionItem() {
        super(new Item.Properties().tab(CreativeModeTab.TAB_MISC));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, List<Component> tooltip, @NotNull TooltipFlag context) {
        tooltip.add(TextHelpers.getTooltip("tooltip.conversion_item.lore"));
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return MiniblockMerchantsClient.CONVERSION_ITEM_RENDERER;
            }
        });
    }

    abstract public String getVillagerProfession();
}
