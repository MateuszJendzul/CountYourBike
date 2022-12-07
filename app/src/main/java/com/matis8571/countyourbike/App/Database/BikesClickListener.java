package com.matis8571.countyourbike.App.Database;

import androidx.cardview.widget.CardView;

import com.matis8571.countyourbike.App.Models.Bikes;

/**
 * Interface used to handle event when user clicks mouse button on onBindViewHolder object.
 */
public interface BikesClickListener {
    void onClick(Bikes bikes);

    void onLongClick(Bikes bikes, CardView cardView);
}
