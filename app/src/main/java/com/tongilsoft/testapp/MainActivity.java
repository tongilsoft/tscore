package com.tongilsoft.testapp;

import com.tongilsoft.tscore.gui.activity.TSBaseActivity;
import com.tongilsoft.tscore.gui.dialog.NotificationDialog;
import com.tongilsoft.tscore.utilidades.TSLogging;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class MainActivity extends TSBaseActivity {
    private static final String TAG = "MainActivity";
    private final TSLogging mLog = new TSLogging(true,true, true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLog.d(TAG,"onCreate() -> " + getString(R.string.app_test_string));

        Button btnMensaje = findViewById(R.id.btn_message);
        btnMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotificationDialog(false, NotificationDialog.OPTION_DLG_TYPE_ACCEPT, "Mensaje de Prueba",
                        "Este mensaje es prueba de que la importación y uso de TSBaseActivity desde el modulo tscore funciona correctamente.");
            }
        });
    }

    @Override
    public void onPositiveButtonPressed(DialogFragment dialog) {
        mLog.d(TAG,"onPositiveButtonPressed()");

        Context context = getApplicationContext();

        if(context != null) {
            Toast.makeText(context, "Positive Button Pressed", Toast.LENGTH_LONG).show();
            mLog.i(TAG,"onPositiveButtonPressed() -> Dialogo ocultado...");
        } else{
            mLog.d(TAG,"onPositiveButtonPressed() -> context is NULL");
        }

        super.onPositiveButtonPressed(dialog);

    }
}
