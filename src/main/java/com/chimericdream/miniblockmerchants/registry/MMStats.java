package com.chimericdream.miniblockmerchants.registry;

import com.chimericdream.miniblockmerchants.ModInfo;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MMStats {
    public static final Identifier TRADE_FOR_MINIBLOCK_ID = new Identifier(ModInfo.MOD_ID, "trade_for_miniblock");

    public static void init() {
        Registry.register(Registry.CUSTOM_STAT, "trade_for_miniblock", TRADE_FOR_MINIBLOCK_ID);
    }
}
