package com.dillonkharris.mealplanning;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import model.Client;

public class MealViewFragment extends Fragment {

    TextView mealTitle;
    TextView ingredientTitle;
    TextView ingredients;
    TextView instructionsTitle;
    TextView instructions;
    TextView linkTitle;
    TextView link;
    TextView photoTitle;
    TextView photo;
    Button editButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        MealViewFragment fragment = this;
        setHasOptionsMenu(true);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater menuInflater) {
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
        super.onResume();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meal_view,container,false);

        mealTitle = v.findViewById(R.id.mealTitle);
        ingredientTitle = v.findViewById(R.id.ingedientTitle);
        ingredients = v.findViewById(R.id.ingredients);
        instructionsTitle = v.findViewById(R.id.instructionsTitle);
        instructions = v.findViewById(R.id.instructions);
        linkTitle = v.findViewById(R.id.linkTitle);
        link = v.findViewById(R.id.link);
        photoTitle = v.findViewById(R.id.photoTitle);
        photo = v.findViewById(R.id.photo);
        editButton = v.findViewById(R.id.editButton);

        setUpMeal();

        ingredients.setVisibility(View.GONE);
        instructions.setVisibility(View.GONE);
        link.setVisibility(View.GONE);
        photo.setVisibility(View.GONE);

        ingredientTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ingredients.getVisibility() == View.GONE) {
                    ingredients.setVisibility(View.VISIBLE);
                }
                else {
                    ingredients.setVisibility(View.GONE);
                }
            }
        });
        instructionsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (instructions.getVisibility() == View.GONE) {
                    instructions.setVisibility(View.VISIBLE);
                }
                else {
                    instructions.setVisibility(View.GONE);
                }
            }
        });
        linkTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (link.getVisibility() == View.GONE) {
                    link.setVisibility(View.VISIBLE);
                }
                else {
                    link.setVisibility(View.GONE);
                }
            }
        });
        photoTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (photo.getVisibility() == View.GONE) {
                    photo.setVisibility(View.VISIBLE);
                }
                else {
                    photo.setVisibility(View.GONE);
                }
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MealEditFragment fragment = new MealEditFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();

                transaction.replace(R.id.fragment_container,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return v;
    }
    private void setUpMeal() {
        Client client = Client.getInstance();
        mealTitle.setText(client.getCurrentMealSelected().getMealTitle());
        ingredients.setText(client.getCurrentMealSelected().getIngredientsList());
        instructions.setText(client.getCurrentMealSelected().getInstructionsList());
        link.setText(client.getCurrentMealSelected().getLink());
        photo.setText(client.getCurrentMealSelected().getPhotos());

    }

}
