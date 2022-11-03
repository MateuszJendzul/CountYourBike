package com.matis8571.countyourbike.Notepad.Models;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.matis8571.countyourbike.Notepad.MainActivityNotepad;
import com.matis8571.countyourbike.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {
    EditText editTextTitle, editTextNotes;
    ImageView imageViewSave;
    Notes notes;
    Button notesTakerBackButton;
    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_taker_activity_layout);

        imageViewSave = findViewById(R.id.image_view_save_ID);
        editTextTitle = findViewById(R.id.edit_text_title_ID);
        editTextNotes = findViewById(R.id.edit_text_notes_ID);
        notesTakerBackButton = findViewById(R.id.notes_taker_back_button_ID);

        notes = new Notes();
        try {
            notes = (Notes) getIntent().getSerializableExtra("oldNote");
            editTextTitle.setText(notes.getTitle());
            editTextNotes.setText(notes.getNotes());
            isOldNote = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        imageViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String description = editTextNotes.getText().toString();

                if (description.isEmpty()) {
                    Toast.makeText(NotesTakerActivity.this, "Empty notes", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Week day, date, month, year, hour:minute, AM or PM
                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter
                        = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
                Date date = new Date();

                if (!isOldNote) {
                    notes = new Notes();
                }
                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDateAndTime(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("note", notes);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        notesTakerBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}