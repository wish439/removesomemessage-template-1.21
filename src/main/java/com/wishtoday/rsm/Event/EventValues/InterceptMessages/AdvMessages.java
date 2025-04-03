package com.wishtoday.rsm.Event.EventValues.InterceptMessages;

import com.wishtoday.rsm.Config.Configs;
import com.wishtoday.rsm.Event.EventValues.StopMessages;
import com.wishtoday.rsm.RemoveSomeMessage;
import net.minecraft.text.Text;

public class AdvMessages implements StopMessages {
    @Override
    public boolean intercept(Text text, boolean b, Configs configs) {
        return (text.getString().contains("取得了进度")
                || text.getString().contains("完成了挑战"))
                && configs.isRemoveAdvMessages();
    }

    @Override
    public String getReturnValue(String text) {
        return text + "由" + RemoveSomeMessage.MOD_ID + " Mod 成就拦截器发起的拦截";
    }
}
