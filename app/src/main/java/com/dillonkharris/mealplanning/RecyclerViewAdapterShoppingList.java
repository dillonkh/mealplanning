package com.dillonkharris.mealplanning;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapterShoppingList extends RecyclerView.Adapter<RecyclerViewAdapterShoppingList.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapterMainScreen";

    private ArrayList<String> items = new ArrayList<>();
    private ArrayList<Boolean> checkBoxes = new ArrayList<>();

    private Context context;
    private LayoutInflater mInflater;

    public RecyclerViewAdapterShoppingList(ArrayList<String> items, ArrayList<Boolean> checkBoxes, Context context) {
        this.items = items;
        this.checkBoxes = checkBoxes;
        this.context = context;
        this.mInflater =LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewAdapterShoppingList.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = mInflater.inflate(R.layout.shopping_list_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapterShoppingList.ViewHolder viewHolder, final int i) {
        viewHolder.listItem.setText(items.get(i));
        viewHolder.itemCheckBox.setChecked(checkBoxes.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView listItem;
        CheckBox itemCheckBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listItem = itemView.findViewById(R.id.listItemTextView);
            itemCheckBox = itemView.findViewById((R.id.shoppingItemCheckBox));
        }
    }
}
