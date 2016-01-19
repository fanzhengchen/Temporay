package com.hcb.jingle.biz;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.adapter.MyOrdersAdapter;
import com.hcb.jingle.bean.MyOrder;
import com.hcb.jingle.bean.MyOrderViewHolder;
import com.hcb.jingle.util.ColorUtil;
import com.hcb.jingle.widget.RCTextView;

/**
 * Created by Administrator on 2016/1/19.
 */
public class OrderManager {
    public static final int GROUP_PENDING = 0;
    public static final int CHANGE_GROUP_PENDING = 1;
    public static final int SECONDARY_GROUP_PENDING = 2;
    public static final int DEAL_PENDING = 3;
    public static final int GROUP_FAIL_BUY_ORIGINAL_PRICE = 4;
    public static final int GROUP_FAIL_REFUND = 5;
    public static final int RECEIVE_PENDING = 6;
    public static final int CONFIRM_RECEPTION = 7;
    public static final int RETURN_GOODS_IN_PROGRESS = 8;
    public static final int RETURN_GOODS_COMPLETE = 9;
    public static final int CANCEL_ORDER = 10;
    private static Context context;

    private static void setContext(Context ctx) {
        context = ctx;
    }

    public static void fillData(MyOrder order, MyOrderViewHolder holder, int position) {
        justForTest(holder, position);
    }

    static void justForTest(MyOrderViewHolder holder, int position) {
        int k = position & 3;
        RCTextView btn1 = holder.getBtn1();
        RCTextView btn2 = holder.getBtn2();
        switch (k) {
            case 0: {
                btn1.setVisibility(View.GONE);
                btn2.setText(R.string.cancel_group);
                break;
            }
            case 1: {
                btn1.setText(R.string.decline_group);
                btn2.setText(R.string.accept_group);
                btn2.setTextColor(ColorUtil.getColor(R.color.main_pink));
                btn2.setStrokeColor(ColorUtil.getColor(R.color.main_pink));
                break;
            }
            case 2: {
                btn1.setVisibility(View.GONE);
                btn2.setText(R.string.cancel_group);
                break;
            }
            case 3: {
                btn1.setText(R.string.afford_original_price);
                btn1.setStrokeColor(ColorUtil.getColor(R.color.main_pink));
                btn1.setTextColor(ColorUtil.getColor(R.color.main_pink));
                btn2.setText(R.string.cancel_group);
                break;
            }

        }
    }
}
