package com.xtc.telephonedemo;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by ouyangfan on 2017/10/14.
 */

public class SmsActivity extends Activity {

    @BindView(R.id.et_input_sms)
    EditText etInputSms;
    @BindView(R.id.btn_sms_send)
    Button btnSmsSend;
    SmsManager smsManager;
    @BindView(R.id.et_dest_addr)
    EditText etDestAddr;
    private final String SEND_SMS_ACTION_REPORT = "SEND_SMS_ACTION_REPORT";
    private final String DELIVERY_SMS_ACTION_REPORT = "DELIVERY_SMS_ACTION_REPORT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);
        smsManager = SmsManager.getDefault();

        IntentFilter sendFilter = new IntentFilter();
        sendFilter.addAction(SEND_SMS_ACTION_REPORT);
        registerReceiver(mSmsSendReceiver, sendFilter);

        IntentFilter deliveryFilter = new IntentFilter();
        deliveryFilter.addAction(DELIVERY_SMS_ACTION_REPORT);
        registerReceiver(mSmsDeliveryReceiver, deliveryFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mSmsSendReceiver);
        unregisterReceiver(mSmsDeliveryReceiver);
    }

    @OnClick({R.id.btn_sms_send})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sms_send:
                String destAddr = etDestAddr.getText().toString();
                String smsMessage = etInputSms.getText().toString();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    String formatNumber = PhoneNumberUtils.formatNumber(destAddr,
                            Locale.getDefault().getCountry());
                    Log.d("SmsActivity", "formatNumber = " + formatNumber);
                }
                if (TextUtils.isEmpty(destAddr) || TextUtils.isEmpty(smsMessage)) {
                    return;
                }
                Intent sendIntent = new Intent(SEND_SMS_ACTION_REPORT);
                // 发送短信回调，如果modem发送给短信中心成功，就会回调该Intent, 它是不管对方有没有接收到的
                PendingIntent sendPendingIntent = PendingIntent.getBroadcast(SmsActivity.this, 0, sendIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                Intent deliveryIntent = new Intent(DELIVERY_SMS_ACTION_REPORT);
                // 交付回调Intent， 一般对方接受成功后，会回调该Intent，该Intent回调后，即表明短信对方已接收。
                PendingIntent deliveryPendingIntent = PendingIntent.getBroadcast(SmsActivity.this, 0, deliveryIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                smsManager.sendTextMessage(destAddr, null, smsMessage, sendPendingIntent, deliveryPendingIntent);
                Log.d("SmsActivity", "send->sms， content = " + smsMessage);
                break;
            default:
                break;
        }
    }

    /**
     * 发送短信中心状态回调(回执)
     */
    private final BroadcastReceiver mSmsSendReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("SmsActivity", "mSmsReceiver intent = " + intent + " , getResultCode = " + getResultCode());
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    Log.d("SmsActivity", "发送至短信中心成功!");
                    break;
                default:
                    Log.d("SmsActivity", "发送至短信中心失败...");
                    break;
            }
        }
    };

    /**
     * 对方接受状态回调(回执)
     */
    private final BroadcastReceiver mSmsDeliveryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("SmsActivity", "mSmsReceiver intent = " + intent + " , getResultCode = " + getResultCode());
            switch (getResultCode()) {
                case Activity.RESULT_OK:
                    Log.d("SmsActivity", "短信对方已接收成功!");
                    break;
                default:
                    Log.d("SmsActivity", "对方接收短信失败...");
                    break;
            }
        }
    };
}
