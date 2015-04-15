package com.moefou.android.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.moefou.android.Application;
import com.moefou.android.R;
import com.moefou.android.event.BusProvider;
import com.moefou.android.event.FetchUserEvent;
import com.moefou.android.event.PlayMusicEvent;
import com.moefou.android.object.fm.PlayList;
import com.moefou.android.service.MoeService;
import com.moefou.android.task.FetchUserTask;
import com.moefou.android.ui.side.SideAdapter;
import com.moefou.android.ui.side.SideLayout;
import com.moefou.android.ui.views.custom.CustomViewPager;
import com.moefou.android.ui.views.font.TypefaceTextView;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;


public class HomeActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView mMenuListView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private SideLayout mSideLayout;
    public View mCurrentView;
    private CustomViewPager mCustomViewPager;
    private PlayMusicLayout mPlayMusicLayout;

    @Override
    public void onDestroy() {
        BusProvider.getInstance().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        BusProvider.getInstance().register(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(getResources().getString(R.string.home));

        mPlayMusicLayout = (PlayMusicLayout) findViewById(R.id.play_music_layout);

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


        Intent workIntent = new Intent(Application.getInstance(), MoeService.class);
        workIntent.putExtra(MoeService.OPERATION, 1);
        startService(workIntent);
    }

    @Subscribe
    public void onFetchUserEvent(FetchUserEvent event) {
        mSideLayout.updatepProfile();
    }


    private void fetchData() {
        // fetch user
        new FetchUserTask().execute();

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

    @Subscribe
    public void onPlayMusicEvent(PlayMusicEvent event) {
        PlayList playList = event.mPlayList;

        mPlayMusicLayout.updatePlayLayout(playList);

    }

}
