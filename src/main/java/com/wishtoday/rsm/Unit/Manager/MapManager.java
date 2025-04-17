package com.wishtoday.rsm.Unit.Manager;

import com.wishtoday.rsm.Config.ResConfig;
import com.wishtoday.rsm.Unit.RemoveStatus;

import java.util.Map;

public class MapManager<K,V> implements CustomManager<Map<K,V>>{
    private String Text;
    private Map<K,V> map;
    private RemoveStatus removeStatus = RemoveStatus.ALL;
    public MapManager(Map<K,V> map, RemoveStatus removeStatus,String text) {
        this.map = map;
        this.removeStatus = removeStatus;
        this.Text = text;
    }
    public MapManager(Map<K,V> map, RemoveStatus removeStatus) {
        this.map = map;
        this.removeStatus = removeStatus;
    }
    public MapManager(Map<K,V> map) {
        this.map = map;
    }
    @Override
    public Map<K, V> get() {
        return this.map;
    }

    @Override
    public void set(Map<K, V> map) {
        this.map = map;
        ResConfig.saveConfigs();
    }

    @Override
    public RemoveStatus getRemoveStatus() {
        return this.removeStatus;
    }

    @Override
    public void setRemoveStatus(RemoveStatus removeStatus) {
        this.removeStatus = removeStatus;
        ResConfig.saveConfigs();
    }

    public String getText() {
        return this.Text;
    }

    public void setText(String text) {
        this.Text = text;
        ResConfig.saveConfigs();
    }
}
