package com.wishtoday.rsm.Unit.Manager;

import com.wishtoday.rsm.Unit.RemoveStatus;

public interface CustomManager<E> {
    E get();
    void set(E e);
    RemoveStatus getRemoveStatus();
    void setRemoveStatus(RemoveStatus removeStatus);
    //String getText();
    //void setText(String text);
}
