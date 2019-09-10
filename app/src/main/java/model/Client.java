package model;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Client {
    private static final Client instance = new Client();


    private ArrayList<Meal> clientSavedMeals = new ArrayList<>();
    private ArrayList<ShoppingItem> clientShoppingItems = new ArrayList<>();
    private User user = new User();
    private Settings settings = new Settings();
    private HashMap<String,Meal> dayToMealDict = new HashMap<>();

    private String currentDateSelected = new String();
    private Meal currentMealSelected = new Meal();


    private Client(){}
    public static Client getInstance() {
        return instance;
    }


    public String getCurrentDateSelected() {
        return currentDateSelected;
    }

    public void setCurrentDateSelected(String currentDateSelected) {
        this.currentDateSelected = currentDateSelected;
    }

    public ArrayList<Meal> getClientSavedMeals() {
        return clientSavedMeals;
    }

    public void setClientSavedMeals(ArrayList<Meal> clientSavedMeals) {
        this.clientSavedMeals = clientSavedMeals;
    }
    public void addNewMeal(Meal newMeal) {
        clientSavedMeals.add(newMeal);
    }

    public ArrayList<ShoppingItem> getClientShoppingItems() {
        return clientShoppingItems;
    }

    public void setClientShoppingItems(ArrayList<ShoppingItem> clientShoppingItems) {
        this.clientShoppingItems = clientShoppingItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Meal getCurrentMealSelected() {
        return currentMealSelected;
    }

    public void setCurrentMealSelected(Meal currentMealSelected) {
        this.currentMealSelected = currentMealSelected;
    }

    // get meals for a given date
    public ArrayList<Meal> getMealsByDay(String date) {
        return new ArrayList<Meal>();
    }

    public HashMap<String, Meal> getDayToMealDict() {
        return dayToMealDict;
    }

    public void addMealToDay(String date,Meal newMeal) {
        dayToMealDict.put(date,newMeal);
    }
    public Meal getMealByName(String name) {
        for (Meal meal : clientSavedMeals) {
            if (meal.getMealTitle() == name) {
                return meal;
            }
        }
        return null;
    }
}
