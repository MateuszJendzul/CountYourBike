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

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent
     * an item.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder inflated from notes_list_adapter_layout that holds
     * a View of Note object.
     */
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list_adapter_layout,
                parent, false));
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * Checks is object is set as pinned, displays pin image if it is.
     * Sets objects background colors to be displayed randomly (from the provided color list).
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *               item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.textViewTitle.setText(list.get(position).getTitle());
        holder.textViewNotes.setText(list.get(position).getNotes());
        holder.textViewDate.setText(list.get(position).getDateAndTime());

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

    /**
     * Creates new list of colors and uses Random to pick and return random one from list.
     * @return returns color.
     */
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