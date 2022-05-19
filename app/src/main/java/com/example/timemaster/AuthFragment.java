/*package com.example.timemaster;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.timemaster.model.User;



public class AuthFragment extends Fragment {

    private EditText loginEdit;
    private EditText passwordEdit;
    private Button enterButton;
    private Button registerButton;
    private Button googleEnterButton;
    //private SharedPreferencesHelper mSharedPreferencesHelper;
    private MainViewModel mainViewModel;

    public static AuthFragment newInstance() {
        Bundle args = new Bundle();
        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }



    private View.OnClickListener OnEnterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isLoginValid() && isPasswordValid())
            {
                for (User user : mainViewModel.getUserLiveData().getValue()) {
                    if (user.login.equalsIgnoreCase(loginEdit.getText().toString()) && user.password.equals(passwordEdit.getText().toString())) {
                        Intent startProfileIntent = new Intent(getActivity(), ProfileActivity.class);
                        startProfileIntent.putExtra(ProfileActivity.USER_KEY, user);
                        startActivity(startProfileIntent);
                        getActivity().finish();
                        break;
                    } else {
                        showMessage(R.string.enter_login_exist_error);
                    }
                }
            } else {
                showMessage(R.string.login_input_error);
            }
        }
    };


    private View.OnClickListener OnRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, RegistrationFragment.newInstance())
                    .addToBackStack(RegistrationFragment.class.getName())
                    .commit();
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_auth, container, false);

        //mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());

        loginEdit = v.findViewById(R.id.login_edit_text);
        passwordEdit = v.findViewById(R.id.password_edit_text);
        enterButton = v.findViewById(R.id.enter_button);
        registerButton = v.findViewById(R.id.register_button);
        googleEnterButton= v.findViewById(R.id.google_button);

        enterButton.setOnClickListener(OnEnterClickListener);
        registerButton.setOnClickListener(OnRegisterClickListener);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        return v;
    }


    private boolean isLoginValid() {
        return !TextUtils.isEmpty(loginEdit.getText()) && Patterns.EMAIL_ADDRESS.matcher(loginEdit.getText()).matches();
    }

    private boolean isPasswordValid() {
        return !TextUtils.isEmpty(passwordEdit.getText());
    }

    private void showMessage(@StringRes int string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }
}*/
package com.example.timemaster;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthFragment extends Fragment {

    EditText loginEdit;
    EditText passwordEdit;
    Button enterButton;
    Button registerButton;
    Button googleEnterButton;
    private SharedPreferencesHelper mSharedPreferencesHelper;

    public static AuthFragment newInstance() {
        Bundle args = new Bundle();
        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private View.OnClickListener OnEnterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isLoginValid() && isPasswordValid())
            {
                for (User user : mSharedPreferencesHelper.getUsers()) {
                    if (user.getEmail().equalsIgnoreCase(loginEdit.getText().toString()) && user.getPassword().equals(passwordEdit.getText().toString())) {
                        Intent startProfileIntent = new Intent(getActivity(), ProfileActivity.class);
                        startProfileIntent.putExtra(ProfileActivity.USER_KEY, user);
                        startActivity(startProfileIntent);
                        getActivity().finish();
                        break;
                    } else {
                        showMessage(R.string.enter_login_exist_error);
                    }
                }
            } else {
                showMessage(R.string.login_input_error);
            }
        }
    };

    private View.OnClickListener OnRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, RegistrationFragment.newInstance())
                    .addToBackStack(RegistrationFragment.class.getName())
                    .commit();
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_auth, container, false);

        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());

        loginEdit = v.findViewById(R.id.login_edit_text);
        passwordEdit = v.findViewById(R.id.password_edit_text);
        enterButton = v.findViewById(R.id.enter_button);
        registerButton = v.findViewById(R.id.register_button);
        googleEnterButton= v.findViewById(R.id.google_button);

        enterButton.setOnClickListener(OnEnterClickListener);
        registerButton.setOnClickListener(OnRegisterClickListener);

        return v;
    }


    private boolean isLoginValid() {
        return !TextUtils.isEmpty(loginEdit.getText()) && Patterns.EMAIL_ADDRESS.matcher(loginEdit.getText()).matches();
    }

    private boolean isPasswordValid() {
        return !TextUtils.isEmpty(passwordEdit.getText());
    }

    private void showMessage(@StringRes int string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }
}