package com.tongilsoft.tscore.interfaces.notifications;

public interface ServerNotificationDialogCallbacks {
    boolean showNotificationDialog(boolean cancelable, int dialogType, String title, String message);
    boolean hideNotificationDialog();
}
