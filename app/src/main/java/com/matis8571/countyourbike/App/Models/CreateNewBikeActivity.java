package com.matis8571.countyourbike.App.Models;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.matis8571.countyourbike.Notepad.MainActivityNotepad;
import com.matis8571.countyourbike.R;

import java.util.Calendar;

@SuppressWarnings("Convert2Lambda")
@SuppressLint("SetTextI18n")
public class CreateNewBikeActivity extends AppCompatActivity {
    private static final String TAG = "CreateNewBikeActivity";
    DatePickerDialog datePickerDialog;
    Button createNewBikeBackButton, createNewBikeAddButton, createNewBikeNotesButton,
            datePickerButton, removeKmButton, addKmButton;
    TextView nameText, bikeTypeText, brandText, modelText, mileageText, kmTodayTitleText, kmThisWeekTitleText,
            kmThisMonthTitleText, kmThisYearTitleText, kmTodayText, kmThisWeekText, kmThisMonthText,
            kmThisYearText, kmToCountText;
    EditText nameEdit, bikeTypeEdit, brandEdit, modelEdit, mileageEdit, kmToCountEdit;
    Bikes bikes;
    Calendar currentDateCalendar = Calendar.getInstance();
    private int toAdd, toRemove;
    boolean isOldBike = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_bike_activity_layout);
        Log.d(TAG, "onCreate");
        resetVariables();
        initDatePicker();

        createNewBikeAddButton = findViewById(R.id.create_new_bike_add_button_ID);
        createNewBikeBackButton = findViewById(R.id.create_new_bike_back_button_ID);
        createNewBikeNotesButton = findViewById(R.id.create_new_bike_notes_button_ID);
        datePickerButton = findViewById(R.id.date_picker_button_ID);
        addKmButton = findViewById(R.id.add_km_button_ID);
        removeKmButton = findViewById(R.id.remove_km_button_ID);

        nameText = findViewById(R.id.name_text_ID);
        bikeTypeText = findViewById(R.id.bike_type_text_ID);
        brandText = findViewById(R.id.brand_text_ID);
        modelText = findViewById(R.id.model_text_ID);
        mileageText = findViewById(R.id.mileage_text_ID);
        kmTodayTitleText = findViewById(R.id.km_today_title_text_ID);
        kmThisWeekTitleText = findViewById(R.id.km_this_week_title_text_ID);
        kmThisMonthTitleText = findViewById(R.id.km_this_month_title_text_ID);
        kmThisYearTitleText = findViewById(R.id.km_this_year_title_text_ID);
        kmToCountText = findViewById(R.id.km_to_count_text_ID);
        kmTodayText = findViewById(R.id.km_today_text_ID);
        kmThisWeekText = findViewById(R.id.km_this_week_text_ID);
        kmThisMonthText = findViewById(R.id.km_this_month_text_ID);
        kmThisYearText = findViewById(R.id.km_this_year_text_ID);

        nameEdit = findViewById(R.id.name_edit_ID);
        bikeTypeEdit = findViewById(R.id.bike_type_edit_ID);
        brandEdit = findViewById(R.id.brand_edit_ID);
        modelEdit = findViewById(R.id.model_edit_ID);
        mileageEdit = findViewById(R.id.mileage_edit_ID);
        kmToCountEdit = findViewById(R.id.km_to_count_edit_ID);

        bikes = new Bikes();
        try {
            bikes = (Bikes) getIntent().getSerializableExtra("oldBike");

            nameEdit.setText(bikes.getName());
            bikeTypeEdit.setText(bikes.getBikeType());
            brandEdit.setText(bikes.getBrand());
            modelEdit.setText(bikes.getModel());
            mileageEdit.setText("" + bikes.getMileage());
            kmTodayText.setText("" + bikes.getKmToday());
            kmThisWeekText.setText("" + bikes.getKmThisWeek());
            kmThisMonthText.setText("" + bikes.getKmThisMonth());
            kmThisYearText.setText("" + bikes.getKmThisYear());

            isOldBike = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        createNewBikeAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nameEdit.getText().toString().isEmpty() || bikeTypeEdit.getText().toString().isEmpty()
                        || brandEdit.getText().toString().isEmpty() || modelEdit.getText().toString().isEmpty()
                        || mileageEdit.getText().toString().isEmpty()) {
                    Toast.makeText(CreateNewBikeActivity.this, "Empty fields", Toast.LENGTH_SHORT).show();

                } else {
                    int mileage = Integer.parseInt(mileageEdit.getText().toString());
                    String name = nameEdit.getText().toString();
                    String bikeType = bikeTypeEdit.getText().toString();
                    String brand = brandEdit.getText().toString();
                    String model = modelEdit.getText().toString();

                    if (!isOldBike) {
                        bikes = new Bikes();
                    }
                    bikes.setName(name);
                    bikes.setBikeType(bikeType);
                    bikes.setBrand(brand);
                    bikes.setModel(model);
                    bikes.setMileage(mileage);

                    Intent createNewBikeExtraIntent = new Intent();
                    // To put extra make Bikes.class implement Serializable
                    createNewBikeExtraIntent.putExtra("bike", bikes);
                    setResult(Activity.RESULT_OK, createNewBikeExtraIntent);
                    Toast.makeText(CreateNewBikeActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        addKmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kmToCountEdit.getText().toString().isEmpty()) {
                    Toast.makeText(CreateNewBikeActivity.this, "Empty field", Toast.LENGTH_SHORT).show();
                } else {
                    toAdd = Integer.parseInt(kmToCountEdit.getText().toString());
                    countKm();
                }
            }
        });

        removeKmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kmToCountEdit.getText().toString().isEmpty()) {
                    Toast.makeText(CreateNewBikeActivity.this, "Empty field", Toast.LENGTH_SHORT).show();
                } else {
                    toRemove = Integer.parseInt(kmToCountEdit.getText().toString());
                    countKm();
                }
            }
        });

        //Opens method which allows to pick a date
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: datePickerButton");
                datePickerDialog.show();
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

    private void resetVariables() {
        Calendar savedDayCalendar = Calendar.getInstance();
        if (currentDateCalendar.after(savedDayCalendar)) {
            bikes.setKmToday(0);
            savedDayCalendar.add(Calendar.DATE, 1);
        }
    }

    private void countKm() {
        bikes.setKmToday(bikes.getKmToday() + toAdd - toRemove);
        bikes.setKmThisWeek(bikes.getKmThisWeek() + toAdd - toRemove);
        bikes.setKmThisMonth(bikes.getKmThisMonth() + toAdd - toRemove);
        bikes.setKmThisYear(bikes.getKmThisYear() + toAdd - toRemove);
        bikes.setMileage(bikes.getMileage() + toAdd - toRemove);

        kmTodayText.setText("" + bikes.getKmToday());
        kmThisWeekText.setText("" + bikes.getKmThisWeek());
        kmThisMonthText.setText("" + bikes.getKmThisMonth());
        kmThisYearText.setText("" + bikes.getKmThisYear());
        mileageEdit.setText("" + bikes.getMileage());
        toAdd = 0;
        toRemove = 0;
    }

    /**
     * Allows user to manually set a date.
     */
    private void initDatePicker() {
        Log.d(TAG, "initDatePicker");
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                bikes.setDay(day);
                bikes.setMonth(month);
                bikes.setYear(year);
                datePickerButton.setText(makeDateString(bikes.getDay(), bikes.getMonth(), bikes.getYear()));
            }
        };

        Calendar currentDate = Calendar.getInstance();
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        int month = currentDate.get(Calendar.MONTH);
        int year = currentDate.get(Calendar.YEAR);

        Calendar minDate = Calendar.getInstance();
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        minDate.set(Calendar.MONTH, 0);
        minDate.set(Calendar.YEAR, 2020);

        @SuppressWarnings("deprecation") int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, day, month, year);
        datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
        Log.d(TAG, "makeDateString");
        return getMonthFormat(month) + " " + day + " " + year;
    }

    /**
     * Gets month as int value and matches it with its name to display.
     *
     * @param month set month in its numeric (int) format.
     * @return matched name, or displays ERROR if probably number is out of range of month amount (1-12).
     */
    private String getMonthFormat(int month) {
        Log.d(TAG, "getMonthFormat");
        if (month == 1) {
            return "JAN";
        } else if (month == 2) {
            return "FEB";
        } else if (month == 3) {
            return "MAR";
        } else if (month == 4) {
            return "APR";
        } else if (month == 5) {
            return "MAY";
        } else if (month == 6) {
            return "JUN";
        } else if (month == 7) {
            return "JUL";
        } else if (month == 8) {
            return "AUG";
        } else if (month == 9) {
            return "SEP";
        } else if (month == 10) {
            return "OCT";
        } else if (month == 11) {
            return "NOV";
        } else if (month == 12) {
            return "DEC";
        } else {
            return "ERROR: Out of range?";
        }
    }
}