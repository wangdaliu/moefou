package com.moefou.android.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.moefou.android.R;
import com.moefou.android.event.BusProvider;
import com.moefou.android.event.FetchWikiEvent;
import com.moefou.android.object.wiki.Wiki;
import com.moefou.android.task.FetchWikiTask;
import com.moefou.android.ui.BaseFragment;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class WikiFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private WikiAdapter mAdapter;
    private ListView mListView;
    private String mWikiType;
    private List<Wiki> mWikiList = new ArrayList<Wiki>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.getInstance().register(this);

        if (null != getArguments()) {
            this.mWikiType = getArguments().getString("wiki_type");
        }
    }

    @Override
    public void onDestroy() {
        BusProvider.getInstance().unregister(this);
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wiki_layout, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListView = (ListView) view.findViewById(R.id.list);
        // Set up our adapter
        mAdapter = new WikiAdapter(getActivity(), mWikiList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new FetchWikiTask(mWikiType).execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Subscribe
    public void onFetchWikiEvent(FetchWikiEvent event) {
        if (!event.getWikiType().equals(mWikiType)) {
            return;
        }

        if (event.isInit()) {
            if (mWikiList.isEmpty()) {
                mWikiList.addAll(event.getWikiList());
            }
        } else {
            mWikiList.addAll(event.getWikiList());
        }
        mAdapter.notifyDataSetChanged();
    }
}
