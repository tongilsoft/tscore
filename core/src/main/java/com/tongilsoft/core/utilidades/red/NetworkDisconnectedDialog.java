package com.tongilsoft.core.utilidades.red;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class NetworkDisconnectedDialog extends DialogFragment {

    public interface ServerCallbacks {
        boolean showNetworkDisconnectedDialog(boolean cancelable, String title, NetworkDisconnectedException nde);
        boolean hideNetworkDisconnectedDialog();
    }

    public interface ServerActionCallbacks{
        void onCancelNetworkTask(DialogFragment dialog);
        void onRetryNetworkTask(DialogFragment dialog);
    }

    public interface ClientCallbacks{
        void handleNetworkDisconnectedDialog();
    }

    public static final String
            TAG         =   "NetworkDisconnDlg",
            FIRMA       =   "DLG_NETWORK_DISCONNECTED";

    private String
            DLG_TITLE   ="Titulo indefinido.",
            DLG_MESSAGE ="Mensaje de error indefinido.";

    public ServerActionCallbacks serverActionCallbacks;

    public void setTitle(String title){
        DLG_TITLE = title;
    }

    public void setMessage(String message){
        DLG_MESSAGE = message;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG,"onAttach()");
        try {
            serverActionCallbacks = (ServerActionCallbacks) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " la interfaz ServerMonoprogressCallbacks no está implementada.");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Log.d(TAG,"onCreateDialog()");

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle(DLG_TITLE)
                .setMessage(DLG_MESSAGE)
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        serverActionCallbacks.onCancelNetworkTask(NetworkDisconnectedDialog.this);
                    }
                })
                .setPositiveButton("Reintentar",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        serverActionCallbacks.onRetryNetworkTask(NetworkDisconnectedDialog.this);
                    }
                });
        Dialog d=builder.create();
        d.setCanceledOnTouchOutside(false);
        return d;
    }
}
