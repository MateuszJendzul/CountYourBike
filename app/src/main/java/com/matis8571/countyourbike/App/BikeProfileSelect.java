package com.matis8571.countyourbike.App;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.matis8571.countyourbike.App.Database.BikesRoomDB;
import com.matis8571.countyourbike.App.Models.Bikes;
import com.matis8571.countyourbike.Notepad.Adapters.NotesListAdapter;
import com.matis8571.countyourbike.R;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Convert2Lambda")
public class BikeProfileSelect extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final String TAG = "BikeProfileSelect";
    List<Bikes> bikes = new ArrayList<>();
    RecyclerView bikeProfileRecycler;
    NotesListAdapter bikeProfileNotesListAdapter;
    BikesRoomDB bikesRoomDB;
    Bikes selectedBikes;
    TextView bikeProfileText;
    Button bikeProfileAddNewBikeButton, bikeProfileBackButton;

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
        bikeProfileAddNewBikeButton = findViewById(R.id.bike_profile_add_new_bike_button_ID);
        bikeProfileBackButton = findViewById(R.id.bike_profile_back_button_ID);
        bikeProfileRecycler = findViewById(R.id.bike_profile_recycler);

        bikeProfileText.setText("Select your bike:");
        bikeProfileText.setTextSize(28);
        bikeProfileAddNewBikeButton.setText(bike1Title);

        bikesRoomDB = BikesRoomDB.getInstance(this);
        bikes = bikesRoomDB.bikesDAO().getAll();
        updateRecycler(bikes);

        bikeProfileAddNewBikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: addNewBikeButton");

            }
        });

        bikeProfileBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: profileSelectToMainButton");
                finish();
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
