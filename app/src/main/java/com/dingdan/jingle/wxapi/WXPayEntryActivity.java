package com.dingdan.jingle.wxapi;

import android.app.Activity;
import android.util.Log;

import com.hcb.jingle.GlobalBeans;
import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.biz.EventCenter;
import com.hcb.jingle.biz.EventCenter.EventType;
import com.hcb.jingle.util.ToastUtil;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXPayEntryActivity";
    private IWXAPI api;
    private EventCenter eventCenter;

    @Override
    protected void onResume() {
        super.onResume();
        eventCenter = GlobalBeans.getSelf().getEventCenter();
        api = WXAPIFactory.createWXAPI(this, GlobalConsts.WX_ID, true);
        api.registerApp(GlobalConsts.WX_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Log.i(TAG, "微信支付回调code" + resp.errCode);
            int result;
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    eventCenter.sendType(EventType.EVT_PAY_SUCCEED);
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    ToastUtil.show("支付取消");
                    finish();
                    break;
                default:
                    Log.e(TAG, "wxpay err:" + resp.errCode + ", " + resp.errStr);
                    eventCenter.sendType(EventType.EVT_PAY_FAILED);
                    finish();
                    break;
            }
        }
    }
}