package com.teampotato.targetato.mixin;

import com.teampotato.targetato.Targetato;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public abstract class MixinMobEntity extends LivingEntity {
    protected MixinMobEntity(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);
    }

    @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
    private void injectSetTarget(LivingEntity entity, CallbackInfo ci) {
        if (this.getType().getRegistryName() == null || (entity instanceof ServerPlayerEntity && !entity.isSpectator() && !((ServerPlayerEntity) entity).isCreative()) || !entity.getType().getCategory().equals(EntityClassification.MONSTER)) return;
        boolean checker = Targetato.LIST.get().contains(this.getType().getRegistryName().toString());
        if (Targetato.MODE.get().equals("B")) {
            if (checker) return;
        } else {
            if (!checker) return;
        }
        ci.cancel();
    }
}
