package com.wishtoday.rsm.Config;

import com.wishtoday.rsm.Unit.Manager.ListManager;
import com.wishtoday.rsm.Unit.Manager.MapManager;
import com.wishtoday.rsm.Unit.RemoveStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configs {
    private boolean removeMusicMessages = true;
    private boolean removeAdvMessages = true;
    private boolean removeJoinGameMessages = false;
    private boolean removeQuitGameMessages = false;
    private ListManager<String> RemoveMessage = new ListManager<>(new ArrayList<>());
    private MapManager<String,String> ReceiveMessageAndCommand = new MapManager<>(new HashMap<>());
    public MapManager<String,String> getReceiveMessageAndCommand() {
        return this.ReceiveMessageAndCommand;
    }
    public ListManager<String> getRemoveMessage() {
        return this.RemoveMessage;
    }
    //private List<String> RemoveMessages = new ArrayList<>();
    //private List<String> PlayerChatMessages = new ArrayList<>();
    //private RemoveStatus status = RemoveStatus.ALL;
    //private Map<String,String> MessageAndCommand = new HashMap<>();
    //private String MessageAndCommandText = "";

    /*public RemoveStatus getStatus() {
        return this.status;
    }

    public void setStatus(RemoveStatus status) {
        this.status = status;
        ResConfig.saveConfigs();
    }

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
    public List<String> getPlayerChatMessages() {
        return this.PlayerChatMessages;
    }

    public void setPlayerChatMessages(List<String> playerChatMessages) {
        this.PlayerChatMessages = playerChatMessages;
        ResConfig.saveConfigs();
    }
    public List<String> getRemoveMessages() {
        return this.RemoveMessages;
    }
    public void setRemoveMessages(List<String> removeMessages) {
        this.RemoveMessages = removeMessages;
        ResConfig.saveConfigs();
    }*/

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
}
