package com.tongilsoft.core.utilidades;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class UserInputReaders {
    public static int readIntegerInput(Context context, EditText editText, int defaultValue,
                                       String fieldCaption, boolean allowNegative, boolean allowZero){
        String errorMsg="\'" + fieldCaption + "\'";
        int tmp_rii=defaultValue,rii;
        try{
            tmp_rii=Integer.parseInt(editText.getText().toString());
        }catch(Exception e){
            tmp_rii=defaultValue;
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }finally {
            rii=tmp_rii;
            if(rii < 0 && !allowNegative){
                rii=defaultValue;
                Toast.makeText(context,errorMsg + " no puede ser negativo.", Toast.LENGTH_LONG).show();
                editText.setText(Integer.toString(rii));
            }
            if(rii==0 && !allowZero){
                rii=defaultValue;
                Toast.makeText(context,errorMsg + " no puede ser Cero.", Toast.LENGTH_LONG).show();
                editText.setText(Integer.toString(rii));
            }
            return rii;
        }
    }
    public static String readStringInput(Context context, EditText editText, String defaultValue,
                                       String fieldCaption, boolean allowNull, boolean allowEmpty){
        String errorMsg="\'" + fieldCaption + "\'";
        String tmp_rsi=defaultValue,rsi;
        try{
            tmp_rsi=editText.getText().toString();
        }catch(Exception e){
            tmp_rsi=defaultValue;
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }finally {
            rsi=tmp_rsi;
            if(rsi==null && !allowNull){
                rsi=defaultValue;
                Toast.makeText(context,errorMsg + " no puede ser Nulo.", Toast.LENGTH_LONG).show();
                editText.setText(rsi);
            }
            if(rsi.isEmpty() && !allowEmpty){
                rsi=defaultValue;
                Toast.makeText(context,errorMsg + " no puede estar Vacío.", Toast.LENGTH_LONG).show();
                editText.setText(rsi);
            }
            return rsi;
        }
    }
}
