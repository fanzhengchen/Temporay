package com.hcb.jingle.frg;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcb.jingle.R;
import com.hcb.jingle.util.BitmapUtil;
import com.hcb.jingle.util.FileUtil;
import com.hcb.jingle.util.ScreenUtil;
import com.hcb.jingle.widget.ClipSquareImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PickAvatarFrg extends TitleFragment {

    public final static String SRC = "SRC";
    public final static String DST = "DST";
    public final static String TITLE = "title";
    private String title;
    private String srcPath;
    private String dstPath;
    private Bitmap srcBitmap;

    @Override
    public int getTitleId() {
        return R.string.pick_avatar;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDataFromBundle(getArguments());
    }

    private void getDataFromBundle(Bundle bundle) {
        if (null != bundle) {
            srcPath = bundle.getString(SRC);
            dstPath = bundle.getString(DST);
            title = bundle.getString(TITLE);
        }
    }

    @Bind(R.id.clipSquareIV)
    ClipSquareImageView csiv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_pick_avatar, container, false);
        ButterKnife.bind(this, rootView);
        if (null != title) {
            act.setNaviTitle(title);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setInitImage();
    }

    private void setInitImage() {
        try {
            srcBitmap = BitmapUtil.decodeInside(srcPath,
                    ScreenUtil.getScreenWidth(act), ScreenUtil.getScreenHeight(act));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != srcBitmap) {
            csiv.setImageBitmap(srcBitmap);
        }
    }

    @OnClick(R.id.doneBtn)
    void done(View v) {
        final Bitmap bitmap = csiv.clip();
        FileUtil.saveImageFile(dstPath, bitmap);
        act.setResult(Activity.RESULT_OK);
        act.finish();
    }

    @Override
    public void onDestroyView() {
        if (null != srcBitmap) {
            srcBitmap.recycle();
        }
        super.onDestroyView();
    }
}