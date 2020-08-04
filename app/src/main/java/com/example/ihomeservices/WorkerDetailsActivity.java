package com.example.ihomeservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class WorkerDetailsActivity extends AppCompatActivity {

    private ImageView img1;
    private ImageView img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_details);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);

        img1.setImageResource(R.drawable.unhas_manicure);
        img2.setImageResource(R.drawable.unhas_manicure2);
    }
}
