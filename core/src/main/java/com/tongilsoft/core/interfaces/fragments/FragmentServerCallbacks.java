package com.tongilsoft.core.interfaces.fragments;

public interface FragmentServerCallbacks {
    void putFragment(int containerViewId, String tag, int fragmentId);
    void hideFragment(String tag, int fragmentId);
    void backstackFragment(String tag, int fragmentId);
    void removeFragment(String tag, int fragmentId);
}