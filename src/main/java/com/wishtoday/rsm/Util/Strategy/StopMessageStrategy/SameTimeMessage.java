package com.wishtoday.rsm.Util.Strategy.StopMessageStrategy;

import com.wishtoday.rsm.Config.Configs;
import com.wishtoday.rsm.RemoveSomeMessage;
import net.minecraft.text.Text;

import java.time.Instant;

public class SameTimeMessage implements StopMessages {
    public static long TimeSecond = Instant.now().getEpochSecond();
    @Override
    public boolean intercept(Text text, Configs configs) {
        boolean l = false;
        if (configs.getRemoveSameTimeMessage().get()
                && TimeSecond == Instant.now().getEpochSecond())
            l = true;
        else TimeSecond = Instant.now().getEpochSecond();
        return l;
    }

    @Override
    public String getReturnValue(String text) {
        return text + "由" + RemoveSomeMessage.MOD_ID + " Mod 同时间消息拦截器发起的拦截";
    }
}
