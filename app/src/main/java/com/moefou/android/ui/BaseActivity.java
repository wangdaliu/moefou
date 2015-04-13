package com.moefou.android.ui;

import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

public class BaseActivity extends ActionBarActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
