package com.matis8571.countyourbike.App.Adapters;

import android.content.Context;
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

    Context context;
    List<Bikes> bikesList;
    LeaderboardClickListener listener;

    public LeaderboardListAdapter(Context context, List<Bikes> bikesList, LeaderboardClickListener listener) {
        this.context = context;
        this.bikesList = bikesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeaderboardViewHolder(LayoutInflater.from(context).inflate(
                R.layout.leaderboard_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {

        bikesList.sort(new Comparator<Bikes>() {
            @Override
            public int compare(Bikes bike1, Bikes bike2) {
                return Integer.compare(bike2.getMileage(), bike1.getMileage());
            }
        });
        String place = "";
        if (bikesList.get(position) == bikesList.get(0)) {
            place = "1st:  ";
            holder.leaderboardName.setTextSize(18);
            holder.leaderboardKM.setTextSize(18);

        } else if (bikesList.get(position) == bikesList.get(1)) {
            place = "2nd:  ";
            holder.leaderboardName.setTextSize(17);
            holder.leaderboardKM.setTextSize(17);

        } else if (bikesList.get(position) == bikesList.get(2)) {
            place = "3th:  ";
            holder.leaderboardName.setTextSize(16);
            holder.leaderboardKM.setTextSize(16);

        } else  {
            holder.leaderboardName.setTextSize(15);
            holder.leaderboardKM.setTextSize(15);
        }

        String nameString = place + bikesList.get(position).getName();
        String kmString = "TOTAL: " + bikesList.get(position).getMileage() + "KM";
        holder.leaderboardName.setText(nameString);
        holder.leaderboardKM.setText(kmString);


        holder.leaderboardContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(bikesList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return bikesList.size();
    }
}
