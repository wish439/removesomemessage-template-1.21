package com.wishtoday.rsm.Unit;

import com.wishtoday.rsm.RemoveSomeMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public enum MatchMode {
    CONTAINS("rsm.gui.contains") {
        @Override
        public boolean matches(String s, String p) {
            return s.contains(p) && !s.isEmpty();
        }

        @Override
        public MatchMode getNextMode() {
            return PRECISION;
        }
    },
    PRECISION("rsm.gui.precision") {
        @Override
        public boolean matches(String s, String p) {
            RemoveSomeMessage.LOGGER.info("消息处理前" + s);
            MinecraftClient mc = MinecraftClient.getInstance();
            String name = mc.player.getGameProfile().getName();
            name = "<" + name + "> ";
            s = s.substring(name.length());
            RemoveSomeMessage.LOGGER.info("消息处理后" + s);
            return s.equals(p) && !s.isEmpty();
        }

        @Override
        public MatchMode getNextMode() {
            return CONTAINS;
        }
    };
    public final String TranslationKey;

    MatchMode(String translationKey) {
        TranslationKey = translationKey;
    }

    public abstract boolean matches(String s, String p);

    public abstract MatchMode getNextMode();

    public boolean equals(MatchMode mode) {
        return this == mode;
    }

    public Text getTranslation() {
        return Text.translatable(this.TranslationKey);
    }
}
