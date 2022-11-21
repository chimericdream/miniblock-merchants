package com.chimericdream.miniblockmerchants.advancement;

import com.chimericdream.miniblockmerchants.ModInfo;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;

public class MMAdvancements {
    public static final ResourceLocation TRADE_100_TIMES = new ResourceLocation(ModInfo.MOD_ID, "trades/100_trades");
    public static final ResourceLocation TRADE_250_TIMES = new ResourceLocation(ModInfo.MOD_ID, "trades/250_trades");
    public static final ResourceLocation TRADE_500_TIMES = new ResourceLocation(ModInfo.MOD_ID, "trades/500_trades");
    public static final ResourceLocation TRADE_1000_TIMES = new ResourceLocation(ModInfo.MOD_ID, "trades/1000_trades");
    public static final ResourceLocation TRADE_5000_TIMES = new ResourceLocation(ModInfo.MOD_ID, "trades/5000_trades");
    public static final ResourceLocation TRADE_10000_TIMES = new ResourceLocation(ModInfo.MOD_ID, "trades/10000_trades");

    public static Advancement getAdvancement(MinecraftServer server, ResourceLocation id) {
        return server.getAdvancements().getAdvancement(id);
    }
}
