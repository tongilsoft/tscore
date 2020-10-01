package com.tongilsoft.core.utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.LocalDateTime;

public class FechaHora {
    public static final String TAG = "FechaHora";

    interface iBase{
        String[] getFormats();
        String displayAvailableFormats();
    }

    interface iString{
        String toString(Calendar cal, int index);
    }

    interface iCalendar{
        Calendar parseString(String s, int index);
    }

    public static long secondsFromMidNight(){
        /*
        //Implementacion Java 8
        ZonedDateTime nowZoned = ZonedDateTime.now();
        Instant midnight = nowZoned.toLocalDate().atStartOfDay(nowZoned.getZone()).toInstant();
        Duration duration = Duration.between(midnight, Instant.now());
        long seconds = duration.getSeconds();
        */

        //Implementacion con JODA
        LocalDateTime ldt = LocalDateTime.now();
        long seconds = ldt.getMillisOfDay() / 1000;
        return seconds;
    }

    public static String secondsToHMS(long ss){
        String fmt = "%02d:%02d:%02d";
        int h, m, s;

        if(ss % 3600 > 0){
            h=(int)((ss-(ss%3600))/3600);
        }else{
            h=(int)(ss/3600);
        }

        long srm=(ss -(3600 * h));
        if(srm % 60 > 0){
            m=(int)((srm-(srm%60))/60);
        }else{
            m=(int) (srm/60);
        }

        s=(int) (ss - ((3600 * h) + (60 * m)));
        String hms=String.format(fmt,h, m, s);
        return hms;
    }

    public static class ISO8806 implements iBase, iString {
        static final String TAG = "Utilidades.ISO8806";

        @Override
        public String[] getFormats() {
            return new String[]{"%1$td-%1$tm-%1$tY %1$tH:%1$tM:%1$tS",
                        "%1$td-%1$tm-%1$tY %1$tH:%1$tM",
                        "%1$td-%1$tm-%1$tY",
                        "%1$tH:%1$tM:%1$tS",
                        "%1$tH:%1$tM",
                        "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS",
                        "%1$tY-%1$tm-%1$td %1$tH:%1$tM"};
        }

        @Override
        public String displayAvailableFormats(){
            String[] f=getFormats();
            String s = "Formatos disponibles [\n";
            for (int i = 0; i < f.length; i++) {
                s += "\t[" + Integer.toString(i) + "] " + f[i] + "\n";
            }
            s += "]";
            return s;
        }

        @Override
        public String toString(Calendar cal, int index) {
            String s = "2000-01-01 00:00:00";
            String[] f=getFormats();
            try {
                if(index <= f.length) {
                    s = String.format(f[index], cal);
                }else{
                    throw new ISO8806.FormatNotFoundException(index,f.length,displayAvailableFormats());
                }
            } catch (ISO8806.FormatNotFoundException fnfe) {
                fnfe.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return s;
            }
        }

        public int timeToSeconds(Calendar cal, int index){
            String s = "2000-01-01 00:00:00";
            String[] f=getFormats();
            try {
                if(index <= f.length) {
                    s = String.format(f[index], cal);
                }else{
                    throw new ISO8806.FormatNotFoundException(index,f.length,displayAvailableFormats());
                }
            } catch (ISO8806.FormatNotFoundException fnfe) {
                fnfe.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return 0;
            }
        }

        public static class BasicFormat implements iBase, iCalendar {
            static final String TAG = "ISO8806.BasicFormat";

            @Override
            public String[] getFormats() {
                return new String[]{"yyyyMMddTHHmmss",
                        "yyyyMMddTHHmm",
                        "yyyyMMdd",
                        "HHmmss",
                        "HHmm",
                        "ddMMyyyyTHHmmss",
                        "ddMMyyyyTHHmm"};
            }

            @Override
            public String displayAvailableFormats() {
                String[] f=getFormats();
                String s = "Formatos disponibles{\n";
                for (int i = 0; i <= f.length; i++) {
                    s += "\t[" + Integer.toString(i) + "] " + f[i] + "\n";
                }
                s += "}";
                return s;
            }

            @Override
            public Calendar parseString(String s, int index) {
                String defDate="2000-01-01 00:00:00";
                String[] f=getFormats();
                DateFormat df= new SimpleDateFormat(f[0], Locale.ENGLISH);
                String fecha=s!=null?!s.isEmpty()?s:defDate:defDate ;
                Calendar cal = Calendar.getInstance();
                try {
                    if (index <= f.length) {
                        df = new SimpleDateFormat(f[index], Locale.ENGLISH);
                        cal.setTime(df.parse(fecha));
                    } else {
                        throw new ISO8806.FormatNotFoundException(index,f.length,displayAvailableFormats());
                    }
                } catch (ISO8806.FormatNotFoundException fnfe) {
                    fnfe.printStackTrace();
                    // Fecha por defecto;
                    cal.setTime(df.parse(fecha));
                } catch (ParseException pe){
                    pe.printStackTrace();
                } finally {
                    return cal;
                }
            }
        }

        public static class ExtendedFormat implements iBase, iCalendar {
            static final String TAG = "ISO8806.ExtendedFormat";

            @Override
            public String[] getFormats() {
                return new String[]{
                        "dd-MM-yyyy HH:mm:ss",
                        "dd-MM-yyyy HH:mm",
                        "dd-MM-yyyy",
                        "HH:mm:ss",
                        "HH:mm",
                        "dd-MM-yyyy HH:mm:ss",
                        "dd-MM-yyyy HHmm"
                };
            }

            @Override
            public String displayAvailableFormats(){
                String[] f=getFormats();
                String s = "Formatos disponibles [\n";
                for (int i = 0; i < f.length; i++) {
                    s += "\t[" + Integer.toString(i) + "] " + f[i] + "\n";
                }
                s += "]";
                return s;
            }

            @Override
            public Calendar parseString(String s, int index) {
                String defDate="2000-01-01 00:00:00";
                String[] f=getFormats();
                DateFormat df= new SimpleDateFormat(f[0], Locale.ENGLISH);
                String fecha=s!=null?!s.isEmpty()?s:defDate:defDate ;
                Calendar cal = Calendar.getInstance();
                try {
                    if (index <= f.length) {
                        df = new SimpleDateFormat(f[index], Locale.ENGLISH);
                        cal.setTime(df.parse(fecha));
                    } else {
                        throw new ISO8806.FormatNotFoundException(index,f.length,displayAvailableFormats());
                    }
                } catch (ISO8806.FormatNotFoundException fnfe) {
                    fnfe.printStackTrace();
                    // Fecha por defecto;
                    cal.setTime(df.parse(fecha));
                } catch (ParseException pe){
                    pe.printStackTrace();
                } finally {
                    return cal;
                }
            }
        }

        private static class FormatNotFoundException extends ArrayIndexOutOfBoundsException{
            FormatNotFoundException(int i, int n, String af){
                super("Formato NO Disponible en el indice [" + i + "]\n"
                        + "La funcion solo acepta indices entre 0 y " + Integer.toString(n-1) + ".\n"
                        + af);
            }
        }

        public static String restarFechas(String fechaInicio, String fehcaFin, boolean allowDays,
                                          boolean allowHours, boolean allowMinutes, boolean allowSeconds) {
            TSLogging log;
            log = new TSLogging(true,false,true);

            ISO8806.ExtendedFormat extendedFormat=new ISO8806.ExtendedFormat();
            Date startDate = extendedFormat.parseString(fechaInicio, 0).getTime();
            Date endDate = extendedFormat.parseString(fehcaFin, 0).getTime();

            //milliseconds
            long different = endDate.getTime() - startDate.getTime();

            log.i(TAG,"startDate -> " + startDate.toString());
            log.i(TAG,"endDate -> " + endDate.toString());
            log.i(TAG,"different -> " + Long.toString(different));

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;

            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;

            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;

            String diferencia = "";
            if(allowDays) {
                if (elapsedDays > 0) {
                    diferencia += Long.toString(elapsedDays) + "d, ";
                }
            }
            if(allowHours) {
                if (elapsedHours > 0) {
                    diferencia += String.format("%1$02d",elapsedHours) + "h";
                } else{
                    diferencia += "00h";
                }
            }
            if(allowMinutes) {
                if (elapsedMinutes > 0) {
                    diferencia += ":" + String.format("%1$02d",elapsedMinutes) + "m";
                } else{
                    diferencia += ":00m";
                }
            }
            if(allowSeconds) {
                if (elapsedSeconds > 0) {
                    diferencia += ":" + String.format("%1$02d",elapsedSeconds) + "s";
                }else{
                    diferencia += ":00s";
                }
            }
            return diferencia;
        }
    }
}
