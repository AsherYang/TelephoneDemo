package com.xtc.telephonedemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.CellInfo;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lv_telephony)
    ListView lvTelephony;
    @BindView(R.id.ib_click)
    ImageView ibClick;

    private List<TelephonyMsg> mTelephonyMsgs = new ArrayList<>();
    private TelephonyAdapter mTelephonyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
        getTelephonyMsg();
        mTelephonyAdapter.notifyDataSetChanged();
        lvTelephony.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showMsgDialog(position);
            }
        });
        startSmsActivity();
    }

    private void startSmsActivity() {
        Intent intent = new Intent(this, SmsActivity.class);
        startActivity(intent);
    }

    private void showMsgDialog(int lvPosition) {
        if (null == mTelephonyAdapter) {
            return;
        }
        TelephonyMsg teleMsg = (TelephonyMsg) mTelephonyAdapter.getItem(lvPosition);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(teleMsg.getTitle());
        builder.setMessage(teleMsg.getContent());
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    private void initData() {
        mTelephonyAdapter = new TelephonyAdapter(this, mTelephonyMsgs);
        lvTelephony.setAdapter(mTelephonyAdapter);
    }

    private void getTelephonyMsg() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        mTelephonyMsgs.clear();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            addMsgToList(createTelephonyMsg("phone count: ", String.valueOf(tm.getPhoneCount())));
            addMsgToList(createTelephonyMsg("is world phone: ", String.valueOf(tm.isWorldPhone())));
            addMsgToList(createTelephonyMsg("is network roaming: ", String.valueOf(tm.isTtyModeSupported())));
            addMsgToList(createTelephonyMsg("is hearing aid supported: ", String.valueOf(tm.isHearingAidCompatibilitySupported())));
        }
        addMsgToList(createTelephonyMsg("phone type: ", ConvertMsgUtil.convertPhoneType(tm.getPhoneType())));
        addMsgToList(createTelephonyMsg("has icc card: ", String.valueOf(tm.hasIccCard())));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            addMsgToList(createTelephonyMsg("is sms capable: ", String.valueOf(tm.isSmsCapable())));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            addMsgToList(createTelephonyMsg("is voice capable: ", String.valueOf(tm.isVoiceCapable())));
            addMsgToList(createTelephonyMsg("has carrier privileges: ", String.valueOf(tm.hasCarrierPrivileges())));
        }

        addMsgToList(createTelephonyMsg("sim country iso: ", tm.getSimCountryIso()));
        addMsgToList(createTelephonyMsg("sim operator: ", tm.getSimOperator()));
        addMsgToList(createTelephonyMsg("sim operator name: ", tm.getSimOperatorName()));
        addMsgToList(createTelephonyMsg("sim serial number: ", tm.getSimSerialNumber()));
        addMsgToList(createTelephonyMsg("sim state: ", ConvertMsgUtil.convertSimState(tm.getSimState())));

        addMsgToList(createTelephonyMsg("call state: ", ConvertMsgUtil.convertCallState(tm.getCallState())));
        addMsgToList(createTelephonyMsg("data activity: ", ConvertMsgUtil.convertDataActivity(tm.getDataActivity())));
        addMsgToList(createTelephonyMsg("data state: ", ConvertMsgUtil.convertDataState(tm.getDataState())));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            addMsgToList(createTelephonyMsg("data network type: ", ConvertMsgUtil.convertNetworkType(tm.getDataNetworkType())));
            addMsgToList(createTelephonyMsg("voice network type: ", ConvertMsgUtil.convertNetworkType(tm.getVoiceNetworkType())));
        }
        addMsgToList(createTelephonyMsg("network type: ", ConvertMsgUtil.convertNetworkType(tm.getNetworkType())));
        addMsgToList(createTelephonyMsg("network country iso: ", tm.getNetworkCountryIso()));
        addMsgToList(createTelephonyMsg("network operator: ", tm.getNetworkOperator()));
        addMsgToList(createTelephonyMsg("network operator name: ", tm.getNetworkOperatorName()));
        addMsgToList(createTelephonyMsg("is network roaming: ", String.valueOf(tm.isNetworkRoaming())));

        addMsgToList(createTelephonyMsg("device id(IMEI): ", tm.getDeviceId()));
        addMsgToList(createTelephonyMsg("device software version(IMEI SV): ", tm.getDeviceSoftwareVersion()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            addMsgToList(createTelephonyMsg("group id level1: ", tm.getGroupIdLevel1()));
        }
        addMsgToList(createTelephonyMsg("line1 number: ", tm.getLine1Number()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            addMsgToList(createTelephonyMsg("mms UA prof url: ", tm.getMmsUAProfUrl()));
            Log.i("UA prof url ", tm.getMmsUAProfUrl());
            addMsgToList(createTelephonyMsg("mms user agent: ", tm.getMmsUserAgent()));
        }

        addMsgToList(createTelephonyMsg("subscriber Id: ", tm.getSubscriberId()));

        addMsgToList(createTelephonyMsg("voice mail alpha tag: ", tm.getVoiceMailAlphaTag()));
        addMsgToList(createTelephonyMsg("voice mail number: ", tm.getVoiceMailNumber()));

        addMsgToList(createTelephonyMsg("cell location: ", tm.getCellLocation() == null ? null :
                tm.getCellLocation().toString()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            List<CellInfo> cellInfos = tm.getAllCellInfo();
            if (null != cellInfos && !cellInfos.isEmpty()) {
                for (int i = 0; i < cellInfos.size(); i++) {
                    addMsgToList(createTelephonyMsg("cell info i = " + i, cellInfos.get(i).toString()));
                }
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            addMsgToList(createTelephonyMsg("can change dtmf tone length: ", String.valueOf(tm.canChangeDtmfToneLength())));
        }

        List<NeighboringCellInfo> neighboringCellInfos = tm.getNeighboringCellInfo();
        if (null != neighboringCellInfos && !neighboringCellInfos.isEmpty()) {
            for (int i = 0; i < neighboringCellInfos.size(); i++) {
                addMsgToList(createTelephonyMsg("neighboing cell info i = " + i, neighboringCellInfos.get(i).toString()));
            }
        }
    }

    private TelephonyMsg createTelephonyMsg(String title, String content) {
        TelephonyMsg telephonyMsg = new TelephonyMsg();
        telephonyMsg.setTitle(title);
        telephonyMsg.setContent(content);
        return telephonyMsg;
    }

    private void addMsgToList(TelephonyMsg telephonyMsg) {
        mTelephonyMsgs.add(telephonyMsg);
    }

}
