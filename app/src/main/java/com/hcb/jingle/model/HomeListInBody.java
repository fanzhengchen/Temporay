package com.hcb.jingle.model;

import com.hcb.jingle.bean.CommodityVO;
import com.hcb.jingle.model.base.InBody;

import java.util.ArrayList;

public class HomeListInBody extends InBody {
    private ArrayList<CommodityVO> data;

    public ArrayList<CommodityVO> getData() {
        return data;
    }

    public void setData(ArrayList<CommodityVO> data) {
        this.data = data;
    }
}
