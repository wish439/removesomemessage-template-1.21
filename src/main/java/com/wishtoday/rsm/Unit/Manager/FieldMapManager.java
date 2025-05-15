package com.wishtoday.rsm.Unit.Manager;

import com.wishtoday.rsm.Config.ResConfig;
import com.wishtoday.rsm.Unit.MatchMode;
import com.wishtoday.rsm.Unit.RemoveStatus;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class FieldMapManager<K, V> implements CustomFieldManager<Map<K, V>> {
    @Getter
    private String Text;
    private Map<K, V> map;
    private RemoveStatus removeStatus = RemoveStatus.ALL;
    private MatchMode matchMode = MatchMode.CONTAINS;

    public FieldMapManager(Map<K, V> map, RemoveStatus removeStatus, String text) {
        this.map = map;
        this.removeStatus = removeStatus;
        this.Text = text;
    }

    public FieldMapManager(Map<K, V> map, RemoveStatus removeStatus) {
        this.map = map;
        this.removeStatus = removeStatus;
    }

    public FieldMapManager(Map<K, V> map) {
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
        return Optional.ofNullable(this.removeStatus).orElseGet(() -> {
            this.setRemoveStatus(RemoveStatus.ALL);
            return this.removeStatus;
        });
    }

    @Override
    public void setRemoveStatus(RemoveStatus removeStatus) {
        this.removeStatus = removeStatus;
        ResConfig.saveConfigs();
    }

    @Override
    public MatchMode getMatchMode() {
        return Optional.ofNullable(this.matchMode).orElseGet(() -> {
            this.setMatchMode(MatchMode.CONTAINS);
            return this.matchMode;
        });
    }

    @Override
    public void setMatchMode(MatchMode matchMode) {
        this.matchMode = matchMode;
        ResConfig.saveConfigs();
    }

    public void setKV(K key, V value) {
        this.map.put(key, value);
        ResConfig.saveConfigs();
    }

    public void setText(String text) {
        this.Text = text;
        ResConfig.saveConfigs();
    }
}
