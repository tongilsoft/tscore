package com.tongilsoft.core.interfaces.dialogs;

public interface ClientNotificationDialogCallbacks {
    void handleNotificationDialog(int action, boolean cancelable, int dialogType, String title, String message);
}
