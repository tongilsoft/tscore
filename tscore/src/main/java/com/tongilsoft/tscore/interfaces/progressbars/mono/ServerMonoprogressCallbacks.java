package com.tongilsoft.tscore.interfaces.progressbars.mono;

import com.tongilsoft.tscore.utilidades.ProgressInfo;

public interface ServerMonoprogressCallbacks {
    boolean putFrgProgressBar();
    boolean removeFrgProgressBar();
    void refreshProgressBar(ProgressInfo progressInfo);
}
