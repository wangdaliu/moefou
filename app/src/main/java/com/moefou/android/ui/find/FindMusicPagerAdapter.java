package com.moefou.android.ui.find;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.moefou.android.Application;
import com.moefou.android.Const;
import com.moefou.android.R;
import com.moefou.android.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Pager adapter for a user's different views
 */
public class FindMusicPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragments = new ArrayList<BaseFragment>();

    public FindMusicPagerAdapter(Fragment fragment) {
        super(fragment.getChildFragmentManager());
        mFragments.add(getInstance(Const.RADIO));
        mFragments.add(getInstance(Const.MUSIC));
    }

    private WikiFragment getInstance(String type) {
        WikiFragment radioFragment = new WikiFragment();
        Bundle bundle = new Bundle();
        bundle.putString("wiki_type", type);
        radioFragment.setArguments(bundle);
        return radioFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
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

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return Application.getInstance().getResources().getString(R.string.radio);
            case 1:
                return Application.getInstance().getResources().getString(R.string.album);
            default:
                return null;
        }
    }
}
