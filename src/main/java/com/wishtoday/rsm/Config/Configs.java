package com.wishtoday.rsm.Config;

import com.wishtoday.rsm.Unit.Config.ConfigProject;
import com.wishtoday.rsm.Unit.Manager.ListManager;
import com.wishtoday.rsm.Unit.Manager.MapManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Configs {
    private ConfigProject<Boolean> removeSameTimeMessage = new ConfigProject<>(false);
    private ConfigProject<Boolean> removeMusicMessages = new ConfigProject<>(false);
    private ConfigProject<Boolean> removeAdvMessages = new ConfigProject<>(false);
    private ConfigProject<Boolean> removeJoinGameMessages = new ConfigProject<>(false);
    private ConfigProject<Boolean> removeQuitGameMessages = new ConfigProject<>(false);
    private ListManager<String> RemoveMessage = new ListManager<>(new ArrayList<>());
    private MapManager<String, String> ReceiveMessageAndCommand = new MapManager<>(new HashMap<>());
    public ConfigProject<Boolean> getRemoveSameTimeMessage() {
        return removeSameTimeMessage;
    }
    public MapManager<String, String> getReceiveMessageAndCommand() {
        return this.ReceiveMessageAndCommand;
    }
    public ListManager<String> getRemoveMessage() {
        return this.RemoveMessage;
    }
    public ConfigProject<Boolean> getRemoveQuitGameMessages() {
        return this.removeQuitGameMessages;
    }
    public ConfigProject<Boolean> getRemoveJoinGameMessages() {
        return this.removeJoinGameMessages;
    }
    public ConfigProject<Boolean> getRemoveMusicMessages() {
        return this.removeMusicMessages;
    }
    public ConfigProject<Boolean> getRemoveAdvMessages() {
        return this.removeAdvMessages;
    }
}
