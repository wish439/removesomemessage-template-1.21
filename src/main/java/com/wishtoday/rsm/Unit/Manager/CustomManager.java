package com.wishtoday.rsm.Unit.Manager;

public interface CustomManager<E> {
    E get();
    void set(E e);
    //String getText();
    //void setText(String text);
}
