package model;

public class Meal {
    private String ID;
    private String mealTitle;
    private String ingredientsList;
    private String instructionsList;
    private String link;
    private String photos;

    public Meal() {
        mealTitle = "No Meal";
        ingredientsList = "None";
        instructionsList = "None";
        link = "None";
        photos = "None";
    }

    public Meal(String ID, String mealTitle, String ingredientsList, String instructionsList, String link, String photos) {
        this.ID = ID;
        this.mealTitle = mealTitle;
        this.ingredientsList = ingredientsList;
        this.instructionsList = instructionsList;
        this.link = link;
        this.photos = photos;
    }

    public String getMealTitle() {
        return mealTitle;
    }

    public void setMealTitle(String mealTitle) {
        this.mealTitle = mealTitle;
    }

    public String getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(String ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public String getInstructionsList() {
        return instructionsList;
    }

    public void setInstructionsList(String instructionsList) {
        this.instructionsList = instructionsList;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
