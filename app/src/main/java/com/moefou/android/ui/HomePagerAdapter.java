package com.moefou.android.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.google.common.collect.ImmutableList;
import com.moefou.android.ui.fav.FavouriteFragment;
import com.moefou.android.ui.home.HomeFragment;
import com.moefou.android.ui.offline.OfflineFragment;

import java.util.List;

public class HomePagerAdapter extends FragmentPagerAdapter {

    private List mFragments;

    public HomePagerAdapter(FragmentActivity activity) {
        super(activity.getSupportFragmentManager());
        HomeFragment homeFragment = new HomeFragment();
        OfflineFragment offlineFragment = new OfflineFragment();
        FavouriteFragment favouriteFragment = new FavouriteFragment();
        mFragments = ImmutableList.of(homeFragment, offlineFragment, favouriteFragment);
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) mFragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}

