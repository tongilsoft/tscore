package com.tongilsoft.core.utilidades.red;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import com.tongilsoft.core.BuildConfig;
import com.tongilsoft.core.TSRecursos;

public class NetworkDetector {
    public static final int
            NETWORK_TYPE_REVEAL = 0,
            NETWORK_TYPE_ANY = 1,
            NETWORK_TYPE_WIFI = 2,
            NETWORK_TYPE_MOBILE = 3;
    public static int useNetworkType;

    static{
        /**
         * TODO >> Encontrar una forma de detectar el Tethering WiFi / USB / Bluetooth
         * mientras tanto, dejamos para pruebas con:
         * useNetworkType = NETWORK_TYPE_ANY;
         */
        /*switch(BuildConfig.CUSTOM_BUILD_TYPE){
            case TSRecursos.BUILD_TYPE_RELEASE:
                switch(BuildConfig.CUSTOM_PRODUCT_FLAVOR){
                    case TSRecursos.PRODUCT_FLAVOR_EXTERNO:
                        // para produccion -> NETWORK_TYPE_ANY
                        useNetworkType = NETWORK_TYPE_ANY;
                        break;
                    default:
                        // para produccion -> NETWORK_TYPE_WIFI
                        useNetworkType = NETWORK_TYPE_WIFI;
                        break;
                }
                break;
            default:
                // para pruebas -> NETWORK_TYPE_ANY
                //useNetworkType = NETWORK_TYPE_ANY;
                useNetworkType = NETWORK_TYPE_CONFESS;
                break;
        }*/
        useNetworkType = NETWORK_TYPE_ANY;
    }

    public static boolean isNetworkDetected(Context context){
        //v1
        /*
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        switch (useNetworkType) {
            case NETWORK_TYPE_MOBILE:
                return isMobileNetworkDetected(activeNetwork);
            case NETWORK_TYPE_WIFI:
                return isWiFiNetworkDetected(activeNetwork);
            case NETWORK_TYPE_ANY:
                return isAnyNetworkDetected(activeNetwork);
            default:
                return isConfessNetworkDetected(activeNetwork,context);
        }
        */
        //TODO: verificar si esto es util o debemos volver al original ^ (v1)
        //v2
        /*
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2){
            return true;
        }else {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            switch (useNetworkType) {
                case NETWORK_TYPE_WIFI:
                    return isWiFiNetworkDetected(activeNetwork);
                case NETWORK_TYPE_MOBILE:
                    return isMobileNetworkDetected(activeNetwork);
                case NETWORK_TYPE_ANY:
                    return isAnyNetworkDetected(activeNetwork);
                default:
                    return isConfessNetworkDetected(activeNetwork,context);
            }
        }
        */
        /*
        //v3 Probamos usar NetworkCapabilities porque ConnectivityManager fue deprecado.
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network=cm.getActiveNetwork();
        NetworkCapabilities nc=cm.getNetworkCapabilities();
        */
        //v4
        switch(BuildConfig.CORE_BUILD_TYPE) {
            case TSRecursos.BUILD_TYPE_RELEASE:
                // July 2013: Android 4.3 (API 18)
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    return true;
                } else {
                    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                    switch (useNetworkType) {
                        case NETWORK_TYPE_WIFI:
                            return isWiFiNetworkDetected(activeNetwork);
                        case NETWORK_TYPE_MOBILE:
                            return isMobileNetworkDetected(activeNetwork);
                        case NETWORK_TYPE_ANY:
                            return isAnyNetworkDetected(activeNetwork);
                        default:
                            return isConfessNetworkDetected(activeNetwork, context);
                    }
                }
            default:
                return true;
        }
    }

    private static boolean isConfessNetworkDetected(NetworkInfo activeNetwork, Context context) {
        boolean result;
        String ni = "activeNetwork\n" +
                "\t-> isNull(): " + Boolean.toString(activeNetwork == null) + "\n";
        ni += (activeNetwork != null) ? "\t-> isConnected(): " + Boolean.toString(activeNetwork.isConnected()) + "\n"
                + "\tgetType(): " + Integer.toString(activeNetwork.getType()) + "\n" : "";
        ni += "}";
        Toast.makeText(context, ni, Toast.LENGTH_LONG).show();
        return isAnyNetworkDetected(activeNetwork);
    }

    private static boolean isAnyNetworkDetected(NetworkInfo activeNetwork) {
        if(activeNetwork != null) {
            if(activeNetwork.isConnected()){
                return true;
            }
        }
        return false;
    }

    private static boolean isWiFiNetworkDetected(NetworkInfo activeNetwork){
        if(activeNetwork != null) {
            if(activeNetwork.isConnected() && activeNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                return true;
            }
        }
        return false;
    }

    private static boolean isMobileNetworkDetected(NetworkInfo activeNetwork){
        if(activeNetwork != null) {
            if(activeNetwork.isConnected() && activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE){
                return true;
            }
        }
        return false;
    }

}
