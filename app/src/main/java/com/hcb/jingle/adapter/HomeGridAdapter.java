package com.hcb.jingle.adapter;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.bean.HomeGridVO;
import com.hcb.jingle.util.TypeSafer;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeGridAdapter extends AbsFitAdapter {

    private List<HomeGridVO> data;

    public HomeGridAdapter(List<HomeGridVO> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return null == data ? 0 : data.size();
    }

    class Holder {
        int pos;
        @Bind(R.id.grid_image) ImageView iv;
        @Bind(R.id.grid_label) TextView tv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder h;
        if (null == convertView) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cell_homegrid, parent, false);
            h = new Holder();
            ButterKnife.bind(h, convertView);
            convertView.setTag(h);
        } else {
            h = (Holder) convertView.getTag();
        }
        h.pos = position;
        bindData(h);
        return convertView;
    }

    private void bindData(Holder h) {
        final HomeGridVO vo = data.get(h.pos);
        TypeSafer.text(h.tv, vo.getTitle());
        if (TextUtils.isEmpty(vo.getImgUrl())) {
            h.iv.setBackgroundColor(COLORS[h.pos]);
        } else {
            h.iv.setBackgroundColor(0);
            ImageLoader.getInstance().displayImage(vo.getImgUrl(), h.iv);
        }
    }

    private final static int[] COLORS = {
            Color.parseColor("#fe706f"),
            Color.parseColor("#e687fe"),
            Color.parseColor("#73bc77"),
            Color.parseColor("#e0bb4c"),
            Color.parseColor("#8a9fed"),
            // total 10
            Color.parseColor("#ee8ded"),
            Color.parseColor("#eccf80"),
            Color.parseColor("#9fb9c8"),
            Color.parseColor("#769ebd"),
            Color.parseColor("#bbd976"),
    };
}
