package com.wishtoday.rsm.Util.Strategy.StopMessageStrategy;

import com.wishtoday.rsm.Config.Configs;
import com.wishtoday.rsm.RemoveSomeMessage;
import com.wishtoday.rsm.Util.Config.DefaultConfigEnum;
import net.minecraft.text.Text;

public class QuitGameMessages implements StopMessages {
    @Override
    public boolean intercept(Text text, Configs configs) {
        return text.getString().contains("退出了游戏")
                && DefaultConfigEnum.QUITGAME.getValue();
    }

    @Override
    public String getReturnValue(String text) {
        return text + "由" + RemoveSomeMessage.MOD_ID + " Mod 退出游戏拦截器发起的拦截";
    }
}
