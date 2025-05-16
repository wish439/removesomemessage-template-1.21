package com.wishtoday.rsm.Unit.Config;

import com.wishtoday.rsm.Config.ResConfig;

public enum DefaultConfigEnum {
    ADV {
        @Override
        public boolean getValue() {
            return ResConfig.getConfigs().getRemoveAdvMessages().get();
        }

        @Override
        public void setValue(boolean value) {
            ResConfig.getConfigs().getRemoveAdvMessages().set(value);
        }
    },
    JOINGAME {
        @Override
        public boolean getValue() {
            return ResConfig.getConfigs().getRemoveJoinGameMessages().get();
        }

        @Override
        public void setValue(boolean value) {
            ResConfig.getConfigs().getRemoveJoinGameMessages().set(value);
        }
    },
    DEATH {
        @Override
        public boolean getValue() {
            return ResConfig.getConfigs().getRemoveDeathMessages().get();
        }

        @Override
        public void setValue(boolean value) {
            ResConfig.getConfigs().getRemoveDeathMessages().set(value);
        }
    },
    QUITGAME {
        @Override
        public boolean getValue() {
            return ResConfig.getConfigs().getRemoveQuitGameMessages().get();
        }

        @Override
        public void setValue(boolean value) {
            ResConfig.getConfigs().getRemoveQuitGameMessages().set(value);
        }
    },
    SAMETIME {
        @Override
        public boolean getValue() {
            return ResConfig.getConfigs().getRemoveSameTimeMessage().get();
        }

        @Override
        public void setValue(boolean value) {
            ResConfig.getConfigs().getRemoveSameTimeMessage().set(value);
        }
    };

    public abstract boolean getValue();

    public abstract void setValue(boolean value);
}
