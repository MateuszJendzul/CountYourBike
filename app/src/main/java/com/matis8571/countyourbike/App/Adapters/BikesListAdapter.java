package com.matis8571.countyourbike.App.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matis8571.countyourbike.App.Database.BikesClickListener;
import com.matis8571.countyourbike.App.Models.Bikes;
import com.matis8571.countyourbike.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("Convert2Lambda")
public class BikesListAdapter extends RecyclerView.Adapter<BikeViewHolder> {
    private static final String TAG = "BikesListAdapter";
    Context context;
    List<Bikes> bikesList;
    BikesClickListener listener;

    public BikesListAdapter(Context context, List<Bikes> bikesList, BikesClickListener listener) {
        Log.d(TAG, "Constructor");
        this.context = context;
        this.bikesList = bikesList;
        this.listener = listener;
    }

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent
     * an item.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder inflated from bikes_list_adapter_layout that holds
     * a View of Bike object.
     */
    @NonNull
    @Override
    public BikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        return new BikeViewHolder(LayoutInflater.from(context).inflate(R.layout.bikes_list_adapter_layout,
                parent, false));
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * Uses compare method to sort (descending order) Bike objects of bikesList based on their
     * current mileage value.
     * Objects displayed at first 3 places are given bonus numeric values to display it.
     * Updates texts of every displayed object.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *               item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull BikeViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        holder.bikeListNameText.setText(bikesList.get(position).getName());
        holder.bikesListImage.setImageResource(setImage(bikesList.get(position).getBikeImageID()));
        holder.bikeListDateText.setText(bikesList.get(position).getDateAndTime());

        int color = getColor();
        holder.bikesContainer.setCardBackgroundColor(holder.itemView.getResources()
                .getColor(color, null));

        holder.bikesContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(bikesList.get(holder.getAdapterPosition()));
            }
        });

        holder.bikesContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(bikesList.get(holder.getAdapterPosition()), holder.bikesContainer);
                return true;
            }
        });
    }

    /**
     * Creates new list of colors and uses it to return random color.
     * @return returns random color form list.
     */
    private int getColor() {
        Log.d(TAG, "getColor");
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.color3);
        colorCode.add(R.color.gray);
        colorCode.add(R.color.button_color);
        colorCode.add(R.color.color5);
        Random random = new Random();
        int randomColor = random.nextInt(colorCode.size());
        return colorCode.get(randomColor);
    }

    /**
     * Gets integer value and returns image reference assigned to specific number.
     * Created to work on values, which are assigned when user selects specific bike image
     * when creating or editing bike object.
     * @param boardPosition pass value to get image reference.
     * @return returns reference to image.
     */
    private int setImage(int boardPosition) {
        Log.d(TAG, "setImage");
        if (boardPosition == 0) {
            //Mountain
            return (R.drawable.bike_4);

        } else if (boardPosition == 1) {
            //Road
            return (R.drawable.bike_6);

        } else if (boardPosition == 2) {
            //Gravel
            return (R.drawable.bike_3);

        } else if (boardPosition == 3) {
            //Electric
            return (R.drawable.bike_2);

        } else if (boardPosition == 4) {
            //City
            return (R.drawable.bike_5);

        } else {
            //Should never happen, because of limitations set while changing board position
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount");
        return bikesList.size();
    }
}