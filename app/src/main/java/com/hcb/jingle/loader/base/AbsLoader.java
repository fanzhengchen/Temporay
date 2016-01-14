package com.hcb.jingle.loader.base;

import com.alibaba.fastjson.JSONObject;
import com.hcb.jingle.GlobalBeans;
import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.biz.CurrentUser;
import com.hcb.jingle.http.HttpProvider;
import com.hcb.jingle.model.base.BaseResp;
import com.hcb.jingle.model.base.InBody;
import com.hcb.jingle.model.base.InHead;

import org.slf4j.Logger;

import java.util.Locale;

public abstract class AbsLoader {
    protected final static String KEY_HEAD = "head";
    protected final static String KEY_DODY = "body";

    protected final GlobalBeans beans;
    protected final HttpProvider httpProvider;
    protected final CurrentUser curUser;

    protected AbsLoader() {
        this.beans = GlobalBeans.getSelf();
        this.httpProvider = beans.getHttpProvider();
        this.curUser = beans.getCurUser();
    }

    protected String genUrl(final String path, final Object... params) {
        return GlobalConsts.HOST + String.format(Locale.getDefault(), path, params);
    }

    protected BaseResp parseObj(final String jsonStr) {
        BaseResp resp = new BaseResp();
        if (null != jsonStr) {
            try {
                JSONObject jo = JSONObject.parseObject(jsonStr);
                resp.setHead(JSONObject.parseObject(jo.getString(KEY_HEAD), InHead.class));
                resp.setBody(JSONObject.parseObject(jo.getString(KEY_DODY), inBodyClass()));
            } catch (Exception e) {
                logger().error("json parse error:{}", e.getMessage());
                markParseError(resp);
            }
        }
        return resp;
    }

    private void markParseError(BaseResp resp) {
        final InHead h = new InHead();
        h.setReturnCode("111");
        h.setReturnDescription("数据解析错误");
        final InBody b = new InBody();
        resp.setHead(h);
        resp.setBody(b);
    }

    protected void dataBack(final RespReactor reactor, final BaseResp resp) {
        if (null == reactor) {
            logger().warn("Null reactor!");
            return;
        }
        try {
            if (checkResp(reactor, resp)) {
                reactor.succeed(resp.getBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger().warn("Exception:{}", e.getLocalizedMessage());
        }
    }

    protected abstract Class<? extends InBody> inBodyClass();

    protected abstract Logger logger();

    public interface RespReactor<BODY extends InBody> {
        void succeed(BODY body);

        void failed(String code, String reason);
    }

    protected boolean isRespNoError(final BaseResp resp) {
        return null != resp
                && isReturnOk(resp.getHead().getReturnCode())
                && isBizOk(resp.getBody().getResult());
    }

    private final static String RETURN_OK = "000";
    private final static String BIZ_OK = "0";

    private static boolean isReturnOk(final String code) {
        return RETURN_OK.equals(code);
    }

    protected boolean isBizOk(final String code) {
        return null != code && code.startsWith(BIZ_OK);
    }

    protected boolean checkResp(final RespReactor reactor, final BaseResp resp) {
        if (null == resp || null == resp.getHead() || null == resp.getBody()) {
            reactor.failed(null, "网络错误");
            return false;
        }
        String code = resp.getHead().getReturnCode();
        String desc = resp.getHead().getReturnDescription();
        if (!isReturnOk(code)) {
            logger().warn("err--code:{},desc:{}", code, desc);
            reactor.failed(code, desc);
            return false;
        }
        code = resp.getBody().getResult();
        desc = resp.getBody().getDescription();
        if (!isBizOk(code)) {
            logger().warn("err--code:{},desc:{}", code, desc);
            reactor.failed(code, desc);
            return false;
        }
        return true;
    }

}
