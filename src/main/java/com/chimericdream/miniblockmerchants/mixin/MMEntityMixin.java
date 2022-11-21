package com.chimericdream.miniblockmerchants.mixin;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Entity.class)
abstract public class MMEntityMixin {
    @Shadow @Final
    protected SynchedEntityData entityData;
}
