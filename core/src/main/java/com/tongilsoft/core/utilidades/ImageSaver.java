package com.tongilsoft.core.utilidades;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Ilya Gazman on 3/6/2016.
 */
public class ImageSaver {

    public static final String TAG = "ImageSaver";
    public static final int
            IMG_FORMAT_NONE = 0,
            IMG_FORMAT_JPEG = 1,
            IMG_FORMAT_PNG = 2,
            IMG_FORMAT_GIF = 3,
            IMG_FORMAT_JPG = 4,
            IMG_FORMAT_WEBP = 5;
    public static final String[] IMG_FORMAT_STRINGS = {
            "",
            ".jpeg",
            ".png",
            ".gif",
            ".jpg",
            ".webp"
    };
    private final TSLogging mLog = new TSLogging(true,true,true);
    private Context mContext;
    private File mDefaultDirectory, mDirectory;
    private String mDirectoryName = "images";
    private String mFileName = "image.jpg";
    private boolean mExternal;

    public ImageSaver(Context context) {
        mLog.d(TAG,"constructor()");
        mContext = context;
        mDefaultDirectory = context.getCacheDir();
        setExternal(false);
    }

    public ImageSaver setFileName(String fileName) {
        mFileName = fileName;
        return this;
    }

    public ImageSaver setExternal(boolean external) {
        mExternal = external;
        return this;
    }

    /**
     * Establece el nombre del directorio de trabajo donde ImageSaver hará la lectura/escritura
     * de las imágenes, luego de establecer el directorio este se intenta crear automáticamente,
     * seguidamente verifica si el directorio existe, en caso que si, devuelve el objeto this,
     * si no existe, se lanza la excepcion IOException y devuelve null. El método depende del
     * valor de mExternal para decidir la ubicación del directorio ya que true para mExternal
     * significa que se desea utilizar la tarjeta SD del dispositivo y false significa que se
     * desea utilizar la memoria interna del dispositivo.
     * @param directoryName
     * @return ImageSaver
     */
    public ImageSaver setDirectoryName(String directoryName) {
        mLog.d(TAG,"setDirectoryName() -> " + directoryName + " >> [mExternal = " + mExternal + "]");

        mDirectoryName = directoryName;

        boolean success = false;
        String createMethod = "[ createMethod = ";
        String logMsg = "";

        /**
         * Hay dos formas
         * 1- Utilizando del Contexto las funciones:
         *      a. getDir(String,int): obtiene el directorio en modo privado y en ella las carpetas necesarias, si no existen las crea
         *      b. getCacheDir(): obtiene el directorio cache en modo privado y en ella las carpetas necesarias, si no existen las crea
         *      c. getFilesDir(): obtiene el directorio para documentos y en ella las carpetas necesarias, si no existen las crea
         * 2- Utilizando de Environment la funcion
         *      a. getExternalStorageDirectory(): obtiene el directorio del almacenamiento externo y en ella las carpetas necesarias, si no existen las crea
         */
        //1a.

        try {
            if (mExternal) {
                mDirectory = getAlbumStorageDir(mDirectoryName);
                createMethod += "getAlbumStorageDir() ]";
                if (directoryExists()) {
                    logMsg = String.format("directory exists.\n" +
                            "Abs PATH >> %s %s", mDirectory.getAbsolutePath(), createMethod);
                    success = true;
                }else if(mDirectory.mkdirs()) {
                    logMsg = String.format("directory successfully created.\n" +
                            "Abs PATH >> %s %s", mDirectory.getAbsolutePath(), createMethod);
                    success = true;
                } else {
                    logMsg = String.format("Error al crear el directorio %s en el almacenamiento externo.\n" +
                            "%s", mDirectoryName, createMethod);
                    throw new IOException(logMsg);
                }
            } else {
                mDirectory = mContext.getDir(mDirectoryName, Context.MODE_PRIVATE);
                createMethod += "Context.getDir(mDirectoryName,MODE_PRIVATE) ]";
                if (directoryExists()) {
                    logMsg = String.format("directory exists.\n" +
                            "Abs PATH >> %s %s", mDirectory.getAbsolutePath(), createMethod);
                    success = true;
                } else {
                    logMsg = String.format("Error al crear el directorio %s en el almacenamiento interno.\n" +
                            "%s", mDirectoryName, createMethod);
                    throw new IOException(logMsg);
                }
            }

            /**
             * 1b.
             * finalCatMiniatPath=miniatDirName;
             * miniatDir = new File(miContexto.getCacheDir(), finalCatMiniatPath);
             * createMethod="createPersDirs(method=Context.getCacheDir()):";
             *
             * 1c.
             * finalCatMiniatPath="documents/pizzeriag/" + miniatDirName;
             * miniatDir = new File(miContexto.getFilesDir(),finalCatMiniatPath);
             * createMethod="createPersDirs(method=Context.getFilesDir()):";
             *
             * 2a.
             * finalCatMiniatPath="pizzeriag/" + miniatDirName;
             * miniatDir = new File(Environment.getExternalStorageDirectory(), finalCatMiniatPath);
             * createMethod="createPersDirs(method=Environment.getExternalStorageDirectory()):";
             */
        } catch (Exception e){
            /**
             * Debiamos escribir codigo en donde nos recuperamos de una excepcion creando el
             * directorio por defecto como alternativa pero hemos comentado ese bloque dejandolo
             * para despues.
             */
            //mDirectory = mDefaultDirectory;
            /**
             * En vez de ello solo devolvemos imprimimos la excepcion y dejamos que se devuelva null
             */
            mLog.e(TAG, "setDirectoryName() -> Exception",e);
        } finally {
            mLog.d(TAG,"setDirectoryName() -> " + logMsg);
            if(success) {
                return this;
            }else{
                return null;
            }
        }
    }

    public void save(Bitmap bitmapImage, int preferredFormat) {
        mLog.d(TAG,"save()");

        FileOutputStream fileOutputStream = null;
        try {
            File file2Save = createFile();
            fileOutputStream = new FileOutputStream(file2Save);
            if (fileOutputStream != null) {
                switch (preferredFormat) {
                    case IMG_FORMAT_PNG:
                        bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                        break;
                    case IMG_FORMAT_GIF:
                        bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                        break;
                    case IMG_FORMAT_JPEG:
                        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                        break;
                    case IMG_FORMAT_JPG:
                        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                        break;
                    case IMG_FORMAT_WEBP:
                        bitmapImage.compress(Bitmap.CompressFormat.WEBP, 100, fileOutputStream);
                        break;
                }
                fileOutputStream.close();
            }
        } catch (FileNotFoundException fnfe) {
            mLog.e(TAG,"save() -> FileNotFoundException", fnfe);
        } catch (IOException ioe) {
            mLog.e(TAG,"save() -> IOException", ioe);
        } catch (Exception e) {
            mLog.e(TAG,"save() -> Exception",e);
        }
    }

    @NonNull
    private File createFile() {
        mLog.d(TAG,"createFile()");
        File file = null;
        try {
            file = new File(mDirectory, mFileName);
            mLog.d(TAG,String.format("createFile() -> created file >> OK \n" +
                    "[ Abs PATH = %s ]", file.getAbsolutePath()));
        } catch (Exception e){
            mLog.e(TAG, "createFile() -> Exception",e);
            file = new File(mDefaultDirectory, mFileName);
        } finally {
            return file;
        }
    }

    public static File getAlbumStorageDir(String albumName) {
        return new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public Bitmap load() {
        mLog.d(TAG,"load()");

        Bitmap decodedBmp = null;
        try {
            File file2Load = createFile();
            FileInputStream inputStream = new FileInputStream(file2Load);
            if (inputStream != null) {
                decodedBmp = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
            }
        } catch (FileNotFoundException fnfe) {
            mLog.e(TAG,"load() -> FileNotFoundException", fnfe);
        } catch (IOException ioe) {
            mLog.e(TAG,"load() -> IOException", ioe);
        } catch (Exception e) {
            mLog.e(TAG,"load() -> Exception",e);
        } finally {
            return decodedBmp;
        }
    }

    public boolean deleteFile(){
        mLog.d(TAG,"deleteFile()");

        return createFile().delete();
    }

    /**
     * Verifica si existe uel directorio para el almacenamiento de imagenes
     * @return boolean
     */
    private boolean directoryExists(){
        if(mDirectory instanceof File) {
            if (mDirectory.exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve la ruta absoluta donde ImageSaver hará la lectura/escritura de imágenes
     * @return String
     */
    public String getDirectoryAbsPath(){
        return mDirectory.getAbsolutePath();
    }
}
