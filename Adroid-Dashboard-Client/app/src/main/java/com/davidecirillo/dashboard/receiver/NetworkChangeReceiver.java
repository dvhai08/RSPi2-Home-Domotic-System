package com.davidecirillo.dashboard.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.davidecirillo.dashboard.app.AppConfig;
import com.davidecirillo.dashboard.app.DashboardApplication;
import com.davidecirillo.dashboard.utils.NetworkUtils;

import javax.inject.Inject;

/*
  ~ Copyright (c) 2014 Davide Cirillo
  ~
  ~     Licensed under the Apache License, Version 2.0 (the "License");
  ~     you may not use this file except in compliance with the License.
  ~     You may obtain a copy of the License at
  ~
  ~        http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~     Unless required by applicable law or agreed to in writing, software
  ~     distributed under the License is distributed on an "AS IS" BASIS,
  ~     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~     See the License for the specific language governing permissions and
  ~     limitations under the License.
  ~     Come on, don't tell me you read that.
*/
public class NetworkChangeReceiver extends BroadcastReceiver {

    private String NETWORK_TAG = "NETWORK_TAG";

    @Inject
    NetworkUtils mNetworkUtils;

    @Override
    public void onReceive(final Context context, final Intent intent) {

        DashboardApplication.getAppComponent().inject(this);

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        if(wifiInfo.getSSID().replaceAll("\"", "").equals(AppConfig.SSID_LOCAL_WIFI)){
            mNetworkUtils.setIsInPiLocalNetwork(true);
            Log.i(NETWORK_TAG, "Switch to LOCAL network");
//            Toast.makeText(context, "Switch to local network", Toast.LENGTH_SHORT).show();
        }else{
            mNetworkUtils.setIsInPiLocalNetwork(false);
            Log.i(NETWORK_TAG, "Switch to EXTERNAL network");
//            Toast.makeText(context, "Switch to remote network", Toast.LENGTH_SHORT).show();
        }

    }
}

