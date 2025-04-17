package com.wishtoday.rsm.Unit.Manager;

import com.wishtoday.rsm.Config.ResConfig;
import com.wishtoday.rsm.Unit.RemoveStatus;

import java.util.List;

public class ListManager<E> implements CustomManager<List<E>> {
    private List<E> list;
    private RemoveStatus removeStatus = RemoveStatus.ALL;

    public ListManager(List<E> list,RemoveStatus removeStatus) {
        this.list = list;
        this.removeStatus = removeStatus;
    }
    public ListManager(List<E> list) {
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
        return this.removeStatus;
    }

    @Override
    public void setRemoveStatus(RemoveStatus removeStatus) {
        this.removeStatus = removeStatus;
        ResConfig.saveConfigs();
    }
}
