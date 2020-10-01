package com.tongilsoft.core.gui.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.tongilsoft.core.utilidades.TSLogging;
import com.tongilsoft.core.interfaces.dialogs.ServerNotificationActionCallbacks;
import com.tongilsoft.core.interfaces.dialogs.ServerNotificationDialogCallbacks;
import com.tongilsoft.core.gui.dialog.NotificationDialog;

/**
 * Created by tongilsoft on 1/7/2019.
 */
public class TSBaseActivity extends AppCompatActivity implements
        ServerNotificationDialogCallbacks,
        ServerNotificationActionCallbacks{
    private static final String BA_TAG = "TSBaseActivity";
    private final TSLogging baLog =new TSLogging(true,true,true);
    public boolean saveOnPause, closeOnPause;
    public NotificationDialog notificationDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baLog.d(BA_TAG,"onCreate()");

        saveOnPause = closeOnPause = true;
    }

    @Override
    protected void onPause(){
        super.onPause();
        baLog.d(BA_TAG,"onPause()");

        hideNotificationDialog();
    }

    @Override
    public boolean showNotificationDialog(boolean cancelable, int dialogType, String title, String message) {
        baLog.d(BA_TAG,"showNotificationDialog() -> " + NotificationDialog.FIRMA);

        //Fragment Manager
        FragmentManager fragmentManager=getSupportFragmentManager();

        if(fragmentManager != null) {
            notificationDlg = new NotificationDialog();
            notificationDlg.setCancelable(cancelable);
            notificationDlg.setDialogType(dialogType);
            notificationDlg.setTitle(title);
            notificationDlg.setMessage(message);
            notificationDlg.show(fragmentManager, NotificationDialog.FIRMA);
            baLog.d(BA_TAG,"showNotificationDialog() -> MOSTRADO" );
            return true;
        }

        baLog.d(BA_TAG,"showNotificationDialog() -> NO MOSTRADO >> NO HAY GUI" );

        return false;
    }

    @Override
    public boolean hideNotificationDialog() {
        baLog.d(BA_TAG,"hideNotificationDialog() -> " + NotificationDialog.FIRMA);

        //Fragment Manager
        FragmentManager fragmentManager=getSupportFragmentManager();

        if(fragmentManager != null) {
            if (notificationDlg != null) {
                //Hacemos el dismiss
                notificationDlg.dismissAllowingStateLoss();
                baLog.d(BA_TAG, "hideNotificationDialog() -> DISMISSED");
                //Quitar dialogo
                fragmentManager.beginTransaction()
                        .remove(notificationDlg)
                        .commitNowAllowingStateLoss();
                baLog.d(BA_TAG, "hideNotificationDialog() -> QUITADO");
                notificationDlg=null;
                return true;
            }
        }
        baLog.d(BA_TAG, "hideNotificationDialog() -> NO QUITADO >> NO ENCONTRADO o NO HAY GUI");
        return false;
    }

    @Override
    public void onNegativeButtonPressed(DialogFragment dialog) {
        baLog.d(BA_TAG,"onNegativeButtonPressed()");

        hideNotificationDialog();
    }

    @Override
    public void onPositiveButtonPressed(DialogFragment dialog) {
        baLog.d(BA_TAG,"onPositiveButtonPressed()");

        hideNotificationDialog();
    }
}
