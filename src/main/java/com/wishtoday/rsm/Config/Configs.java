package com.wishtoday.rsm.Config;

import com.wishtoday.rsm.Unit.Config.ConfigProject;
import com.wishtoday.rsm.Unit.Manager.FieldListManager;
import com.wishtoday.rsm.Unit.Manager.FieldMapManager;
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
    private final FieldListManager<String> RemoveMessage = new FieldListManager<>(new ArrayList<>());
    private final FieldMapManager<String, String> ReceiveMessageAndCommand = new FieldMapManager<>(new HashMap<>());
}
