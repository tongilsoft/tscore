package com.tongilsoft.core.interfaces.progressbars.multi;

import com.tongilsoft.core.utilidades.ProgressInfo;

public interface ClientMultiprogressCallbacks {
    void handleAProgressBar(int action, int progressId, ProgressInfo pi);
}
