package com.tongilsoft.core.utilidades;

import static java.lang.Math.round;

public class ProgressInfo {
    public static final int
            TASK_PROGRESS_INIT = 0,
            TASK_PROGRESS_REFRESH = 1,
            TASK_PROGRESS_DONE = 2;

    public static final String[] TASK_PROGRESS_STRINGS = {
            "INIT",
            "REFRESH",
            "DONE"
    };

    private String progressTitle, progressMessage, progressCommand;
    private int progressVal, progressMax, progressStatus;
    private boolean refreshMax, indeterminate, refreshIndeterminate, base100;

    public ProgressInfo(){
        progressTitle="";
        progressMessage="";
        progressCommand="";
        indeterminate=true;
        refreshIndeterminate=false;
        progressVal=0;
        progressMax=100;
        refreshMax=false;
        progressStatus = TASK_PROGRESS_INIT;
        base100 = false;
    }

    public ProgressInfo setTitle(String title){
        setProgressTitle(title);
        return this;
    }

    public ProgressInfo setMessage(String message){
        setProgressMessage(message);
        return this;
    }

    public ProgressInfo setCommand(String command){
        setProgressMessage(command);
        return this;
    }

    public ProgressInfo setVal(int val){
        setProgressVal(val);
        return this;
    }

    public ProgressInfo setMax(int max){
        setProgressMax(max);
        return this;
    }

    public ProgressInfo base100(boolean ib100){
        setBase100(ib100);
        return this;
    }

    public ProgressInfo enableRefreshMax(boolean rMax){
        setRefreshMax(rMax);
        return this;
    }

    public ProgressInfo enableIndeterminate(boolean i){
        setIndeterminate(i);
        return this;
    }

    public ProgressInfo enableRefreshIndeterminate(boolean rIndeterminate){
        setRefreshIndeterminate(rIndeterminate);
        return this;
    }

    public String getProgressTitle() {
        return progressTitle;
    }

    public void setProgressTitle(String pTitle) {
        progressTitle = pTitle;
    }

    public String getProgressMessage() {
        return progressMessage;
    }

    public void setProgressMessage(String pMessage) {
        progressMessage = pMessage;
    }

    public String getProgressCommand() {
        return progressCommand;
    }

    public void setProgressCommand(String cmd) {
        progressCommand = cmd;
    }

    public int getProgressVal() {
        return progressVal;
    }

    public void setProgressVal(int pVal) {
        progressVal = pVal;
    }

    public boolean isRefreshMax() {
        return refreshMax;
    }

    public void setRefreshMax(boolean rMax) {
        refreshMax = rMax;
    }

    public int getProgressMax() {
        return progressMax;
    }

    public void setProgressMax(int pMax) {
        progressMax = pMax;
    }

    public boolean isIndeterminate() {
        return indeterminate;
    }

    public void setIndeterminate(boolean i) {
        indeterminate = i;
    }

    public boolean isRefreshIndeterminate() {
        return refreshIndeterminate;
    }

    public void setRefreshIndeterminate(boolean ri) {
        refreshIndeterminate = ri;
    }

    public int getProgressStatus() {
        return progressStatus;
    }

    public String getProgressStatusString() {
        return TASK_PROGRESS_STRINGS[progressStatus];
    }

    public void setProgressStatus(int progressStatus) {
        this.progressStatus = progressStatus;
    }

    @Override
    public String toString(){
        String string="ProgressInfo {\n"
                + "\tTitulo: " + progressTitle + "\n"
                + "\tMensaje: " + progressMessage + "\n"
                + "\tProgress CMD: " + progressCommand + "\n"
                + "\tRefreshMax: " + (refreshMax ? "SI" : "NO") + "\n"
                + "\tRefreshIndeterminate: " + (refreshIndeterminate ? "SI" : "NO") + "\n"
                + "\tIndeterminado: " + (indeterminate ? "SI" : "NO") + "\n"
                + "\tEstado: " + getProgressStatusString() + "\n";
        string += (indeterminate ? "}" : "\tCompletado: " + progressVal + " / " + progressMax
                + "[ " + getProgressValBase100() + "% ]" + "\n}");
        return string;
    }

    public boolean isBase100() {
        return base100;
    }

    public void setBase100(boolean base100) {
        this.base100 = base100;
    }

    public int getProgressValBase100() {
        if(base100) {
            //Si progressVal esta en base a 100, se utiliza directamente progressVal
            return progressVal;
        }else{
            //Si progressVal NO esta en base a 100, se calcula base 100
            int val100 = 0;
            if (progressVal > 0 && progressMax > 0) {
                val100 = round((float) ((progressVal * 100) / progressMax));
            }
            return val100;
        }
    }
}
