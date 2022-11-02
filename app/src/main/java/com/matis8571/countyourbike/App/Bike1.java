package com.matis8571.countyourbike.App;

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

import com.matis8571.countyourbike.Notepad.MainActivityNotepad;
import com.matis8571.countyourbike.R;

import java.util.Calendar;

@SuppressWarnings("FieldCanBeLocal")
public class Bike1 extends AppCompatActivity {
    private static final String TAG = "Bike1";

    private EditText bike1KmSinceMaintenanceEdit, bike1TotalKmEdit, bike1KmThisMonthEdit;
    private TextView bike1LastMaintenanceText, bike1Text, bike1KmThisMonthText, bike1TotalKmText,
            bike1KmSinceMaintenanceText;
    private Button bike1DatePickerButton, bike1BackButton, bike1SubmitButton,
            bike1ToHomeButton, bike1NotesButton;
    private DatePickerDialog datePickerDialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bike_1_layout);
        Log.d(TAG, "onCreate: Start");
        initDatePicker();


        bike1LastMaintenanceText = findViewById(R.id.bike1LastMaintenanceTextID);
        bike1Text = findViewById(R.id.bike1TextID);
        bike1KmThisMonthText = findViewById(R.id.bike1KmThisMonthTextID);
        bike1TotalKmText = findViewById(R.id.bike1TotalKmTextID);
        bike1KmSinceMaintenanceText = findViewById(R.id.bike1KmSinceMaintenanceTextID);
        bike1DatePickerButton = findViewById(R.id.bike1DatePickerButtonID);
        bike1KmSinceMaintenanceEdit = findViewById(R.id.bike1KmSinceMaintenanceEditID);
        bike1TotalKmEdit = findViewById(R.id.bike1TotalKmEditID);
        bike1KmThisMonthEdit = findViewById(R.id.bike1KmThisMonthEditID);
        bike1BackButton = findViewById(R.id.bike1BackButtonID);
        bike1SubmitButton = findViewById(R.id.bike1SubmitButtonID);
        bike1ToHomeButton = findViewById(R.id.bike1ToHomeButtonID);
        bike1NotesButton = findViewById(R.id.bike1NotesButtonID);

        bike1Text.setText("Bike 1 profile:");
        bike1Text.setTextSize(24);
        bike1LastMaintenanceText.setText("Last maintenance:");
        bike1LastMaintenanceText.setTextSize(16);
        bike1KmThisMonthText.setText("km this month:");
        bike1KmThisMonthText.setTextSize(16);
        bike1TotalKmText.setText("km in total:");
        bike1TotalKmText.setTextSize(16);
        bike1KmSinceMaintenanceText.setText("km since maintenance:");
        bike1KmSinceMaintenanceText.setTextSize(16);
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
        bike1BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: bike1ToMainButton");
                finish();
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

        bike1ToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bike1ToHomeButtonIntent = new Intent(Bike1.this, MainActivity.class);
                startActivity(bike1ToHomeButtonIntent);
            }
        });

        bike1NotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bike1NotesButtonIntent = new Intent(Bike1.this, MainActivityNotepad.class);
                startActivity(bike1NotesButtonIntent);
            }
        });
    }

    private String getCurrentDate() {
        Log.d(TAG, "getCurrentDate");
        SharedPreferences bike1PrefsReceiver = getApplicationContext().getSharedPreferences(
                "bike1Prefs", Context.MODE_PRIVATE);
        Calendar calendar = Calendar.getInstance();

        int day = bike1PrefsReceiver.getInt("day", calendar.get(Calendar.DAY_OF_MONTH));
        int month = bike1PrefsReceiver.getInt("month", (calendar.get(Calendar.MONTH) + 1));
        int year = bike1PrefsReceiver.getInt("year", calendar.get(Calendar.YEAR));

        return makeDateString(day, month, year);
    }

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
