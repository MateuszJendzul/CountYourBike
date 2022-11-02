package com.matis8571.countyourbike.Notepad.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matis8571.countyourbike.Notepad.Models.Notes;
import com.matis8571.countyourbike.Notepad.Database.NotesClickListener;
import com.matis8571.countyourbike.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("Convert2Lambda")
public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder> {
    Context context;
    List<Notes> list;
    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.textViewTitle.setText(list.get(position).getTitle());
        holder.textViewTitle.setSelected(true);

        holder.textViewNotes.setText(list.get(position).getNotes());

        holder.textViewDate.setText(list.get(position).getDateAndTime());
        holder.textViewDate.setSelected(true);

        if (list.get(position).isPinned()) {
            holder.imageViewPin.setImageResource(R.drawable.ic_pin);
        } else {
            holder.imageViewPin.setImageResource(0);
        }

        int colorCode = getRandomColor();
        holder.notesContainer.setCardBackgroundColor(holder.itemView.getResources()
                .getColor(colorCode, null));

        holder.notesContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.notesContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(list.get(holder.getAdapterPosition()), holder.notesContainer);
                return true;
            }
        });
    }

    private int getRandomColor() {
        List<Integer> colorCode = new ArrayList<>();

        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        colorCode.add(R.color.color5);

        Random random = new Random();
        int randomColor = random.nextInt(colorCode.size());
        return colorCode.get(randomColor);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(List<Notes> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }
}

