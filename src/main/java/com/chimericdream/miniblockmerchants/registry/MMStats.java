package com.chimericdream.miniblockmerchants.registry;

import com.chimericdream.miniblockmerchants.ModInfo;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MMStats {
    public static final Identifier TRADE_FOR_MINIBLOCK_ID = Identifier.of(ModInfo.MOD_ID, "trade_for_miniblock");

    public static void init() {
        Registry.register(Registries.CUSTOM_STAT, "trade_for_miniblock", TRADE_FOR_MINIBLOCK_ID);
    }
}
