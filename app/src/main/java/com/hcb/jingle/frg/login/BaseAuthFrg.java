package com.hcb.jingle.frg.login;

import android.os.Bundle;
import android.text.TextUtils;

import com.hcb.jingle.R;
import com.hcb.jingle.biz.EventCenter;
import com.hcb.jingle.frg.TitleFragment;
import com.hcb.jingle.model.login.LoginInBody;
import com.hcb.jingle.util.StringUtil;
import com.hcb.jingle.util.ToastUtil;

/**
 * Created by Administrator on 2015/12/24.
 */
public class BaseAuthFrg extends TitleFragment implements EventCenter.EventListener {

    protected EventCenter eventCenter;

    protected String captcha;
    protected String phone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventCenter = beans.getEventCenter();
        eventCenter.registEvent(this, EventCenter.EventType.EVT_LOGIN);
    }

    protected void onLogin(LoginInBody body) {
        dismissDialog();

        curUser.setUid(body.getUuid());

        eventCenter.evtLogin();
    }

    protected boolean checkPhone(String tempPhone) {
        phone = null;
        if (TextUtils.isEmpty(tempPhone)) {
            ToastUtil.show(getString(R.string.phone_number_cant_empty));
        } else {
            tempPhone = tempPhone.trim();
            if (tempPhone.length() != 11 || !tempPhone.startsWith("1")) {
                ToastUtil.show(getString(R.string.phone_number_invalid));
            } else {
                phone = tempPhone;
            }
        }
        return null != phone;
    }

    protected boolean checkCaptcha(String tempCaptcha) {
        captcha = null;
        if (TextUtils.isEmpty(tempCaptcha)) {
            ToastUtil.show(getString(R.string.please_input_captcha_with_6_digits));
        } else {
            tempCaptcha = tempCaptcha.trim();
            captcha = tempCaptcha;
        }
        return null != captcha;
    }

    @Override
    public void onEvent(EventCenter.HcbEvent e) {
        if (EventCenter.EventType.EVT_LOGIN == e.type) {
            hideKeyboard();
            act.finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        eventCenter.unregistEvent(this, EventCenter.EventType.EVT_LOGIN);
    }
}
