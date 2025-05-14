package com.wishtoday.rsm.Unit.Strategy.StopMessageStrategy;

import com.wishtoday.rsm.Config.Configs;
import com.wishtoday.rsm.RemoveSomeMessage;
import com.wishtoday.rsm.Unit.Config.DefaultConfigEnum;
import net.minecraft.text.Text;

public class AdvMessages implements StopMessages {
    @Override
    public boolean intercept(Text text, Configs configs) {
        return (text.getString().contains("取得了进度")
                || text.getString().contains("完成了挑战")
        || text.getString().contains("达成了目标"))
                && DefaultConfigEnum.ADV.getValue();
    }

    @Override
    public String getReturnValue(String text) {
        return text + "由" + RemoveSomeMessage.MOD_ID + " Mod 成就拦截器发起的拦截";
    }
}
