package com.chimericdream.miniblockmerchants;

import com.chimericdream.miniblockmerchants.client.render.VillagerConversionItemRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MiniblockMerchantsClient {
    public static VillagerConversionItemRenderer CONVERSION_ITEM_RENDERER = null;

    public static void init(Minecraft instance) {
        MiniblockMerchantsMod.LOGGER.info(String.format("[%s] Initializing client code", ModInfo.MOD_ID));

        CONVERSION_ITEM_RENDERER = new VillagerConversionItemRenderer(instance.getBlockEntityRenderDispatcher(), instance.getEntityModels());
    }
}
