package com.commax.contentproviderclienttest;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.commax.contentproviderclienttest.sqlite.ContentProviderConstants;


/**
 * Created by sam_chordas on 7/23/15.
 */
public class DeviceListAdapter extends CursorAdapter {

    private Context mContext;
    private static int sLoaderID;

    public static class ViewHolder {
        public final TextView deviceName;
        public final TextView ipAddress;

        public ViewHolder(View view){
            deviceName = (TextView) view.findViewById(R.id.deviceName);
            ipAddress = (TextView) view.findViewById(R.id.ipAddress);
        }
    }

    public DeviceListAdapter(Context context, Cursor c, int flags, int loaderID){
        super(context, c, flags);

        mContext = context;
        sLoaderID = loaderID;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        int layoutId = R.layout.list_item_device;



        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){

        ViewHolder viewHolder = (ViewHolder) view.getTag();



        int deviceTypeIndex = cursor.getColumnIndex(ContentProviderConstants.DeviceEntry.COLUMN_NAME_DEVICE_TYPE);
        final String deviceTypeString = cursor.getString(deviceTypeIndex);
        viewHolder.deviceName.setText(deviceTypeString);

        int ipAddressIndex = cursor.getColumnIndex(ContentProviderConstants.DeviceEntry.COLUMN_NAME_IP_ADDRESS);
        final String ipAddressString = cursor.getString(ipAddressIndex);
        viewHolder.ipAddress.setText(ipAddressString);

    }

}
