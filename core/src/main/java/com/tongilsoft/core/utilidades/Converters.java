package com.tongilsoft.core.utilidades;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Converters {
    public static String convertStreamToString(InputStream is) throws Exception {
        String convertedString = "";
        if(is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            reader.close();
            isr.close();
            convertedString = sb.toString();
        }
        return convertedString ;
    }

    public static String convertStringArrayToCSV(@NonNull String[] stringArray, boolean cutCsv, int cutCsvEach){
        String string="";
        boolean first = true;
        int itemCount = 0;
        if(stringArray != null) {
            for (int i = 0; i < stringArray.length; i++) {
                if (first) {
                    string += stringArray[i].trim();
                    first = false;
                } else {
                    string += "," + stringArray[i].trim();
                    if(cutCsv){
                        itemCount++;
                        if(itemCount == cutCsvEach){
                            string += "\n";
                            itemCount=0;
                        }
                    }
                }
            }
        }
        return string;
    }
}
