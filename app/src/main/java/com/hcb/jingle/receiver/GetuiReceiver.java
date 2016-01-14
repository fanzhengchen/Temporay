package com.hcb.jingle.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hcb.jingle.GlobalBeans;
//import com.hcb.jingle.loader.RpcUploader;
import com.hcb.jingle.util.LoggerUtil;
//import com.igexin.sdk.PushConsts;
//import com.igexin.sdk.PushManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetuiReceiver extends BroadcastReceiver {

    private final static Logger LOG = LoggerFactory.getLogger(GetuiReceiver.class);

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
//        switch (bundle.getInt(PushConsts.CMD_ACTION)) {
//            case PushConsts.GET_CLIENTID:
//                final String cid = bundle.getString("clientid");
//                saveCid(cid);
//                break;
//
//            case PushConsts.GET_MSG_DATA:
//                // 获取透传（payload）数据
//                byte[] payload = bundle.getByteArray("payload");
//                String taskid = bundle.getString("taskid");
//                String messageid = bundle.getString("messageid");
//                // smartPush第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
//                boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
//                LoggerUtil.d(LOG, "GetuiReceiver", "----回执接口调用" + (result ? "成功" : "失败"));
//                if (payload != null) {
//                    String data = new String(payload);
//                    LoggerUtil.d(LOG, "GetuiSdkDemo", "----receiver payload : " + data);
//                }
//                break;
//
//            default:
//                LoggerUtil.d(LOG, "GetuiReceiver", "----onReceive:" + bundle.getInt(PushConsts.CMD_ACTION));
//                break;
//        }
    }

    private void saveCid(String cid) {
        if (null != GlobalBeans.getSelf()) {
            GlobalBeans.getSelf().getCurUser().setCid(cid);
//            new RpcUploader().updateCid();
        }
    }
}
