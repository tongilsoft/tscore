package com.tongilsoft.tscore.interfaces.sync;

public interface ServerSyncCallbacks {
    void reloadOnSyncReady(boolean detachFirst, String action);
}