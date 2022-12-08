package com.matis8571.countyourbike.App.Database;

import com.matis8571.countyourbike.App.Models.Bikes;

/**
 * Interface used to handle event when user clicks mouse button on onBindViewHolder object.
 */
public interface LeaderboardClickListener {
    void onClick(Bikes bikes);
}
