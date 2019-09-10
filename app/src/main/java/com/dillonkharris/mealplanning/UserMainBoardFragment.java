package com.dillonkharris.mealplanning;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import model.Client;
import model.Meal;

public class UserMainBoardFragment extends Fragment {

    private ArrayList<String> days = new ArrayList<>();
    private ArrayList<String> dates = new ArrayList<>();
    private ArrayList<String> meals = new ArrayList<>();
//    private String date;

    private RecyclerViewAdapterDayMeals adapter;
    private FloatingActionButton fab;
    private RelativeLayout addNewArea;
    private LinearLayout mealListArea;

    private CalendarView calendarView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        UserMainBoardFragment fragment = this;
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Meal Planning");

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main_menu,menu);
        ((SingleFragmentActivity)getActivity()).setToolBar(false);
    }
    @Override
    public void onResume() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Meal Planning");
        super.onResume();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_board,container,false);
        addNewArea = v.findViewById(R.id.addNewArea);
        mealListArea = v.findViewById(R.id.mealListArea);

        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNewMeal();
            }
        });


        setUpRecyclerView(v);
        calendarView = v.findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                updateMeals(dayOfMonth,month,year);
//                Client.getInstance().setCurrentDateSelected(date);
            }
        });
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int week = c.get(Calendar.WEEK_OF_MONTH);
        int year = c.get(Calendar.YEAR);
        updateMeals(day,week,year);
        return v;
    }
    private void updateMeals(int day, int week, int year) {
        String date = Integer.toString(day)+Integer.toString(week)+Integer.toString(year);
        Client.getInstance().setCurrentDateSelected(date);
        adapter.updateMeals(getMeals(date));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.shoppingIcon:
                loadShoppingList();
                return true;
            case R.id.mealListIcon:
                loadMealList();
                return true;
            case R.id.settingsIcon:
                loadSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void loadShoppingList() {
        ShoppingListFragment fragment = new ShoppingListFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void loadMealList() {
        MealListFragment fragment = new MealListFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void loadNewMeal() {
        MealEditFragment fragment = new MealEditFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void loadSettings(){
        SettingsFragment fragment = new SettingsFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private ArrayList<String> getMeals(String date) {
        meals.clear();
        ArrayList<Meal> actualMeals = Client.getInstance().getMealsByDay(date);
        meals = getMealNames(actualMeals);

        if (meals.size() == 0) {
            addNewArea.setVisibility(RelativeLayout.VISIBLE);
            mealListArea.setVisibility(LinearLayout.GONE);
        }
        else {
            addNewArea.setVisibility(RelativeLayout.GONE);
            mealListArea.setVisibility(LinearLayout.VISIBLE);
        }
        return meals;
    }

    private ArrayList<String> getMealNames(ArrayList<Meal> actualMeals) {
        ArrayList<String> m = new ArrayList<>();
        for (Meal meal : actualMeals) {
            m.add(meal.getMealTitle());
        }

        return m;
    }

//    private void setUpList() {
//        Client client = Client.getInstance();
//
//        for (int i = 0; i < 7; i++) {
//            if (client.getClientSavedMeals().get(i) == null) {
//                meals.add("No meal saved...");
//            }
//            else {
//                meals.add(client.getClientSavedMeals().get(i).getMealTitle());
//            }
//        }
////
//
//        Calendar c = Calendar.getInstance();
//        int date = c.get(Calendar.DAY_OF_MONTH);
//        String day = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
//
//        days.add(day+" ");
//        dates.add(" "+String.valueOf(date));
//
//        c.add(Calendar.DAY_OF_YEAR,1);
//        date = c.get(Calendar.DAY_OF_MONTH);
//        day = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
//        days.add(day+" ");
//        dates.add(" "+String.valueOf(date));
//
//        c.add(Calendar.DAY_OF_YEAR,1);
//        date = c.get(Calendar.DAY_OF_MONTH);
//        day = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
//        days.add(day+" ");
//        dates.add(" "+String.valueOf(date));
//
//        c.add(Calendar.DAY_OF_YEAR,1);
//        date = c.get(Calendar.DAY_OF_MONTH);
//        day = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
//        days.add(day+" ");
//        dates.add(" "+String.valueOf(date));
//
//        c.add(Calendar.DAY_OF_YEAR,1);
//        date = c.get(Calendar.DAY_OF_MONTH);
//        day = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
//        days.add(day+" ");
//        dates.add(" "+String.valueOf(date));
//
//        c.add(Calendar.DAY_OF_YEAR,1);
//        date = c.get(Calendar.DAY_OF_MONTH);
//        day = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
//        days.add(day+" ");
//        dates.add(" "+String.valueOf(date));
//
//        c.add(Calendar.DAY_OF_YEAR,1);
//        date = c.get(Calendar.DAY_OF_MONTH);
//        day = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
//        days.add(day+" ");
//        dates.add(" "+String.valueOf(date));
//
//    }

    private void setUpRecyclerView(View v) {
        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.fragmentMealList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RecyclerViewAdapterDayMeals(meals,getContext());
        recyclerView.setAdapter(adapter);

    }
}
