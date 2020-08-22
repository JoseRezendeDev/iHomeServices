package com.example.ihomeservices.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.example.ihomeservices.R;
import com.example.ihomeservices.model.Trabalhador;

import java.util.ArrayList;
import java.util.List;

public class ImageGridAdapter extends BaseAdapter {

    private Trabalhador trabalhador;
    private Context mContext;

    private List<Integer> fotos = new ArrayList<>();

    public ImageGridAdapter(Context mContext, Trabalhador trabalhador) {
        this.mContext = mContext;
        this.trabalhador = trabalhador;
        setFotos();
    }

    private void setFotos() {
        switch (trabalhador.getOficio()) {
            case PINTOR:
                fotos.add(R.drawable.pintor1);
                fotos.add(R.drawable.pintor2);
                break;
            case JARDINEIRO:
                fotos.add(R.drawable.jardineiro1);
                fotos.add(R.drawable.jardineiro2);
                break;
            case MANICURE:
                fotos.add(R.drawable.manicure1);
                fotos.add(R.drawable.manicure2);
                break;
            case CABELEIREIRO:
                fotos.add(R.drawable.cabeleireiro1);
                fotos.add(R.drawable.cabeleireiro1);
                break;
            case PEDREIRO:
                fotos.add(R.drawable.pedreiro1);
                fotos.add(R.drawable.pedreiro2);
                break;
            case PISCINEIRO:
                fotos.add(R.drawable.piscineiro1);
                fotos.add(R.drawable.piscineiro2);
                break;
        }
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
