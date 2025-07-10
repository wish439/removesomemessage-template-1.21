package com.wishtoday.rsm.Util.Strategy.StopMessageStrategy;

import com.wishtoday.rsm.Config.Configs;
import com.wishtoday.rsm.RemoveSomeMessage;
import com.wishtoday.rsm.Util.Config.DefaultConfigEnum;
import net.minecraft.text.Text;

public class JoinGameMessages implements StopMessages {
    @Override
    public boolean intercept(Text text, Configs configs) {
        return text.getString().contains("加入了游戏")
                && DefaultConfigEnum.JOINGAME.getValue();
    }

    @Override
    public String getReturnValue(String text) {
        return text + "由" + RemoveSomeMessage.MOD_ID + " Mod 加入游戏拦截器发起的拦截";
    }
}
