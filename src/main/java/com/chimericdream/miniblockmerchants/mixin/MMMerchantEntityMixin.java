package com.chimericdream.miniblockmerchants.mixin;

import com.chimericdream.miniblockmerchants.advancement.MMAdvancements;
import com.chimericdream.miniblockmerchants.registry.MMStats;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractVillager.class)
abstract public class MMMerchantEntityMixin extends AgeableMob {
    @Shadow
    abstract public boolean isTrading();

    @Shadow
    abstract protected void addParticlesAroundSelf(ParticleOptions parameters);

    @Shadow @Nullable public abstract Player getTradingPlayer();

    protected MMMerchantEntityMixin(EntityType<? extends AbstractVillager> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "notifyTrade", at = @At("TAIL"))
    private void mm_incrementMiniblockTrade(MerchantOffer offer, CallbackInfo ci) {
        if (this.getTradingPlayer() instanceof ServerPlayer player) {
            player.awardStat(MMStats.TRADE_FOR_MINIBLOCK_ID.get());
            checkPlayerAdvancements(player);
        }
    }

    private void checkPlayerAdvancements(ServerPlayer player) {
        int tradeCount = player.getStats().getValue(Stats.CUSTOM.get(MMStats.TRADE_FOR_MINIBLOCK_ID.get()));
        MinecraftServer server = player.getServer();

        // Theoretically, this shouldn't be possible. But the interfaces/IDE say this is technically nullable, so...
        if (server == null) {
            return;
        }

        PlayerAdvancements tracker = player.getAdvancements();

        if (tradeCount >= 100) {
            tracker.award(MMAdvancements.getAdvancement(server, MMAdvancements.TRADE_100_TIMES), "magic");
        }

        if (tradeCount >= 250) {
            tracker.award(MMAdvancements.getAdvancement(server, MMAdvancements.TRADE_250_TIMES), "magic");
        }

        if (tradeCount >= 500) {
            tracker.award(MMAdvancements.getAdvancement(server, MMAdvancements.TRADE_500_TIMES), "magic");
        }

        if (tradeCount >= 1000) {
            tracker.award(MMAdvancements.getAdvancement(server, MMAdvancements.TRADE_1000_TIMES), "magic");
        }

        if (tradeCount >= 5000) {
            tracker.award(MMAdvancements.getAdvancement(server, MMAdvancements.TRADE_5000_TIMES), "magic");
        }

        if (tradeCount >= 10000) {
            tracker.award(MMAdvancements.getAdvancement(server, MMAdvancements.TRADE_10000_TIMES), "magic");
        }
    }
}
