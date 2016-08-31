package com.xy.rxjavaretrofit.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by xingyun on 2016/8/30.
 */
public class NetworkUtils {
    /**
     * 判断网络连接是否存在
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        NetworkInfo info = manager.getActiveNetworkInfo();
        boolean connected = info != null && info.isConnected();
        return connected;
    }
}
