package com.moefou.android.ui.find;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.moefou.android.R;
import com.moefou.android.task.FetchRelationshipTask;
import com.moefou.android.ui.BaseActivity;

public class RadioDetailActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.radio_detail);

        setupToolBar(getIntent().getStringExtra("wiki_title"));

        new FetchRelationshipTask(getIntent().getIntExtra("wiki_id", -1)).execute();
    }

    private void setupToolBar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
    }
}
