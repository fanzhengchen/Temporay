package com.hcb.jingle.loader.base;

import com.alibaba.fastjson.JSONObject;
import com.hcb.jingle.biz.CurrentUser;
import com.hcb.jingle.http.HttpProvider;
import com.hcb.jingle.model.base.InBody;
import com.hcb.jingle.model.base.OutBody;
import com.hcb.jingle.model.base.OutHead;
import com.hcb.jingle.util.LoggerUtil;
import com.hcb.jingle.util.ReflectUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePostLoader<OUTBODY extends OutBody, INBODY extends InBody> extends AbsLoader {

    private final static Logger LOG = LoggerFactory.getLogger(BasePostLoader.class);

    @Override
    protected Logger logger() {
        return LOG;
    }

    private OUTBODY curLoading = null;

    protected OutHead genDefaultOutHead() {
        final OutHead head = new OutHead();
        final CurrentUser user = beans.getCurUser();
        if (null != user) {
            head.setCid(user.getCid()).setUid(user.getUid())
                    .setPassword(user.getPassword());
            head.setAppVersion(beans.getAppInfo().getVersionName());
        }
        // no need location
        return head;
    }

    protected void load(final String uri, final OUTBODY body, final RespReactor reactor) {
        if (null == uri || null == body) {
            logger().warn("error! NULL params in calling load()");
            return;
        }
        if (isDuplicateReq(curLoading, body)) {
            LoggerUtil.i(logger(), "Ignore a duplicate load(). uri:{}", uri);
            return;
        }
        httpProvider.post(uri, buildReqJson(body), new HttpProvider.RespStringListener() {
            @Override
            public void onResp(String str) {
                dataBack(reactor, parseObj(str));
            }
        });
    }

    protected String buildReqJson(final OUTBODY body) {
        JSONObject jo = new JSONObject();
        jo.put(KEY_HEAD, genDefaultOutHead());
        jo.put(KEY_DODY, body);
        return jo.toJSONString();
    }

    @Override
    protected Class<INBODY> inBodyClass() {
        return ReflectUtil.getClassGenricType(getClass(), 1);
    }

    protected boolean isDuplicateReq(final OUTBODY origin, final OUTBODY fresh) {
        return null != origin && origin.equals(fresh);
    }

}
