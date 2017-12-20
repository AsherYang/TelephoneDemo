package com.xtc.telephonedemo;

import android.telephony.TelephonyManager;

/**
 * Created by ouyangfan on 2017/8/16.
 */

public class ConvertMsgUtil {

    // convert phony type
    public static String convertPhoneType(int phoneType) {
        switch (phoneType) {
            case TelephonyManager.PHONE_TYPE_NONE:
                return "PHONE_TYPE_NONE";
            case TelephonyManager.PHONE_TYPE_GSM:
                return "PHONE_TYPE_GSM";
            case TelephonyManager.PHONE_TYPE_CDMA:
                return "PHONE_TYPE_CDMA";
            case TelephonyManager.PHONE_TYPE_SIP:
                return "PHONE_TYPE_SIP";
            default:
                return "UnKnown";
        }
    }

    // get network type name {@see @hide TelephonyManager.getNetworkTypeName}
    public static String convertNetworkType(int type) {
        switch (type) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return "GPRS";
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return "EDGE";
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return "UMTS";
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return "HSDPA";
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return "HSUPA";
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return "HSPA";
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return "CDMA";
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return "CDMA - EvDo rev. 0";
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return "CDMA - EvDo rev. A";
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return "CDMA - EvDo rev. B";
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return "CDMA - 1xRTT";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "LTE";
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return "CDMA - eHRPD";
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "iDEN";
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "HSPA+";
            case TelephonyManager.NETWORK_TYPE_GSM:
                return "GSM";
            case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                return "TD_SCDMA";
            case TelephonyManager.NETWORK_TYPE_IWLAN:
                return "IWLAN";
//            case TelephonyManager.NETWORK_TYPE_LTE_CA:
            case 19:
                return "LTE_CA";
            default:
                return "UnKnown";
        }
    }

    // convert sim state
    public static String convertSimState(int simState) {
        switch (simState) {
            case TelephonyManager.SIM_STATE_UNKNOWN:
                return "SIM_STATE_UNKNOWN";
            case TelephonyManager.SIM_STATE_ABSENT:
                return "SIM_STATE_ABSENT";
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                return "SIM_STATE_PIN_REQUIRED";
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                return "SIM_STATE_PUK_REQUIRED";
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                return "SIM_STATE_NETWORK_LOCKED";
            case TelephonyManager.SIM_STATE_READY:
                return "SIM_STATE_READY";
//            case TelephonyManager.SIM_STATE_NOT_READY:
            case 6:
                return "SIM_STATE_NOT_READY";
//            case TelephonyManager.SIM_STATE_PERM_DISABLED:
            case 7:
                return "SIM_STATE_PERM_DISABLED";
//            case TelephonyManager.SIM_STATE_CARD_IO_ERROR:
            case 8:
                return "SIM_STATE_CARD_IO_ERROR";
//            case TelephonyManager.SIM_STATE_CARD_RESTRICTED:
            case 9:
                return "SIM_STATE_CARD_RESTRICTED";
            default:
                return "UnKnown";
        }
    }

    // convert call state
    public static String convertCallState(int callState) {
        switch (callState) {
            case TelephonyManager.CALL_STATE_IDLE:
                return "CALL_STATE_IDLE";
            case TelephonyManager.CALL_STATE_RINGING:
                return "CALL_STATE_RINGING";
            case TelephonyManager.CALL_STATE_OFFHOOK:
                return "CALL_STATE_OFFHOOK";
            default:
                return "UnKnown";
        }
    }

    // convert data activity
    public static String convertDataActivity(int dataActivity) {
        switch (dataActivity) {
            case TelephonyManager.DATA_ACTIVITY_NONE:
                return "DATA_ACTIVITY_NONE";
            case TelephonyManager.DATA_ACTIVITY_IN:
                return "DATA_ACTIVITY_IN";
            case TelephonyManager.DATA_ACTIVITY_OUT:
                return "DATA_ACTIVITY_OUT";
            case TelephonyManager.DATA_ACTIVITY_INOUT:
                return "DATA_ACTIVITY_INOUT";
            case TelephonyManager.DATA_ACTIVITY_DORMANT:
                return "DATA_ACTIVITY_DORMANT";
            default:
                return "Unknown";
        }
    }

    // convert data state
    public static String convertDataState(int dataState) {
        switch (dataState) {
            case TelephonyManager.DATA_DISCONNECTED:
                return "DATA_DISCONNECTED";
            case TelephonyManager.DATA_CONNECTING:
                return "DATA_CONNECTING";
            case TelephonyManager.DATA_CONNECTED:
                return "DATA_CONNECTED";
            case TelephonyManager.DATA_SUSPENDED:
                return "DATA_SUSPENDED";
            default:
                return "Unknown";
        }
    }

    // convert signalStrength level
    /*
    public static final int SIGNAL_STRENGTH_NONE_OR_UNKNOWN = 0;
    public static final int SIGNAL_STRENGTH_POOR = 1;
    public static final int SIGNAL_STRENGTH_MODERATE = 2;
    public static final int SIGNAL_STRENGTH_GOOD = 3;
    public static final int SIGNAL_STRENGTH_GREAT = 4;
     */
    public static String convertSsLevel(int level) {
        switch (level) {
            case 0:
                return "SIGNAL_STRENGTH_NONE_OR_UNKNOWN";
            case 1:
                return "SIGNAL_STRENGTH_POOR";
            case 2:
                return "SIGNAL_STRENGTH_MODERATE";
            case 3:
                return "SIGNAL_STRENGTH_GOOD";
            case 4:
                return "SIGNAL_STRENGTH_GREAT";
            default:
                return "SIGNAL_STRENGTH_NONE_OR_UNKNOWN";
        }
    }
}
