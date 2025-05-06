package com.wishtoday.rsm.Unit;

import lombok.Getter;

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
    ALL("rsm.gui.player") {
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
}
