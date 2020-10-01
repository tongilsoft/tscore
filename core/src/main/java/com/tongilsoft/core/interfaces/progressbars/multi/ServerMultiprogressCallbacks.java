package com.tongilsoft.core.interfaces.progressbars.multi;

import com.tongilsoft.core.utilidades.ProgressInfo;

public interface ServerMultiprogressCallbacks {
    boolean putAFrgProgressBar(int firma);
    boolean removeAFrgProgressBar(int firma);
    void refreshAFrgProgressBar(int guiSignature, ProgressInfo progressInfo);
    boolean removeAllFrgProgressBars();
}
