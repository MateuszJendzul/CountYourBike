package com.matis8571.countyourbike.App;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.matis8571.countyourbike.App.Adapters.BikesListAdapter;
import com.matis8571.countyourbike.App.Database.BikesClickListener;
import com.matis8571.countyourbike.App.Database.BikesRoomDB;
import com.matis8571.countyourbike.App.Models.Bikes;
import com.matis8571.countyourbike.App.Models.CreateNewBikeActivity;
import com.matis8571.countyourbike.Notepad.NotepadActivity;
import com.matis8571.countyourbike.R;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"Convert2Lambda", "deprecation"})
@SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
public class BikeProfileSelect extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final String TAG = "BikeProfileSelect";
    List<Bikes> bikesList = new ArrayList<>();
    RecyclerView bikeProfileRecycler;
    BikesListAdapter bikesListAdapter;
    BikesRoomDB bikesRoomDB;
    Bikes selectedBike;
    TextView bikeProfileText;
    Button bikeProfileAddNewBikeButton, bikeProfileBackButton, bikeProfileHomeButton,
            bikeProfileNotesButton;
    SnapHelper snapHelper = new LinearSnapHelper();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bike_profile_select_layout);
        Log.d(TAG, "onCreate: Start");

        bikeProfileRecycler = findViewById(R.id.bike_profile_recycler);

        bikeProfileText = findViewById(R.id.bike_profile_text_ID);

        bikeProfileAddNewBikeButton = findViewById(R.id.bike_profile_add_new_bike_button_ID);
        bikeProfileBackButton = findViewById(R.id.bike_profile_back_button_ID);
        bikeProfileHomeButton = findViewById(R.id.bike_profile_home_button_ID);
        bikeProfileNotesButton = findViewById(R.id.bike_profile_notes_button_ID);

        bikeProfileText.setText("<<   Select your bike   >>");
        bikeProfileText.setTextSize(28);

        bikesRoomDB = BikesRoomDB.getInstance(this);
        bikesList = bikesRoomDB.bikesDAO().getAll();
        updateRecycler(bikesList);

        bikeProfileAddNewBikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: addNewBikeButton");
                Intent bikeProfileAddNewBikeButtonIntent = new Intent(
                        BikeProfileSelect.this, CreateNewBikeActivity.class);
                startActivityForResult(bikeProfileAddNewBikeButtonIntent, 201);
            }
        });

        bikeProfileNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: bikeProfileNotesButton");
                Intent bikeProfileNotesButtonIntent = new Intent(BikeProfileSelect.this,
                        NotepadActivity.class);
                startActivity(bikeProfileNotesButtonIntent);
            }
        });

        bikeProfileHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: bikeProfileHomeButton");
                Intent bikeProfileHomeButtonIntent = new Intent(BikeProfileSelect.this,
                        MainActivity.class);
                startActivity(bikeProfileHomeButtonIntent);
            }
        });

        bikeProfileBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: profileSelectToMainButton");
                Intent bikeProfileBackButtonIntent = new Intent(BikeProfileSelect.this,
                        MainActivity.class);
                startActivity(bikeProfileBackButtonIntent);
            }
        });
    }

    /**
     * Overrides default action on pressing phone back button.
     * Takes user back to previous activity using Intent.
     */
    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
        Intent createNewBikeBackButtonIntent = new Intent(BikeProfileSelect.this,
                MainActivity.class);
        startActivity(createNewBikeBackButtonIntent);
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d(TAG, "onActivityResult");
        if (requestCode == 201) {
            if (resultCode == Activity.RESULT_OK) {
                assert intent != null;
                Bikes newBikes = (Bikes) intent.getSerializableExtra("bike");
                bikesRoomDB.bikesDAO().insert(newBikes);
                bikesList.clear();
                bikesList.addAll(bikesRoomDB.bikesDAO().getAll());
                bikesListAdapter.notifyDataSetChanged();
            }
        } else if (requestCode == 202) {
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
                bikesListAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateRecycler(List<Bikes> bikesList) {
        Log.d(TAG, "updateRecycler");
        bikeProfileRecycler.setHasFixedSize(true);
        bikeProfileRecycler.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL));
        bikesListAdapter = new BikesListAdapter(BikeProfileSelect.this, bikesList, bikesClickListener);
        bikeProfileRecycler.setAdapter(bikesListAdapter);
        snapHelper.attachToRecyclerView(bikeProfileRecycler);
    }

    private final BikesClickListener bikesClickListener = new BikesClickListener() {
        private static final String TAG = "bikesClickListener";

        @Override
        public void onClick(Bikes bikes) {
            Log.d(TAG, "onClick");
            Intent bikesClickListenerIntent = new Intent(BikeProfileSelect.this, CreateNewBikeActivity.class);
            bikesClickListenerIntent.putExtra("oldBike", bikes);
            startActivityForResult(bikesClickListenerIntent, 202);
        }

        @Override
        public void onLongClick(Bikes bikes, CardView cardView) {
            Log.d(TAG, "onLongClick");
            selectedBike = bikes;
            showPopUp(cardView);
        }
    };

    private void showPopUp(CardView cardView) {
        Log.d(TAG, "showPopUp");
        PopupMenu popupMenu = new PopupMenu(this, cardView);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_bike_menu);
        popupMenu.show();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Log.d(TAG, "onMenuItemClick");
        if (item.getItemId() == R.id.delete_bike_menu_ID) {
            bikesRoomDB.bikesDAO().delete(selectedBike);
            bikesList.remove(selectedBike);
            bikesListAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
