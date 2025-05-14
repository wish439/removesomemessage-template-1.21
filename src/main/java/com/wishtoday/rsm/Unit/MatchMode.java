package com.wishtoday.rsm.Unit;

import net.minecraft.text.Text;

public enum MatchMode {
    CONTAINS("rsm.gui.contains") {
        @Override
        public boolean matches(String s, String p) {
            return s.contains(p) && !s.isEmpty();
        }
    },
    PRECISION("rsm.gui.precision") {
        @Override
        public boolean matches(String s, String p) {
            return s.equals(p) && !s.isEmpty();
        }
    };
    public final String TranslationKey;

    MatchMode(String translationKey) {
        TranslationKey = translationKey;
    }

    public abstract boolean matches(String s, String p);

    public boolean equals(MatchMode mode) {
        return this == mode;
    }
    public Text getTranslation(){
        return Text.translatable(this.TranslationKey);
    }
}
