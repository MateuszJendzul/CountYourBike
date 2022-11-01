package com.matis8571.countyourbike.Notepad;

import androidx.cardview.widget.CardView;

import com.matis8571.countyourbike.Notepad.Models.Notes;

public interface NotesClickListener {
    void onClick(Notes notes);

    void onLongClick(Notes notes, CardView cardView);
}
