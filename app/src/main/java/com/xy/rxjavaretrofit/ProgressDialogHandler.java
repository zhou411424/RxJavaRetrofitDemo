package com.xy.rxjavaretrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.xy.rxjavaretrofit.subscribers.ProgressCancelListener;

/**
 * Created by xingyun on 2016/8/31.
 */
public class ProgressDialogHandler extends Handler {
    private Context context;
    private ProgressCancelListener progressCancelListener;
    private static final int SHOW_PROGRESS_DIALOG_MSG = 0;
    private static final int DISMISS_PROGRESS_DIALOG_MSG = 1;
    private ProgressDialog mProgressDialog;

    public ProgressDialogHandler(Context context, ProgressCancelListener progressCancelListener) {
        this.context = context;
        this.progressCancelListener = progressCancelListener;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG_MSG:
                showProgressDialog(context);
                break;
            case DISMISS_PROGRESS_DIALOG_MSG:
                dismissProgressDialog();
                break;
        }
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showProgressDialog(Context context) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setCancelable(true);
            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    progressCancelListener.onCancelProgress();
                }
            });
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        }
    }

}
