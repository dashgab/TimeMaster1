package com.example.timemaster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.timemaster.model.Folder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddFolderDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {

    private SharedPreferences preferences;
    private Button saveFolderButton;
    //@BindView(R.id.choose_color_spinner)
    Spinner folderColorSpinner;
    private EditText folderNameText;
    private Folder folder;
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initSpinner() {

        // Create an array adapter for all three spinners using the string array
        ArrayAdapter<CharSequence> folderColorAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.folder_color_array, R.layout.spinner_item);

        // Layout to use when list of choices appears
        folderColorAdapter.setDropDownViewResource(R.layout.spinner_item);

        // Apply the adapter to the spinner
        folderColorSpinner.setAdapter(folderColorAdapter);

        // Set the default selection
        folderColorSpinner.setSelection(preferences.getInt(getString(R.string.folder_color_key), 1));
        folderColorSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // initialize the editor
        SharedPreferences.Editor editor = preferences.edit();
        // switch case to handle different spinners
        switch (parent.getId()) {
            // item selected in work duration spinner
            case R.id.choose_color_spinner:
                // save the corresponding item position
                editor.putInt(getString(R.string.folder_color_key), position);
                break;

        }
        editor.apply();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_folder, null);
        builder.setView(view);

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        //ButterKnife.bind();
        folderColorSpinner = (Spinner)view.findViewById(R.id.choose_color_spinner);
        initSpinner();

        folderNameText = (EditText) view.findViewById(R.id.folder_name_edit_text);
        saveFolderButton=view.findViewById(R.id.dialog_add_folder_button);

        saveFolderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                folder = new Folder();
                folder.setName(folderNameText.getText().toString());
                folder.setId_user(1);
                folder.setColor(folderColorSpinner.getSelectedItem().toString());
                App.getInstance().getFolderDao().insert(folder);
                getDialog().dismiss();
            }
        });
        return builder.create();
    }

    /*myDialogFragment.show(manager, "myDialog");
    final Dialog dialog=new Dialog(ProfileActivity.this);
            dialog.setContentView(R.layout.dialog_add_folder);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
    saveFolderButton=dialog.findViewById(R.id.dialog_add_folder_button);
    //initSpinner();
    ArrayAdapter<CharSequence> folderColorAdapter = ArrayAdapter.createFromResource(dialog.getContext(),
            R.array.folder_color_array, R.layout.spinner_item);

    // Layout to use when list of choices appears
            folderColorAdapter.setDropDownViewResource(R.layout.spinner_item);

    // Apply the adapter to the spinner
            folderColorSpinner.setAdapter(folderColorAdapter);

    // Set the default selection
            folderColorSpinner.setSelection(preferences.getInt(getString(R.string.folder_color_key), 1));

            folderColorSpinner.setOnItemSelectedListener();
            saveFolderButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            folderNameText = findViewById(R.id.folder_name_edit_text);
            folder = new Folder();
            folder.setName(folderNameText.toString());
            folder.setUid(1);
            folder.setColor(folderColorSpinner.toString());
            App.getInstance().getFolderDao().insert(folder);
            dialog.dismiss();
        }
    });
            dialog.show();*/
}
