package com.moefou.android.ui.home;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.moefou.android.R;
import com.moefou.android.object.wiki.Wiki;
import com.moefou.android.ui.views.font.TypefaceTextView;
import com.moefou.android.util.TimeFormatUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WikiAdapter extends BaseAdapter {

    private Context mContext;
    private List<Wiki> mWikiList = new ArrayList<Wiki>();

    public WikiAdapter(Context context, List<Wiki> wikiList) {
        mContext = context;
        mWikiList = wikiList;
    }

    @Override
    public int getCount() {
        return mWikiList.size();
    }

    @Override
    public Object getItem(int position) {
        return mWikiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.wiki_item, null);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);
            viewHolder.name = (TypefaceTextView) convertView.findViewById(R.id.name);
            viewHolder.date = (TypefaceTextView) convertView.findViewById(R.id.date);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Wiki wiki = mWikiList.get(position);

        if (null != wiki.getWiki_cover()) {
            Picasso.with(mContext).load(Uri.parse(wiki.getWiki_cover().getSmall())).into(viewHolder.icon);
        } else {
            viewHolder.icon.setImageResource(R.mipmap.cover_small);
        }

        viewHolder.date.setText(TimeFormatUtil.formatTime(wiki.getWiki_date()));
        viewHolder.name.setText(wiki.getWiki_title());
        return convertView;
    }


    private final class ViewHolder {
        ImageView icon;
        TypefaceTextView name;
        TypefaceTextView date;
    }
}
