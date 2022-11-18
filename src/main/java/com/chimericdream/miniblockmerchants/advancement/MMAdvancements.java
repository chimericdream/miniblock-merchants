package com.chimericdream.miniblockmerchants.advancement;

import com.chimericdream.miniblockmerchants.ModInfo;
import net.minecraft.advancement.Advancement;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

public class MMAdvancements {
    public static final Identifier TRADE_100_TIMES = new Identifier(ModInfo.MOD_ID, "trades/100_trades");
    public static final Identifier TRADE_250_TIMES = new Identifier(ModInfo.MOD_ID, "trades/250_trades");
    public static final Identifier TRADE_500_TIMES = new Identifier(ModInfo.MOD_ID, "trades/500_trades");
    public static final Identifier TRADE_1000_TIMES = new Identifier(ModInfo.MOD_ID, "trades/1000_trades");
    public static final Identifier TRADE_5000_TIMES = new Identifier(ModInfo.MOD_ID, "trades/5000_trades");
    public static final Identifier TRADE_10000_TIMES = new Identifier(ModInfo.MOD_ID, "trades/10000_trades");

    public static Advancement getAdvancement(MinecraftServer server, Identifier id) {
        return server.getAdvancementLoader().get(id);
    }
}
