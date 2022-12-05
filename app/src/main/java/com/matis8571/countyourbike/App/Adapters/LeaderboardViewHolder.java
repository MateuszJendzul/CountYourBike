package com.matis8571.countyourbike.App.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.matis8571.countyourbike.R;

public class LeaderboardViewHolder extends RecyclerView.ViewHolder {
    TextView leaderboardName, leaderboardKM;
    CardView leaderboardContainer;

    public LeaderboardViewHolder(@NonNull View itemView) {
        super(itemView);
        leaderboardName = itemView.findViewById(R.id.leaderboard_name_ID);
        leaderboardKM = itemView.findViewById(R.id.leaderboard_km_ID);
        leaderboardContainer = itemView.findViewById(R.id.leaderboard_adapter_layout_ID);
    }
}
