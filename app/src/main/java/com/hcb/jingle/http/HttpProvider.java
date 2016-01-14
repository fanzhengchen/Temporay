package com.hcb.jingle.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hcb.jingle.util.LoggerUtil;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import okio.Buffer;

public class HttpProvider {

    private final static Logger LOG = LoggerFactory.getLogger(HttpProvider.class);

    private final static String MULTIPART_FORM_DATA = "multipart/form-data";
    private final static String BOUNDARY = "----AllThingsJingleAndroidClient";
    private final static String REQBODY_CONTENT_TYPE
            = MULTIPART_FORM_DATA + "; boundary=" + BOUNDARY;
    private final static String JSON_PACKAGE = "json_package";

    private static final int TIMEOUT_LONG = 90 * 1000;
    private static final int RETRY_LONG = 2;
    private static final int TIMEOUT_SHORT = 3 * 1000;
    private static final int RETRY_SHORT = 3;

    public interface RespStringListener {
        void onResp(String str);
    }

    private final RequestQueue mRequestQueue;

    public HttpProvider(Context ctx) {
        mRequestQueue = Volley.newRequestQueue(ctx, new OkHttpStack(new OkHttpClient()));
    }

    public void get(final String uri, final RespStringListener listener) {
        LoggerUtil.d(LOG, "GET :{}", uri);
        final long t = System.currentTimeMillis();
        StringRequest req = new StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LoggerUtil.d(LOG, "Resp:{}", uri);
                LoggerUtil.d(LOG, "Cost {}ms", System.currentTimeMillis() - t);
                if (null != listener) {
                    listener.onResp(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LoggerUtil.d(LOG, "Error: {}", uri);
                LoggerUtil.d(LOG, "Cost {}ms", System.currentTimeMillis() - t);
                if (null != listener) {
                    listener.onResp(null);
                }
            }
        });
        req.setShouldCache(false);
        mRequestQueue.add(req);
    }

    public void post(final String uri, final String str, final RespStringListener listener) {
        post(uri, str, listener, null);
    }

    public void post(final String uri, final String str, final RespStringListener listener, final PartAppender appender) {
        LoggerUtil.d(LOG, "POST:{}", uri);
        final long t = System.currentTimeMillis();
        PostFormRequest req = new PostFormRequest(uri, str, appender, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LoggerUtil.d(LOG, "Resp:{}", uri);
                LoggerUtil.d(LOG, "Cost {}ms", System.currentTimeMillis() - t);
                if (null != listener) {
                    listener.onResp(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LoggerUtil.d(LOG, "Error: {}", uri);
                LoggerUtil.d(LOG, "Cost {}ms", System.currentTimeMillis() - t);
                if (null != listener) {
                    listener.onResp(null);
                }
            }
        });
        mRequestQueue.add(req);
    }

    public class PostFormRequest extends Request<String> {

        private String txtPart;
        private PartAppender appender;
        private Response.Listener<String> mListener;

        public PostFormRequest(String url,
                               String txtPart,
                               PartAppender appender,
                               Response.Listener listener,
                               Response.ErrorListener errListener) {
            super(Method.POST, url, errListener);
            this.txtPart = txtPart;
            this.appender = appender;
            this.mListener = listener;
            setShouldCache(false);
            if (null != appender) {
                setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_LONG, RETRY_LONG,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            } else {
                setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_SHORT, RETRY_SHORT,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            }
        }

        @Override
        public String getBodyContentType() {
            return REQBODY_CONTENT_TYPE;
        }

        @Override
        public byte[] getBody() throws AuthFailureError {
            if (txtPart == null) {
                return super.getBody();
            }
            final MultipartBuilder builder = new MultipartBuilder(BOUNDARY).type(MultipartBuilder.FORM);
            builder.addFormDataPart(JSON_PACKAGE, txtPart);
            if (null != appender) {
                appender.addMoreParts(builder);
            }
            final Buffer sink = new Buffer();
            try {
                builder.build().writeTo(sink);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sink.readByteArray();
        }

        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {
            try {
                String jsonString = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers));
                return Response.success(jsonString,
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            }
        }

        @Override
        protected void deliverResponse(String response) {
            mListener.onResponse(response);
        }

    }

}
