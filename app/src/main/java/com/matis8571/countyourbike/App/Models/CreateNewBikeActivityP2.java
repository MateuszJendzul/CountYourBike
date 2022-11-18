package com.matis8571.countyourbike.App.Models;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.matis8571.countyourbike.Notepad.MainActivityNotepad;
import com.matis8571.countyourbike.R;

import java.util.Calendar;

@SuppressWarnings("Convert2Lambda")
public class CreateNewBikeActivityP2 extends AppCompatActivity {
    private static final String TAG = "CreateNewBikeActivityP2";
    DatePickerDialog datePickerDialog;
    TextView kmTodayText, kmThisWeekText, kmThisMonthText, kmThisYearText;
    EditText kmTodayEdit, kmThisWeekEdit, kmThisMonthEdit, kmThisYearEdit;
    Button createNewBike2NotesButton, createNewBike2BackButton, createNewBike2AddButton,
            datePickerButton;
    Bikes bikesP2;
    boolean isOldBike = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_bike_activity_p2_layout);
        Log.d(TAG, "onCreate");
        initDatePicker();

        kmTodayText = findViewById(R.id.km_today_text_ID);
        kmThisWeekText = findViewById(R.id.km_this_week_text_ID);
        kmThisMonthText = findViewById(R.id.km_this_month_text_ID);
        kmThisYearText = findViewById(R.id.km_this_year_text_ID);

        kmTodayEdit = findViewById(R.id.km_today_edit_ID);
        kmThisWeekEdit = findViewById(R.id.km_this_week_edit_ID);
        kmThisMonthEdit = findViewById(R.id.km_this_month_edit_ID);
        kmThisYearEdit = findViewById(R.id.km_this_year_edit_ID);

        createNewBike2NotesButton = findViewById(R.id.create_new_bike_2_notes_button_ID);
        createNewBike2BackButton = findViewById(R.id.create_new_bike_2_back_button_ID);
        createNewBike2AddButton = findViewById(R.id.create_new_bike_2_add_button_ID);
        datePickerButton = findViewById(R.id.date_picker_button_ID);

        bikesP2 = new Bikes();
        try {
            bikesP2 = (Bikes) getIntent().getSerializableExtra("oldBike");

            kmTodayEdit.setText(bikesP2.getKmToday());
            kmThisWeekEdit.setText(bikesP2.getKmThisWeek());
            kmThisMonthEdit.setText(bikesP2.getKmThisMonth());
            kmThisYearEdit.setText(bikesP2.getKmThisYear());
            isOldBike = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        createNewBike2AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick");
                if (kmTodayEdit.getText().toString().isEmpty() || kmThisWeekEdit.getText().toString().isEmpty()
                        || kmThisMonthEdit.getText().toString().isEmpty()
                        || kmThisYearEdit.getText().toString().isEmpty()) {
                    Toast.makeText(CreateNewBikeActivityP2.this, "Empty fields", Toast.LENGTH_SHORT).show();

                } else {
                    int kmToday = Integer.parseInt(kmTodayEdit.getText().toString());
                    int kmThisWeek = Integer.parseInt(kmThisWeekEdit.getText().toString());
                    int kmThisMonth = Integer.parseInt(kmThisMonthEdit.getText().toString());
                    int kmThisYear = Integer.parseInt(kmThisYearEdit.getText().toString());

                    if (!isOldBike) {
                        bikesP2 = new Bikes();
                    }
                    bikesP2.setKmToday(kmToday);
                    bikesP2.setKmThisWeek(kmThisWeek);
                    bikesP2.setKmThisMonth(kmThisMonth);
                    bikesP2.setKmThisYear(kmThisYear);

                    Intent createNewBikeP2ExtraIntent = new Intent();
                    // To put extra make Bikes.class implement Serializable
                    createNewBikeP2ExtraIntent.putExtra("bike", bikesP2);
                    setResult(Activity.RESULT_OK, createNewBikeP2ExtraIntent);

                    Toast.makeText(CreateNewBikeActivityP2.this, "Updated", Toast.LENGTH_SHORT).show();
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

        createNewBike2NotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick");
                Intent createNewBike2NotesButtonIntent = new Intent(
                        CreateNewBikeActivityP2.this, MainActivityNotepad.class);
                startActivity(createNewBike2NotesButtonIntent);
            }
        });

        createNewBike2BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick");
                finish();
            }
        });
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
                bikesP2.setDay(day);
                bikesP2.setMonth(month);
                bikesP2.setYear(year);
                datePickerButton.setText(makeDateString(bikesP2.getDay(), bikesP2.getMonth(), bikesP2.getYear()));
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
