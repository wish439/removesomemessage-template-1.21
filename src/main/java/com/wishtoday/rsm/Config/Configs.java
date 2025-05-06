package com.wishtoday.rsm.Config;

import com.wishtoday.rsm.Unit.Config.ConfigProject;
import com.wishtoday.rsm.Unit.Manager.ListManager;
import com.wishtoday.rsm.Unit.Manager.MapManager;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class Configs {
    private final ConfigProject<Boolean> removeSameTimeMessage = ConfigProject.of(false);
    private final ConfigProject<Boolean> removeMusicMessages = ConfigProject.of(false);
    private final ConfigProject<Boolean> removeAdvMessages = ConfigProject.of(false);
    private final ConfigProject<Boolean> removeJoinGameMessages = ConfigProject.of(false);;
    private final ConfigProject<Boolean> removeQuitGameMessages = ConfigProject.of(false);;
    private final ListManager<String> RemoveMessage = new ListManager<>(new ArrayList<>());
    private final MapManager<String, String> ReceiveMessageAndCommand = new MapManager<>(new HashMap<>());
}
