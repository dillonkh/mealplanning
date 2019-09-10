package com.dillonkharris.mealplanning;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import model.Client;
import model.Meal;

import static java.security.AccessController.getContext;

public class RecyclerViewAdapterMainScreen extends RecyclerView.Adapter<RecyclerViewAdapterMainScreen.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapterMainScreen";
    
    private ArrayList<String> days = new ArrayList<>();
    private ArrayList<String> dates = new ArrayList<>();
    private ArrayList<String> mealsList = new ArrayList<>();



    private Context context;
    private LayoutInflater mInflater;

    public RecyclerViewAdapterMainScreen(ArrayList<String> days, ArrayList<String> dates,
                                         ArrayList<String> mealsList, Context context) {
        this.days = days;
        this.dates = dates;
        this.mealsList = mealsList;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = mInflater.inflate(R.layout.layout_day,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.day.setText(days.get(i));
        viewHolder.date.setText(dates.get(i));

//        viewHolder.meals.setText((mealsList.get(i)));

//        viewHolder.mealList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Pair<Meal,Integer> pair = new Pair<>(Client.getInstance().getClientSavedMeals().get(i),i);
//                Client.getInstance().setSelectedMeal(pair);
//                makeSwitch(v);
//            }
//        });
    }

    private void makeSwitch(View v) {
        AppCompatActivity activity = (AppCompatActivity)v.getContext();
        MealViewFragment fragment = new MealViewFragment();
        FragmentTransaction transaction = activity.getSupportFragmentManager().
                beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView day;
        TextView date;
        RecyclerViewAdapterDayMeals meals;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.day);
            date = itemView.findViewById(R.id.date);
//            meals = new RecyclerViewAdapterDayMeals(mealsList,context);
            setUpMeals(itemView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
        private void setUpMeals(View v) {
            RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recyclerViewMealList);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            RecyclerViewAdapterDayMeals adapter = new RecyclerViewAdapterDayMeals(mealsList,context);
            recyclerView.setAdapter(adapter);
        }
    }
}
