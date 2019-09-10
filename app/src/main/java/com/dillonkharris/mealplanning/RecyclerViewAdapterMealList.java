package com.dillonkharris.mealplanning;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import model.Client;
import model.Meal;

public class RecyclerViewAdapterMealList extends RecyclerView.Adapter<RecyclerViewAdapterMealList.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapterMainScreen";

    private ArrayList<String> meals = new ArrayList<>();

    private Context context;
    private LayoutInflater mInflater;

    public RecyclerViewAdapterMealList(ArrayList<String> meals, Context context) {

        this.meals = meals;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewAdapterMealList.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = mInflater.inflate(R.layout.meal_list_item,viewGroup,false);
        RecyclerViewAdapterMealList.ViewHolder viewHolder = new RecyclerViewAdapterMealList.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapterMealList.ViewHolder viewHolder, final int i) {
        viewHolder.meal.setText(meals.get(i));

        viewHolder.meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Pair<Meal,Integer> pair = new Pair<>(Client.getInstance().getClientSavedMeals().get(i),i);
//                Client.getInstance().setSelectedMeal(pair);
//                Client.getInstance().setCurrentMealSelected(Client.getInstance().getMealByName(meals.get(i)));

                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                MealViewFragment fragment = new MealViewFragment();
                FragmentTransaction transaction = activity.getSupportFragmentManager().
                        beginTransaction();
                transaction.replace(R.id.fragment_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView meal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            meal = itemView.findViewById(R.id.mealListItem);
        }
    }
}
