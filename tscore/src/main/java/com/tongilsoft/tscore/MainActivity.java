package com.tongilsoft.tscore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tongilsoft.mathlib.Aritmetica;
import com.tongilsoft.core.utilidades.TSLogging;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private final TSLogging mLog = new TSLogging(true,true, true);
    private final Aritmetica aritmetica=new Aritmetica();
    private TextView tvInputA, tvInputB, tvOutputC;
    private Button btnSumar, btnRestar, btnMultiplicar, btnDividir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLog.d(TAG, "onCreate()");

        //Inputs
        tvInputA = findViewById(R.id.et_a_value);
        tvInputB = findViewById(R.id.et_b_value);
        tvOutputC = findViewById(R.id.et_c_value);

        //Buttons
        btnSumar = findViewById(R.id.btn_sumar);
        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int a = Integer.parseInt(String.valueOf(tvInputA.getText()));
                    int b = Integer.parseInt(String.valueOf(tvInputB.getText()));
                    String logMsg = String.format("Se sumarán A(%d) y B(%d)",a,b);
                    mLog.d(TAG, logMsg);
                    int c = aritmetica.sumar(a, b);
                    tvOutputC.setText(Integer.toString(c));
                } catch (Exception e){
                    String errMsg = e.getMessage() + " en btnSumar";
                    Context context = getApplicationContext();
                    if(context != null){
                        Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();
                    }
                    mLog.d(TAG, errMsg);
                }
            }
        });

        btnRestar = findViewById(R.id.btn_restar);
        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int a = Integer.parseInt(tvInputA.getText().toString());
                    int b = Integer.parseInt(tvInputB.getText().toString());
                    String logMsg = String.format("Se restarán A(%d) y B(%d)",a,b);
                    mLog.d(TAG, logMsg);
                    int c = aritmetica.restar(a, b);
                    tvOutputC.setText(Integer.toString(c));
                } catch (Exception e){
                    String errMsg = e.getMessage() + " en btnRestar.";
                    Context context = getApplicationContext();
                    if(context != null){
                        Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();
                    }
                    mLog.d(TAG, errMsg);
                }
            }
        });

        btnMultiplicar = findViewById(R.id.btn_multiplicar);
        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int a = Integer.parseInt(tvInputA.getText().toString());
                    int b = Integer.parseInt(tvInputB.getText().toString());
                    String logMsg = String.format("Se multiplicarán A(%d) y B(%d)",a,b);
                    mLog.d(TAG, logMsg);
                    int c = aritmetica.multiplicar(a, b);
                    tvOutputC.setText(Integer.toString(c));
                } catch (Exception e){
                    String errMsg = e.getMessage() + " en btnMultiplicar.";
                    Context context = getApplicationContext();
                    if(context != null){
                        Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();
                    }
                    mLog.d(TAG, errMsg);
                }
            }
        });
        btnDividir = findViewById(R.id.btn_dividir);
        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int a = Integer.parseInt(tvInputA.getText().toString());
                    int b = Integer.parseInt(tvInputB.getText().toString());
                    double c = aritmetica.dividir(a, b);
                    String logMsg = String.format("División entre A(%d) y B(%d) = %.2f",a,b,c);
                    mLog.d(TAG, logMsg);
                    tvOutputC.setText(String.format("%.2f",c));
                } catch (Exception e){
                    String errMsg = e.getMessage() + " en btnDividir.";
                    Context context = getApplicationContext();
                    if(context != null){
                        Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();
                    }
                    mLog.d(TAG, errMsg);
                }
            }
        });
    }
}
