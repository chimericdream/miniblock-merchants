package com.chimericdream.blocktraders.mixin;

import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.village.TradeOfferList;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MerchantEntity.class)
abstract public class BTMerchantEntityMixin {
    @Shadow @Nullable protected TradeOfferList offers;
}
