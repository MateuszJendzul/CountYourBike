package com.matis8571.countyourbike.App;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.matis8571.countyourbike.App.Adapters.LeaderboardListAdapter;
import com.matis8571.countyourbike.App.Database.BikesRoomDB;
import com.matis8571.countyourbike.App.Database.LeaderboardClickListener;
import com.matis8571.countyourbike.App.Models.Bikes;
import com.matis8571.countyourbike.App.Models.CreateNewBikeActivity;
import com.matis8571.countyourbike.Notepad.NotepadActivity;
import com.matis8571.countyourbike.R;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"Convert2Lambda", "deprecation"})
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button bikesButton, mainActivityNotesButton;
    TextView mainActivityTitleText;
    RecyclerView mainLeaderboardRecycler;
    BikesRoomDB bikesRoomDB;
    LeaderboardListAdapter leaderboardListAdapter;
    List<Bikes> bikesList = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        Log.d(TAG, "onCreate: Start");
        bikesRoomDB = BikesRoomDB.getInstance(this);
        bikesList = bikesRoomDB.bikesDAO().getAll();

        bikesButton = findViewById(R.id.bikes_button_ID);
        mainActivityNotesButton = findViewById(R.id.main_activity_notes_button_ID);

        mainActivityTitleText = findViewById(R.id.main_activity_title_text_ID);
        mainActivityTitleText.setText("Count Your Bike");
        mainActivityTitleText.setTextSize(40);

        mainLeaderboardRecycler = findViewById(R.id.main_leaderboard_recycler_ID);
        updateLeaderboardRecycler(bikesList);

        bikesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bikesButton");
                Intent bikesButtonIntent = new Intent(MainActivity.this, BikeProfileSelect.class);
                startActivity(bikesButtonIntent);
            }
        });

        mainActivityNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivityNotesButtonIntent = new Intent(MainActivity.this,
                        NotepadActivity.class);
                startActivity(mainActivityNotesButtonIntent);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d(TAG, "onActivityResult");
        if (requestCode == 202) {
            if (resultCode == Activity.RESULT_OK) {
                assert intent != null;
                Bikes newBikes = (Bikes) intent.getSerializableExtra("bike");
                bikesRoomDB.bikesDAO().update(newBikes.getID(), newBikes.getName(),
                        newBikes.getBrand(), newBikes.getModel(), newBikes.getMileage(),
                        newBikes.getDay(), newBikes.getMonth(), newBikes.getYear(),
                        newBikes.getKmToday(), newBikes.getKmThisWeek(), newBikes.getKmThisMonth(),
                        newBikes.getKmThisYear(), newBikes.getBikeImageID(),
                        newBikes.getBikeImageBoardPosition(), newBikes.isBikeCreated(),
                        newBikes.getDayToCompare(), newBikes.getWeekToCompare(),
                        newBikes.getMonthToCompare(), newBikes.getYearToCompare());
                bikesList.clear();
                bikesList.addAll(bikesRoomDB.bikesDAO().getAll());
                leaderboardListAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateLeaderboardRecycler(List<Bikes> bikesList) {
        mainLeaderboardRecycler.setHasFixedSize(true);
        mainLeaderboardRecycler.setLayoutManager(new StaggeredGridLayoutManager(
                1, LinearLayoutManager.VERTICAL));
        leaderboardListAdapter = new LeaderboardListAdapter(MainActivity.this, bikesList,
                leaderboardClickListener);
        mainLeaderboardRecycler.setAdapter(leaderboardListAdapter);
    }

    private final LeaderboardClickListener leaderboardClickListener = new LeaderboardClickListener() {

        @Override
        public void onClick(Bikes bikes) {
            Log.d(TAG, "onClick");
            Intent bikesClickListenerIntent = new Intent(MainActivity.this, CreateNewBikeActivity.class);
            bikesClickListenerIntent.putExtra("oldBike", bikes);
            startActivityForResult(bikesClickListenerIntent, 202);
        }
    };
    //SP - SharedPreferences
}