package com.matis8571.countyourbike.App;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.matis8571.countyourbike.Notepad.MainActivityNotepad;
import com.matis8571.countyourbike.R;

@SuppressWarnings("Convert2Lambda")
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button bikesButton, mainToNotesButton;
    TextView mainActivityTitleText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        Log.d(TAG, "onCreate: Start");

        bikesButton = findViewById(R.id.bikesButtonID);
        mainToNotesButton = findViewById(R.id.mainToNotesButtonID);
        mainActivityTitleText = findViewById(R.id.mainActivityTitleID);
        mainActivityTitleText.setText("Count Your Bike");

        bikesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bikesButton");
                Intent bikesButtonIntent = new Intent(MainActivity.this, BikeProfileSelect.class);
                startActivity(bikesButtonIntent);
            }
        });

        mainToNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainToNotesButtonIntent = new Intent(MainActivity.this, MainActivityNotepad.class);
                startActivity(mainToNotesButtonIntent);
            }
        });
    }
}