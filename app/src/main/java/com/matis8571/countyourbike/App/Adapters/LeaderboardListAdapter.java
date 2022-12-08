package com.matis8571.countyourbike.App.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matis8571.countyourbike.App.Database.LeaderboardClickListener;
import com.matis8571.countyourbike.App.Models.Bikes;
import com.matis8571.countyourbike.R;

import java.util.Comparator;
import java.util.List;

@SuppressWarnings("Convert2Lambda")
public class LeaderboardListAdapter extends RecyclerView.Adapter<LeaderboardViewHolder> {
    private static final String TAG = "LeaderboardListAdapter";
    Context context;
    List<Bikes> bikesList;
    LeaderboardClickListener listener;

    public LeaderboardListAdapter(Context context, List<Bikes> bikesList,
                                  LeaderboardClickListener listener) {
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
     * @return A new ViewHolder inflated from leaderboard_adapter_layout that holds
     * a View of Bike object.
     */
    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        return new LeaderboardViewHolder(LayoutInflater.from(context).inflate(
                R.layout.leaderboard_adapter_layout, parent, false));
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
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        bikesList.sort(new Comparator<Bikes>() {
            @Override
            public int compare(Bikes bike1, Bikes bike2) {
                return Integer.compare(bike2.getMileage(), bike1.getMileage());
            }
        });

        String place = "";
        if (bikesList.get(position) == bikesList.get(0)) {
            place = "1 st:  ";
            holder.leaderboardNameText.setTextSize(18);
            holder.leaderboardKmText.setTextSize(18);

        } else if (bikesList.get(position) == bikesList.get(1)) {
            place = "2 nd:  ";
            holder.leaderboardNameText.setTextSize(17);
            holder.leaderboardKmText.setTextSize(17);

        } else if (bikesList.get(position) == bikesList.get(2)) {
            place = "3 th:  ";
            holder.leaderboardNameText.setTextSize(16);
            holder.leaderboardKmText.setTextSize(16);

        } else {
            holder.leaderboardNameText.setTextSize(15);
            holder.leaderboardKmText.setTextSize(15);
        }

        String nameString = place + bikesList.get(position).getName();
        String kmString = "TOTAL: " + bikesList.get(position).getMileage() + "KM";
        holder.leaderboardNameText.setText(nameString);
        holder.leaderboardKmText.setText(kmString);

        holder.leaderboardContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: leaderboardContainer");
                listener.onClick(bikesList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount");
        return bikesList.size();
    }
}
