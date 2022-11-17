package com.chimericdream.miniblockmerchants.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MerchantEntity.class)
abstract public class MMMerchantEntityMixin extends MobEntity {
    @Shadow
    abstract public boolean hasCustomer();

    @Shadow
    abstract protected void produceParticles(ParticleEffect parameters);

    @Shadow @Nullable public abstract PlayerEntity getCustomer();

    protected MMMerchantEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
}
