package com.tongilsoft.tscore.gui.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.tongilsoft.tscore.gui.dialog.NotificationDialog;
import com.tongilsoft.tscore.interfaces.notifications.ServerNotificationActionCallbacks;
import com.tongilsoft.tscore.interfaces.notifications.ServerNotificationDialogCallbacks;
import com.tongilsoft.tscore.utilidades.PGLogging;

/**
 * Created by tongilsoft on 1/7/2019.
 */
public class PGBaseActivity extends AppCompatActivity implements
        ServerNotificationDialogCallbacks,
        ServerNotificationActionCallbacks{
    private static final String PGB_TAG = "PGBaseActivity";
    private final PGLogging mLog=new PGLogging(false,false,false);
    public boolean saveOnPause, closeOnPause;
    public NotificationDialog notificationDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLog.d(PGB_TAG,"onCreate()");
        saveOnPause = closeOnPause = true;
    }

    @Override
    protected void onPause(){
        super.onPause();
        mLog.d(PGB_TAG,"onPause()");
        hideNotificationDialog();
    }

    @Override
    public boolean showNotificationDialog(boolean cancelable, int dialogType, String title, String message) {
        mLog.d(PGB_TAG,"showNotificationDialog()" + NotificationDialog.FIRMA);

        //Fragment Manager
        FragmentManager fragmentManager=getSupportFragmentManager();

        if(fragmentManager != null) {
            notificationDlg = new NotificationDialog();
            notificationDlg.setCancelable(cancelable);
            notificationDlg.setDialogType(dialogType);
            notificationDlg.setTitle(title);
            notificationDlg.setMessage(message);
            notificationDlg.show(fragmentManager, NotificationDialog.FIRMA);
            mLog.d(PGB_TAG,"showNotificationDialog() -> MOSTRADO" );
            return true;
        }

        mLog.d(PGB_TAG,"showNotificationDialog() -> NO MOSTRADO >> NO HAY GUI" );

        return false;
    }

    @Override
    public boolean hideNotificationDialog() {
        mLog.d(PGB_TAG,"hideNotificationDialog()" + NotificationDialog.FIRMA);

        //Fragment Manager
        FragmentManager fragmentManager=getSupportFragmentManager();

        if(fragmentManager != null) {
            if (notificationDlg != null) {
                //Hacemos el dismiss
                notificationDlg.dismissAllowingStateLoss();
                mLog.d(PGB_TAG, "hideNotificationDialog() -> DISMISSED");
                //Quitar dialogo
                fragmentManager.beginTransaction()
                        .remove(notificationDlg)
                        .commitNowAllowingStateLoss();
                mLog.d(PGB_TAG, "hideNotificationDialog() -> QUITADO");
                notificationDlg=null;
                return true;
            }
        }
        mLog.d(PGB_TAG, "hideNotificationDialog() -> NO QUITADO >> NO ENCONTRADO o NO HAY GUI");
        return false;
    }

    @Override
    public void onNegativeButtonPressed(DialogFragment dialog) {
        mLog.d(PGB_TAG,"onNegativeButtonPressed()");
        hideNotificationDialog();
    }

    @Override
    public void onPositiveButtonPressed(DialogFragment dialog) {
        mLog.d(PGB_TAG,"onPositiveButtonPressed()");
        hideNotificationDialog();
    }
}
