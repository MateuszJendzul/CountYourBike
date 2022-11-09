package com.matis8571.countyourbike.App.Models;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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

            Spannable editNameString = new SpannableString("Name: " + bikes.getName());
            editNameString.setSpan(new RelativeSizeSpan(1f), 0,0,0);
            editNameString.setSpan(new ForegroundColorSpan(Color.BLUE),0,0,0);

            String bikeTypeEditString = "Bike Type: " + bikes.getBikeType();
            String brandEditString = "Brand: " + bikes.getBrand();
            String modelEditString = "Model: " + bikes.getModel();
            String mileageEditString = "Mileage: " + bikes.getMileage();

            nameEdit.setText(editNameString);
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

    public static void setFontSizeForPath(Spannable spannable, String path, int fontSizeInPixel) {
        int startIndexOfPath = spannable.toString().indexOf(path);
        spannable.setSpan(new AbsoluteSizeSpan(fontSizeInPixel), startIndexOfPath,
                startIndexOfPath + path.length(), 0);
    }
}
