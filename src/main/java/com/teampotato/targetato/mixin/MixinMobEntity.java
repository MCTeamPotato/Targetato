package com.teampotato.targetato.mixin;

import com.teampotato.targetato.Targetato;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public abstract class MixinMobEntity extends LivingEntity{
    protected MixinMobEntity(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);
    }

    @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
    private void injectSetTarget(LivingEntity entity, CallbackInfo ci) {
        if (this.getType().getRegistryName() == null || Targetato.ENTITY_LIST.get().contains(this.getType().getRegistryName().toString())) return;
        if (entity instanceof PlayerEntity) return;
        ci.cancel();
    }
}
