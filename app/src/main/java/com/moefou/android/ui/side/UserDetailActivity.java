package com.moefou.android.ui.side;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.moefou.android.R;
import com.moefou.android.core.UserManager;
import com.moefou.android.object.user.User;
import com.moefou.android.ui.BaseActivity;
import com.moefou.android.ui.views.AlphaForegroundColorSpan;
import com.moefou.android.ui.views.font.TypefaceTextView;
import com.moefou.android.util.TimeFormatUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserDetailActivity extends BaseActivity {

    private int mActionBarTitleColor;
    private int mActionBarHeight;
    private int mHeaderHeight;
    private int mMinHeaderTranslation;
    private ListView mListView;
    private ImageView mHeaderLogo;
    private View mHeader;
    private View mPlaceHolderView;
    private AccelerateDecelerateInterpolator mSmoothInterpolator;

    private RectF mRect1 = new RectF();
    private RectF mRect2 = new RectF();

    private AlphaForegroundColorSpan mAlphaForegroundColorSpan;
    private SpannableString mSpannableString;

    private TypedValue mTypedValue = new TypedValue();

    private User mUser;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUser = UserManager.getInstance().getCurrentUser();

        mSmoothInterpolator = new AccelerateDecelerateInterpolator();
        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = -mHeaderHeight + getActionBarHeight();

        setContentView(R.layout.user_detail);

        mListView = (ListView) findViewById(R.id.listview);
        mHeader = findViewById(R.id.header);

        mHeaderLogo = (ImageView) findViewById(R.id.header_logo);

        Picasso.with(this).load(Uri.parse(mUser.getUser_avatar().getMedium())).into(mHeaderLogo);

        mActionBarTitleColor = getResources().getColor(android.R.color.white);

        mSpannableString = new SpannableString(mUser.getUser_nickname());
        mAlphaForegroundColorSpan = new AlphaForegroundColorSpan(mActionBarTitleColor);

        setupToolBar();
        setupListView();
    }

    private void setupListView() {
        // data
        String[] userInfoArray = getResources().getStringArray(R.array.user);
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        userInfoList.add(new UserInfo(userInfoArray[0], mUser.getUser_name()));
        userInfoList.add(new UserInfo(userInfoArray[1], mUser.getUser_nickname()));
        userInfoList.add(new UserInfo(userInfoArray[2], TimeFormatUtil.formatTime(mUser.getUser_registered() * 1000)));
        userInfoList.add(new UserInfo(userInfoArray[3], TimeFormatUtil.formatTime(mUser.getUser_lastactivity() * 1000)));
        userInfoList.add(new UserInfo(userInfoArray[4], mUser.getUser_url()));
        userInfoList.add(new UserInfo(userInfoArray[5], mUser.getUser_fm_url()));
        userInfoList.add(new UserInfo(userInfoArray[6], mUser.getAbout()));

        mPlaceHolderView = getLayoutInflater().inflate(R.layout.view_header_placeholder, mListView, false);
        mListView.addHeaderView(mPlaceHolderView);
        TextView view = new TextView(this);
        view.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 800));
        mListView.addFooterView(view);
        mListView.setAdapter(new UserInfoAdapter(this, userInfoList));
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int scrollY = getScrollY();
                //sticky actionbar
                mHeader.setTranslationY(Math.max(-scrollY, mMinHeaderTranslation));
                //header_logo --> actionbar icon
                float ratio = clamp(mHeader.getTranslationY() / mMinHeaderTranslation, 0.0f, 1.0f);
                interpolate(mHeaderLogo, getToolbarIcon(), mSmoothInterpolator.getInterpolation(ratio));
                //actionbar title alpha
                //getActionBarTitleView().setAlpha(clamp(5.0F * ratio - 4.0F, 0.0F, 1.0F));
                //---------------------------------
                //better way thanks to @cyrilmottier
                setTitleAlpha(clamp(5.0F * ratio - 4.0F, 0.0F, 1.0F));
            }
        });
    }

    private void setTitleAlpha(float alpha) {
        mAlphaForegroundColorSpan.setAlpha(alpha);
        mSpannableString.setSpan(mAlphaForegroundColorSpan, 0, mSpannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mToolbar.setTitle(mSpannableString);
    }

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
    }

    private void interpolate(View view1, View view2, float interpolation) {
        getOnScreenRect(mRect1, view1);
        getOnScreenRect(mRect2, view2);

        float scaleX = 1.0F + interpolation * (mRect2.width() / mRect1.width() - 1.0F);
        float scaleY = 1.0F + interpolation * (mRect2.height() / mRect1.height() - 1.0F);
        float translationX = 0.5F * (interpolation * (mRect2.left + mRect2.right - mRect1.left - mRect1.right));
        float translationY = 0.5F * (interpolation * (mRect2.top + mRect2.bottom - mRect1.top - mRect1.bottom));

        view1.setTranslationX(translationX);
        view1.setTranslationY(translationY - mHeader.getTranslationY());
        view1.setScaleX(scaleX);
        view1.setScaleY(scaleY);
    }

    private RectF getOnScreenRect(RectF rect, View view) {
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        return rect;
    }

    public int getScrollY() {
        View c = mListView.getChildAt(0);
        if (c == null) {
            return 0;
        }

        int firstVisiblePosition = mListView.getFirstVisiblePosition();
        int top = c.getTop();

        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            headerHeight = mPlaceHolderView.getHeight();
        }

        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
    }

    public ImageView getToolbarIcon() {
        // can't find if title not set
        for (int i = 0; i < mToolbar.getChildCount(); i++) {
            View v = mToolbar.getChildAt(i);
            if (v != null && v instanceof ImageView && !(v instanceof ImageButton)) {
                ImageView imageView = (ImageView) v;
                return imageView;
            }
        }
        return null;
    }

    private void setupToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_transparent);
    }

    public int getActionBarHeight() {
        if (mActionBarHeight != 0) {
            return mActionBarHeight;
        }
        getTheme().resolveAttribute(android.R.attr.actionBarSize, mTypedValue, true);
        mActionBarHeight = TypedValue.complexToDimensionPixelSize(mTypedValue.data, getResources().getDisplayMetrics());
        return mActionBarHeight;
    }

    private class UserInfoAdapter extends BaseAdapter {

        private Context mContext;

        private List<UserInfo> mUserInfos = new ArrayList<UserInfo>();

        public UserInfoAdapter(Context context, List<UserInfo> userInfos) {
            mContext = context;
            mUserInfos = userInfos;
        }

        @Override
        public int getCount() {
            return mUserInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return mUserInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.user_item,
                        parent, false);
                holder = new ViewHolder();
                holder.title = (TypefaceTextView) convertView.findViewById(R.id.title);
                holder.info = (TypefaceTextView) convertView.findViewById(R.id.info);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            UserInfo info = mUserInfos.get(position);
            holder.title.setText(info.title);
            holder.info.setText(info.info);

            return convertView;
        }
    }

    class ViewHolder {
        TypefaceTextView title;
        TypefaceTextView info;
    }

    private class UserInfo {
        String title;
        String info;

        public UserInfo(String title, String info) {
            this.title = title;
            this.info = info;
        }
    }

}
