package com.commax.contentproviderclienttest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.commax.contentproviderclienttest.sqlite.Device;
import com.commax.contentproviderclienttest.util.PlusViewHolder;


import java.util.List;

/**
 * 디바이스 리스트 어댑터
 * Created by bagjeong-gyu on 2016. 8. 22..
 */
public class IPDeviceListAdapter extends ArrayAdapter<Device> {


    private final LayoutInflater mLayoutInflater;
    private final List<Device> mDatas;
    private final Context mContext;




    public IPDeviceListAdapter(Context context, int resource, List<Device> devices) {
        super(context, resource, devices);
        mLayoutInflater = LayoutInflater.from(context);
        mDatas = devices;
        mContext = context;




    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_device,
                    parent, false);
        }


        TextView deviceName = PlusViewHolder.get(convertView, R.id.deviceName);

        String deviceNameString = mDatas.get(position).getDeviceType();
        deviceName.setText(deviceNameString);



        TextView ipAddress = PlusViewHolder.get(convertView, R.id.ipAddress);

        String newIPAddressString = mDatas.get(position).getIpAddress();
        ipAddress.setText(newIPAddressString);




        return convertView;
    }


}
