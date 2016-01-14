package com.hcb.jingle.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

import com.hcb.jingle.GlobalBeans;
import com.hcb.jingle.util.BitmapUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;

public class LocalBitmap {

    private static final Logger LOG = LoggerFactory
            .getLogger(LocalBitmap.class);

    public interface BitmapReactor {

        public void onBitmap(Bitmap bitmap);
    }

    private final ExecutorService exeService;
    private final Handler uiHandler;

    public LocalBitmap() {

        final GlobalBeans beans = GlobalBeans.getSelf();
        this.exeService = beans.getExecutorService();
        this.uiHandler = beans.getHandler();
    }

    private void asyncDecodeThumb(final String thumb,
                                  final BitmapReactor callback) {
        exeService.execute(new Runnable() {

            @Override
            public void run() {

                // decode thumb
                Bitmap bitmap = BitmapFactory.decodeFile(thumb, null);
                if (null != bitmap) {
                    syncBackBitmap(callback, bitmap);
                }
            }
        });
    }

    private void asyncDecodeSmall(final String imgPath,
                                  final BitmapReactor callback) {
        exeService.execute(new Runnable() {

            @Override
            public void run() {
                // decode origin picture
                Bitmap bitmap = BitmapUtil.decodeSquare(imgPath, 200);
                if (null != bitmap) {
                    syncBackBitmap(callback, bitmap);
                }
            }
        });
    }

    private void syncBackBitmap(final BitmapReactor callback,
                                final Bitmap bitmap) {
        uiHandler.post(new Runnable() {

            @Override
            public void run() {
                backBitmap(callback, bitmap);
            }
        });
    }

    private void backBitmap(final BitmapReactor callback, final Bitmap bitmap) {
        if (null != callback) {
            try {
                callback.onBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isBitmapEnable(final Bitmap bitmap) {
        return null != bitmap && !bitmap.isRecycled();
    }

}
