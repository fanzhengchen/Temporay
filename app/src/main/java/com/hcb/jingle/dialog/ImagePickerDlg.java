package com.hcb.jingle.dialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.R;
import com.hcb.jingle.cache.CacheCenter;
import com.hcb.jingle.util.ToastUtil;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/1/21.
 */
public class ImagePickerDlg extends BaseDialog {
    private Bitmap bitmap;
    private File photoFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dlg_image_picker, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.dlg_whole, R.id.dlg_frame, R.id.btn_cancel})
    void cancel(View v) {
        switch (v.getId()) {
            case R.id.dlg_whole:
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

    private int picked = 0;

    @OnClick({R.id.take_photo, R.id.image_gallery})
    void onPick(View v) {
        picked = v.getId();
        dismiss();
    }

    @Override
    protected void willDismiss() {
        switch (picked) {
            case R.id.take_photo:
                break;
            case R.id.image_gallery:
                break;
        }
    }

    protected void takePhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        act.startActivityForResult(intent, GlobalConsts.ACTREQ_PICTURE);
    }

    protected void pickImage() {
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

    private String genTempCameraFile() {
        return "camera_" + System.currentTimeMillis() + ".jpg";
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

//        if (resultCode == Activity.RESULT_OK) {
//            String path = null;
//            switch (requestCode) {
//                case GlobalConsts.ACTREQ_PICTURE:
//                    Uri uri = data.getData();
//                    path = UriUtil.getPath(act, uri);
//                    break;
//                case GlobalConsts.ACTREQ_CAMERA:
//                    path = photoFile.getAbsolutePath();
//                    break;
//                case GlobalConsts.ACTREQ_CLIP_AVATAR:
//                    avatarPath = destPath;
//                    break;
//            }
//            if (null != path) {
//                photoFile = CacheCenter.genCameraFile(act, genTempCameraFile());
//                if (null == photoFile) {
//                    ToastUtil.show("不能拍照：存储卡空间不足！");
//                    return;
//                }
//                destPath = photoFile.getAbsolutePath();
//                final Bundle bundle = new Bundle();
//                bundle.putString(PickAvatarFrg.SRC, path);
//                bundle.putString(PickAvatarFrg.DST, destPath);
//                ActivitySwitcher.startFrgForResult(act, PickAvatarFrg.class,
//                        bundle, GlobalConsts.ACTREQ_CLIP_AVATAR);
//            } else if (null != avatarPath) {
//                portrait.setImageBitmap(BitmapFactory.decodeFile(avatarPath));
//                if (null != avatarPath) {
//                    uploadImage(avatarPath);
//                }
//            }
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
