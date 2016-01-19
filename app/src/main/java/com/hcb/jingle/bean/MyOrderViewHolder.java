package com.hcb.jingle.bean;

import android.widget.ImageView;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.widget.RCTextView;

import butterknife.Bind;

public class MyOrderViewHolder {
    @Bind(R.id.cell_my_order_date)
    TextView date;
    @Bind(R.id.cell_my_order_freight)
    TextView freight;
    @Bind(R.id.cell_my_order_goods_amount)
    TextView amount;
    @Bind(R.id.cell_my_order_goods_price)
    TextView price;
    @Bind(R.id.cell_my_order_total)
    TextView total;
    @Bind(R.id.cell_my_order_btn1)
    RCTextView btn1;
    @Bind(R.id.cell_my_order_btn2)
    RCTextView btn2;
    @Bind(R.id.cell_my_order_goods_img)
    ImageView imageView;

    public TextView getDate() {
        return date;
    }

    public void setDate(TextView date) {
        this.date = date;
    }

    public TextView getFreight() {
        return freight;
    }

    public void setFreight(TextView freight) {
        this.freight = freight;
    }

    public TextView getAmount() {
        return amount;
    }

    public void setAmount(TextView amount) {
        this.amount = amount;
    }

    public TextView getPrice() {
        return price;
    }

    public void setPrice(TextView price) {
        this.price = price;
    }

    public TextView getTotal() {
        return total;
    }

    public void setTotal(TextView total) {
        this.total = total;
    }

    public RCTextView getBtn1() {
        return btn1;
    }

    public void setBtn1(RCTextView btn1) {
        this.btn1 = btn1;
    }

    public RCTextView getBtn2() {
        return btn2;
    }

    public void setBtn2(RCTextView btn2) {
        this.btn2 = btn2;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
