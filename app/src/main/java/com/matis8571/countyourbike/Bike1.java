package com.matis8571.countyourbike;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

@SuppressWarnings("FieldCanBeLocal")
public class Bike1 extends AppCompatActivity {
    private static final String TAG = "Bike1";

    private EditText bike1KmSinceMaintenanceEdit, bike1TotalKmEdit, bike1KmThisMonthEdit;
    private TextView bike1LastMaintenanceText, bike1Text;
    private Button bike1DatePickerButton, bike1ToProfileSelectButton, bike1SubmitButton;
    private DatePickerDialog datePickerDialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bike_1_layout);
        Log.d(TAG, "onCreate: Start");
        initDatePicker();


        bike1LastMaintenanceText = findViewById(R.id.bike_1_last_maintenance_text_id);
        bike1Text = findViewById(R.id.bike_1_text_id);
        bike1DatePickerButton = findViewById(R.id.bike_1_date_picker_button_id);
        bike1KmSinceMaintenanceEdit = findViewById(R.id.bike_1_km_since_maintenance_edit_id);
        bike1TotalKmEdit = findViewById(R.id.bike_1_total_km_edit_id);
        bike1KmThisMonthEdit = findViewById(R.id.bike_1_km_this_month_edit_id);
        bike1ToProfileSelectButton = findViewById(R.id.bike1_to_profile_select_button_id);
        bike1SubmitButton = findViewById(R.id.bike1_submit_button_id);

        bike1Text.setText("Bike 1 profile:");
        bike1Text.setTextSize(24);
        bike1LastMaintenanceText.setText("Last Maintenance:");
        bike1LastMaintenanceText.setTextSize(18);
        bike1DatePickerButton.setText(getCurrentDate());

        //Button to open method which allows to pick a date
        bike1DatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bike1DatePickerButton");
                datePickerDialog.show();
            }
        });

        //Button to go back to MainActivity from this one
        bike1ToProfileSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bike1ToMainButton");
                Intent bike1ToMainButtonIntent = new Intent(Bike1.this, BikeProfileSelect.class);
                startActivity(bike1ToMainButtonIntent);
            }
        });

        bike1SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bike1KmSinceMaintenanceEdit.getText().toString().isEmpty() ||
                        bike1KmThisMonthEdit.getText().toString().isEmpty() ||
                        bike1TotalKmEdit.getText().toString().isEmpty()) {
                    Toast.makeText(Bike1.this, "Empty fields", Toast.LENGTH_SHORT).show();
                } else {
                    int bike1KmSinceMaintenance = Integer.parseInt(bike1KmSinceMaintenanceEdit.getText().toString());
                    int bike1KmThisMonth = Integer.parseInt(bike1KmThisMonthEdit.getText().toString());
                    int bike1TotalKm = Integer.parseInt(bike1TotalKmEdit.getText().toString());

                    SharedPreferences bike1Prefs = getSharedPreferences("bike1Prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor bike1PrefsEditor = bike1Prefs.edit();
                    bike1PrefsEditor.putInt("bike1KmSinceMaintenance", bike1KmSinceMaintenance).apply();
                    bike1PrefsEditor.putInt("bike1KmThisMonth", bike1KmThisMonth).apply();
                    bike1PrefsEditor.putInt("bike1TotalKm", bike1TotalKm).apply();

                    Toast.makeText(Bike1.this, "Bike updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getCurrentDate() {
        Log.d(TAG, "getCurrentDate");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int year = calendar.get(Calendar.YEAR);

        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        Log.d(TAG, "initDatePicker");
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                bike1DatePickerButton.setText(date);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, day, month, year);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
        Log.d(TAG, "makeDateString");
        return getMonthFormat(month) + " " + day + " " + year;
    }

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
            return "ERROR";
        }
    }
}
