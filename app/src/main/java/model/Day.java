package model;

import java.util.ArrayList;

public class Day {
    private ArrayList<Meal> meals;
    private String date;

    public Day(ArrayList<Meal> meals, String date) {
        this.meals = meals;
        this.date = date;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
