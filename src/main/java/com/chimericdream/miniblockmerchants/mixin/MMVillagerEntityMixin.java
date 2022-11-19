package com.chimericdream.miniblockmerchants.mixin;

import com.chimericdream.miniblockmerchants.MiniblockMerchantsMod;
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
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        MutableText msg = Text.of("").copy();
        Text lcarat = Text.of("").copy().append("<").formatted(Formatting.WHITE);
        Text rcarat = Text.of("").copy().append(">").formatted(Formatting.WHITE);
        Text name = new TranslatableText(String.format("entity.minecraft.villager.%s", profession)).formatted(Formatting.GOLD);

        MutableText greeting = Text.of("").copy();
        greeting.append(new TranslatableText(String.format("%s.message.conversion.%s", ModInfo.MOD_ID, profession)).formatted(Formatting.GREEN));
        if (profession.equals("mm_ritualist")) {
            greeting.append(" ").append(new TranslatableText(String.format("%s.message.conversion.%s.obfuscated", ModInfo.MOD_ID, profession)).formatted(Formatting.GREEN).formatted(Formatting.OBFUSCATED));
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
                cir.setReturnValue(ActionResult.success(this.world.isClient));
            } else {
                VillagerProfession currentProfession = this.getVillagerData().getProfession();
                if (currentProfession == VillagerProfession.NONE) {
                    VillagerProfession newProfession = MMProfessions.get(item.getVillagerProfession());

                    if (this.world.isClient) {
                        this.world.playSoundFromEntity(player, this, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    } else {
                        ((ServerPlayerEntity) player).sendMessage(getPlayerMessage(item.getVillagerProfession()), false);

                        NbtCompound nbtOffers = MMProfessions.getOffersForProfession(item.getVillagerProfession());
                        TradeOfferList offerList = new TradeOfferList(nbtOffers);

                        this.setVillagerData(new VillagerData(VillagerType.PLAINS, newProfession, 5));
                        this.setOffers(offerList);
                        this.setExperience(250);
                        this.produceParticles(ParticleTypes.HAPPY_VILLAGER);
                        this.setPersistent();

                        this.reinitializeBrain((ServerWorld) this.world);

                        if (!player.isCreative()) {
                            itemStack.decrement(1);
                        }
                    }

                    player.incrementStat(Stats.TALKED_TO_VILLAGER);

                    cir.setReturnValue(ActionResult.success(this.world.isClient));
                }
            }
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void mm_setBlockTraderOffers(NbtCompound nbt, CallbackInfo ci) {
        Set<String> tags = this.getScoreboardTags();

        if (tags.contains("mt_trader")) {
            mm_setBaseXp(nbt);
            mm_convertDatapackTrader(nbt);
            return;
        }

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
        if (this.getCustomer() instanceof ServerPlayerEntity player) {
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

    private void mm_convertDatapackTrader(NbtCompound nbt) {
        Set<String> tags = this.getScoreboardTags();
        Optional<String> dpProfession = tags.stream().filter(tag -> tag.startsWith("mt_trader") && !tag.equals("mt_trader")).findFirst();

        if (dpProfession.isEmpty()) {
            return;
        }

        String profession = switch (dpProfession.get()) {
            case "mt_trader_alchemy" -> ModInfo.MOD_ID + ":mm_alchemist";
            case "mt_trader_sapling" -> ModInfo.MOD_ID + ":mm_arboriculturist";
            case "mt_trader_astronomy" -> ModInfo.MOD_ID + ":mm_astronomer";
            case "mt_trader_bake" -> ModInfo.MOD_ID + ":mm_baker";
            case "mt_trader_bartender" -> ModInfo.MOD_ID + ":mm_bartender";
            case "mt_trader_bees" -> ModInfo.MOD_ID + ":mm_beekeeper";
            case "mt_trader_smithy" -> ModInfo.MOD_ID + ":mm_blacksmith";
            case "mt_trader_chef" -> ModInfo.MOD_ID + ":mm_chef";
            case "mt_trader_engineer" -> ModInfo.MOD_ID + ":mm_engineer";
            case "mt_trader_desert" -> ModInfo.MOD_ID + ":mm_eremologist";
            case "mt_trader_furnish" -> ModInfo.MOD_ID + ":mm_furnisher";
            case "mt_trader_game" -> ModInfo.MOD_ID + ":mm_gamemaster";
            case "mt_trader_flower" -> ModInfo.MOD_ID + ":mm_horticulturist";
            case "mt_trader_mineral" -> ModInfo.MOD_ID + ":mm_mineralogist";
            case "mt_trader_nether" -> ModInfo.MOD_ID + ":mm_netherographer";
            case "mt_trader_ocean" -> ModInfo.MOD_ID + ":mm_oceanographer";
            case "mt_trader_carrot" -> ModInfo.MOD_ID + ":mm_olericulturist";
            case "mt_trader_rock" -> ModInfo.MOD_ID + ":mm_petrologist";
            case "mt_trader_plush" -> ModInfo.MOD_ID + ":mm_plushie_maniac";
            case "mt_trader_apple" -> ModInfo.MOD_ID + ":mm_pomologist";
            case "mt_trader_recycle" -> ModInfo.MOD_ID + ":mm_recycler";
            case "mt_trader_ritual" -> ModInfo.MOD_ID + ":mm_ritualist";
            case "mt_trader_sculpt" -> ModInfo.MOD_ID + ":mm_sculptor";
            case "mt_trader_steampunk" -> ModInfo.MOD_ID + ":mm_steampunker";
            case "mt_trader_clothes" -> ModInfo.MOD_ID + ":mm_tailor";
            default -> "";
        };

        if (profession.equals("")) {
            return;
        }

        NbtCompound data = nbt.getCompound("VillagerData");
        data.putString("profession", profession);
        data.putString("type", "minecraft:plains");

        nbt.put("VillagerData", data);
        nbt.put("Offers", MMProfessions.getOffersForProfession(profession));

        List<String> removeTheseTags = new ArrayList<>(Set.of(
            "mt_trader",
            "mt_trader_alchemy",
            "mt_trader_sapling",
            "mt_trader_astronomy",
            "mt_trader_bake",
            "mt_trader_bartender",
            "mt_trader_bees",
            "mt_trader_smithy",
            "mt_trader_chef",
            "mt_trader_engineer",
            "mt_trader_desert",
            "mt_trader_furnish",
            "mt_trader_game",
            "mt_trader_flower",
            "mt_trader_mineral",
            "mt_trader_nether",
            "mt_trader_ocean",
            "mt_trader_carrot",
            "mt_trader_rock",
            "mt_trader_plush",
            "mt_trader_apple",
            "mt_trader_recycle",
            "mt_trader_ritual",
            "mt_trader_sculpt",
            "mt_trader_steampunk",
            "mt_trader_clothes"
        ));

        removeTheseTags.forEach(this::removeScoreboardTag);

        List<String> datapackNames = new ArrayList<>(Set.of(
            "\"Alchemist\"",
            "\"Arborculturalist\"",
            "\"Astronomer\"",
            "\"Baker\"",
            "\"Bartender\"",
            "\"Beekeeper\"",
            "\"Chef\"",
            "\"Engineer\"",
            "\"Eremologist\"",
            "\"Furnisher\"",
            "\"Gamemaster\"",
            "\"Horticulturist\"",
            "\"Mineralogist\"",
            "\"Netherographer\"",
            "\"Oceanographer\"",
            "\"Olericulturist\"",
            "\"Petrologist\"",
            "\"Plushie Maniac\"",
            "\"Pomologist\"",
            "\"Recycler\"",
            "\"Ritualist\"",
            "\"Sculptor\"",
            "\"Blacksmith\"",
            "\"Steampunker\"",
            "\"Tailor\""
        ));

        if (nbt.contains("CustomName") && datapackNames.contains(nbt.getString("CustomName"))) {
            MiniblockMerchantsMod.LOGGER.info("Removing custom name");
            this.setCustomName(null);
            nbt.remove("CustomName");
        }
    }
}
