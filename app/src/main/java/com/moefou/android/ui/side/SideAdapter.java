package com.moefou.android.ui.side;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.moefou.android.R;
import com.moefou.android.ui.views.font.TypefaceTextView;

public class SideAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mLabelArray;

    public SideAdapter(Context context, String[] labelArray) {
        this.mContext = context;
        this.mLabelArray = labelArray;
    }

    @Override
    public int getCount() {
        return mLabelArray.length;
    }

    @Override
    public Object getItem(int position) {
        return mLabelArray[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.menu_item,
                    parent, false);
            holder = new ViewHolder();
            holder.label = (TypefaceTextView) convertView.findViewById(R.id.label);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder {
        TypefaceTextView label;
    }
}
