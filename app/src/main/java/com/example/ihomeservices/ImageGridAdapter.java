package com.example.ihomeservices;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import java.util.Arrays;
import java.util.List;

public class ImageGridAdapter extends BaseAdapter {

    private Context mContext;

    private List<Integer> fotos = Arrays.asList(
            R.drawable.unhas_manicure1,
            R.drawable.unhas_manicure2,
            R.drawable.unhas_manicure3,
            R.drawable.unhas_manicure4,
            R.drawable.unhas_manicure5,
            R.drawable.unhas_manicure6);

    public ImageGridAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return fotos.size();
    }

    @Override
    public Object getItem(int position) {
        return fotos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return fotos.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(fotos.get(position));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return imageView;
    }
}
