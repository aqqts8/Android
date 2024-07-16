package com.example.lab6custom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MyImageAdapter  extends BaseAdapter {
    private Context mContext;
    private Integer []mThumbIds;

    public MyImageAdapter (Context context_) {
        mContext = context_;
    }

    public MyImageAdapter(Context context_, Integer []arrIds){
        mContext=context_;
        mThumbIds=arrIds;
    }
    @Override
    public int getCount() {
        return mThumbIds.length;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imgView;
        if (convertView == null) {
            imgView = new ImageView(mContext);
            imgView.setLayoutParams(new GridView.LayoutParams(200, 200));

            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgView.setPadding(8,8,8,8);
        } else {
            imgView = (ImageView) convertView;
        }

        imgView.setImageResource(mThumbIds[position]);
        return imgView;
    }

}
