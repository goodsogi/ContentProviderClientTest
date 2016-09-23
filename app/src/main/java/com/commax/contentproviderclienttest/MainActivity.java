package com.commax.contentproviderclienttest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.commax.contentproviderclienttest.sqlite.ContentProviderConstants;
import com.commax.contentproviderclienttest.sqlite.Device;

import java.util.ArrayList;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    Device[] devices = new Device[4];
    private static final int CURSOR_LOADER_ID = 0;
    private DeviceListAdapter mDeviceListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialize loader
        initListView();
        deleteAllDevice();
        deleteMyDeviceInfo();
        deleteConfigureInfo();
        createTestDevices();
        insertDataThroughContentProvider();
        insertMyDeviceDataThroughContentProvider();
        insertConfigureDataThroughContentProvider();
    }

    private void deleteConfigureInfo() {
        getContentResolver().delete(ContentProviderConstants.ConfigureEntry.CONTENT_URI,
                null, null);
    }

    private void deleteMyDeviceInfo() {
        getContentResolver().delete(ContentProviderConstants.MyDeviceEntry.CONTENT_URI,
                null, null);
    }

    private void insertConfigureDataThroughContentProvider() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContentProviderConstants.ConfigureEntry.COLUMN_NAME_IS_CONFIGURED,"false");
        getContentResolver().insert(ContentProviderConstants.ConfigureEntry.CONTENT_URI,
                contentValues);
    }

    private void insertMyDeviceDataThroughContentProvider() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContentProviderConstants.MyDeviceEntry.COLUMN_NAME_DEVICE_TYPE, "MyDevice");
        contentValues.put(ContentProviderConstants.MyDeviceEntry.COLUMN_NAME_IP_ADDRESS,"10.150.200.202");
        contentValues.put(ContentProviderConstants.MyDeviceEntry.COLUMN_NAME_SIP_PHONE_NO,"300");
        getContentResolver().insert(ContentProviderConstants.MyDeviceEntry.CONTENT_URI,
                contentValues);
    }

    private void initListView() {
        // initialize our DeviceListAdapter
        mDeviceListAdapter = new DeviceListAdapter(this, null, 0, CURSOR_LOADER_ID);
        ListView deviceList = (ListView) findViewById(R.id.deviceList);
        deviceList.setAdapter(mDeviceListAdapter);

    }

    private void deleteAllDevice() {
        getContentResolver().delete(ContentProviderConstants.DeviceEntry.CONTENT_URI,
                null, null);
    }

    private void createTestDevices() {

        Device device1 = new Device();
        device1.setDeviceType("슬레이브 월패드1");
        device1.setIpAddress("10.150.200.1");
        device1.setSipPhoneNo("501");

        Device device2 = new Device();
        device2.setDeviceType("슬레이브 월패드2");
        device2.setIpAddress("10.150.200.2");
        device2.setSipPhoneNo("502");

        Device device3 = new Device();
        device3.setDeviceType("슬레이브 월패드3");
        device3.setIpAddress("10.150.200.3");
        device3.setSipPhoneNo("503");

        Device device4 = new Device();
        device4.setDeviceType("슬레이브 월패드4");
        device4.setIpAddress("10.150.200.4");
        device4.setSipPhoneNo("504");

        devices[0] = device1;
        devices[1] = device2;
        devices[2] = device3;
        devices[3] = device4;

    }

    private void insertDataThroughContentProvider() {
        ContentValues[] flavorValuesArr = new ContentValues[devices.length];
        // Loop through static array of Flavors, add each to an instance of ContentValues
        // in the array of ContentValues
        for(int i = 0; i < devices.length; i++){
            flavorValuesArr[i] = new ContentValues();
            flavorValuesArr[i].put(ContentProviderConstants.DeviceEntry.COLUMN_NAME_DEVICE_TYPE, devices[i].getDeviceType());
            flavorValuesArr[i].put(ContentProviderConstants.DeviceEntry.COLUMN_NAME_IP_ADDRESS,
                    devices[i].getIpAddress());
            flavorValuesArr[i].put(ContentProviderConstants.DeviceEntry.COLUMN_NAME_SIP_PHONE_NO,
                    devices[i].getSipPhoneNo());
        }

        // bulkInsert our ContentValues array
        getContentResolver().bulkInsert(ContentProviderConstants.DeviceEntry.CONTENT_URI,
                flavorValuesArr);
    }

    public void getDatas(View view) {

//UI를 Blocking하지 않고 데이터를 비동기적으로 가져오기 위해 LoaderManager 사용
// initialize loader
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);



//        //두번째 파라미터에 칼럼명을 지정하면 해당 칼럼만 가져옴
//        Cursor cursor =
//                getContentResolver().query(ContentProviderConstants.DeviceEntry.CONTENT_URI,
//                        null,
//                        null,
//                        null,
//                        null);
//
//
//        DeviceListAdapter adapter = new DeviceListAdapter(this, null, 0, CURSOR_LOADER_ID);
//        adapter.swapCursor(cursor);
//
//        ListView deviceList = (ListView) findViewById(R.id.deviceList);
//        deviceList.setAdapter(adapter);






//        //두번째 파라미터에 칼럼명을 지정하면 해당 칼럼만 가져옴
//        Cursor cursor =
//                getContentResolver().query(ContentProviderConstants.DeviceEntry.CONTENT_URI,
//                        null,
//                        null,
//                        null,
//                        null);
//        cursor.moveToFirst();
//
//        ArrayList<Device> devices = new ArrayList<Device>();
//
//        while (!cursor.isAfterLast()) {
//            String deviceType = cursor.getString(cursor
//                    .getColumnIndex(ContentProviderConstants.DeviceEntry.COLUMN_NAME_DEVICE_TYPE));
//            String ipAddress = cursor.getString(cursor
//                    .getColumnIndex(ContentProviderConstants.DeviceEntry.COLUMN_NAME_IP_ADDRESS));
//            String sipPhoneNo = cursor.getString(cursor
//                    .getColumnIndex(ContentProviderConstants.DeviceEntry.COLUMN_NAME_SIP_PHONE_NO));
//
//
//            Device device = new Device();
//
//            device.setDeviceType(deviceType);
//            device.setIpAddress(ipAddress);
//            device.setSipPhoneNo(sipPhoneNo);
//
//
//            devices.add(device);
//
//            cursor.moveToNext();
//        }
//        cursor.close();
//
//        IPDeviceListAdapter adapter = new IPDeviceListAdapter(this, R.layout.list_item_device, devices);
//
//
//        ListView deviceList = (ListView) findViewById(R.id.deviceList);
//        deviceList.setAdapter(adapter);

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(this,
                ContentProviderConstants.DeviceEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mDeviceListAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mDeviceListAdapter.swapCursor(null);
    }

    public void getMyDeviceDatas(View view) {
        Cursor cursor =
                getContentResolver().query(ContentProviderConstants.MyDeviceEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);

        cursor.moveToFirst();
        String deviceType = cursor.getString(cursor
                    .getColumnIndex(ContentProviderConstants.MyDeviceEntry.COLUMN_NAME_DEVICE_TYPE));
            String ipAddress = cursor.getString(cursor
                    .getColumnIndex(ContentProviderConstants.MyDeviceEntry.COLUMN_NAME_IP_ADDRESS));
            String sipPhoneNo = cursor.getString(cursor
                    .getColumnIndex(ContentProviderConstants.MyDeviceEntry.COLUMN_NAME_SIP_PHONE_NO));

        Toast.makeText(this, "내 디바이스 정보: " + deviceType + " " + ipAddress + " " + sipPhoneNo, Toast.LENGTH_SHORT ).show();

    }

    public void checkConfigure(View view) {
        Cursor cursor =
                getContentResolver().query(ContentProviderConstants.ConfigureEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);

        cursor.moveToFirst();
        String isConfigured = cursor.getString(cursor
                .getColumnIndex(ContentProviderConstants.ConfigureEntry.COLUMN_NAME_IS_CONFIGURED));


        Toast.makeText(this, "Configure 여부: " + isConfigured, Toast.LENGTH_SHORT ).show();
    }
}
