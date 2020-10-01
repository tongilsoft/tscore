package com.tongilsoft.core.interfaces.progressbars.mono;

import com.tongilsoft.core.utilidades.ProgressInfo;

public interface ServerMonoprogressCallbacks {
    boolean putFrgProgressBar();
    boolean removeFrgProgressBar();
    void refreshProgressBar(ProgressInfo progressInfo);
}
