package com.tongilsoft.core.gui.activity;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;

import com.tongilsoft.core.utilidades.TSLogging;
import com.tongilsoft.core.utilidades.red.NetworkDisconnectedDialog;
import com.tongilsoft.core.utilidades.red.NetworkDisconnectedException;

/**
 * Created by tongilsoft on 28/8/2019.
 */
public abstract class TSNetworkFriendlyActivity extends TSBaseActivity implements
        NetworkDisconnectedDialog.ServerCallbacks {
    public static final String NFA_TAG = "TSNetFriendlyActivity";
    private final TSLogging nfaLog =new TSLogging(false,false,false);
    public NetworkDisconnectedDialog noNetDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nfaLog.d(NFA_TAG,"onCreate()");

    }

    @Override
    protected void onPause(){
        super.onPause();
        nfaLog.d(NFA_TAG,"onPause()");
        hideNetworkDisconnectedDialog();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        nfaLog.d(NFA_TAG,"onDestroy()");
    }

    @Override
    public boolean showNetworkDisconnectedDialog(boolean cancelable, String title, NetworkDisconnectedException nde){
        nfaLog.d(NFA_TAG,"showNetworkDisconnectedDialog() -> " + NetworkDisconnectedDialog.FIRMA);

        //Fragment Manager
        FragmentManager fragmentManager=getSupportFragmentManager();

        if(fragmentManager != null) {

            noNetDlg = new NetworkDisconnectedDialog();
            noNetDlg.setCancelable(cancelable);
            noNetDlg.setTitle(title);
            noNetDlg.setMessage(nde.getMessage());
            noNetDlg.show(fragmentManager, NetworkDisconnectedDialog.FIRMA);

            nfaLog.d(NFA_TAG,"showNetworkDisconnectedDialog() -> MOSTRADO" );

            return true;
        }

        nfaLog.d(NFA_TAG,"showNetworkDisconnectedDialog() -> NO MOSTRADO >> NO HAY GUI" );

        return false;
    }

    @Override
    public boolean hideNetworkDisconnectedDialog() {
        nfaLog.d(NFA_TAG,"hideNetworkDisconnectedDialog() -> " + NetworkDisconnectedDialog.FIRMA);

        //Fragment Manager
        FragmentManager fragmentManager=getSupportFragmentManager();

        if(fragmentManager != null) {
            if (noNetDlg != null) {
                //Hacemos el dismiss
                noNetDlg.dismissAllowingStateLoss();
                nfaLog.d(NFA_TAG, "hideNetworkDisconnectedDialog() -> DISMISSED" );
                //Quitar dialogo
                fragmentManager.beginTransaction().remove(noNetDlg).commitNowAllowingStateLoss();
                nfaLog.d(NFA_TAG, "hideNetworkDisconnectedDialog() -> QUITADO");
                noNetDlg=null;
                return true;
            }
        }
        nfaLog.d(NFA_TAG, "NO QUITADO -> NO ENCONTRADO o NO HAY GUI");
        return false;
    }
}
