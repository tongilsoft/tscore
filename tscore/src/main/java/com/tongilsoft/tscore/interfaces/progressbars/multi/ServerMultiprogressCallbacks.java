package com.tongilsoft.tscore.interfaces.progressbars.multi;

import com.tongilsoft.tscore.utilidades.ProgressInfo;

public interface ServerMultiprogressCallbacks {
    boolean putAFrgProgressBar(int firma);
    boolean removeAFrgProgressBar(int firma);
    void refreshAFrgProgressBar(int guiSignature, ProgressInfo progressInfo);
    boolean removeAllFrgProgressBars();
}
