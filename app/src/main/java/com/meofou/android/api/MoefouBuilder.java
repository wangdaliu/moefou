package com.meofou.android.api;

public class MoefouBuilder {

    String accessToken;
    String accessTokenSecret;

    public MoefouManagerImpl build() {
        return new MoefouManagerImpl(this);
    }

    public MoefouBuilder withAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public MoefouBuilder withAccessTokenSecret(String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
        return this;
    }
}
