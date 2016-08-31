package com.xy.rxjavaretrofit.subscribers;

import android.content.Context;
import android.util.Log;

import com.xy.rxjavaretrofit.ProgressDialogHandler;

import rx.Subscriber;

/**
 * Created by xingyun on 2016/8/31.
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private static final String TAG = "ProgressSubscriber";
    private Context context;
    private SubscriberOnNextListener subscriberOnNextListener;
    private final ProgressDialogHandler progressDialogHandler;

    public ProgressSubscriber(Context context, SubscriberOnNextListener subscriberOnNextListener) {
        this.context = context;
        this.subscriberOnNextListener = subscriberOnNextListener;
        progressDialogHandler = new ProgressDialogHandler(context, this);
    }

    private void showProgressDialog(Context context) {
        if (progressDialogHandler != null) {
            progressDialogHandler.showProgressDialog(context);
        }
    }

    private void dismissProgressDialog() {
        if (progressDialogHandler != null) {
            progressDialogHandler.dismissProgressDialog();
        }
    }

    @Override
    public void onCompleted() {
        Log.d(TAG, "onCompleted==>");
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError==>err msg="+e.getMessage());
        dismissProgressDialog();
    }

    @Override
    public void onNext(T t) {
        Log.d(TAG, "onNext==>");
        subscriberOnNextListener.onNext(t);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart==>");
        showProgressDialog(context);
    }

    @Override
    public void onCancelProgress() {
        Log.d(TAG, "onCancelProgress==>");
        //取消订阅
        if (isUnsubscribed()) {
            unsubscribe();
        }
    }
}