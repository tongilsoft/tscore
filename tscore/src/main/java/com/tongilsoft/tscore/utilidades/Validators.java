package com.tongilsoft.tscore.utilidades;

public class Validators {
    public static final String TAG = "Validators";
    public static final PGLogging vLog = new PGLogging(true,true,true);
    public static boolean validImgPath(String imgName) {
        vLog.d(TAG,"validImgPath()");

        if(imgName != null) {
            if (!imgName.isEmpty()) {
                if (imgName.toUpperCase().equals("NULL")) {
                    vLog.d(TAG,"validImgPath() -> imgName >> equals 'NULL'");
                }else{
                    if (imgName.equals("_")) {
                        vLog.d(TAG,"validImgPath() -> imgName >> equals '_'");
                    }else{
                        vLog.d(TAG,"validImgPath() -> imgName >> is VALID");
                        return true;
                    }
                }
            }else{
                vLog.d(TAG,"validImgPath() -> imgName >> isEmpty");
            }
        }else{
            vLog.d(TAG,"validImgPath() -> imgName >> is NULL");
        }
        return false;
    }
}
