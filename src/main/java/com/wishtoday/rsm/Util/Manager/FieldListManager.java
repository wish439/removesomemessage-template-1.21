package com.wishtoday.rsm.Util.Manager;

import com.wishtoday.rsm.Config.ResConfig;
import com.wishtoday.rsm.Util.Config.MatchMode;
import com.wishtoday.rsm.Util.Config.RemoveStatus;

import java.util.List;
import java.util.Optional;

public class FieldListManager<E> implements CustomFieldManager<List<E>> {
    private List<E> list;
    private RemoveStatus removeStatus = RemoveStatus.ALL;
    private MatchMode matchMode = MatchMode.CONTAINS;

    public FieldListManager(List<E> list, RemoveStatus removeStatus, MatchMode matchMode) {
        this.list = list;
        this.setRemoveStatus(removeStatus);
        this.setMatchMode(matchMode);
    }

    public FieldListManager(List<E> list) {
        this.list = list;
    }

    @Override
    public List<E> get() {
        return list;
    }

    @Override
    public void set(List<E> es) {
        this.list = es;
        ResConfig.saveConfigs();
    }

    @Override
    public RemoveStatus getRemoveStatus() {
        return Optional.ofNullable(this.removeStatus).orElseGet(() -> {
            this.setRemoveStatus(RemoveStatus.ALL);
            return this.removeStatus;
        });
        //return this.removeStatus;
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
        //return this.matchMode;
    }

    @Override
    public void setMatchMode(MatchMode matchMode) {
        this.matchMode = matchMode;
        ResConfig.saveConfigs();
    }
}
