package com.matis8571.countyourbike.App;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.matis8571.countyourbike.Notepad.MainActivityNotepad;
import com.matis8571.countyourbike.R;

import java.util.Calendar;

@SuppressWarnings({"FieldCanBeLocal", "Convert2Lambda"})
public class Bike1 extends AppCompatActivity {
    private static final String TAG = "Bike1";

    EditText bike1KmToAddOrRemoveEdit, bike1MaintenanceKmEdit;
    TextView bike1Text, bike1LastMaintenanceText, bike1TotalKmText, bike1KmAtMaintenanceText,
            bike1KmTodayText, bike1KmThisWeekText, bike1KmThisMonthText, bike1KmThisYearText,
            bike1KmToAddOrRemoveText, bike1KmToAddOrRemoveTextTip;
    Button bike1DatePickerButton, bike1BackButton, bike1ToHomeButton,
            bike1NotesButton, bike1KmRemoveButton, bike1KmToAddButton;
    DatePickerDialog datePickerDialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bike_1_layout);
        Log.d(TAG, "onCreate: Start");
        initDatePicker();

        SharedPreferences bike1PrefsReceiver = getApplicationContext().getSharedPreferences(
                "bike1Prefs", Context.MODE_PRIVATE);
        int kmAtMaintenance = bike1PrefsReceiver.getInt("kmAtMaintenance", 0);

        bike1Text = findViewById(R.id.bike_1_text_ID);
        bike1LastMaintenanceText = findViewById(R.id.bike_1_last_maintenance_text_ID);
        bike1KmTodayText = findViewById(R.id.bike_1_km_today_text_ID);
        bike1KmThisWeekText = findViewById(R.id.bike_1_km_this_week_text_ID);
        bike1KmThisMonthText = findViewById(R.id.bike_1_km_this_month_text_ID);
        bike1KmThisYearText = findViewById(R.id.bike_1_km_this_year_text_ID);
        bike1TotalKmText = findViewById(R.id.bike_1_total_km_text_ID);
        bike1KmAtMaintenanceText = findViewById(R.id.bike_1_km_at_maintenance_text_ID);
        bike1KmToAddOrRemoveText = findViewById(R.id.bike_1_km_to_add_or_remove_text_ID);
        bike1KmToAddOrRemoveTextTip = findViewById(R.id.bike_1_km_to_add_or_remove_text_tip_ID);
        bike1DatePickerButton = findViewById(R.id.bike_1_date_picker_button_ID);
        bike1BackButton = findViewById(R.id.bike_1_back_button_ID);
        bike1ToHomeButton = findViewById(R.id.bike_1_to_home_button_ID);
        bike1NotesButton = findViewById(R.id.bike_1_notes_button_ID);
        bike1KmToAddButton = findViewById(R.id.bike_1_km_add_button_ID);
        bike1KmRemoveButton = findViewById(R.id.bike_1_km_remove_button_ID);
        bike1KmToAddOrRemoveEdit = findViewById(R.id.bike_1_km_to_add_or_remove_edit_ID);
        bike1MaintenanceKmEdit = findViewById(R.id.bike_1_maintenance_km_edit_ID);

        bike1Text.setText("Bike 1 profile:");
        bike1Text.setTextSize(24);
        bike1LastMaintenanceText.setText("Last maintenance:");
        bike1LastMaintenanceText.setTextSize(16);
        bike1KmTodayText.setText("km today:");
        bike1KmTodayText.setTextSize(16);
        bike1KmThisWeekText.setText("km this week");
        bike1KmThisWeekText.setTextSize(16);
        bike1KmThisMonthText.setText("km this month:");
        bike1KmThisMonthText.setTextSize(16);
        bike1KmThisYearText.setText("km this year:");
        bike1KmThisYearText.setTextSize(16);
        bike1TotalKmText.setText("km in total:");
        bike1TotalKmText.setTextSize(16);
        bike1KmAtMaintenanceText.setText("km at maintenance: " + kmAtMaintenance);
        bike1KmAtMaintenanceText.setTextSize(16);
        bike1KmToAddOrRemoveText.setText("Add or remove km");
        bike1KmToAddOrRemoveTextTip.setText("Hold button to add count outside of today's summary");
        bike1KmToAddOrRemoveTextTip.setTextSize(8);
        bike1DatePickerButton.setText(getSavedDate());

        //Open method which allows to pick a date
        bike1DatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bike1DatePickerButton");
                datePickerDialog.show();
            }
        });

        //Go back to previous activity
        bike1BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bike1ToMainButton");
                finish();
            }
        });

        //Open home activity
        bike1ToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bike1ToHomeButtonIntent = new Intent(Bike1.this, MainActivity.class);
                startActivity(bike1ToHomeButtonIntent);
            }
        });

        //Open notepad activity
        bike1NotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bike1NotesButtonIntent = new Intent(Bike1.this, MainActivityNotepad.class);
                startActivity(bike1NotesButtonIntent);
            }
        });

        /* Change EditText according to contained methods, pretty self-exploratory
            also store and use user input using SP (SharedPreferences)
         */
        //noinspection FieldMayBeFinal
        bike1MaintenanceKmEdit.addTextChangedListener(new TextWatcher() {
            int kmAtMaintenance;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                bike1MaintenanceKmEdit.setHint("type here:");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                bike1KmAtMaintenanceText.setText("km at maintenance: " + charSequence);
                kmAtMaintenance = Integer.parseInt(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                bike1MaintenanceKmEdit.setHint("type here:");
                SharedPreferences bike1Prefs = getSharedPreferences("bike1Prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor bike1PrefsEditor = bike1Prefs.edit();
                bike1PrefsEditor.putInt("kmAtMaintenance", kmAtMaintenance).apply();
            }
        });
    }

    /**
     * Gets from SP (SharedPreferences) variables with previously saved date values and sets it to
     * display at spinner button which is responsible for setting up and displaying date.
     *
     * @return use makeDateString method to create String containing the set date.
     */
    private String getSavedDate() {
        Log.d(TAG, "getSavedDate");
        SharedPreferences bike1PrefsReceiver = getApplicationContext().getSharedPreferences(
                "bike1Prefs", Context.MODE_PRIVATE);
        Calendar calendar = Calendar.getInstance();
        int day = bike1PrefsReceiver.getInt("day", calendar.get(Calendar.DAY_OF_MONTH));
        int month = bike1PrefsReceiver.getInt("month", (calendar.get(Calendar.MONTH) + 1));
        int year = bike1PrefsReceiver.getInt("year", calendar.get(Calendar.YEAR));

        return makeDateString(day, month, year);
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
                SharedPreferences bike1Prefs = getSharedPreferences("bike1Prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor bike1PrefsEditor = bike1Prefs.edit();
                bike1PrefsEditor.putInt("day", day).apply();
                bike1PrefsEditor.putInt("month", month).apply();
                bike1PrefsEditor.putInt("year", year).apply();
            }
        };

        Calendar calendar = Calendar.getInstance();
        Calendar minDate = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        minDate.set(Calendar.DAY_OF_MONTH, 1);
        minDate.set(Calendar.MONTH, 0);
        minDate.set(Calendar.YEAR, 2020);

        @SuppressWarnings("deprecation") int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, day, month, year);
        datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    /**
     * Sets set variables in order to create date String.
     *
     * @param day   set day to save.
     * @param month set month to save.
     * @param year  set year to save.
     * @return date String value in MM/DD/YYYY format with MM in the form of its name (e.g. FEB).
     */
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
