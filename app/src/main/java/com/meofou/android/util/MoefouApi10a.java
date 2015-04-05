package com.meofou.android.util;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

public class MoefouApi10a extends DefaultApi10a {

    private static OAuthService service;

    public static OAuthService getOAuthService() {
        if (service == null) {
            service = new ServiceBuilder()
                    .provider(MoefouApi10a.class)
                    .apiKey(MoefouApi10a.CONSUMER_KEY)
                    .apiSecret(MoefouApi10a.CONSUMER_SECRET)
                    .callback(MoefouApi10a.CALLBACK_URL)
                    .build();
        }

        return service;
    }


    private static final String REQUEST_TOKEN_URL = "http://api.moefou.org/oauth/request_token";
    private static final String ACCESS_TOKEN_URL = "http://api.moefou.org/oauth/access_token";
    private static final String AUTHORIZE_URL = "http://api.moefou.org/oauth/authorize?oauth_token=%s";

    public static final String CALLBACK_URL = "http://api.moefou.org/oauth/verifier";

    public static final String CONSUMER_KEY = "d22c94e0c66cc0cfb366607dacc0204e053572fc0";
    public static final String CONSUMER_SECRET = "3a428ed671e9739c738500d7e2312276";

    @Override
    public String getRequestTokenEndpoint() {
        return REQUEST_TOKEN_URL;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return ACCESS_TOKEN_URL;
    }

    @Override
    public String getAuthorizationUrl(Token requestToken) {
        return String.format(AUTHORIZE_URL, requestToken.getToken());
    }
}
