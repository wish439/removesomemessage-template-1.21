package com.wishtoday.rsm.Event.EventValues.InterceptMessages;

import com.wishtoday.rsm.Config.Configs;
import com.wishtoday.rsm.Event.EventValues.StopMessages;
import com.wishtoday.rsm.RemoveSomeMessage;
import net.minecraft.text.Text;

public class QuitGameMessages implements StopMessages {
    @Override
    public boolean intercept(Text text, boolean b, Configs configs) {
        return text.getString().contains("退出了游戏")
                && configs.isRemoveQuitGameMessages();
    }

    @Override
    public String getReturnValue(String text) {
        return text + "由" + RemoveSomeMessage.MOD_ID + " Mod 退出游戏拦截器发起的拦截";
    }
}
