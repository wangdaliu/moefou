package com.meofou.android.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.meofou.android.Const;
import com.meofou.android.R;
import com.meofou.android.ui.BaseActivity;
import com.meofou.android.ui.HomeActivity;
import com.meofou.android.util.MoefouApi10a;
import com.meofou.android.util.SharedPreferenceUtil;
import com.novoda.notils.caster.Views;

import org.scribe.model.Token;
import org.scribe.model.Verifier;

import java.util.Set;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginWebActivity extends BaseActivity {

    private WebView mWebView;
    private ProgressBar webViewProgress;
    private Token requestToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_web_layout);

        mWebView = Views.findById(this, R.id.webview);
        webViewProgress = Views.findById(this, R.id.article_progress);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new HackerNewsWebClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                webViewProgress.setProgress(progress);
                if (webViewProgress.getProgress() >= 100) {
                    webViewProgress.setVisibility(View.GONE);
                } else {
                    webViewProgress.setVisibility(View.VISIBLE);
                }
            }
        });

        AppObservable.bindActivity(LoginWebActivity.this, fetchAuthorizationUrlObservable());
    }

    private Observable<String> fetchAuthorizationUrlObservable() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                requestToken = MoefouApi10a.getOAuthService().getRequestToken();
                String authorizationUrl = MoefouApi10a.getOAuthService().getAuthorizationUrl(requestToken);
                subscriber.onNext(authorizationUrl);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                mWebView.loadUrl(s);
            }
        });
        return observable;
    }

    private Observable<String> getAccessToken(final String verifier) {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Token accessToken = MoefouApi10a.getOAuthService().getAccessToken(requestToken, new Verifier(verifier));
                SharedPreferenceUtil.save(Const.USER_INFO_FILE, Const.USER_VERIFIER, verifier);
                SharedPreferenceUtil.save(Const.USER_INFO_FILE, Const.USER_SECRET, accessToken.getSecret());
                SharedPreferenceUtil.save(Const.USER_INFO_FILE, Const.USER_TOKEN, accessToken.getToken());
                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                startActivity(new Intent(LoginWebActivity.this, HomeActivity.class));
                LoginWebActivity.this.finish();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });

        return observable;
    }

    private class HackerNewsWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Uri uri = Uri.parse(url);
            if (!uri.toString().startsWith(Const.BASE_URL)) {
                return true;
            }
            view.loadUrl(url);
            Set<String> set = uri.getQueryParameterNames();
            if (set.contains("oauth_verifier")) {
                AppObservable.bindActivity(LoginWebActivity.this, getAccessToken(uri.getQueryParameter("oauth_verifier")));
                return true;
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

}
