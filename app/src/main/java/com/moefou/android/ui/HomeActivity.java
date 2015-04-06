package com.moefou.android.ui;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.activeandroid.query.Delete;
import com.moefou.android.R;
import com.moefou.android.api.MoefouManagerImpl;
import com.moefou.android.object.user.User;
import com.moefou.android.object.user.UserResponse;
import com.moefou.android.ui.side.SideAdapter;
import com.moefou.android.ui.side.SideLayout;
import com.moefou.android.ui.views.custom.CustomViewPager;
import com.moefou.android.ui.views.font.TypefaceTextView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class HomeActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView mMenuListView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private SideLayout mSideLayout;
    public View mCurrentView;
    private CustomViewPager mCustomViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(getResources().getString(R.string.home));


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        mDrawerToggle.syncState();

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // load menu
        mMenuListView = (ListView) findViewById(R.id.menu_list);

        mSideLayout = new SideLayout(this);
        mMenuListView.addHeaderView(mSideLayout, null, false);
        mMenuListView.setAdapter(new SideAdapter(this, getResources().getStringArray(R.array.side)));

        mMenuListView.setOnItemClickListener(this);

        mCustomViewPager = (CustomViewPager) findViewById(R.id.vp_pages);
        mCustomViewPager.setOffscreenPageLimit(3);
        HomePagerAdapter pagerAdapter = new HomePagerAdapter(this);
        mCustomViewPager.setAdapter(pagerAdapter);

        fetchData();
    }

    private void fetchData() {
        // fetch user
        Observable<UserResponse> observable = MoefouManagerImpl.getInstance().getCurrentUser();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserResponse>() {
                    @Override
                    public void call(UserResponse responseUser) {
                        new Delete().from(User.class).execute();
                        User user = responseUser.getResponse().getUser();
                        user.getUser_avatar().save();
                        user.save();
                        mSideLayout.updatepProfile();
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mDrawerLayout.closeDrawer(mMenuListView);
        String[] sideArray = getResources().getStringArray(R.array.side);
        getSupportActionBar().setTitle(sideArray[position - 1]);
        mCustomViewPager.setCurrentItem(position - 1, false);
        TypefaceTextView label = (TypefaceTextView) view.findViewById(R.id.label);
        label.setTextColor(getResources().getColor(R.color.item_focus));
        if (null != mCurrentView && mCurrentView != view) {
            ((TypefaceTextView) mCurrentView.findViewById(R.id.label)).setTextColor(getResources().getColor(R.color.black87));
        }
        mCurrentView = view;
    }

}
