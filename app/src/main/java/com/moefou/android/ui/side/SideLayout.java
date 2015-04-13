package com.moefou.android.ui.side;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.moefou.android.R;
import com.moefou.android.core.UserManager;
import com.moefou.android.object.user.User;
import com.moefou.android.ui.views.font.TypefaceTextView;
import com.squareup.picasso.Picasso;

public class SideLayout extends LinearLayout {

    private ImageView mIconImage;
    private TypefaceTextView mNameText;
    private Context mContext;

    public SideLayout(Context context) {
        this(context, null);
    }

    public SideLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideLayout(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.menu_header, this);

        mIconImage = (ImageView) findViewById(R.id.icon);
        mNameText = (TypefaceTextView) findViewById(R.id.name);
        updatepProfile();

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, UserDetailActivity.class));
            }
        });
    }


    public void updatepProfile() {
        User user = UserManager.getInstance().getCurrentUser();
        if (null == user) {
            return;
        }
        mNameText.setText(user.getUser_nickname());
        if (null != user.getUser_avatar()) {
            Picasso.with(mContext).load(Uri.parse(user.getUser_avatar().getMedium())).into(mIconImage);
        }
    }

}
