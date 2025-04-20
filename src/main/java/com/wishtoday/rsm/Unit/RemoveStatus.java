package com.wishtoday.rsm.Unit;

public enum RemoveStatus {
    PLAYER {
        @Override
        public String getKey() {
            return "rsm.gui.player";
        }

        @Override
        public RemoveStatus getNext() {
            return SERVER;
        }
    },
    SERVER {
        @Override
        public String getKey() {
            return "rsm.gui.server";
        }

        @Override
        public RemoveStatus getNext() {
            return ALL;
        }
    },
    ALL {
        @Override
        public String getKey() {
            return "rsm.gui.all";
        }

        @Override
        public RemoveStatus getNext() {
            return PLAYER;
        }
    };

    public abstract String getKey();
    public abstract RemoveStatus getNext();
}
