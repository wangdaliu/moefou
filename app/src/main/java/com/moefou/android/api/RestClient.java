package com.moefou.android.api;

import android.text.TextUtils;

import com.moefou.android.Const;
import com.moefou.android.util.MoefouApi10a;
import com.moefou.android.util.SharedPreferenceUtil;
import com.squareup.okhttp.OkHttpClient;

import org.scribe.model.OAuthConstants;
import org.scribe.model.Verb;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class RestClient {

    private static final String TAG = "RestClient";

    public static <T> T getService(final String host, Class<T> t, final Verb verb, final String path) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .setRequestInterceptor(new RequestInterceptor() {
                                           @Override
                                           public void intercept(RequestFacade request) {
                                               request.addHeader("Accept", "application/json");
                                               addAuthHeader(request, verb, host + path);
                                           }
                                       }
                )
                .setEndpoint(host)
                .setClient(new OkClient(okHttpClient))
                .build();
        return restAdapter.create(t);
    }

    private static void addAuthHeader(RequestInterceptor.RequestFacade request, Verb verb, String path) {
        if (TextUtils.isEmpty(SharedPreferenceUtil.getValue(Const.USER_INFO_FILE, Const.USER_TOKEN))) {
            return;
        }

        String verifier = SharedPreferenceUtil.getValue(Const.USER_INFO_FILE, Const.USER_VERIFIER);
        String secret = SharedPreferenceUtil.getValue(Const.USER_INFO_FILE, Const.USER_SECRET);
        String token = SharedPreferenceUtil.getValue(Const.USER_INFO_FILE, Const.USER_TOKEN);


        String authHeader = MoefouApi10a.getOAuthService().getAuthorizationHeader(verb, secret, token, verifier, path);
        request.addHeader(OAuthConstants.HEADER, authHeader);
    }
}
