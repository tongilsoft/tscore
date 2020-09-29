package com.tongilsoft.tscore.gui.activity;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;

import com.tongilsoft.tscore.utilidades.PGLogging;
import com.tongilsoft.tscore.utilidades.red.NetworkDisconnectedDialog;
import com.tongilsoft.tscore.utilidades.red.NetworkDisconnectedException;

/**
 * Created by tongilsoft on 28/8/2019.
 */
public abstract class PGNetworkFriendlyActivity extends PGBaseActivity implements
        NetworkDisconnectedDialog.ServerCallbacks {
    public static final String PGNF_TAG = "PGNetFriendlyActivity";
    private final PGLogging pgnfLog =new PGLogging(false,false,false);
    public NetworkDisconnectedDialog noNetDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pgnfLog.d(PGNF_TAG,"onCreate()");

    }

    @Override
    protected void onPause(){
        super.onPause();
        pgnfLog.d(PGNF_TAG,"onPause()");
        hideNetworkDisconnectedDialog();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        pgnfLog.d(PGNF_TAG,"onDestroy()");
    }

    @Override
    public boolean showNetworkDisconnectedDialog(boolean cancelable, String title, NetworkDisconnectedException nde){
        pgnfLog.d(PGNF_TAG,"showNetworkDisconnectedDialog() -> " + NetworkDisconnectedDialog.FIRMA);

        //Fragment Manager
        FragmentManager fragmentManager=getSupportFragmentManager();

        if(fragmentManager != null) {

            noNetDlg = new NetworkDisconnectedDialog();
            noNetDlg.setCancelable(cancelable);
            noNetDlg.setTitle(title);
            noNetDlg.setMessage(nde.getMessage());
            noNetDlg.show(fragmentManager, NetworkDisconnectedDialog.FIRMA);

            pgnfLog.d(PGNF_TAG,"showNetworkDisconnectedDialog() -> MOSTRADO" );

            return true;
        }

        pgnfLog.d(PGNF_TAG,"showNetworkDisconnectedDialog() -> NO MOSTRADO >> NO HAY GUI" );

        return false;
    }

    @Override
    public boolean hideNetworkDisconnectedDialog() {
        pgnfLog.d(PGNF_TAG,"hideNetworkDisconnectedDialog() -> " + NetworkDisconnectedDialog.FIRMA);

        //Fragment Manager
        FragmentManager fragmentManager=getSupportFragmentManager();

        if(fragmentManager != null) {
            if (noNetDlg != null) {
                //Hacemos el dismiss
                noNetDlg.dismissAllowingStateLoss();
                pgnfLog.d(PGNF_TAG, "hideNetworkDisconnectedDialog() -> DISMISSED" );
                //Quitar dialogo
                fragmentManager.beginTransaction().remove(noNetDlg).commitNowAllowingStateLoss();
                pgnfLog.d(PGNF_TAG, "hideNetworkDisconnectedDialog() -> QUITADO");
                noNetDlg=null;
                return true;
            }
        }
        pgnfLog.d(PGNF_TAG, "NO QUITADO -> NO ENCONTRADO o NO HAY GUI");
        return false;
    }
}
