package com.moefou.android.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.moefou.android.Application;
import com.moefou.android.R;
import com.moefou.android.ui.BaseFragment;



public class HomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener, TabHost.TabContentFactory {

    private TabHost mTabHost;
    private HomePagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewPager = (ViewPager) view.findViewById(R.id.vp_pages);
        mViewPager.setOffscreenPageLimit(2);
        mPagerAdapter = new HomePagerAdapter(this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(this);

        mTabHost = (TabHost) view.findViewById(R.id.th_tabs);
        mTabHost.setup();
        mTabHost.setOnTabChangedListener(this);

        createTabs();
    }

    protected void createTabs() {
        if (mTabHost.getTabWidget().getTabCount() > 0) {
            // Crash on Gingerbread if tab isn't set to zero since adding a
            // new tab removes selection state on the old tab which will be
            // null unless the current tab index is the same as the first
            // tab index being added
            mTabHost.setCurrentTab(0);
            mTabHost.clearAllTabs();
        }

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        int count = mPagerAdapter.getCount();
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec spec = mTabHost.newTabSpec("" + i);
            spec.setContent(this);
            View view = inflater.inflate(R.layout.tab_item, null);
            TextView textView = (TextView) view.findViewById(R.id.title);
            view.findViewById(R.id.directory_line).setVisibility(View.GONE);
            textView.setText(mPagerAdapter.getPageTitle(i));
            spec.setIndicator(view);
            mTabHost.addTab(spec);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mTabHost.getCurrentTab() != position)
            mTabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {
        int page = Integer.parseInt(tabId);
        if (mViewPager.getCurrentItem() != page) {
            mViewPager.setCurrentItem(page, false);
        }
        for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
            View tabView = mTabHost.getTabWidget().getChildAt(i);
            if (page == i) {
                tabView.findViewById(R.id.directory_line).setVisibility(View.VISIBLE);
                ((TextView) (tabView.findViewById(R.id.title))).setTextColor(getResources().getColor(R.color.green));
            } else {
                tabView.findViewById(R.id.directory_line).setVisibility(View.GONE);
                ((TextView) (tabView.findViewById(R.id.title))).setTextColor(getResources().getColor(R.color.tab_grey));
            }
        }
    }

    @Override
    public View createTabContent(String tag) {
        return new View(Application.getInstance());
    }
}
