package com.wishtoday.rsm.Util.Manager;

import com.wishtoday.rsm.Util.Config.MatchMode;
import com.wishtoday.rsm.Util.Config.RemoveStatus;

public interface CustomFieldManager<E> extends CustomManager<E> {
    RemoveStatus getRemoveStatus();
    void setRemoveStatus(RemoveStatus removeStatus);
    MatchMode getMatchMode();
    void setMatchMode(MatchMode matchMode);
}
