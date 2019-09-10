package model;

import java.util.ArrayList;

public class ShoppingItem {
    private String item;
    private boolean selected;

    public ShoppingItem(String item, boolean selected) {
        this.item = item;
        this.selected = selected;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
