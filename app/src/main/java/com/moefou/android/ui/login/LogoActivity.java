package com.moefou.android.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.moefou.android.Const;
import com.moefou.android.R;
import com.moefou.android.ui.BaseActivity;
import com.moefou.android.ui.HomeActivity;
import com.moefou.android.util.SharedPreferenceUtil;
import com.novoda.notils.caster.Views;

public class LogoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.logo);
        ImageView entranceImage = Views.findById(this, R.id.entrance);
        Animation entrance = AnimationUtils.loadAnimation(this, R.anim.entrance);
        entrance.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                String userToken = SharedPreferenceUtil.getValue(Const.USER_INFO_FILE, Const.USER_TOKEN);
                if (TextUtils.isEmpty(userToken)) {
                    startActivity(new Intent(LogoActivity.this, LoginWebActivity.class));
                } else {
                    startActivity(new Intent(LogoActivity.this, HomeActivity.class));
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        entranceImage.startAnimation(entrance);
    }
}
