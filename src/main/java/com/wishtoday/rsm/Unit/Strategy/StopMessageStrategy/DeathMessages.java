package com.wishtoday.rsm.Unit.Strategy.StopMessageStrategy;

import com.wishtoday.rsm.Config.Configs;
import com.wishtoday.rsm.RemoveSomeMessage;
import com.wishtoday.rsm.Unit.Config.DefaultConfigEnum;
import com.wishtoday.rsm.Unit.RemoveStatus;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;

public class DeathMessages implements StopMessages {

    @Override
    public boolean intercept(Text text, Configs configs) {
        if (text.getContent().getType() != TranslatableTextContent.TYPE) {
            return false;
        }
        TranslatableTextContent translatableTextContent = (TranslatableTextContent) text.getContent();
        RemoveSomeMessage.LOGGER.info("TranslationKey:" + translatableTextContent.getKey());
        return translatableTextContent.getKey().startsWith("death.")
                && DefaultConfigEnum.DEATH.getValue();
    }

    @Override
    public String getReturnValue(String text) {
        return text + "由" + RemoveSomeMessage.MOD_ID + " Mod 死亡消息拦截器发起的拦截";
    }
}
