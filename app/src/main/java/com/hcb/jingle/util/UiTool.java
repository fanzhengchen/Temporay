package com.hcb.jingle.util;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.content.ClipboardManager;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UiTool {

    public static void strikeTextView(final TextView tv) {
        final Paint p = tv.getPaint();
        p.setStrikeThruText(true);
        p.setAntiAlias(true);
    }

    public static void copyToClipboard(final Context ctx, final String text) {
        if (null == ctx || null == text) {
            return;
        }
        ((ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE))
                .setPrimaryClip(ClipData.newPlainText(null, text.trim()));
    }
}
