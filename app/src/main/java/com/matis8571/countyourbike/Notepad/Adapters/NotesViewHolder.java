package com.matis8571.countyourbike.Notepad.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.matis8571.countyourbike.R;

class NotesViewHolder extends RecyclerView.ViewHolder {

    CardView notesContainer;
    TextView textViewTitle, textViewNotes, textViewDate;
    ImageView imageViewPin;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        notesContainer = itemView.findViewById(R.id.notes_container);
        textViewDate = itemView.findViewById(R.id.textViewDateID);
        textViewNotes = itemView.findViewById(R.id.textViewNotesID);
        textViewTitle = itemView.findViewById(R.id.textViewTitleID);
        imageViewPin = itemView.findViewById(R.id.imageViewPinID);
    }
}
