package com.dillonkharris.mealplanning;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    private EditText usernameText;
    private EditText passwordText;
    private Button loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        LoginFragment mLoginFragment = this;
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login,container,false);

        usernameText = v.findViewById(R.id.userNameText);
        passwordText = v.findViewById(R.id.passwordText);
        loginButton = v.findViewById(R.id.loginButton);

        setUpUserNameText();
        setUpPasswordText();
        setUpLoginButton();

        return v;
    }
    private void setUpUserNameText() {
        usernameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        LoginFragment.super.getContext(),
                        "Clicked username",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setUpPasswordText() {
        passwordText.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        LoginFragment.super.getContext(),
                        "Clicked password",
                        Toast.LENGTH_SHORT).show();
            }
        }));
    }
    private void setUpLoginButton() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        LoginFragment.super.getContext(),
                        "Clicked Button",
                        Toast.LENGTH_SHORT).show();

                UserMainBoardFragment fragment = new UserMainBoardFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();

                transaction.replace(R.id.fragment_container,fragment);
//                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
