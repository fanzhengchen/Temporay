package com.hcb.jingle.loader;

import com.hcb.jingle.loader.base.BaseGetLoader;
import com.hcb.jingle.model.HomeListInBody;

public class HomeListLoader extends BaseGetLoader<HomeListInBody> {

    public final static int PAGE_SIZE = 10;
    private final static String PATH = "/home_list/%d/%d";

    public void load(int page, RespReactor reactor) {
        super.load(genUrl(PATH, page, PAGE_SIZE), reactor);
    }
}
