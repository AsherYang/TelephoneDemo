package com.xtc.telephonedemo;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ouyangfan on 2017/10/14.
 * <p>
 * 获取信号强度
 */

public class SignalStrengthActivity extends Activity {

    private static final String LOG_TAG = "SignalStrengthActivity";

    @BindView(R.id.tv_current_data_nw_mode)
    TextView tvCurrentDataNwMode;
    @BindView(R.id.tv_current_voice_nw_mode)
    TextView tvCurrentVoiceNwMode;
    @BindView(R.id.tv_ims_reg_state)
    TextView tvImsRegState;
    @BindView(R.id.tv_2g_ss)
    TextView tv2gSs;
    @BindView(R.id.tv_4g_ss)
    TextView tv4gSs;
    private TelephonyManager mTelephonyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signal_strength);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mTelephonyManager = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        mTelephonyManager.listen(new PhoneStateListener() {

            @Override
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                super.onSignalStrengthsChanged(signalStrength);
                setSignalStrength(signalStrength);
            }
        }, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setSignalStrength(SignalStrength signalStrength) {
        Log.d(LOG_TAG, "ss = " + signalStrength);

        tvCurrentDataNwMode.setText(ConvertMsgUtil.convertNetworkType(mTelephonyManager.getDataNetworkType()));
        tvCurrentVoiceNwMode.setText(ConvertMsgUtil.convertNetworkType(mTelephonyManager.getVoiceNetworkType()));
        String imsReg = "isVolteAvailable = " + String.valueOf(isVolteAvailable()) + "\nisImsRegistered = " + String.valueOf(isImsRegistered());
        tvImsRegState.setText(imsReg);
        String ss2G = "gsm SS = " + String.valueOf(signalStrength.getGsmSignalStrength())
                + "\nlevel = " + ConvertMsgUtil.convertSsLevel(getGsmLevel(signalStrength))
                + "\ncdma level = " + ConvertMsgUtil.convertSsLevel(getCdmaLevel(signalStrength));
        tv2gSs.setText(ss2G);
        String ss4G = "lte SS = " + String.valueOf(getLteSignalStrength(signalStrength))
                + "\nlevel = " + ConvertMsgUtil.convertSsLevel(getLteLevel(signalStrength));
        tv4gSs.setText(ss4G);
    }

    private boolean isVolteAvailable() {
        boolean isVolteAvailable = false;
        try {
            Method isVolteAvailableMethod = mTelephonyManager.getClass()
                    .getMethod("isVolteAvailable");
            isVolteAvailable = (boolean) isVolteAvailableMethod.invoke(mTelephonyManager);
        } catch (Exception e) {
            Log.d(LOG_TAG, "isVolteAvailable e = " + e.getMessage());
        }
        return isVolteAvailable;
    }

    private boolean isImsRegistered() {
        boolean isImsRegistered = false;
        try {
            Method isImsRegisteredMethod = mTelephonyManager.getClass()
                    .getMethod("isImsRegistered");
            isImsRegistered = (boolean) isImsRegisteredMethod.invoke(mTelephonyManager);
        } catch (Exception e) {
            Log.d(LOG_TAG, "isImsRegistered e = " + e.getMessage());
        }
        return isImsRegistered;
    }

    private int getGsmLevel(SignalStrength signalStrength) {
        int gsmLevel = 0;
        try {
            Method getGsmLevelMethod = signalStrength.getClass()
                    .getMethod("getGsmLevel");
            gsmLevel = (int) getGsmLevelMethod.invoke(signalStrength);
        } catch (Exception e) {
            Log.d(LOG_TAG, "getGsmLevel e = " + e.getMessage());
        }
        return gsmLevel;
    }

    private int getCdmaLevel(SignalStrength signalStrength) {
        int cdmaLevel = 0;
        try {
            Method getCdmaLevelMethod = signalStrength.getClass()
                    .getMethod("getCdmaLevel");
            cdmaLevel = (int) getCdmaLevelMethod.invoke(signalStrength);
        } catch (Exception e) {
            Log.d(LOG_TAG, "getCdmaLevel e = " + e.getMessage());
        }
        return cdmaLevel;
    }

    private int getLteLevel(SignalStrength signalStrength) {
        int lteLevel = 0;
        try {
            Method getLteLevelMethod = signalStrength.getClass()
                    .getMethod("getLteLevel");
            lteLevel = (int) getLteLevelMethod.invoke(signalStrength);
        } catch (Exception e) {
            Log.d(LOG_TAG, "getLteLevel e = " + e.getMessage());
        }
        return lteLevel;
    }

    private int getLteSignalStrength(SignalStrength signalStrength) {
        int lteSignalStrength = 0;
        try {
            Method getLteSignalStrengthMethod = signalStrength.getClass()
                    .getMethod("getLteSignalStrength");
            lteSignalStrength = (int) getLteSignalStrengthMethod.invoke(signalStrength);
        } catch (Exception e) {
            Log.d(LOG_TAG, "getLteSignalStrength e = " + e.getMessage());
        }
        return lteSignalStrength;
    }
}
