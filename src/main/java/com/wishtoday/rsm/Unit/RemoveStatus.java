package com.wishtoday.rsm.Unit;

import lombok.Getter;
import net.minecraft.text.Text;

@Getter
public enum RemoveStatus {
    PLAYER("rsm.gui.player") {
        @Override
        public RemoveStatus getNext() {
            return SERVER;
        }
    },
    SERVER("rsm.gui.server") {
        @Override
        public RemoveStatus getNext() {
            return ALL;
        }
    },
    ALL("rsm.gui.all") {
        @Override
        public RemoveStatus getNext() {
            return PLAYER;
        }
    };
    private final String key;
    RemoveStatus(String key) {
        this.key = key;
    }

    public abstract RemoveStatus getNext();
    public Text getTranslation(){
        return Text.translatable(key);
    }
}
