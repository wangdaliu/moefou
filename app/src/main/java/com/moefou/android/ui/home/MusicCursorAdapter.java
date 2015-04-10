package com.moefou.android.ui.home;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;

import com.moefou.android.R;
import com.moefou.android.ui.views.font.TypefaceTextView;

public class MusicCursorAdapter extends ResourceCursorAdapter {

    public MusicCursorAdapter(Context context, int layout, Cursor c) {
        super(context, layout, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = super.newView(context, cursor, parent);
        ViewHolder holder = new ViewHolder();
        holder.icon = (ImageView) view.findViewById(R.id.icon);
        holder.name = (TypefaceTextView) view.findViewById(R.id.name);
        holder.date = (TypefaceTextView) view.findViewById(R.id.date);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        String title = cursor.getString(cursor.getColumnIndex("wiki_title"));
        long date = cursor.getLong(cursor.getColumnIndex("wiki_date"));

        holder.name.setText(title);
    }


    private final class ViewHolder {
        ImageView icon;
        TypefaceTextView name;
        TypefaceTextView date;
    }
}
