package com.wishtoday.rsm.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.wishtoday.rsm.Unit.Config.DefaultConfigEnum;
import net.minecraft.client.network.ClientAdvancementManager;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientAdvancementManager.class)
public class ClientAdvancementManagerMixin {
    //将此段代码
    // this.client.getToastManager().add(new AdvancementToast(placedAdvancement.getAdvancementEntry()));
    //包围在if(!DefaultConfigEnum.ADV.getValue())中
    @WrapWithCondition(method = "onAdvancements",at = @At(value = "INVOKE", target = "Lnet/minecraft/client/toast/ToastManager;add(Lnet/minecraft/client/toast/Toast;)V"))
    private boolean onAdvancementsAdded(ToastManager instance, Toast toast) {
        return !DefaultConfigEnum.ADV.getValue();
    }
}
