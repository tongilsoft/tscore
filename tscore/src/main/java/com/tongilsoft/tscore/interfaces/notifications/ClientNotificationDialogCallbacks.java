package com.tongilsoft.tscore.interfaces.notifications;

public interface ClientNotificationDialogCallbacks {
    void handleNotificationDialog(int action, boolean cancelable, int dialogType, String title, String message);
}
