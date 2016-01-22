package com.hcb.jingle.frg.personal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.R;
import com.hcb.jingle.actlink.NaviRightDecorator;
import com.hcb.jingle.biz.ActivitySwitcher;
import com.hcb.jingle.cache.CacheCenter;
import com.hcb.jingle.dialog.DatePickerDlg;
import com.hcb.jingle.dialog.ImagePickerDialog;
import com.hcb.jingle.dialog.PickTextDlg;
import com.hcb.jingle.frg.PickAvatarFrg;
import com.hcb.jingle.frg.TitleFragment;
import com.hcb.jingle.loader.ImageUploader;
import com.hcb.jingle.util.ColorUtil;
import com.hcb.jingle.util.ToastUtil;
import com.hcb.jingle.util.UriUtil;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/1/20.
 */
public class MyInfoFrg extends TitleFragment implements NaviRightDecorator {

    @Bind(R.id.frg_my_info_birth_date)
    TextView birthday;
    @Bind(R.id.frg_my_info_gender)
    TextView gender;
    @Bind(R.id.frg_my_info_portrait)
    ImageView portrait;

    private File photoFile;
    private String avatarPath;
    private String destPath;

    @Override
    public int getTitleId() {
        return R.string.my_information;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_my_info, container, false);
        ButterKnife.bind(this, rootView);
        imagePicker();
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

    @OnClick(R.id.frg_my_info_portrait)
    public void imagePicker() {

        ImagePickerDialog imagePickerDialog = new ImagePickerDialog();
        imagePickerDialog.setHandleImage(new ImagePickerDialog.HandleImage() {
            @Override
            public void onPick() {
//                ToastUtil.show("onPick");
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                act.startActivityForResult(intent, GlobalConsts.ACTREQ_PICTURE);

            }

            @Override
            public void onCamera() {
//                ToastUtil.show("onCamera");
                photoFile = CacheCenter.genCameraFile(act, genTempCameraFile());
                if (null == photoFile) {
                    ToastUtil.show("不能拍照：存储卡空间不足！");
                    return;
                }
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                act.startActivityForResult(intent, GlobalConsts.ACTREQ_CAMERA);

            }
        });
//        imagePickerDialog.show(getFragmentManager(), "imagePickerDialog");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            String path = null;
            switch (requestCode) {
                case GlobalConsts.ACTREQ_PICTURE:
                    Uri uri = data.getData();
                    path = UriUtil.getPath(act, uri);
                    break;
                case GlobalConsts.ACTREQ_CAMERA:
                    path = photoFile.getAbsolutePath();
                    break;
                case GlobalConsts.ACTREQ_CLIP_AVATAR:
                    avatarPath = destPath;
                    break;
            }
            if (null != path) {
                photoFile = CacheCenter.genCameraFile(act, genTempCameraFile());
                if (null == photoFile) {
                    ToastUtil.show("不能拍照：存储卡空间不足！");
                    return;
                }
                destPath = photoFile.getAbsolutePath();
                final Bundle bundle = new Bundle();
                bundle.putString(PickAvatarFrg.SRC, path);
                bundle.putString(PickAvatarFrg.DST, destPath);
                ActivitySwitcher.startFrgForResult(act, PickAvatarFrg.class,
                        bundle, GlobalConsts.ACTREQ_CLIP_AVATAR);
            } else if (null != avatarPath) {
                portrait.setImageBitmap(BitmapFactory.decodeFile(avatarPath));
                if (null != avatarPath) {
                    uploadImage(avatarPath);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadImage(String path) {

        showProgressDialog("修改资料", "上传头像...");
        new ImageUploader().upload(path, new ImageUploader.UploadReactor() {
            @Override
            public void onResult(String url) {
                dismissDialog();
                if (TextUtils.isEmpty(url)) {
                    ToastUtil.show(R.string.load_err_retry);
                } else {
                    ToastUtil.show("修改成功");

                }
            }
        });

    }

    private String genTempCameraFile() {
        return "camera_" + System.currentTimeMillis() + ".jpg";
    }
}
