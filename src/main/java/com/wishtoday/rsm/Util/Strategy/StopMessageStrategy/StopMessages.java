package com.wishtoday.rsm.Util.Strategy.StopMessageStrategy;

import com.wishtoday.rsm.Config.Configs;
import com.wishtoday.rsm.RemoveSomeMessage;
import net.minecraft.text.Text;

public interface StopMessages {
    //返回值-true为拦截,false为不拦截
    boolean intercept(Text text, Configs configs);

    default String getReturnValue(String text) {
        return text + "由" + RemoveSomeMessage.MOD_ID + " Mod 自定义消息拦截器发起的拦截";
    }
}