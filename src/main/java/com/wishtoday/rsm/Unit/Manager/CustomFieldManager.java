package com.wishtoday.rsm.Unit.Manager;

import com.wishtoday.rsm.Unit.MatchMode;
import com.wishtoday.rsm.Unit.RemoveStatus;

public interface CustomFieldManager<E> extends CustomManager<E> {
    RemoveStatus getRemoveStatus();
    void setRemoveStatus(RemoveStatus removeStatus);
    MatchMode getMatchMode();
    void setMatchMode(MatchMode matchMode);
}
