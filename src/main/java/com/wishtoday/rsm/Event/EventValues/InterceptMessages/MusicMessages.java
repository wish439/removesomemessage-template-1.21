package com.wishtoday.rsm.Event.EventValues.InterceptMessages;

import com.wishtoday.rsm.Config.Configs;
import com.wishtoday.rsm.Event.EventValues.StopMessages;
import com.wishtoday.rsm.RemoveSomeMessage;
import com.wishtoday.rsm.Unit.Config.DefaultConfigEnum;
import net.minecraft.text.Text;

public class MusicMessages implements StopMessages {

    @Override
    public boolean intercept(Text text, boolean b, Configs configs) {
        return text.getString().contains("[AllMusic3]")
                && DefaultConfigEnum.MUSIC.getValue();
    }

    @Override
    public String getReturnValue(String text) {
        return text + "由" + RemoveSomeMessage.MOD_ID + " Mod 音乐拦截器发起的拦截";
    }
}
