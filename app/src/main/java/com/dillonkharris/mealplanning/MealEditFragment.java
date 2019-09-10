package com.dillonkharris.mealplanning;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

import model.Client;
import model.Meal;

public class MealEditFragment extends Fragment {


    EditText mealTitle;
    TextView ingredientTitleEdit;
    EditText ingredientsEdit;
    TextView instructionsTitleEdit;
    EditText instructionsEdit;
    TextView linkTitleEdit;
    EditText linkEdit;
    TextView photoTitleEdit;
    EditText photoEdit;
    Button saveButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        MealEditFragment fragment = this;
        setHasOptionsMenu(true);

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
        super.onResume();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_meal_edit,container,false);

        mealTitle = v.findViewById(R.id.mealTitleEdit);
        ingredientTitleEdit = v.findViewById(R.id.ingedientTitleEdit);
        ingredientsEdit = v.findViewById(R.id.ingredientsEdit);
        instructionsTitleEdit = v.findViewById(R.id.instructionsTitleEdit);
        instructionsEdit = v.findViewById(R.id.instructionsEdit);
        linkTitleEdit = v.findViewById(R.id.linkTitleEdit);
        linkEdit = v.findViewById(R.id.linkEdit);
        photoTitleEdit = v.findViewById(R.id.photoTitleEdit);
        photoEdit = v.findViewById(R.id.photoEdit);
        saveButton = v.findViewById(R.id.saveButton);

        setUpMeal();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMeal(Client.getInstance().getCurrentDateSelected());
                MealViewFragment fragment = new MealViewFragment();
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
        ingredientsEdit.setText(client.getCurrentMealSelected().getIngredientsList());
        instructionsEdit.setText(client.getCurrentMealSelected().getInstructionsList());
        linkEdit.setText(client.getCurrentMealSelected().getLink());
        photoEdit.setText(client.getCurrentMealSelected().getPhotos());
    }
    private void saveMeal(String date) {
        Meal meal = new Meal(UUID.randomUUID().toString(),mealTitle.getText().toString(),
                ingredientsEdit.getText().toString(),
                instructionsEdit.getText().toString(),
                linkEdit.getText().toString(),
                photoEdit.getText().toString());

        Client client = Client.getInstance();
        client.addNewMeal(meal);
        client.addMealToDay(date,meal);
        client.setCurrentMealSelected(meal);
    }
}
