package com.example.ihomeservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WorkersActivity extends AppCompatActivity {

    private Button btnFakeListaWorkers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers);

        btnFakeListaWorkers = findViewById(R.id.btnFakeListaWorkers);

        btnFakeListaWorkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WorkerDetailsActivity.class);

                startActivity(intent);
            }
        });
    }
}
