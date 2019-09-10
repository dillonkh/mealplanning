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

public class ShoppingListFragment extends Fragment {
    private ArrayList<String> items = new ArrayList<>();
    private ArrayList<Boolean> checkBoxes = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ShoppingListFragment fragment = this;
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Shopping List");

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
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Shopping List");
        super.onResume();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shopping_list,container,false);
        setUpList();
        setUpRecyclerView(v);
        return v;
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch(item.getItemId()) {
//            case R.id.mealListIcon:
//                loadMealList();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//    private void loadMealList() {
//        ShoppingListFragment fragment = new ShoppingListFragment();
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction transaction = fm.beginTransaction();
//
//        transaction.replace(R.id.fragment_container,fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }

    private void setUpList() {

//        for (int i = 0; i < 7; i++) {
//            items.add("item " + i);
//        }
//        for (int i = 0; i < 7; i++) {
//            checkBoxes.add(false);
//        }
    }

    private void setUpRecyclerView(View v) {
        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recyclerViewShoppingList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RecyclerViewAdapterShoppingList adapter = new RecyclerViewAdapterShoppingList(items, checkBoxes, getContext());
        recyclerView.setAdapter(adapter);

    }
}
