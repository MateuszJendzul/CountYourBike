package com.matis8571.countyourbike.App.Models;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.matis8571.countyourbike.Notepad.MainActivityNotepad;
import com.matis8571.countyourbike.R;

@SuppressWarnings("Convert2Lambda")
public class CreateNewBikeActivity extends AppCompatActivity {
    private static final String TAG = "CreateNewBikeActivity";
    Button createNewBikeBackButton, createNewBikeAddButton, createNewBikeNotesButton;
    EditText nameEdit, bikeTypeEdit, brandEdit, modelEdit, mileageEdit;
    Bikes bikes;
    boolean isOldNote = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_bike_activity_layout);
        Log.d(TAG, "onCreate");

        createNewBikeAddButton = findViewById(R.id.create_new_bike_add_button_ID);
        createNewBikeBackButton = findViewById(R.id.create_new_bike_back_button_ID);
        createNewBikeNotesButton = findViewById(R.id.create_new_bike_notes_button_ID);
        nameEdit = findViewById(R.id.name_edit_ID);
        bikeTypeEdit = findViewById(R.id.bike_type_edit_ID);
        brandEdit = findViewById(R.id.brand_edit_ID);
        modelEdit = findViewById(R.id.model_edit_ID);
        mileageEdit = findViewById(R.id.mileage_edit_ID);

        bikes = new Bikes();
        try {
            bikes = (Bikes) getIntent().getSerializableExtra("oldBike");

            String nameEditString = bikes.getName();
            String bikeTypeEditString = bikes.getBikeType();
            String brandEditString = bikes.getBrand();
            String modelEditString = bikes.getModel();
            String mileageEditString = bikes.getMileage();

            nameEdit.setText(nameEditString);
            bikeTypeEdit.setText(bikeTypeEditString);
            brandEdit.setText(brandEditString);
            modelEdit.setText(modelEditString);
            mileageEdit.setText(mileageEditString);
            isOldNote = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        createNewBikeAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdit.getText().toString();
                String bikeType = bikeTypeEdit.getText().toString();
                String brand = brandEdit.getText().toString();
                String model = modelEdit.getText().toString();
                String mileage = mileageEdit.getText().toString();

                if (nameEdit.getText().toString().isEmpty() || bikeTypeEdit.getText().toString().isEmpty()
                        || brandEdit.getText().toString().isEmpty() || modelEdit.getText().toString().isEmpty()
                        || mileageEdit.getText().toString().isEmpty()) {
                    Toast.makeText(CreateNewBikeActivity.this, "Empty fields", Toast.LENGTH_SHORT).show();
                } else {

                    if (!isOldNote) {
                        bikes = new Bikes();
                    }
                    bikes.setName(name);
                    bikes.setBikeType(bikeType);
                    bikes.setBrand(brand);
                    bikes.setModel(model);
                    bikes.setMileage(mileage);

                    Intent createNewBikeAddButtonIntent = new Intent();
                    // To put extra make Notes.class implement Serializable
                    createNewBikeAddButtonIntent.putExtra("bike", bikes);
                    setResult(Activity.RESULT_OK, createNewBikeAddButtonIntent);
                    finish();
                }
            }
        });

        bikeTypeEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        createNewBikeBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        createNewBikeNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createNewBikeNotesButtonIntent = new Intent(
                        CreateNewBikeActivity.this, MainActivityNotepad.class);
                startActivity(createNewBikeNotesButtonIntent);
            }
        });
    }
}
