package com.wishtoday.rsm.Event.EventValues.InterceptMessages;

import com.wishtoday.rsm.Config.Configs;
import com.wishtoday.rsm.Event.EventValues.StopMessages;
import com.wishtoday.rsm.RemoveSomeMessage;
import net.minecraft.text.Text;

public class JoinGameMessages implements StopMessages {
    @Override
    public boolean intercept(Text text, boolean b, Configs configs) {
        return text.getString().contains("加入了游戏")
                && configs.isRemoveJoinGameMessages();
    }

    @Override
    public String getReturnValue(String text) {
        return text + "由" + RemoveSomeMessage.MOD_ID + " Mod 加入游戏拦截器发起的拦截";
    }
}
