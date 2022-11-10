package com.chimericdream.miniblockmerchants.mixin;

import com.chimericdream.miniblockmerchants.MiniblockMerchantsMod;
import com.chimericdream.miniblockmerchants.item.VillagerConversionItem;
import com.chimericdream.miniblockmerchants.registry.MMProfessions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.message.MessageType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.VillagerData;
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
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
    abstract public void setOffers(TradeOfferList offers);

    @Shadow
    abstract public void setVillagerData(VillagerData villagerData);

    protected MMVillagerEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    private Text getPlayerMessage(String profession) {
        MutableText msg = MutableText.of(TextContent.EMPTY);
        Text lcarat = MutableText.of(TextContent.EMPTY).append("<").formatted(Formatting.WHITE);
        Text rcarat = MutableText.of(TextContent.EMPTY).append(">").formatted(Formatting.WHITE);
        Text name = Text.translatable(String.format("entity.minecraft.villager.%s", profession)).formatted(Formatting.GOLD);

        MutableText greeting = MutableText.of(TextContent.EMPTY);
        greeting.append(Text.translatable(String.format("miniblockmerchants.message.conversion.%s", profession)).formatted(Formatting.GREEN));
        if (profession.equals("mm_ritualist")) {
            greeting.append(" ").append(Text.translatable(String.format("miniblockmerchants.message.conversion.%s.obfuscated", profession)).formatted(Formatting.GREEN).formatted(Formatting.OBFUSCATED));
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

                    this.world.playSoundFromEntity(player, this, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    if (this.world instanceof ServerWorld) {
                        ((ServerPlayerEntity) player).sendMessage(getPlayerMessage(item.getVillagerProfession()), MessageType.TELLRAW_COMMAND);
                    }

                    NbtCompound nbtOffers = MMProfessions.getOffersForProfession(item.getVillagerProfession());
                    TradeOfferList offerList = new TradeOfferList(nbtOffers);

                    this.setVillagerData(new VillagerData(VillagerType.PLAINS, newProfession, 5));
                    this.setOffers(offerList);
                    this.produceParticles(ParticleTypes.HAPPY_VILLAGER);
                    this.setPersistent();

                    if (this.world instanceof ServerWorld) {
                        this.reinitializeBrain((ServerWorld) this.world);
                    }

                    if (!player.world.isClient && !player.isCreative()) {
                        itemStack.decrement(1);
                    }

                    player.incrementStat(Stats.TALKED_TO_VILLAGER);

                    cir.setReturnValue(ActionResult.success(player.world.isClient));
                }
            }
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void mm_setBlockTraderOffers(NbtCompound nbt, CallbackInfo ci) {
        Set<String> tags = this.getScoreboardTags();

        if (tags.contains("mt_trader")) {
            mm_convertDatapackTrader(nbt);
            return;
        }

        NbtCompound data = nbt.getCompound("VillagerData");
        String profession = data.getString("profession");

        if (!profession.startsWith("miniblockmerchants:")) {
            return;
        }

        nbt.put("Offers", MMProfessions.getOffersForProfession(profession));
    }

    private void mm_convertDatapackTrader(NbtCompound nbt) {
        Set<String> tags = this.getScoreboardTags();
        Optional<String> dpProfession = tags.stream().filter(tag -> tag.startsWith("mt_trader") && !tag.equals("mt_trader")).findFirst();

        if (dpProfession.isEmpty()) {
            return;
        }

        String profession = switch (dpProfession.get()) {
            case "mt_trader_alchemy" -> "miniblockmerchants:mm_alchemist";
            case "mt_trader_sapling" -> "miniblockmerchants:mm_arboriculturist";
            case "mt_trader_astronomy" -> "miniblockmerchants:mm_astronomer";
            case "mt_trader_bake" -> "miniblockmerchants:mm_baker";
            case "mt_trader_bartender" -> "miniblockmerchants:mm_bartender";
            case "mt_trader_bees" -> "miniblockmerchants:mm_beekeeper";
            case "mt_trader_smithy" -> "miniblockmerchants:mm_blacksmith";
            case "mt_trader_chef" -> "miniblockmerchants:mm_chef";
            case "mt_trader_engineer" -> "miniblockmerchants:mm_engineer";
            case "mt_trader_desert" -> "miniblockmerchants:mm_eremologist";
            case "mt_trader_furnish" -> "miniblockmerchants:mm_furnisher";
            case "mt_trader_game" -> "miniblockmerchants:mm_gamemaster";
            case "mt_trader_flower" -> "miniblockmerchants:mm_horticulturist";
            case "mt_trader_mineral" -> "miniblockmerchants:mm_mineralogist";
            case "mt_trader_nether" -> "miniblockmerchants:mm_netherographer";
            case "mt_trader_ocean" -> "miniblockmerchants:mm_oceanographer";
            case "mt_trader_carrot" -> "miniblockmerchants:mm_olericulturist";
            case "mt_trader_rock" -> "miniblockmerchants:mm_petrologist";
            case "mt_trader_plush" -> "miniblockmerchants:mm_plushie_maniac";
            case "mt_trader_apple" -> "miniblockmerchants:mm_pomologist";
            case "mt_trader_recycle" -> "miniblockmerchants:mm_recycler";
            case "mt_trader_ritual" -> "miniblockmerchants:mm_ritualist";
            case "mt_trader_sculpt" -> "miniblockmerchants:mm_sculptor";
            case "mt_trader_steampunk" -> "miniblockmerchants:mm_steampunker";
            case "mt_trader_clothes" -> "miniblockmerchants:mm_tailor";
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
