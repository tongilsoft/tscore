package com.tongilsoft.core.interfaces.fragments;

import androidx.fragment.app.Fragment;

public interface FragmentClientCallbacks {
    void handleFragmentOnServer(int action, Fragment fragment, String tag);
}