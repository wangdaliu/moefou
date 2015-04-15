package com.moefou.android.ui.find;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.moefou.android.Const;
import com.moefou.android.R;
import com.moefou.android.event.BusProvider;
import com.moefou.android.event.FetchWikiEvent;
import com.moefou.android.provider.MoeTables;
import com.moefou.android.provider.MoeTables.TWikiJoinTWikiCover;
import com.moefou.android.task.FetchWikiTask;
import com.moefou.android.ui.BaseFragment;
import com.moefou.android.ui.views.LoadMoreListView;
import com.squareup.otto.Subscribe;

public class WikiFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, LoadMoreListView.OnLoadMoreListener {

    private WikiAdapter mAdapter;
    private LoadMoreListView mListView;
    private String mWikiType;
    private SwipeRefreshLayout mRefreshLayout;
    private int mRefreshViewOffset;

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

        mListView = (LoadMoreListView) view.findViewById(R.id.list);
        // Set up our adapter
        mAdapter = new WikiAdapter(getActivity(), R.layout.wiki_item, null);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);

        mListView.setOnLoadMoreListener(this);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);

        setupRefreshLayout();

        fetchData(true);
    }

    private void fetchData(boolean reload) {
        new FetchWikiTask(mWikiType, reload).execute();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getLoaderManager().getLoader(0) == null) {
            getLoaderManager().initLoader(0, null, this);
        } else {
            getLoaderManager().restartLoader(0, null, this);
        }
        startRefreshing();
    }

    protected void setupRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(R.color.actionbar_color, R.color.statusbar_color);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshViewOffset = getResources().getDimensionPixelSize(R.dimen.feed_refresh_top_padding);
        mRefreshLayout.setProgressViewOffset(false, 0, mRefreshViewOffset);
    }

    protected void startRefreshing() {
        mRefreshLayout.postOnAnimation(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
            }
        });
    }

    protected void stopRefreshing() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cursor cursor = (Cursor) mAdapter.getItem(position);
        String wikiType = cursor.getString(cursor.getColumnIndex(MoeTables.TWiki.WIKI_TYPE));
        int wikiId = cursor.getInt(cursor.getColumnIndex(MoeTables.TWiki.WIKI_ID));
        String wikiTitle = cursor.getString(cursor.getColumnIndex(MoeTables.TWiki.WIKI_TITLE));
        Intent intent;
        if (wikiType.equals(Const.RADIO)) {
            intent = new Intent(getActivity(), RadioDetailActivity.class);
        } else {
            intent = new Intent(getActivity(), MusicDetailActivity.class);
        }
        intent.putExtra("wiki_title", wikiTitle);
        intent.putExtra("wiki_id", wikiId);
        startActivity(intent);
    }

    @Subscribe
    public void onFetchWikiEvent(FetchWikiEvent event) {
        if (!event.getWikiType().equals(mWikiType)) {
            return;
        }

        if (event.isSuccess()) {
            getLoaderManager().restartLoader(0, null, this);
        } else {
            stopRefreshing();
            mListView.onLoadMoreComplete();
        }
    }

    @Override
    public void onRefresh() {
        if (isOnline()) {
            if (isAdded()) {
                startRefreshing();
                fetchData(true);
            }
        } else {
            stopRefreshing();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), TWikiJoinTWikiCover.CONTENT_URI_WIKI_JOIN_COVER, null, TWikiJoinTWikiCover.WIKI_TYPE + " = '" + mWikiType + "'", null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
        stopRefreshing();
        mListView.onLoadMoreComplete();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onLoadMore() {
        fetchData(false);
    }
}
