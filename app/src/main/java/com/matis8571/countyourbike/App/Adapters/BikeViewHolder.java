package com.matis8571.countyourbike.App.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.matis8571.countyourbike.R;

class BikeViewHolder extends RecyclerView.ViewHolder {
    TextView textViewName, textViewBikeType, textViewBrand, textViewModel, textViewMileage;
    CardView bikesContainer;

    public BikeViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.bike_text_view_name_ID);
        textViewBikeType = itemView.findViewById(R.id.bike_text_view_bike_type_ID);
        textViewBrand = itemView.findViewById(R.id.bike_text_view_brand_ID);
        textViewModel = itemView.findViewById(R.id.bike_text_view_model_ID);
        textViewMileage = itemView.findViewById(R.id.bike_ext_view_mileage_ID);
        bikesContainer = itemView.findViewById(R.id.bikes_container_ID);
    }
}
