package com.wishtoday.rsm.Util.Manager;

public interface CustomManager<E> {
    E get();
    void set(E e);
    //String getText();
    //void setText(String text);
}
