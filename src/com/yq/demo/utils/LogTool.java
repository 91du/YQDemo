package com.yq.demo.utils;

import android.util.Log;

public class LogTool {

    /**
     * 堆栈等级
     */
    public static final int Stack_Livel = 5;
    /**
     * 是否显示LOG
     */
    public static final boolean isShowLog = true;
    /**
     * 连接线标记
     */
    public static final String MSG_CONNECT = "---";
    /**
     * log标记
     */
    public static final String TAG_PREFIX = "[qyq]";

    public static void e(String Msg) {
        if (isShowLog) {
            Log.e(getFinalTAG(), getFinalMSG(Msg));
        }
    }

    private static String getFinalTAG() {

        return TAG_PREFIX + getClassName();
    }

    private static String getFinalMSG(String Msg) {
        if (Msg == "" || Msg == null) {
            return "Msg is Empty";
        } else {
            return getLinnerNumber() + " " + getMethodName() + MSG_CONNECT + Msg;
        }

    }

    public static String getClassName() {
        return Thread.currentThread().getStackTrace()[Stack_Livel].getClassName();

    }

    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[Stack_Livel].getMethodName();

    }

    public static String getLinnerNumber() {
        return "L" + Thread.currentThread().getStackTrace()[Stack_Livel].getLineNumber();

    }

}
