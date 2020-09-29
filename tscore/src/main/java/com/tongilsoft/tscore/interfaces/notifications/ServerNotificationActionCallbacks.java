package com.tongilsoft.tscore.interfaces.notifications;

import androidx.fragment.app.DialogFragment;

public interface ServerNotificationActionCallbacks {
    void onNegativeButtonPressed(DialogFragment dialog);
    void onPositiveButtonPressed(DialogFragment dialog);
}
