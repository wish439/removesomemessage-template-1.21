package com.wishtoday.rsm.Event.EventValues;

import com.wishtoday.rsm.Config.Configs;
import net.minecraft.text.Text;

public interface StopMessages {
    boolean intercept(Text text, boolean b, Configs configs);
    String getReturnValue(String text);
}
