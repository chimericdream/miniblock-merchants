package com.chimericdream.miniblockmerchants.mixin;

import com.chimericdream.miniblockmerchants.ModInfo;
import com.chimericdream.miniblockmerchants.registry.MMProfessions;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.village.VillagerData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieVillagerEntity.class)
abstract public class MMZombieVillagerEntityMixin extends MMEntityMixin {
    @Shadow
    public NbtCompound offerData;

    @Shadow @Final
    public static TrackedData<VillagerData> VILLAGER_DATA;

    @Inject(method = "setVillagerData", at = @At("TAIL"))
    private void mm_updateVillagerData(VillagerData data, CallbackInfo ci) {
        String profession = data.getProfession().id();

        if (!profession.startsWith(ModInfo.MOD_ID)) {
            return;
        }

        this.dataTracker.set(VILLAGER_DATA, data.withLevel(5));
        this.offerData = MMProfessions.getOffersForProfession(profession);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void mm_setBlockTraderOffers(NbtCompound nbt, CallbackInfo ci) {
        NbtCompound data = nbt.getCompound("VillagerData");
        String profession = data.getString("profession");

        if (!profession.startsWith(ModInfo.MOD_ID)) {
            return;
        }

        mm_setBaseXp(nbt);

        data.putInt("level", 5);
        nbt.put("VillagerData", data);

        nbt.put("Offers", MMProfessions.getOffersForProfession(profession));
    }

    private void mm_setBaseXp(NbtCompound nbt) {
        int currentXp = nbt.getInt("Xp");

        if (currentXp == 0) {
            nbt.putInt("Xp", 250);
        }
    }
}
