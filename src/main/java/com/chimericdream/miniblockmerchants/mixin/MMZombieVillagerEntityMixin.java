package com.chimericdream.miniblockmerchants.mixin;

import com.chimericdream.miniblockmerchants.registry.MMProfessions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.VillagerData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieVillager.class)
abstract public class MMZombieVillagerEntityMixin extends MMEntityMixin {
    @Shadow
    private CompoundTag tradeOffers;

    @Shadow @Final
    private static EntityDataAccessor<VillagerData> DATA_VILLAGER_DATA;

    @Inject(method = "setVillagerData", at = @At("TAIL"))
    private void mm_updateVillagerData(VillagerData data, CallbackInfo ci) {
        String profession = data.getProfession().name();

        if (!MMProfessions.TRADES.containsKey(profession)) {
            return;
        }

        this.entityData.set(DATA_VILLAGER_DATA, data.setLevel(5));
        this.tradeOffers = MMProfessions.getOffersForProfession(profession);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    private void mm_setBlockTraderOffers(CompoundTag nbt, CallbackInfo ci) {
        CompoundTag data = nbt.getCompound("VillagerData");
        String profession = data.getString("profession");

        if (!MMProfessions.TRADES.containsKey(profession)) {
            return;
        }

        mm_setBaseXp(nbt);

        data.putInt("level", 5);
        nbt.put("VillagerData", data);

        nbt.put("Offers", MMProfessions.getOffersForProfession(profession));
    }

    private void mm_setBaseXp(CompoundTag nbt) {
        int currentXp = nbt.getInt("Xp");

        if (currentXp == 0) {
            nbt.putInt("Xp", 250);
        }
    }
}
