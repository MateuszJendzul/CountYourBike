package com.matis8571.countyourbike.App.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matis8571.countyourbike.App.Database.BikesClickListener;
import com.matis8571.countyourbike.App.Models.Bikes;
import com.matis8571.countyourbike.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("Convert2Lambda")
public class BikesListAdapter extends RecyclerView.Adapter<BikeViewHolder> {
    Context context;
    List<Bikes> list;
    BikesClickListener listener;

    public BikesListAdapter(Context context, List<Bikes> list, BikesClickListener listener){
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BikeViewHolder(LayoutInflater.from(context).inflate(R.layout.bikes_list_adapter_layout,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BikeViewHolder holder, int position) {
        holder.textViewName.setText(list.get(position).getName());
        holder.textViewName.setSelected(true);

        holder.textViewBikeType.setText(list.get(position).getBikeType());
        holder.textViewBikeType.setSelected(true);

        holder.textViewBrand.setText(list.get(position).getBrand());
        holder.textViewBrand.setSelected(true);

        holder.textViewModel.setText(list.get(position).getModel());
        holder.textViewModel.setSelected(true);

        holder.textViewMileage.setText(list.get(position).getMileage());
        holder.textViewMileage.setSelected(true);

        int colorCode = getRandomColor();
        holder.bikesContainer.setCardBackgroundColor(holder.itemView.getResources()
                .getColor(colorCode, null));

        holder.bikesContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.bikesContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(list.get(holder.getAdapterPosition()), holder.bikesContainer);
                return true;
            }
        });
    }

    private int getRandomColor() {
        List<Integer> colorCode = new ArrayList<>();

        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        colorCode.add(R.color.color5);

        Random random = new Random();
        int randomColor = random.nextInt(colorCode.size());
        return colorCode.get(randomColor);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(List<Bikes> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }
}