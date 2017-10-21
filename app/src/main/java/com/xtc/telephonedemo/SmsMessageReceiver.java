package com.xtc.telephonedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by ouyangfan on 2017/10/17.
 * <p>
 * 短信接收器
 */

public class SmsMessageReceiver extends BroadcastReceiver {

    private static final String TAG = "SmsMessageReceiver";
    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    public static final String SMS_DELIVER_ACTION = "android.provider.Telephony.SMS_DELIVER";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "receiver sms action = " + action);
        switch (action) {
            case SMS_RECEIVED_ACTION:
            case SMS_DELIVER_ACTION:
                doReceiveSms(intent);
                break;
            default:
                break;
        }
    }

    private void doReceiveSms(Intent intent) {
        if (null == intent) {
            return;
        }
        Bundle bundle = intent.getExtras();
        if (null == bundle) {
            return;
        }
        Object[] pdus = (Object[]) bundle.get("pdus");
        if (pdus != null && pdus.length > 0) {
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                byte[] pdu = (byte[]) pdus[i];
                messages[i] = SmsMessage.createFromPdu(pdu);
            }
            for (SmsMessage message : messages) {
                String content = message.getMessageBody();             // 短信内容
                String sender = message.getOriginatingAddress();       // 发送短信的号码
                Log.d(TAG, "sms sender = " + sender + " , content = " + content);
            }
        }
    }
}
