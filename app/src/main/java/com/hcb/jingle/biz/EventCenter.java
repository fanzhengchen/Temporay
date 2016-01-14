package com.hcb.jingle.biz;

import android.os.Handler;

import com.hcb.jingle.util.ListUtil;
import com.hcb.jingle.util.LoggerUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventCenter {

    private final static Logger LOG = LoggerFactory.getLogger(
            EventCenter.class);

    public interface EventListener {

        public void onEvent(HcbEvent e);
    }

    private void sendEvtWithKey(final EventType evt,
                                final String key, final String value) {
        final Map<String, Object> param = new HashMap<String, Object>(1);
        param.put(key, value);
        send(new HcbEvent(evt, param));
    }

    public void sendType(final EventType evt) {
        send(new HcbEvent(evt));
    }

    public void evtLogin() {
        send(new HcbEvent(EventType.EVT_LOGIN));
    }

    public void evtLogout() {
        send(new HcbEvent(EventType.EVT_LOGOUT));
    }

    public static class HcbEvent {

        public EventType type;
        public Map<String, Object> params;

        public HcbEvent(EventType type) {
            this.type = type;
        }

        public HcbEvent(EventType type, Map<String, Object> params) {
            this.type = type;
            this.params = params;
        }

    }

    public enum EventType {
        EVT_NET_STATE, // 网络状态变化

        EVT_LOGIN, // 登录
        EVT_LOGOUT, // 退出
        EVT_USER_EDITED, //用户资料编辑

        EVT_MSG_UNREAD,//未读数变动
    }

    private final Handler uiHandler;
    private final Map<EventType, List<EventListener>> center;

    public EventCenter(final Handler handler) {
        uiHandler = handler;
        center = new HashMap<EventType, List<EventListener>>();
    }

    public void registEvent(final EventListener l, final EventType e) {
        LoggerUtil.t(LOG, "registEvent. l=[{}], e=[{}]", l, e);
        List<EventListener> list = center.get(e);
        if (null == list) {
            list = new ArrayList<EventListener>();
            list.add(l);
            center.put(e, list);
        } else if (!list.contains(l)) {
            list.add(l);
        }
    }

    public void unregistEvent(final EventListener l, final EventType e) {
        LoggerUtil.t(LOG, "unregistEvent. l=[{}], e=[{}]", l, e);
        final List<EventListener> list = center.get(e);
        if (null != list) {
            list.remove(l);
        }
    }

    public void send(final HcbEvent e) {
        final List<EventListener> list = getCopy(e.type);
        if (ListUtil.isEmpty(list)) {
            return;
        }
        boolean post = uiHandler.post(new Runnable() {

            @Override
            public void run() {
                for (int i = list.size() - 1; i >= 0; i--) {
                    list.get(i).onEvent(e);
                }
            }
        });
    }

    private List<EventListener> getCopy(final EventType et) {
        final List<EventListener> origin = center.get(et);
        if (null == origin || origin.isEmpty()) {
            return null;
        }
        return new ArrayList<EventListener>() {

            private static final long serialVersionUID = 1L;

            {
                addAll(center.get(et));
            }
        };
    }

}
