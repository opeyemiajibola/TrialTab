package com.example.home.trialtab;

/**
 * Created by home on 12/01/2016.
 */

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TabFragment1 extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);

        // connectivity issues

        String service = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager connectivity = (ConnectivityManager) this.getContext().getSystemService(service);

        // Get the active network information.
        NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
        // check what getActiveNetworkInfo returns, what can be useful there
        //network is connected
        boolean isConnected = ((activeNetwork != null) && (activeNetwork.isConnectedOrConnecting()));

        // check if GSM/CDMA is supported

        PackageManager pm = this.getContext().getPackageManager();
        boolean telephonySupported =
                pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
        boolean gsmSupported =
                pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_CDMA);
        boolean cdmaSupported =
                pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_GSM);

        // accessing telephony properties
        String srvcName = Context.TELEPHONY_SERVICE;
        TelephonyManager telephonyManager = (TelephonyManager) this.getContext().getSystemService(srvcName);

        String phoneTypeStr = "unknown";
        int phoneType = telephonyManager.getPhoneType();
        switch (phoneType) {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                phoneTypeStr = "CDMA";
                break;
            case (TelephonyManager.PHONE_TYPE_GSM) :
                phoneTypeStr = "GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_SIP):
                phoneTypeStr = "SIP";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                phoneTypeStr = "None";
                break;
            default: break;
        }

        // Get connected network country ISO code
        String networkCountry = telephonyManager.getNetworkCountryIso();
        // Get the connected network operator ID (MCC + MNC)
        String networkOperatorId = telephonyManager.getNetworkOperator();
        // Get the connected network operator name
        String networkName = telephonyManager.getNetworkOperatorName();
        // Get the type of network you are connected to
        String networkTypeStr = "unknown";
        int networkType = telephonyManager.getNetworkType();
        switch (networkType) {
            case (TelephonyManager.NETWORK_TYPE_1xRTT) :
                networkTypeStr = "1xRTT";
                break;
            case (TelephonyManager.NETWORK_TYPE_CDMA) :
                networkTypeStr = "CDMA";
                break;
            case (TelephonyManager.NETWORK_TYPE_EDGE) :
                networkTypeStr = "EDGE";
                break;
            case (TelephonyManager.NETWORK_TYPE_EHRPD) :
                networkTypeStr = "EHRPD";
                break;
            case (TelephonyManager.NETWORK_TYPE_EVDO_0) :
                networkTypeStr = "EVDO_0";
                break;
            case (TelephonyManager.NETWORK_TYPE_EVDO_A) :
                networkTypeStr = "EVDO_A";
                break;
            case (TelephonyManager.NETWORK_TYPE_EVDO_B) :
                networkTypeStr = "EVDO_B";
                break;
            case (TelephonyManager.NETWORK_TYPE_GPRS) :
                networkTypeStr = "GPRS";
                break;
            case (TelephonyManager.NETWORK_TYPE_HSDPA) :
                networkTypeStr = "HSDPA";
                break;
            case (TelephonyManager.NETWORK_TYPE_HSPA) :
                networkTypeStr = "HSPA";
                break;
            case (TelephonyManager.NETWORK_TYPE_HSPAP) :
                networkTypeStr = "HSPAP";
                break;
            case (TelephonyManager.NETWORK_TYPE_HSUPA) :
                networkTypeStr = "HSUPA";
                break;
            case (TelephonyManager.NETWORK_TYPE_IDEN) :
                networkTypeStr = "IDEN";
                break;
            case (TelephonyManager.NETWORK_TYPE_LTE) :
                networkTypeStr = "LTE";
                break;
            case (TelephonyManager.NETWORK_TYPE_UMTS) :
                networkTypeStr = "UMTS";
                break;
            case (TelephonyManager.NETWORK_TYPE_UNKNOWN) :
                networkTypeStr = "Unknown";
                break;
            default: break;
        }

        // obtain sim details
        String simCountry = " ";
        String simOperatorCode = " ";
        String simOperatorName = " ";
        String simSerial = " ";

        int simState = telephonyManager.getSimState();
        switch (simState) {
            case (TelephonyManager.SIM_STATE_ABSENT): break;
            case (TelephonyManager.SIM_STATE_NETWORK_LOCKED): break;
            case (TelephonyManager.SIM_STATE_PIN_REQUIRED): break;
            case (TelephonyManager.SIM_STATE_PUK_REQUIRED): break;
            case (TelephonyManager.SIM_STATE_UNKNOWN): break;
            case (TelephonyManager.SIM_STATE_READY): {
            // Get the SIM country ISO code
                simCountry = telephonyManager.getSimCountryIso();
            // Get the operator code of the active SIM (MCC + MNC)
                simOperatorCode = telephonyManager.getSimOperator();
                // Get the name of the SIM operator
                simOperatorName = telephonyManager.getSimOperatorName();
                // -- Requires READ_PHONE_STATE uses-permission --
                // Get the SIM’s serial number
                simSerial = telephonyManager.getSimSerialNumber();
                break;
            }
            default: break;
        }

        //Data Connection and Transfer state
        String data_State = " ";

        int dataActivity = telephonyManager.getDataActivity();
        int dataState = telephonyManager.getDataState();
        switch (dataActivity) {
            case TelephonyManager.DATA_ACTIVITY_IN : break;
            case TelephonyManager.DATA_ACTIVITY_OUT : break;
            case TelephonyManager.DATA_ACTIVITY_INOUT : break;
            case TelephonyManager.DATA_ACTIVITY_NONE : break;
        }
        switch (dataState) {
            case TelephonyManager.DATA_CONNECTED :
                data_State = "Connected";
                break;
            case TelephonyManager.DATA_CONNECTING :
                data_State = "Connecting";
                break;

            case TelephonyManager.DATA_DISCONNECTED :
                data_State = "disconnected";
                break;
            case TelephonyManager.DATA_SUSPENDED :
                data_State = "Suspended";
                break;
        }

        LocationManager locationManager;
        String svcName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager)this.getContext().getSystemService(svcName);
        String provider = LocationManager.GPS_PROVIDER;


        LocationListener myLocationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
                // Update application based on new location.
            }

            public void onProviderDisabled(String provider) {
                // Update application if provider disabled.
            }

            public void onProviderEnabled(String provider) {

                // Update application if provider enabled.
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                // Update application if provider hardware status changed.
            }
        };

        // check this error
        Looper looper = null;
        locationManager.requestSingleUpdate(provider, myLocationListener,looper);
        Location l = locationManager.getLastKnownLocation(provider);

        //String providerName = LocationManager.GPS_PROVIDER;
       // LocationProvider gpsProvider = locationManager.getProvider(providerName);

        //updateWithNewLocation(l);

        TextView myLocationText;
        //myLocationText = (TextView) this.getView().findViewById(R.id.myLocationText);
        String latLongString = "No location found";
        if (l != null) {
            double lat = l.getLatitude();
            double lng = l.getLongitude();
            latLongString ="Location Info" + "\nLat:" + lat + "\nLong:" + lng + "\nPhone Info\nPhone Type:" + phoneTypeStr
                    + "\nNetwork Info\nNetwork Type:" + networkTypeStr  + "\nOperator/Sim Info \nCountry:" + simCountry
                    + "\nOperator Code:" + simOperatorCode + "\nOperator Name:" + simOperatorName + "\n Serial:" + simSerial
                    + "\nData Connection State:" + data_State;
        }
        Toast.makeText(getActivity(), latLongString, Toast.LENGTH_LONG).show();
        //myLocationText.setText("Your Current Position is:\n" + latLongString);*/


        return view;
    }

   /* private void updateWithNewLocation(Location location) {

        TextView myLocationText;
        myLocationText = (TextView)findViewById(R.id.myLocationText);
        String latLongString = "No location found";
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latLongString = "Lat:" + lat + "\nLong:" + lng;
        }
        myLocationText.setText("Your Current Position is:\n" +
                latLongString);


    }*/


}
