package com.dillonkharris.mealplanning;

import android.os.Bundle;
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

import java.util.ArrayList;

import model.Client;

public class MealListFragment extends Fragment {
//    private ArrayList<String> breakfasts = new ArrayList<>();
//    private ArrayList<String> lunches = new ArrayList<>();
//    private ArrayList<String> dinners = new ArrayList<>();

    private ArrayList<String> meals = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        MealListFragment fragment = this;
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Meal List");

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main_menu,menu);
        ((SingleFragmentActivity)getActivity()).setToolBar(true);
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
            case android.R.id.home:
                loadMainBoard();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void loadMainBoard () {
        UserMainBoardFragment fragment = new UserMainBoardFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
    private void loadSettings(){
        SettingsFragment fragment = new SettingsFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void onResume() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Meal List");
        super.onResume();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meal_list,container,false);
        setUpList();
        setUpRecyclerView(v);
        return v;
    }

    private void setUpList() {
        Client client = Client.getInstance();
        if (client.getClientSavedMeals() != null) {
            for (int i = 0; i < client.getClientSavedMeals().size(); i++) {
                meals.add(client.getClientSavedMeals().get(i).getMealTitle());
            }
        }
    }

    private void setUpRecyclerView(View v) {
        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recyclerViewMealList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RecyclerViewAdapterMealList adapter = new RecyclerViewAdapterMealList(meals, getContext());
        recyclerView.setAdapter(adapter);

    }
}
