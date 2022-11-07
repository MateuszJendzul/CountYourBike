package com.matis8571.countyourbike.App;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.matis8571.countyourbike.R;

@SuppressWarnings("Convert2Lambda")
public class BikeProfileSelect extends AppCompatActivity {
    private static final String TAG = "BikeProfileSelect";

    TextView bikeProfileText;
    Button bikeBike1ProfileSelectButton, profileSelectBackButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bike_profile_select_layout);
        Log.d(TAG, "onCreate: Start");

        SharedPreferences bike1PrefsReceiver = getApplicationContext().getSharedPreferences(
                "bike1Prefs", Context.MODE_PRIVATE);
        String bike1Title = bike1PrefsReceiver.getString("bike1Title", "Bike 1");

        bikeProfileText = findViewById(R.id.bike_profile_text_ID);
        bikeBike1ProfileSelectButton = findViewById(R.id.bike_bike_1_profile_select_button_ID);
        profileSelectBackButton = findViewById(R.id.profile_select_back_button_ID);

        bikeProfileText.setText("Select your bike:");
        bikeProfileText.setTextSize(28);
        bikeBike1ProfileSelectButton.setText(bike1Title);

        bikeBike1ProfileSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bike1BikeProfileSelectButton");
                Intent bike1BikeProfileSelectButtonIntent = new Intent(BikeProfileSelect.this, Bike1.class);
                startActivity(bike1BikeProfileSelectButtonIntent);
            }
        });

        profileSelectBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: profileSelectToMainButton");
                finish();
            }
        });
    }
}
