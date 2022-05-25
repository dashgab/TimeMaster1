package com.example.timemaster;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//import com.example.timemaster.model.User;


public class RegistrationFragment extends Fragment {

    private EditText emailEdit;
    private EditText nameEdit;
    private EditText newPasswordEdit;
    private EditText repeatNewPasswordEdit;
    private Button registerButton;
    private SharedPreferencesHelper mSharedPreferencesHelper;
    private MainViewModel mainViewModel;
    private User user;


    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    private View.OnClickListener OnRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            if (isInputValid()) {
                /*user.login=emailEdit.getText().toString();
                user.password=newPasswordEdit.getText().toString();
                user.name=nameEdit.getText().toString();
                App.getInstance().getUserDao().addUser(user);*/

                boolean isAdded = mSharedPreferencesHelper.addUser(new User(
                        emailEdit.getText().toString(),
                        newPasswordEdit.getText().toString(),
                        nameEdit.getText().toString()
                ));
                if (isAdded) {
                    showMessage(R.string.register_success);
                    getFragmentManager().popBackStack();
                } else {
                    showMessage(R.string.register_error_user_exist);
                }
                showMessage(R.string.register_success);
                getFragmentManager().popBackStack();

            } else {
                showMessage(R.string.register_error_fields);
            }
        }
    };



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());

        emailEdit = view.findViewById(R.id.email_edit_text);
        nameEdit = view.findViewById(R.id.name_edit_text);
        newPasswordEdit = view.findViewById(R.id.new_password_edit_text);
        repeatNewPasswordEdit = view.findViewById(R.id.repeat_new_password_edit_text);
        registerButton = view.findViewById(R.id.register_button_2);

        registerButton.setOnClickListener(OnRegisterClickListener);
        //mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        return view;
    }

    private boolean isInputValid() {
        return isEmailValid() && isNameValid() && isPasswordValid();
    }

    private boolean isPasswordValid(){
        String password = newPasswordEdit.getText().toString();
        String repeatPassword = repeatNewPasswordEdit.getText().toString();
        return password.equals(repeatPassword) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(repeatPassword);
    }
    private boolean isNameValid(){
        String name = nameEdit.getText().toString();
        return !TextUtils.isEmpty(name);
    }
    private boolean isEmailValid(){
        String email = emailEdit.getText().toString();
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void showMessage(@StringRes int string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }



}