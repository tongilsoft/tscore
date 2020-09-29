package com.tongilsoft.tscore.interfaces.progressbars.multi;

import com.tongilsoft.tscore.utilidades.ProgressInfo;

public interface ClientMultiprogressCallbacks {
    void handleAProgressBar(int action, int progressId, ProgressInfo pi);
}
