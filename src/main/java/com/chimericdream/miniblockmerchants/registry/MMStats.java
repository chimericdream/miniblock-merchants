package com.chimericdream.miniblockmerchants.registry;

import com.chimericdream.miniblockmerchants.ModInfo;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MMStats {
    public static final DeferredRegister<ResourceLocation> STATS = DeferredRegister.create(Registry.CUSTOM_STAT_REGISTRY, ModInfo.MOD_ID);

    public static final RegistryObject<ResourceLocation> TRADE_FOR_MINIBLOCK_ID = STATS.register("trade_for_miniblock", () -> new ResourceLocation(""));

    public static void init(IEventBus modEventBus) {
        STATS.register(modEventBus);
    }
}
