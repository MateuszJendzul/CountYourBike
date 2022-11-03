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
        notesContainer = itemView.findViewById(R.id.notes_container_ID);
        textViewDate = itemView.findViewById(R.id.text_view_date_ID);
        textViewNotes = itemView.findViewById(R.id.text_view_notes_ID);
        textViewTitle = itemView.findViewById(R.id.text_view_title_ID);
        imageViewPin = itemView.findViewById(R.id.image_view_pin_ID);
    }
}
