package com.matis8571.countyourbike.App.Adapters;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.matis8571.countyourbike.R;

class BikeViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "BikeViewHolder";
    TextView bikeListNameText, bikeListDateText;
    CardView bikesContainer;
    ImageView bikesListImage;

    public BikeViewHolder(@NonNull View itemView) {
        super(itemView);
        Log.d(TAG, "Constructor");
        bikeListNameText = itemView.findViewById(R.id.bike_list_name_text_ID);
        bikeListDateText = itemView.findViewById(R.id.bike_list_date_text_ID);
        bikesContainer = itemView.findViewById(R.id.bikes_list_adapter_layout_ID);
        bikesListImage = itemView.findViewById(R.id.bikes_list_image_ID);
    }
}
