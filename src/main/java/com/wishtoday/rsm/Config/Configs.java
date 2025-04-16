package com.wishtoday.rsm.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configs {
    private boolean removeMusicMessages = true;
    private boolean removeAdvMessages = true;
    private boolean removeJoinGameMessages = false;
    private boolean removeQuitGameMessages = false;
    private List<String> RemoveMessages = new ArrayList<>();
    private List<String> PlayerChatMessages = new ArrayList<>();
    private Map<String,String> MessageAndCommand = new HashMap<>();
    private String MessageAndCommandText = "";

    public String getMessageAndCommandText() {
        return this.MessageAndCommandText;
    }

    public void setMessageAndCommandText(String messageAndCommandText) {
        this.MessageAndCommandText = messageAndCommandText;
        ResConfig.saveConfigs();
    }

    public Map<String, String> getMessageAndCommand() {
        return this.MessageAndCommand;
    }

    public void setMessageAndCommand(Map<String, String> messageAndCommand) {
        this.MessageAndCommand = messageAndCommand;
        ResConfig.saveConfigs();
    }
    public void addToMessageAndCommand(String key, String value) {
        this.MessageAndCommand.put(key, value);
        ResConfig.saveConfigs();
    }

    public  boolean isRemoveQuitGameMessages() {
        return this.removeQuitGameMessages;
    }

    public void setRemoveQuitGameMessages(boolean b) {
        this.removeQuitGameMessages = b;
        ResConfig.saveConfigs();
    }

    public boolean isRemoveJoinGameMessages() {
        return this.removeJoinGameMessages;
    }

    public List<String> getRemoveMessages() {
        return this.RemoveMessages;
    }
    public void setRemoveMessages(List<String> removeMessages) {
        this.RemoveMessages = removeMessages;
        ResConfig.saveConfigs();
    }

    public void setRemoveJoinGameMessages(boolean b) {
        this.removeJoinGameMessages = b;
        ResConfig.saveConfigs();
    }

    public boolean isRemoveMusicMessages() {
        return this.removeMusicMessages;
    }

    public void setRemoveMusicMessages(boolean b) {
        this.removeMusicMessages = b;
        ResConfig.saveConfigs();
    }

    public boolean isRemoveAdvMessages() {
        return this.removeAdvMessages;
    }

    public void setRemoveAdvMessages(boolean b) {
        this.removeAdvMessages = b;
        ResConfig.saveConfigs();
    }

    public List<String> getPlayerChatMessages() {
        return this.PlayerChatMessages;
    }

    public void setPlayerChatMessages(List<String> playerChatMessages) {
        this.PlayerChatMessages = playerChatMessages;
        ResConfig.saveConfigs();
    }
}
