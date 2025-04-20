package com.wishtoday.rsm.mixin;

import com.wishtoday.rsm.Unit.Config.DefaultConfigEnum;
import net.minecraft.client.network.ClientAdvancementManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientAdvancementManager.class)
public class ClientAdvancementManagerMixin {
    @Inject(method = "onAdvancements", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/toast/ToastManager;add(Lnet/minecraft/client/toast/Toast;)V"), cancellable = true)
    private void onAdvancements(CallbackInfo ci) {
        if (DefaultConfigEnum.ADV.getValue())
            ci.cancel();
    }
}
