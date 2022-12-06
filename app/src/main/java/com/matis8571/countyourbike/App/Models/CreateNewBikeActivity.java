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

import com.matis8571.countyourbike.App.BikeProfileSelect;
import com.matis8571.countyourbike.App.MainActivity;
import com.matis8571.countyourbike.Notepad.MainActivityNotepad;
import com.matis8571.countyourbike.R;

import java.util.Calendar;

@SuppressWarnings("Convert2Lambda")
@SuppressLint("SetTextI18n")
public class CreateNewBikeActivity extends AppCompatActivity {
    private static final String TAG = "CreateNewBikeActivity";
    DatePickerDialog datePickerDialog;
    Button createNewBikeBackButton, createNewBikeAddButton, createNewBikeNotesButton,
            datePickerButton, removeKmButton, addKmButton, bikeTypeLeftArrowButton,
            bikeTypeRightArrowButton, createNewBikeHomeButton;
    TextView nameText, bikeTypeText, brandText, modelText, mileageText, kmTodayTitleText, kmThisWeekTitleText,
            kmThisMonthTitleText, kmThisYearTitleText, kmTodayText, kmThisWeekText, kmThisMonthText,
            kmThisYearText, kmToCountText, bikeTypeSelectorText;
    EditText nameEdit, brandEdit, modelEdit, mileageEdit, kmToCountEdit;
    Bikes bikes;
    private final String[] BIKE_IMAGE_BARD_NAMES = {"Mountain", "Road", "Gravel", "Electric", "City"};
    private int toAdd, toRemove;
    private boolean isOldBike = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_bike_activity_layout);
        Log.d(TAG, "onCreate");

        createNewBikeAddButton = findViewById(R.id.create_new_bike_add_button_ID);
        createNewBikeBackButton = findViewById(R.id.create_new_bike_back_button_ID);
        createNewBikeNotesButton = findViewById(R.id.create_new_bike_notes_button_ID);
        datePickerButton = findViewById(R.id.date_picker_button_ID);
        addKmButton = findViewById(R.id.add_km_button_ID);
        removeKmButton = findViewById(R.id.remove_km_button_ID);
        bikeTypeLeftArrowButton = findViewById(R.id.bike_type_left_arrow_button_ID);
        bikeTypeRightArrowButton = findViewById(R.id.bike_type_right_arrow_button_ID);
        createNewBikeHomeButton = findViewById(R.id.create_new_bike_home_button_ID);

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
        bikeTypeSelectorText = findViewById(R.id.bike_type_selector_text_ID);

        nameEdit = findViewById(R.id.name_edit_ID);
        brandEdit = findViewById(R.id.brand_edit_ID);
        modelEdit = findViewById(R.id.model_edit_ID);
        mileageEdit = findViewById(R.id.mileage_edit_ID);
        kmToCountEdit = findViewById(R.id.km_to_count_edit_ID);

        bikes = new Bikes();
        // If object is already created, gets serialized variables values from Bike.class via intent and
        // displays them on top of default ones.
        Intent ifIntentHasExtra = getIntent();
        if (ifIntentHasExtra.hasExtra("oldBike")) {
            try {
                bikes = (Bikes) getIntent().getSerializableExtra("oldBike");
                nameEdit.setText(bikes.getName());
                bikeTypeSelectorText.setText(BIKE_IMAGE_BARD_NAMES[bikes.getBikeImageBoardPosition()]);
                brandEdit.setText(bikes.getBrand());
                modelEdit.setText(bikes.getModel());
                mileageEdit.setText("" + bikes.getMileage());
                kmTodayText.setText("" + bikes.getKmToday());
                kmThisWeekText.setText("" + bikes.getKmThisWeek());
                kmThisMonthText.setText("" + bikes.getKmThisMonth());
                kmThisYearText.setText("" + bikes.getKmThisYear());
                datePickerButton.setText(makeDateString(bikes.getDay(), bikes.getMonth(), bikes.getYear()));
                bikes.setBikeCreated(true);
                isOldBike = true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            if (!isOldBike) {
                bikes = new Bikes();
            }
            // Makes new bike object if none is already created, initiates all required variables
            // and sets condition to true to prevent further usage of this code.
            if (!bikes.isBikeCreated()) {
                bikes.setName(bikes.getName());
                bikes.setBrand(bikes.getBrand());
                bikes.setModel(bikes.getModel());
                bikes.setMileage(bikes.getMileage());
                bikes.setBikeImageID(bikes.getBikeImageID());
                bikes.setDay(bikes.getDay());
                bikes.setMonth(bikes.getMonth());
                bikes.setYear(bikes.getYear());
                bikes.setDayToCompare(getCurrentDay());
                bikes.setWeekToCompare(getCurrentWeek());
                bikes.setMonthToCompare(getCurrentMonth());
                bikes.setYearToCompare(getCurrentYear());
                bikeTypeSelectorText.setText(BIKE_IMAGE_BARD_NAMES[0]);
                datePickerButton.setText(makeDateString(getCurrentDay(), getCurrentMonth(), getCurrentYear()));
                bikes.setDay(getCurrentDay());
                bikes.setMonth(getCurrentMonth());
                bikes.setYear(getCurrentYear());
                bikes.setBikeCreated(true);
            }

            Intent createNewBikeExtraIntent = new Intent();
            // To put extra make Bikes.class implement Serializable
            createNewBikeExtraIntent.putExtra("bike", bikes);
            setResult(Activity.RESULT_OK, createNewBikeExtraIntent);
        }

        // Methods are added here, because object needs to be created first.
        initDatePicker();
        resetVariables();

        // Updates bike object variables values based on user input.
        createNewBikeAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: createNewBikeAddButton");

                int mileage = Integer.parseInt(mileageEdit.getText().toString());
                String name = nameEdit.getText().toString();
                String brand = brandEdit.getText().toString();
                String model = modelEdit.getText().toString();

                if (!mileageEdit.getText().toString().isEmpty()) {
                    bikes.setMileage(mileage);
                }
                if (!nameEdit.getText().toString().isEmpty()) {
                    bikes.setName(name);
                }
                if (!brandEdit.getText().toString().isEmpty()) {
                    bikes.setBrand(brand);
                }
                if (!modelEdit.getText().toString().isEmpty()) {
                    bikes.setModel(model);
                }
                bikes.setBikeImageID(bikes.getBikeImageBoardPosition());

                Intent createNewBikeExtraIntent = new Intent();
                createNewBikeExtraIntent.putExtra("bike", bikes);
                setResult(Activity.RESULT_OK, createNewBikeExtraIntent);
                Toast.makeText(CreateNewBikeActivity.this, "Updated",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // If bike object is created and kmToCountEdit EditText field is filled, saves input number
        // as value to add to kilometers traveled.
        addKmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: addKmButton");
                if (kmToCountEdit.getText().toString().isEmpty()) {
                    Toast.makeText(CreateNewBikeActivity.this, "Empty field",
                            Toast.LENGTH_SHORT).show();
                } else {
                    toAdd = Integer.parseInt(kmToCountEdit.getText().toString());
                    countKm();
                }
            }
        });

        // Same as addKmButton, but saves value to remove instead of to add.
        removeKmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: removeKmButton");
                if (kmToCountEdit.getText().toString().isEmpty()) {
                    Toast.makeText(CreateNewBikeActivity.this, "Empty field",
                            Toast.LENGTH_SHORT).show();
                } else {
                    toRemove = Integer.parseInt(kmToCountEdit.getText().toString());
                    countKm();
                }
            }
        });

        // If bike object is created, shows DatePickerDialog window.
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: datePickerButton");
                datePickerDialog.show();
            }
        });

        // Ends current activity.
        createNewBikeBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: createNewBikeBackButton");
                Intent createNewBikeBackButtonIntent = new Intent(CreateNewBikeActivity.this,
                        BikeProfileSelect.class);
                startActivity(createNewBikeBackButtonIntent);
            }
        });

        // Opens Notes activity
        createNewBikeNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: createNewBikeNotesButton");
                Intent createNewBikeNotesButtonIntent = new Intent(
                        CreateNewBikeActivity.this, MainActivityNotepad.class);
                startActivity(createNewBikeNotesButtonIntent);
            }
        });

        // Directs user to home activity
        createNewBikeHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createNewBikeHomeButtonIntent = new Intent(CreateNewBikeActivity.this,
                        MainActivity.class);
                startActivity(createNewBikeHomeButtonIntent);
            }
        });

        // Changes bike image to view, by adding one to current position.
        // Skips to first when trying to go past last image in board.
        bikeTypeRightArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bikes.setBikeImageBoardPosition(bikes.getBikeImageBoardPosition() + 1);
                if (bikes.getBikeImageBoardPosition() > BIKE_IMAGE_BARD_NAMES.length - 1) {
                    bikes.setBikeImageBoardPosition(0);
                }

                bikeTypeSelectorText.setText(BIKE_IMAGE_BARD_NAMES[bikes.getBikeImageBoardPosition()]);
                bikes.setBikeImageID(bikes.getBikeImageBoardPosition());
            }
        });

        // Changes bike image to view, by removing one from current position.
        // Skips to last when trying to go past first image in board.
        bikeTypeLeftArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bikes.setBikeImageBoardPosition(bikes.getBikeImageBoardPosition() - 1);
                if (bikes.getBikeImageBoardPosition() < 0) {
                    bikes.setBikeImageBoardPosition(BIKE_IMAGE_BARD_NAMES.length - 1);
                }

                bikeTypeSelectorText.setText(BIKE_IMAGE_BARD_NAMES[bikes.getBikeImageBoardPosition()]);
                bikes.setBikeImageID(bikes.getBikeImageBoardPosition());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
        Intent createNewBikeBackButtonIntent = new Intent(CreateNewBikeActivity.this,
                BikeProfileSelect.class);
        startActivity(createNewBikeBackButtonIntent);
    }

    /**
     * Dedicated to reset values of variables responsible for counting values of driven kilometers.
     * Resets values of individual variables based on how much time passed between.
     * e.g. if saved day is not equal to current day, reset kilometers driven today and set saved
     * day as current one.
     */
    private void resetVariables() {
        Log.d(TAG, "resetVariables");
        if (getCurrentDay() != bikes.getDayToCompare()) {
            bikes.setKmToday(0);
            bikes.setDayToCompare(getCurrentDay());
        }

        if (getCurrentWeek() != bikes.getWeekToCompare()) {
            bikes.setKmThisWeek(0);
            bikes.setWeekToCompare(getCurrentWeek());
        }

        if (getCurrentMonth() != bikes.getMonthToCompare()) {
            bikes.setKmThisMonth(0);
            bikes.setMonthToCompare(getCurrentMonth());
        }

        if (getCurrentYear() != bikes.getYearToCompare()) {
            bikes.setKmThisYear(0);
            bikes.setYearToCompare(getCurrentYear());
        }
    }

    /**
     * Counts traveled kilometers and updates variables values of bike object.
     * Sets texts of TextViews which are meant to display those values in bike profile card.
     * Prevents user from reaching negative numbers in parameters.
     * Resets values of variables carried from buttons, which are adding or removing value of driven
     * kilometers.
     */
    private void countKm() {
        Log.d(TAG, "countKm");
        if (bikes.getKmToday() + toAdd - toRemove >= 0) {
            bikes.setKmToday(bikes.getKmToday() + toAdd - toRemove);
            bikes.setKmThisWeek(bikes.getKmThisWeek() + toAdd - toRemove);
            bikes.setKmThisMonth(bikes.getKmThisMonth() + toAdd - toRemove);
            bikes.setKmThisYear(bikes.getKmThisYear() + toAdd - toRemove);
            bikes.setMileage(bikes.getMileage() + toAdd - toRemove);
        } else {
            Toast.makeText(this, "Do not count below 0", Toast.LENGTH_SHORT).show();
        }

        kmTodayText.setText("" + bikes.getKmToday());
        kmThisWeekText.setText("" + bikes.getKmThisWeek());
        kmThisMonthText.setText("" + bikes.getKmThisMonth());
        kmThisYearText.setText("" + bikes.getKmThisYear());
        mileageEdit.setText("" + bikes.getMileage());
        toAdd = 0;
        toRemove = 0;
    }

    /**
     * Calls for a method which allows to pick a date, which is going to be overridden on top of
     * previously displayed one.
     * After calling method, shows window with current date, chosen style,
     * as well as minimum and maximum values of displayed date.
     */
    private void initDatePicker() {
        Log.d(TAG, "initDatePicker");
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            /**
             * Opens window which allows user to select, specified date, to override shown one.
             * Saves values which user chooses from shown datePicker window. Then saves them
             * as variables of bike object to use them to override previously shown date.
             * @param datePicker name of DatePicker.
             * @param year year to save.
             * @param month month to save.
             * @param day day to save.
             */
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                bikes.setDay(day);
                bikes.setMonth(month);
                bikes.setYear(year);
                datePickerButton.setText(makeDateString(day, month, year));
            }
        };

        Calendar minDate = Calendar.getInstance();
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        minDate.set(Calendar.MONTH, 0);
        minDate.set(Calendar.YEAR, 2000);

        //Window theme
        @SuppressWarnings("deprecation") int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, getCurrentYear(),
                getCurrentMonth(), getCurrentDay());
        datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    /**
     * Creates date format String with text representative of month value.
     * e.g. instead of displaying 01 01 2020, will display JAN 01 2020.
     */
    private String makeDateString(int day, int month, int year) {
        Log.d(TAG, "makeDateString");
        if (day < 10) {
            return getMonthFormat(month) + " 0" + day + " " + year;
        } else {
            return getMonthFormat(month) + " " + day + " " + year;
        }
    }

    /**
     * Standalone method which returns current day.
     *
     * @return returns current day as integer value.
     */
    private int getCurrentDay() {
        Calendar currentDateCalendar = Calendar.getInstance();
        return currentDateCalendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Standalone method which returns current week of the month.
     *
     * @return returns current week of the month as integer value.
     */
    private int getCurrentWeek() {
        Calendar currentDateCalendar = Calendar.getInstance();
        return currentDateCalendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * Standalone method which returns current month.
     * Remember that months counting starts from 0 instead of 1.
     *
     * @return returns current month as integer value.
     */
    private int getCurrentMonth() {
        Calendar currentDateCalendar = Calendar.getInstance();
        return currentDateCalendar.get(Calendar.MONTH);
    }

    /**
     * Standalone method which returns current year.
     *
     * @return returns current year as integer value.
     */
    private int getCurrentYear() {
        Calendar currentDateCalendar = Calendar.getInstance();
        return currentDateCalendar.get(Calendar.YEAR);
    }

    /**
     * Matches numeric month value with its text name, which will be returned.
     *
     * @param month gets month in its numeric (int) format.
     * @return displays ERROR (probably when number is out of range).
     */
    private String getMonthFormat(int month) {
        Log.d(TAG, "getMonthFormat");
        if (month == 0) {
            return "JAN";
        } else if (month == 1) {
            return "FEB";
        } else if (month == 2) {
            return "MAR";
        } else if (month == 3) {
            return "APR";
        } else if (month == 4) {
            return "MAY";
        } else if (month == 5) {
            return "JUN";
        } else if (month == 6) {
            return "JUL";
        } else if (month == 7) {
            return "AUG";
        } else if (month == 8) {
            return "SEP";
        } else if (month == 9) {
            return "OCT";
        } else if (month == 10) {
            return "NOV";
        } else if (month == 11) {
            return "DEC";
        } else {
            return "ERROR: Out of range?";
        }
    }
}