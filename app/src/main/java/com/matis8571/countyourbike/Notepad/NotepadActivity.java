package com.matis8571.countyourbike.Notepad;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.matis8571.countyourbike.App.MainActivity;
import com.matis8571.countyourbike.Notepad.Adapters.NotesListAdapter;
import com.matis8571.countyourbike.Notepad.Database.NotepadRoomDB;
import com.matis8571.countyourbike.Notepad.Database.NotesClickListener;
import com.matis8571.countyourbike.Notepad.Models.Notes;
import com.matis8571.countyourbike.Notepad.Models.NotesTakerActivity;
import com.matis8571.countyourbike.R;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Convert2Lambda")
public class NotepadActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final String TAG = "MainActivityNotepad";
    List<Notes> notes = new ArrayList<>();
    RecyclerView recyclerHome;
    NotesListAdapter notesListAdapter;
    NotepadRoomDB database;
    SearchView searchViewHome;
    Notes selectedNote;
    Button notesBackButton, noteAddButton, notepadActivityHomeButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noptepad_activity_layout);

        recyclerHome = findViewById(R.id.recycler_home_ID);

        searchViewHome = findViewById(R.id.search_view_home_ID);

        noteAddButton = findViewById(R.id.note_add_button_ID);
        notesBackButton = findViewById(R.id.notes_back_button_ID);
        notepadActivityHomeButton = findViewById(R.id.notepad_activity_home_button_ID);

        database = NotepadRoomDB.getInstance(this);
        notes = database.mainNotepadDAO().getAll();
        updateRecycler(notes);

        // Creates new intent and uses it to start activity for a result with provided requestCode.
        noteAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noteAddButtonIntent = new Intent(NotepadActivity.this,
                        NotesTakerActivity.class);
                //noinspection deprecation
                startActivityForResult(noteAddButtonIntent, 101);
            }
        });

        // Search panel of notepad from NotepadActivity. Searches notes based on typed words.
        searchViewHome.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        notepadActivityHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notepadActivityHomeButtonIntent = new Intent(NotepadActivity.this,
                        MainActivity.class);
                startActivity(notepadActivityHomeButtonIntent);
            }
        });

        // Closes activity and returns to one from within it was called.
        notesBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Filters notes and searches for ones with matching texts.
     *
     * @param newText texts to search for.
     */
    private void filter(String newText) {
        Log.d(TAG, "filter");
        List<Notes> filteredList = new ArrayList<>();
        for (Notes singleNote : notes) {
            if (singleNote.getTitle().toLowerCase().contains(newText.toLowerCase())
                    || singleNote.getNotes().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(singleNote);
            }
        }
        notesListAdapter.filterList(filteredList);
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode you started it with
     * the resultCode it returned, and any additional data from it.
     * The resultCode will be RESULT_CANCELED if the activity explicitly returned that
     * didn't return any result, or crashed during its operation.
     * Based on received requestCode opens NotepadActivity to create new or import
     * and edit old note object.
     */
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult");
        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                assert data != null;
                Notes newNotes = (Notes) data.getSerializableExtra("note");
                database.mainNotepadDAO().insert(newNotes);
                notes.clear();
                notes.addAll(database.mainNotepadDAO().getAll());
                notesListAdapter.notifyDataSetChanged();
            }
        } else if (requestCode == 102) {
            if (resultCode == Activity.RESULT_OK) {
                assert data != null;
                Notes newNotes = (Notes) data.getSerializableExtra("note");
                database.mainNotepadDAO().update(newNotes.getID(), newNotes.getTitle(), newNotes.getNotes());
                notes.clear();
                notes.addAll(database.mainNotepadDAO().getAll());
                notesListAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * Formats recycler window.
     * Sets layout and how many objects are displayed in one row or column, based on set view
     * (horizontal/vertical). Creates BikesListAdapter instance with context, list, and interface
     * then uses it to setAdapter of RecyclerView. Uses snapHelper to snap to center displayed
     * objects while scrolling.
     *
     * @param notes pass list here.
     */
    private void updateRecycler(List<Notes> notes) {
        Log.d(TAG, "updateRecycler");
        recyclerHome.setHasFixedSize(true);
        recyclerHome.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
        notesListAdapter = new NotesListAdapter(NotepadActivity.this, notes, notesClickListener);
        recyclerHome.setAdapter(notesListAdapter);
    }

    // Creates instance of interface object
    private final NotesClickListener notesClickListener = new NotesClickListener() {
        private static final String TAG = "notesClickListener";

        /* On click sends to onActivityResult requestCode which is responsible for opening
            note object edit activity with tag of "oldNote", so imports old note data and
            allows to edit.
        */
        @Override
        public void onClick(Notes notes) {
            Log.d(TAG, "onClick");
            Intent notesClickListenerIntent = new Intent(NotepadActivity.this, NotesTakerActivity.class);
            notesClickListenerIntent.putExtra("oldNote", notes);
            //noinspection deprecation
            startActivityForResult(notesClickListenerIntent, 102);
        }

        // On long mouse button click, displays menu
        @Override
        public void onLongClick(Notes notes, CardView cardView) {
            Log.d(TAG, "onLongClick");
            selectedNote = notes;
            showPopUp(cardView);
        }
    };

    /**
     * Creates new PopupMenu to display after user calls it in RecyclerView.
     * Menu has option to delete, or pin selected note.
     * @param cardView pass menu cardView here.
     */
    private void showPopUp(CardView cardView) {
        Log.d(TAG, "showPopup");
        PopupMenu popupMenu = new PopupMenu(this, cardView);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_notepad_menu);
        popupMenu.show();
    }

    /**
     * Allows user to select from delete or pin option from menu called on specific note object.
     * Delete removes selected object from list.
     * Pin displays little pin icon in the top right corner of the displayed object adapter view.
     */
    @SuppressLint({"NonConstantResourceId", "NotifyDataSetChanged"})
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Log.d(TAG, "onMenuItemClick");
        switch (item.getItemId()) {
            case R.id.pin:
                database.mainNotepadDAO().pin(selectedNote.getID(), !selectedNote.isPinned());
                notes.clear();
                notes.addAll(database.mainNotepadDAO().getAll());
                notesListAdapter.notifyDataSetChanged();
                return true;

            case (R.id.delete):
                database.mainNotepadDAO().delete(selectedNote);
                notes.remove(selectedNote);
                notesListAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return false;
        }
    }
}
