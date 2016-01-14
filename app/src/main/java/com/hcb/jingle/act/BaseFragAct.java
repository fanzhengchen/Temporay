package com.hcb.jingle.act;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hcb.jingle.R;
import com.hcb.jingle.biz.ActivityWatcher;
import com.umeng.analytics.MobclickAgent;

public class BaseFragAct extends FragmentActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        changeBgIfNeed();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void changeBgIfNeed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setBackgroundColor(
                    getResources().getColor(R.color.global_bg));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityWatcher.setCurAct(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityWatcher.setCurAct(null);
    }
}
