package com.matis8571.countyourbike;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button bikesButton;
    TextView mainActivityTitleText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        bikesButton = findViewById(R.id.bikes_button_id);
        mainActivityTitleText = findViewById(R.id.mainActivityTitleID);
        mainActivityTitleText.setText("Count Your Bike");
    }
}