package com.xy.rxjavaretrofit.http;

import android.content.Context;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.xy.rxjavaretrofit.http.interceptor.OnOffLineCachedInterceptor;
import com.xy.rxjavaretrofit.http.interceptor.UserAgentInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xingyun on 2016/8/30.
 */
public class RetrofitClient {
    private static final String TAG = "RetrofitClient";
    private static final int CACHE_SIZE = 10 * 1024 * 1024;// 10M
    private static final int TIMEOUT_READ = 10;
    private static final int TIMEOUT_CONNECTION = 10;
    private final Retrofit retrofit;

    /**
     * 1. 创建OkHttpClient
     * 2. 创建Retrofit实例
     * 3. 获取我们写的API interface
     * 4. 在代码中异步调用
     * @param context
     */
    public RetrofitClient(Context context) {
        // okhttp3打印请求log的拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (message.startsWith("{")) {
                    Log.d(TAG, message);
                } else {
                    if (message.contains("-->") || message.contains("<--")) {
                        Log.d(TAG, message);
                    }
                }
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        Cache cache = new Cache(context.getCacheDir(), CACHE_SIZE);

        //1.初始化OkHttpClient
        OkHttpClient okHttpClient =  new OkHttpClient.Builder()
                // 打印请求log
                .addInterceptor(httpLoggingInterceptor)
                // 可以在chrome里查看请求
                .addNetworkInterceptor(new StethoInterceptor())
                //添加UA
                .addInterceptor(new UserAgentInterceptor(HttpHelper.getUserAgent()))
                //必须设置缓存目录
                .cache(cache)
                .addInterceptor(new OnOffLineCachedInterceptor(context))
                //失败重连
                .retryOnConnectionFailure(true)
                // time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ApiConstants.DOUBAN_MOVIE_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                // 整合rxjava需要加入下面一行
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}