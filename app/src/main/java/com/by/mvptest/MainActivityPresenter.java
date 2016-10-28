package com.by.mvptest;

import android.os.Handler;
import android.os.Message;

/**
 * Created by 1 on 2016/10/28.
 */

public class MainActivityPresenter implements IMainActivityPresenter {

    private IMainActivity mainActivity;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            int what = message.what;
            switch (what) {
                case 1: {
                    mainActivity.loginSuccess();
                }
                break;
                case 2:{
                    String errorMsg = (String) message.obj;
                    mainActivity.loginFailed(errorMsg);
                }
                break;
            }
            return true;
        }
    });

    public MainActivityPresenter(IMainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private boolean isLoginSuccess = false;

    @Override
    public void login(String phone, String password) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                isLoginSuccess = !isLoginSuccess;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = handler.obtainMessage();
                if (isLoginSuccess) {
                    msg.what = 1;
                    handler.sendMessage(msg);
                } else {
                    msg.what = 2;
                    msg.obj = "wrong error";
                    handler.sendMessage(msg);
                }
            }
        }).start();

    }

    @Override
    public void release() {

    }
}
