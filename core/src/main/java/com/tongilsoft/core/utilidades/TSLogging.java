package com.tongilsoft.core.utilidades;

import android.util.Log;

public class TSLogging {
    private static final String APP_TAG = "TS";
    private boolean _dbg, _inf, _exc;

    public TSLogging(boolean dbg, boolean inf, boolean exc) {
        _dbg=dbg;
        _inf=inf;
        _exc=exc;
    }

    public void d(String tag, String msg) {
        if (_dbg) Log.d(APP_TAG,tag + " -> " + msg);
    }

    public void i(String tag, String msg){
        if (_inf) Log.i(APP_TAG,tag + " -> " + msg);
    }

    public void e(String tag, String msg, Throwable t){
        if (_exc) Log.e(APP_TAG,tag + " -> " + msg, t);
    }
}
