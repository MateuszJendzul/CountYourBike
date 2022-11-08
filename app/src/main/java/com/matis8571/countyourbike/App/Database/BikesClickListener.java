package com.matis8571.countyourbike.App.Database;

import androidx.cardview.widget.CardView;

import com.matis8571.countyourbike.App.Models.Bikes;

public interface BikesClickListener {
    void onClick(Bikes bikes);

    void onLongClick(Bikes bikes, CardView cardView);
}
