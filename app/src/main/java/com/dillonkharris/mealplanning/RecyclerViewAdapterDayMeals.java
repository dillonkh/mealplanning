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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import model.Client;
import model.Meal;

public class RecyclerViewAdapterDayMeals extends RecyclerView.Adapter<RecyclerViewAdapterDayMeals.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapterMainScreen";
    private ArrayList<String> mealsList = new ArrayList<>();
    private Context context;
    private LayoutInflater mInflater;

    public RecyclerViewAdapterDayMeals(ArrayList<String> mealsList, Context context) {
        this.mealsList = mealsList;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewAdapterDayMeals.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = mInflater.inflate(R.layout.meal_list_item,viewGroup,false);
        RecyclerViewAdapterDayMeals.ViewHolder viewHolder = new RecyclerViewAdapterDayMeals.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapterDayMeals.ViewHolder viewHolder, final int i) {
        viewHolder.meal.setText(mealsList.get(i));

        viewHolder.meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Pair<Meal,Integer> pair = new Pair<>(Client.getInstance().getClientSavedMeals().get(i),i);
//                Client.getInstance().setSelectedMeal(pair);

                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                MealViewFragment fragment = new MealViewFragment();
                FragmentTransaction transaction = activity.getSupportFragmentManager().
                        beginTransaction();
                transaction.replace(R.id.fragment_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
//                Toast.makeText(context,"click",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateMeals(ArrayList<String> meals) {
        mealsList = meals;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return mealsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView meal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            meal = itemView.findViewById(R.id.mealListItem);
        }
    }
}
