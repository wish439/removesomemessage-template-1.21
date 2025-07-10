package com.wishtoday.rsm.Util.Config;

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
