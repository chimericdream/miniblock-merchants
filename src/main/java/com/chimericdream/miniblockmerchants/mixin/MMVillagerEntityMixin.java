package com.chimericdream.miniblockmerchants.mixin;

import com.chimericdream.miniblockmerchants.ModInfo;
import com.chimericdream.miniblockmerchants.advancement.MMAdvancements;
import com.chimericdream.miniblockmerchants.item.VillagerConversionItem;
import com.chimericdream.miniblockmerchants.registry.MMProfessions;
import com.chimericdream.miniblockmerchants.registry.MMStats;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.MutableText;
import net.minecraft.text.PlainTextContent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.village.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerEntity.class)
abstract public class MMVillagerEntityMixin extends MMMerchantEntityMixin {
    private void sayNo() {
    }

    @Shadow
    abstract public VillagerData getVillagerData();

    @Shadow
    abstract public void reinitializeBrain(ServerWorld world);

    @Shadow
    abstract public void setExperience(int experience);

    @Shadow
    abstract public void setOffers(TradeOfferList offers);

    @Shadow
    abstract public void setVillagerData(VillagerData villagerData);

    protected MMVillagerEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    private Text getPlayerMessage(String profession) {
        MutableText msg = MutableText.of(PlainTextContent.EMPTY);
        Text lcarat = MutableText.of(PlainTextContent.EMPTY).append("<").formatted(Formatting.WHITE);
        Text rcarat = MutableText.of(PlainTextContent.EMPTY).append(">").formatted(Formatting.WHITE);
        Text name = Text.translatable(String.format("entity.minecraft.villager.%s", profession)).formatted(Formatting.GOLD);

        MutableText greeting = MutableText.of(PlainTextContent.EMPTY);
        greeting.append(Text.translatable(String.format("%s.message.conversion.%s", ModInfo.MOD_ID, profession)).formatted(Formatting.GREEN));
        if (profession.equals("mm_ritualist")) {
            greeting.append(" ").append(Text.translatable(String.format("%s.message.conversion.%s.obfuscated", ModInfo.MOD_ID, profession)).formatted(Formatting.GREEN).formatted(Formatting.OBFUSCATED));
        }

        msg.append(lcarat).append(name).append(rcarat).append(" ").append(greeting);

        return msg;
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void mm_tryConvertingVillager(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() instanceof VillagerConversionItem item && this.isAlive() && !this.hasCustomer() && !this.isSleeping()) {
            if (this.isBaby()) {
                this.sayNo();
                cir.setReturnValue(ActionResult.success(this.getWorld().isClient));
            } else {
                VillagerProfession currentProfession = this.getVillagerData().getProfession();
                if (currentProfession == VillagerProfession.NONE) {
                    VillagerProfession newProfession = MMProfessions.get(item.getVillagerProfession());

                    if (this.getWorld().isClient) {
                        this.getWorld().playSoundFromEntity(player, this, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    } else {
                        ((ServerPlayerEntity) player).sendMessage(getPlayerMessage(item.getVillagerProfession()), false);

                        NbtCompound nbtOffers = MMProfessions.getOffersForProfession(item.getVillagerProfession());
                        TradeOfferList offerList = new TradeOfferList(nbtOffers);

                        this.setVillagerData(new VillagerData(VillagerType.PLAINS, newProfession, 5));
                        this.setOffers(offerList);
                        this.setExperience(250);
                        this.produceParticles(ParticleTypes.HAPPY_VILLAGER);
                        this.setPersistent();

                        this.reinitializeBrain((ServerWorld) this.getWorld());

                        if (!player.isCreative()) {
                            itemStack.decrement(1);
                        }
                    }

                    player.incrementStat(Stats.TALKED_TO_VILLAGER);

                    cir.setReturnValue(ActionResult.success(this.getWorld().isClient));
                }
            }
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void mm_setBlockTraderOffers(NbtCompound nbt, CallbackInfo ci) {
        NbtCompound data = nbt.getCompound("VillagerData");
        String profession = data.getString("profession");

        if (!profession.startsWith(ModInfo.MOD_ID)) {
            return;
        }

        mm_setBaseXp(nbt);
        nbt.put("Offers", MMProfessions.getOffersForProfession(profession));
    }

    @Inject(method = "afterUsing", at = @At("TAIL"))
    private void mm_incrementMiniblockTrade(TradeOffer offer, CallbackInfo ci) {
        if (this.getCustomer() instanceof ServerPlayerEntity player && MMProfessions.PROFESSION_LIST.contains(this.getVillagerData().getProfession())) {
            player.incrementStat(MMStats.TRADE_FOR_MINIBLOCK_ID);
            checkPlayerAdvancements(player);
        }
    }

    private void checkPlayerAdvancements(ServerPlayerEntity player) {
        int tradeCount = player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(MMStats.TRADE_FOR_MINIBLOCK_ID));
        MinecraftServer server = player.getServer();

        // Theoretically, this shouldn't be possible. But the interfaces/IDE say this is technically nullable, so...
        if (server == null) {
            return;
        }

        PlayerAdvancementTracker tracker = player.getAdvancementTracker();

        if (tradeCount >= 100) {
            tracker.grantCriterion(MMAdvancements.getAdvancement(server, MMAdvancements.TRADE_100_TIMES), "magic");
        }

        if (tradeCount >= 250) {
            tracker.grantCriterion(MMAdvancements.getAdvancement(server, MMAdvancements.TRADE_250_TIMES), "magic");
        }

        if (tradeCount >= 500) {
            tracker.grantCriterion(MMAdvancements.getAdvancement(server, MMAdvancements.TRADE_500_TIMES), "magic");
        }

        if (tradeCount >= 1000) {
            tracker.grantCriterion(MMAdvancements.getAdvancement(server, MMAdvancements.TRADE_1000_TIMES), "magic");
        }

        if (tradeCount >= 5000) {
            tracker.grantCriterion(MMAdvancements.getAdvancement(server, MMAdvancements.TRADE_5000_TIMES), "magic");
        }

        if (tradeCount >= 10000) {
            tracker.grantCriterion(MMAdvancements.getAdvancement(server, MMAdvancements.TRADE_10000_TIMES), "magic");
        }
    }

    private void mm_setBaseXp(NbtCompound nbt) {
        int currentXp = nbt.getInt("Xp");

        if (currentXp == 0) {
            nbt.putInt("Xp", 250);
        }
    }
}
