package com.example.ihomeservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

public class WorkerDetailsActivity extends AppCompatActivity {

    private GridView gridView;
    private ImageGridAdapter imageGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_details);

        gridView = findViewById(R.id.gvFotos);

        imageGridAdapter = new ImageGridAdapter(this);
        gridView.setAdapter(imageGridAdapter);

    }
}
