package com.wishtoday.rsm.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.wishtoday.rsm.Unit.Config.DefaultConfigEnum;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientAdvancementManager;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.CLIENT)
@Mixin(ClientAdvancementManager.class)
public class ClientAdvancementManagerMixin {
    //将
    //this.client.getToastManager().add(new AdvancementToast(placedAdvancement.getAdvancementEntry()));
    //包围在if(!DefaultConfigEnum.ADV.getValue())中
    @WrapWithCondition(method = "onAdvancements",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/toast/ToastManager;add(Lnet/minecraft/client/toast/Toast;)V"))
    private boolean onAdvancementsAdded(ToastManager instance, Toast toast) {
        return !DefaultConfigEnum.ADV.getValue();
    }
}
