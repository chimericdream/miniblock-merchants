package com.chimericdream.miniblockmerchants.mixin;

import com.chimericdream.miniblockmerchants.MiniblockMerchantsMod;
import com.chimericdream.miniblockmerchants.ModInfo;
import com.chimericdream.miniblockmerchants.item.VillagerConversionItem;
import com.chimericdream.miniblockmerchants.registry.MMProfessions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
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

@Mixin(Villager.class)
abstract public class MMVillagerEntityMixin extends MMMerchantEntityMixin {
    private void sayNo() {
    }

    @Shadow
    abstract public VillagerData getVillagerData();

    @Shadow
    abstract public void refreshBrain(ServerLevel world);

    @Shadow
    abstract public void setVillagerXp(int experience);

    @Shadow
    abstract public void setOffers(MerchantOffers offers);

    @Shadow
    abstract public void setVillagerData(VillagerData villagerData);

    protected MMVillagerEntityMixin(EntityType<? extends Villager> entityType, Level world) {
        super(entityType, world);
    }

    private Component getPlayerMessage(String profession) {
        MutableComponent msg = MutableComponent.create(ComponentContents.EMPTY);
        FormattedText lcarat = MutableComponent.create(ComponentContents.EMPTY).append("<").withStyle(ChatFormatting.WHITE);
        FormattedText rcarat = MutableComponent.create(ComponentContents.EMPTY).append(">").withStyle(ChatFormatting.WHITE);
        FormattedText name = Component.translatable(String.format("entity.minecraft.villager.%s", profession)).withStyle(ChatFormatting.GOLD);

        MutableComponent greeting = MutableComponent.create(ComponentContents.EMPTY);
        greeting.append(Component.translatable(String.format("%s.message.conversion.%s", ModInfo.MOD_ID, profession)).withStyle(ChatFormatting.GREEN));
        if (profession.equals("mm_ritualist")) {
            greeting.append(" ").append(Component.translatable(String.format("%s.message.conversion.%s.obfuscated", ModInfo.MOD_ID, profession)).withStyle(ChatFormatting.GREEN).withStyle(ChatFormatting.OBFUSCATED));
        }

        msg.append(lcarat.getString()).append(name.getString()).append(rcarat.getString()).append(" ").append(greeting);

        return msg;
    }

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void mm_tryConvertingVillager(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.getItem() instanceof VillagerConversionItem item && this.isAlive() && !this.isTrading() && !this.isSleeping()) {
            if (this.isBaby()) {
                this.sayNo();
                cir.setReturnValue(InteractionResult.sidedSuccess(this.level.isClientSide));
            } else {
                VillagerProfession currentProfession = this.getVillagerData().getProfession();
                if (currentProfession == VillagerProfession.NONE) {
                    VillagerProfession newProfession = MMProfessions.get(item.getVillagerProfession());

                    if (this.level.isClientSide) {
                        this.level.playSound(player, this, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.NEUTRAL, 1.0F, 1.0F);
                    } else {
                        ((ServerPlayer) player).sendSystemMessage(getPlayerMessage(item.getVillagerProfession()));

                        CompoundTag nbtOffers = MMProfessions.getOffersForProfession(item.getVillagerProfession());
                        MerchantOffers offerList = new MerchantOffers(nbtOffers);

                        this.setVillagerData(new VillagerData(VillagerType.PLAINS, newProfession, 5));
                        this.setOffers(offerList);
                        this.setVillagerXp(250);
                        this.addParticlesAroundSelf(ParticleTypes.HAPPY_VILLAGER);
                        this.setPersistenceRequired();

                        this.refreshBrain((ServerLevel) this.level);

                        if (!player.isCreative()) {
                            itemStack.shrink(1);
                        }
                    }

                    player.awardStat(Stats.TALKED_TO_VILLAGER);

                    cir.setReturnValue(InteractionResult.sidedSuccess(this.level.isClientSide));
                }
            }
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    private void mm_setBlockTraderOffers(CompoundTag nbt, CallbackInfo ci) {
        Set<String> tags = this.getTags();

        if (tags.contains("mt_trader")) {
            mm_setBaseXp(nbt);
            mm_convertDatapackTrader(nbt);
            return;
        }

        CompoundTag data = nbt.getCompound("VillagerData");
        String profession = data.getString("profession");

        if (!profession.startsWith(ModInfo.MOD_ID)) {
            return;
        }

        mm_setBaseXp(nbt);
        nbt.put("Offers", MMProfessions.getOffersForProfession(profession));
    }

    private void mm_setBaseXp(CompoundTag nbt) {
        int currentXp = nbt.getInt("Xp");

        if (currentXp == 0) {
            nbt.putInt("Xp", 250);
        }
    }

    private void mm_convertDatapackTrader(CompoundTag nbt) {
        Set<String> tags = this.getTags();
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

        CompoundTag data = nbt.getCompound("VillagerData");
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

        removeTheseTags.forEach(this::removeTag);

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
