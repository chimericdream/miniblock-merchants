package com.chimericdream.blocktraders.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MerchantEntity.class)
abstract public class BTMerchantEntityMixin extends MobEntity {
    @Shadow
    abstract public boolean hasCustomer();

    @Shadow
    abstract protected void produceParticles(ParticleEffect parameters);

    protected BTMerchantEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
}
