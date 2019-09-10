package com.dillonkharris.mealplanning;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        if (loggedIn() == true) {
            return new UserMainBoardFragment();
        }
        else {
            return new LoginFragment();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    private boolean loggedIn() {
        return true;
    }

}
