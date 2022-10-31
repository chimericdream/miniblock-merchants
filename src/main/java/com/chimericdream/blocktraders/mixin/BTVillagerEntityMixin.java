package com.chimericdream.blocktraders.mixin;

import com.chimericdream.blocktraders.registry.BTProfessions;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public class BTVillagerEntityMixin extends BTMerchantEntityMixin {
	@Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
	private void setBlockTraderOffers(NbtCompound nbt, CallbackInfo ci) {
		NbtCompound data = nbt.getCompound("VillagerData");
		String profession = data.getString("profession");

		if (!profession.startsWith("blocktraders:")) {
			return;
		}

		nbt.put("Offers", BTProfessions.getOffersForProfession(profession));
		nbt.putString("foo", "bar");
	}
}
