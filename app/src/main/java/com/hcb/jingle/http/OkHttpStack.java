package com.hcb.jingle.http;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class OkHttpStack extends HurlStack {

    private final OkUrlFactory okFactory;

    public OkHttpStack(OkHttpClient client) {
        this.okFactory = new OkUrlFactory(client);
    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        return okFactory.open(url);
    }

}
