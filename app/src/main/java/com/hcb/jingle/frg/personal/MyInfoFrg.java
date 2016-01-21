package com.hcb.jingle.frg.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.actlink.NaviRightDecorator;
import com.hcb.jingle.dialog.DatePickerDlg;
import com.hcb.jingle.dialog.PickTextDlg;
import com.hcb.jingle.frg.TitleFragment;
import com.hcb.jingle.util.ColorUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/1/20.
 */
public class MyInfoFrg extends TitleFragment implements NaviRightDecorator{

    @Bind(R.id.frg_my_info_birth_date)
    TextView birthday;
    @Bind(R.id.frg_my_info_gender)
    TextView gender;

    @Override
    public int getTitleId() {
        return R.string.my_information;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_my_info, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.frg_my_info_birth_date_layout)
    public void chooseBirthDate() {
        DatePickerDlg datePickerDlg = new DatePickerDlg();
        datePickerDlg.show(getFragmentManager(), "DatePickerDlg");
        datePickerDlg.setListener(new DatePickerDlg.DatePickListener() {
            @Override
            public void onPicked(String date) {
                birthday.setText(date);
            }
        });
    }

    @OnClick(R.id.frg_my_info_gender_layout)
    public void chooseGender() {
        PickTextDlg pickTextDlg = new PickTextDlg();
        final ArrayList<String> genders = new ArrayList<>();
        genders.add(getString(R.string.male));
        genders.add(getString(R.string.female));
        pickTextDlg.setLabels(genders);
        pickTextDlg.show(getFragmentManager(), "genderPicker");
        pickTextDlg.setListener(new PickTextDlg.PickerListener() {
            @Override
            public void onPicked(int... pos) {
                gender.setText(genders.get(pos[0]));
            }
        });
    }


    @Override
    public void decorRightBtn(TextView btnImage) {
        btnImage.setText(R.string.confirm);
        btnImage.setTextColor(ColorUtil.getColor(R.color.main_pink));
    }
}
