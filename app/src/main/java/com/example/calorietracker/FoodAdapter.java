package com.example.calorietracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private Context context;
    private List<FoodItem> foodList;
    private OnFoodItemClickListener listener;

    public interface OnFoodItemClickListener {
        void onFoodItemClick(FoodItem foodItem);
    }

    public FoodAdapter(Context context, List<FoodItem> foodList, OnFoodItemClickListener listener) {
        this.context = context;
        this.foodList = foodList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem foodItem = foodList.get(position);
        holder.foodName.setText(foodItem.getName());
        holder.foodCalories.setText(String.valueOf(foodItem.getCalories()) + " kcal");
        
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onFoodItemClick(foodItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void updateList(List<FoodItem> newList) {
        this.foodList = newList;
        notifyDataSetChanged();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView foodName, foodCalories;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            foodCalories = itemView.findViewById(R.id.foodCalories);
        }
    }
}