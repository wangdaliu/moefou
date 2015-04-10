package com.moefou.android.ui.home;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.activeandroid.content.ContentProvider;
import com.moefou.android.Const;
import com.moefou.android.R;
import com.moefou.android.event.BusProvider;
import com.moefou.android.event.FetchWikiEvent;
import com.moefou.android.object.wiki.Wiki;
import com.moefou.android.task.FetchWikiTask;
import com.moefou.android.ui.BaseFragment;
import com.squareup.otto.Subscribe;

public class MusicFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {

    private RadioCursorAdapter mAdapter;
    private ListView mListView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onDestroy() {
        if (null != mAdapter) {
            mAdapter.changeCursor(null);
        }
        BusProvider.getInstance().unregister(this);
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.music_layout, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListView = (ListView) view.findViewById(R.id.list);
        // Set up our adapter
        mAdapter = new RadioCursorAdapter(getActivity(), R.layout.radio_item, null);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);

        new FetchWikiTask(Const.MUSIC).execute();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getLoaderManager().getLoader(0) == null) {
            getLoaderManager().initLoader(0, null, this);
        } else {
            getLoaderManager().restartLoader(0, null, this);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), ContentProvider.createUri(Wiki.class, null), null,  "wiki_type = " + Const.MUSIC, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void restartLoader() {
        if (isAdded()) {
            getLoaderManager().restartLoader(0, null, this);
        }
    }

    @Subscribe
    public void onFetchWikiEvent(FetchWikiEvent event) {
        if (event.getWikiType().equals(Const.MUSIC)) {
            restartLoader();
        }
    }
}
