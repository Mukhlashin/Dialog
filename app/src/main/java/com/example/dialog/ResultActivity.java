package com.example.dialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView tvName, tvAge, tvAddress, tvQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvName = findViewById(R.id.tv_name);
        tvAge = findViewById(R.id.tv_age);
        tvAddress = findViewById(R.id.tv_address);
        tvQuote = findViewById(R.id.tv_quote);

        tvName.setText(getIntent().getStringExtra("name"));
        tvAge.setText(getIntent().getStringExtra("age"));
        tvAddress.setText(getIntent().getStringExtra("address"));
        tvQuote.setText(getIntent().getStringExtra("quote"));


    }
}
