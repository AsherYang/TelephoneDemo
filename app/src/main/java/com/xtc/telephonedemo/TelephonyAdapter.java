package com.xtc.telephonedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ouyangfan on 2017/8/16.
 */

public class TelephonyAdapter extends BaseAdapter {

    private Context mContext;
    private List<TelephonyMsg> msgList;
    private LayoutInflater mLayoutInflater;

    public TelephonyAdapter(Context context, List<TelephonyMsg> msgList) {
        this.mContext = context;
        this.msgList = msgList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return msgList == null ? 0 : msgList.size();
    }

    @Override
    public Object getItem(int position) {
        return msgList == null ? null : msgList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_telephony_adapter, parent, false);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        TelephonyMsg telephonyMsg = msgList.get(position);
        mViewHolder.itemTitle.setText(telephonyMsg.getTitle());
        mViewHolder.itemContent.setText(telephonyMsg.getContent());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_content)
        TextView itemContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
