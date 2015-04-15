package com.moefou.android.ui.radio;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;

import com.moefou.android.R;
import com.moefou.android.provider.MoeTables.TFmCover;
import com.moefou.android.provider.MoeTables.TPlaylist;
import com.moefou.android.ui.views.font.TypefaceTextView;
import com.squareup.picasso.Picasso;


public class RadioAdapter extends ResourceCursorAdapter {

    public RadioAdapter(Context context, int layout, Cursor c) {
        super(context, layout, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = super.newView(context, cursor, parent);
        ViewHolder holder = new ViewHolder();
        holder.icon = (ImageView) view.findViewById(R.id.icon);
        holder.name = (TypefaceTextView) view.findViewById(R.id.name);
        holder.time = (TypefaceTextView) view.findViewById(R.id.time);
        holder.artist = (TypefaceTextView) view.findViewById(R.id.artist);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        String title = cursor.getString(cursor.getColumnIndex(TPlaylist.TITLE));
        String time = cursor.getString(cursor.getColumnIndex(TPlaylist.STREAM_TIME));
        String cover = cursor.getString(cursor.getColumnIndex(TFmCover.SMALL));
        String artist = cursor.getString(cursor.getColumnIndex(TPlaylist.ARTIST));

        if (!TextUtils.isEmpty(cover)) {
            Picasso.with(context).load(Uri.parse(cover)).into(holder.icon);
        } else {
            holder.icon.setImageResource(R.mipmap.cover_small);
        }
        holder.artist.setText(artist);
        holder.time.setText(time);
        holder.name.setText(title);
    }

    private final class ViewHolder {
        ImageView icon;
        TypefaceTextView name;
        TypefaceTextView time;
        TypefaceTextView artist;
    }
}
