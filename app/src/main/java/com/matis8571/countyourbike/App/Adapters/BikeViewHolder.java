package com.matis8571.countyourbike.App.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.matis8571.countyourbike.R;

class BikeViewHolder extends RecyclerView.ViewHolder {
    TextView textViewName;
    CardView bikesContainer;

    public BikeViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.bike_text_view_name_ID);
        bikesContainer = itemView.findViewById(R.id.bikes_container_ID);
    }
}
