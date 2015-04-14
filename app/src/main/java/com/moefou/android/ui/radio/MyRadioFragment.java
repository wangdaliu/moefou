package com.moefou.android.ui.radio;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.moefou.android.R;
import com.moefou.android.event.BusProvider;
import com.moefou.android.event.FetchPlaylistEvent;
import com.moefou.android.provider.MoeTables;
import com.moefou.android.task.FetchPlaylistTask;
import com.moefou.android.ui.BaseFragment;
import com.moefou.android.ui.views.LoadMoreListView;
import com.squareup.otto.Subscribe;

public class MyRadioFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, LoadMoreListView.OnLoadMoreListener {


    private RadioAdapter mAdapter;
    private LoadMoreListView mListView;
    private SwipeRefreshLayout mRefreshLayout;
    private int mRefreshViewOffset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onDestroy() {
        BusProvider.getInstance().unregister(this);
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_radio, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (LoadMoreListView) view.findViewById(R.id.list);
        // Set up our adapter
        mAdapter = new RadioAdapter(getActivity(), R.layout.radio_item, null);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnLoadMoreListener(this);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);

        setupRefreshLayout();

        fetchData(true);
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getLoaderManager().getLoader(0) == null) {
            getLoaderManager().initLoader(0, null, this);
        } else {
            getLoaderManager().restartLoader(0, null, this);
        }
    }

    @Subscribe
    public void onFetchPlaylistEvent(FetchPlaylistEvent event) {
        if (event.success) {
            getLoaderManager().restartLoader(0, null, this);
        } else {
            stopRefreshing();
            mListView.onLoadMoreComplete();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), MoeTables.TPlaylistJoinTFmCover.CONTENT_URI_WIKI_JOIN_COVER, null, null, null, null);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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

    private void fetchData(boolean reload) {
        new FetchPlaylistTask(reload).execute();
    }

    @Override
    public void onLoadMore() {
        fetchData(false);
    }
}
