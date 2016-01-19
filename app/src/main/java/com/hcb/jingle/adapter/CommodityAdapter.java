package com.hcb.jingle.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.bean.CommodityVO;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yang.zhao on 2016/01/15.
 */
public class CommodityAdapter extends PagableAdapter {

    private List<CommodityVO> data;
    private Boolean isHomeFrg = true;

    public Boolean getIsHomeFrg() {
        return isHomeFrg;
    }

    public void setIsHomeFrg(Boolean isHomeFrg) {
        this.isHomeFrg = isHomeFrg;
    }

    public CommodityAdapter(Activity act, List<CommodityVO> data) {
        super(act);
        this.data = data;
    }

    @Override
    protected int getRealCount() {

        return null == data ? 0 : data.size();
    }



    @Override
    protected View makeItemView(int position, ViewGroup parent) {
        ViewHolder h = new ViewHolder();
        View convertView = View.inflate(parent.getContext(), R.layout.cell_commodity, null);
        ButterKnife.bind(h,convertView);
        convertView.setTag(h);
        return convertView;
    }

    @Override
    protected void bandData(int position, View convertView) {
        ViewHolder h = (ViewHolder) convertView.getTag();
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder h;
//        if(convertView == null){
//            convertView = View.inflate(parent.getContext(),R.layout.cell_commodity,null);
//            h = new ViewHolder();
//            ButterKnife.bind(h,convertView);
//            convertView.setTag(h);
//        }else{
//            h = (ViewHolder) convertView.getTag();
//        }
//        h.pos = position;
//        fillData(h);
//
//        return convertView;
//    }
//
//    private void fillData(ViewHolder h) {
//
//    }

    class ViewHolder {
        int pos;
        @Bind(R.id.image_background)ImageView image_background;
        @Bind(R.id.relative_bottom)RelativeLayout relative_bottom;
        @Bind(R.id.text_commodity_name)TextView text_commodity_name;
        @Bind(R.id.text_commodity_price)TextView text_commodity_price;
        @Bind(R.id.text_commodity_discount)TextView text_commodity_discount;
        @Bind(R.id.text_open_group)TextView text_open_group;
        @Bind(R.id.linear_tag)LinearLayout linear_tag;
        @Bind(R.id.text_tag1)TextView text_tag1;
        @Bind(R.id.text_tag2)TextView text_tag2;
        @Bind(R.id.text_tag3)TextView text_tag3;
        @Bind(R.id.text_tag4)TextView text_tag4;
    }
}
