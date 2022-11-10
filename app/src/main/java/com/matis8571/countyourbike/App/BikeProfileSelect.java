package com.matis8571.countyourbike.App;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.matis8571.countyourbike.App.Adapters.BikesListAdapter;
import com.matis8571.countyourbike.App.Database.BikesClickListener;
import com.matis8571.countyourbike.App.Database.BikesRoomDB;
import com.matis8571.countyourbike.App.Models.Bikes;
import com.matis8571.countyourbike.App.Models.CreateNewBikeActivity;
import com.matis8571.countyourbike.R;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"Convert2Lambda", "deprecation"})
@SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
public class BikeProfileSelect extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final String TAG = "BikeProfileSelect";
    List<Bikes> bikes = new ArrayList<>();
    RecyclerView bikeProfileRecycler;
    BikesListAdapter bikesListAdapter;
    BikesRoomDB bikesRoomDB;
    Bikes selectedBike;
    TextView bikeProfileText;
    Button bikeProfileAddNewBikeButton, bikeProfileBackButton;
    private int x;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bike_profile_select_layout);
        Log.d(TAG, "onCreate: Start");

        bikeProfileText = findViewById(R.id.bike_profile_text_ID);
        bikeProfileAddNewBikeButton = findViewById(R.id.bike_profile_add_new_bike_button_ID);
        bikeProfileBackButton = findViewById(R.id.bike_profile_back_button_ID);
        bikeProfileRecycler = findViewById(R.id.bike_profile_recycler);

        bikeProfileText.setText("Select your bike:");
        bikeProfileText.setTextSize(28);

        bikesRoomDB = BikesRoomDB.getInstance(this);
        bikes = bikesRoomDB.bikesDAO().getAll();
        updateRecycler(bikes);

        bikeProfileAddNewBikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: addNewBikeButton");
                Intent bikeProfileAddNewBikeButtonIntent = new Intent(
                        BikeProfileSelect.this, CreateNewBikeActivity.class);
                startActivityForResult(bikeProfileAddNewBikeButtonIntent, 201);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult");
        if (requestCode == 201) {
            if (resultCode == Activity.RESULT_OK) {
                assert data != null;
                Bikes newBikes = (Bikes) data.getSerializableExtra("bike");
                bikesRoomDB.bikesDAO().insert(newBikes);
                bikes.clear();
                bikes.addAll(bikesRoomDB.bikesDAO().getAll());
                bikesListAdapter.notifyDataSetChanged();
            }
        } else if (requestCode == 202) {
            if (resultCode == Activity.RESULT_OK) {
                assert data != null;
                Bikes newBikes = (Bikes) data.getSerializableExtra("bike");
                bikesRoomDB.bikesDAO().update(newBikes.getID(), newBikes.getName(),
                        newBikes.getBikeType(), newBikes.getBrand(), newBikes.getModel(), newBikes.getMileage());
                bikes.clear();
                bikes.addAll(bikesRoomDB.bikesDAO().getAll());
                bikesListAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateRecycler(List<Bikes> bikes) {
        Log.d(TAG, "updateRecycler");
        bikeProfileRecycler.setHasFixedSize(true);
        bikeProfileRecycler.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL));
        bikesListAdapter = new BikesListAdapter(BikeProfileSelect.this, bikes, bikesClickListener);
        bikeProfileRecycler.setAdapter(bikesListAdapter);
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
            bikes.remove(selectedBike);
            bikesListAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
