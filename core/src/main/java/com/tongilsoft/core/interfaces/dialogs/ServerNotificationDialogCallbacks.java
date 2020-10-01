package com.tongilsoft.core.interfaces.dialogs;

public interface ServerNotificationDialogCallbacks {
    boolean showNotificationDialog(boolean cancelable, int dialogType, String title, String message);
    boolean hideNotificationDialog();
}
