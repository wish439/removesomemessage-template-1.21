package com.wishtoday.rsm.Util.Config;

import lombok.Getter;
import net.minecraft.text.Text;

@Getter
public enum RemoveStatus {
    PLAYER("rsm.gui.player", true) {
        @Override
        public RemoveStatus getNext() {
            return SERVER;
        }
    },
    SERVER("rsm.gui.server", false) {
        @Override
        public RemoveStatus getNext() {
            return ALL;
        }
    },
    ALL("rsm.gui.all", true) {
        @Override
        public RemoveStatus getNext() {
            return PLAYER;
        }
    };
    private final boolean canRemovePlayer;
    private final String key;

    RemoveStatus(String key, boolean isCanRemovePlayer) {
        this.key = key;
        this.canRemovePlayer = isCanRemovePlayer;
    }

    public abstract RemoveStatus getNext();

    public Text getTranslation() {
        return Text.translatable(key);
    }

    //player = true,player = true --- true
    //all = true,player = true --- true
    //server = false,server = false --- true
    //all = true,server = false --- true
    public boolean canRemoveFrom(RemoveStatus status) {
        if (this == ALL) return true;
        return canRemovePlayer == status.canRemovePlayer;
    }
}