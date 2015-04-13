package com.moefou.android.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.moefou.android.ui.fav.FavouriteFragment;
import com.moefou.android.ui.find.FindMusicFragment;
import com.moefou.android.ui.radio.MyRadioFragment;

import java.util.ArrayList;
import java.util.List;

public class HomePagerAdapter extends FragmentPagerAdapter {

    private List mFragments = new ArrayList();

    public HomePagerAdapter(FragmentActivity activity) {
        super(activity.getSupportFragmentManager());
        mFragments.add(new MyRadioFragment());
        mFragments.add(new FindMusicFragment());
        mFragments.add(new FavouriteFragment());
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

