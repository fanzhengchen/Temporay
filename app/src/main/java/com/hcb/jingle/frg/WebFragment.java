package com.hcb.jingle.frg;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hcb.jingle.R;

public class WebFragment extends TitleFragment {

    public final static String KEY_TITLE = "key_title";
    public final static String KEY_URI = "key_uri";

    private WebView mWebView;
    private String uri;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getDataFromBundle(getArguments());
    }

    private void getDataFromBundle(Bundle extras) {
        if (null == extras) {
            return;
        }
        uri = extras.getString(KEY_URI);
        final String title = extras.getString(KEY_TITLE);
        if (null != title) {
            act.setNaviTitle(title);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.frg_web_container, container, false);
        mWebView = (WebView) rootView.findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setLayoutAlgorithm(
                WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });

        mWebView.loadUrl(uri);
        return rootView;
    }


}
