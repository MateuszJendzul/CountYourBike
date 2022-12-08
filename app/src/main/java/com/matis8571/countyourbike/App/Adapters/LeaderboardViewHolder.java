package com.matis8571.countyourbike.App.Adapters;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.matis8571.countyourbike.R;

public class LeaderboardViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "LeaderboardViewHolder";
    TextView leaderboardNameText, leaderboardKmText;
    CardView leaderboardContainer;

    public LeaderboardViewHolder(@NonNull View itemView) {
        super(itemView);
        Log.d(TAG, "Constructor");
        leaderboardNameText = itemView.findViewById(R.id.leaderboard_name_text_ID);
        leaderboardKmText = itemView.findViewById(R.id.leaderboard_km_text_ID);
        leaderboardContainer = itemView.findViewById(R.id.leaderboard_adapter_layout_ID);
    }
}
