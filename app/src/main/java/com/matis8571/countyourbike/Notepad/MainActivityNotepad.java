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

import com.matis8571.countyourbike.Notepad.Adapters.NotesListAdapter;
import com.matis8571.countyourbike.Notepad.Database.NotepadRoomDB;
import com.matis8571.countyourbike.Notepad.Database.NotesClickListener;
import com.matis8571.countyourbike.Notepad.Models.Notes;
import com.matis8571.countyourbike.Notepad.Models.NotesTakerActivity;
import com.matis8571.countyourbike.R;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Convert2Lambda")
public class MainActivityNotepad extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final String TAG = "MainActivityNotepad";
    List<Notes> notes = new ArrayList<>();
    RecyclerView recyclerHome;
    NotesListAdapter notesListAdapter;
    NotepadRoomDB database;
    SearchView searchViewHome;
    Notes selectedNote;
    Button notesBackButton, noteAddButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_noptepad_layout);

        recyclerHome = findViewById(R.id.recycler_home_ID);
        searchViewHome = findViewById(R.id.search_view_home_ID);
        noteAddButton = findViewById(R.id.note_add_button_ID);
        notesBackButton = findViewById(R.id.notes_back_button_ID);

        database = NotepadRoomDB.getInstance(this);
        notes = database.mainNotepadDAO().getAll();
        updateRecycler(notes);

        noteAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noteAddButtonIntent = new Intent(MainActivityNotepad.this, NotesTakerActivity.class);
                //noinspection deprecation
                startActivityForResult(noteAddButtonIntent, 101);
            }
        });

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

        notesBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

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

    private void updateRecycler(List<Notes> notes) {
        Log.d(TAG, "updateRecycler");
        recyclerHome.setHasFixedSize(true);
        recyclerHome.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
        notesListAdapter = new NotesListAdapter(MainActivityNotepad.this, notes, notesClickListener);
        recyclerHome.setAdapter(notesListAdapter);
    }

    private final NotesClickListener notesClickListener = new NotesClickListener() {
        private static final String TAG = "notesClickListener";
        @Override
        public void onClick(Notes notes) {
            Log.d(TAG, "onClick");
            Intent notesClickListenerIntent = new Intent(MainActivityNotepad.this, NotesTakerActivity.class);
            notesClickListenerIntent.putExtra("oldNote", notes);
            //noinspection deprecation
            startActivityForResult(notesClickListenerIntent, 102);
        }

        @Override
        public void onLongClick(Notes notes, CardView cardView) {
            Log.d(TAG, "onLongClick");
            selectedNote = notes;
            showPopUp(cardView);
        }
    };

    private void showPopUp(CardView cardView) {
        Log.d(TAG, "showPopup");
        PopupMenu popupMenu = new PopupMenu(this, cardView);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_notepad_menu);
        popupMenu.show();
    }

    @SuppressLint({"NonConstantResourceId", "NotifyDataSetChanged"})
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Log.d(TAG, "onMenuItemClick");
        switch (item.getItemId()) {
            case R.id.pin:
                if (selectedNote.isPinned()) {
                    database.mainNotepadDAO().pin(selectedNote.getID(), false);
                    Toast.makeText(this, "Unpinned", Toast.LENGTH_SHORT).show();
                } else {
                    database.mainNotepadDAO().pin(selectedNote.getID(), true);
                    Toast.makeText(this, "Pinned", Toast.LENGTH_SHORT).show();
                }
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
