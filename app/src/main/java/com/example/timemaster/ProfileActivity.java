package com.example.timemaster;

//import android.app.Fragment;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timemaster.model.Folder;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
//import com.example.timemaster.model.User;



public class ProfileActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout fragmentContainer;
    private Button checklistButton;
    private Button calendarButton;
    private ImageView menuIcon;
    private ImageView settingsIcon;
    private TextView titleText;
    private Toolbar toolbar;

    public static final String USER_KEY = "USER_KEY";

    private User mUser;

    private BottomNavigationView navigationView;

    private DrawerLayout drawer;
    private NavigationView leftNavigationView;
    private View header;

    private List<Folder> mFolderList = new ArrayList<>();
    private RecyclerView rvFolder;
    private FolderAdapter mFolderAdapter;
    private FolderViewModel mModel;

    private Button switchButton;

    private ImageButton addFolderButton;






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_profile);


        //получение данных из вызванной активности
        Bundle bundle = getIntent().getExtras();
        mUser = (User)bundle.get(USER_KEY);

        //контейнер с фрагментом
        fragmentContainer = findViewById(R.id.fragment_container_profile);
        setFragment(HomeFragment.newInstance());
        fragmentContainer.setBackgroundColor(getResources().getColor(R.color.white));

        //тулбар
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //меню навигации слева
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                 this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        leftNavigationView = (NavigationView) findViewById(R.id.left_nav_view);
        leftNavigationView.setNavigationItemSelectedListener(this);
        header = leftNavigationView.getHeaderView(0);

        mModel = ViewModelProviders.of(this).get(FolderViewModel.class);
        loadFolderItems();

        //navigation view bottom
        navigationView = findViewById(R.id.bottom_nav_view);
        navigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.bottom_nav_home:
                        setFragment(HomeFragment.newInstance());
                        break;
                    case R.id.bottom_nav_timer:
                        setFragment(TimerFragment.newInstance());
                        break;
                    /*case R.id.bottom_nav_graph:
                        setFragment(GraphFragment.newInstance());
                        break;*/
                }
                return true;
            }
        });


        addFolderButton = header.findViewById(R.id.add_folder_button);

        addFolderButton.setOnClickListener(OnAddFolderClickListener);



    }

    private View.OnClickListener OnAddFolderClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            FragmentManager manager = getSupportFragmentManager();
            AddFolderDialogFragment myDialogFragment = new AddFolderDialogFragment();
            myDialogFragment.show(manager, "myDialog");
        }
    };




    private void loadFolderItems() {
        rvFolder = header.findViewById(R.id.rv_nav_drawer);
        rvFolder.setLayoutManager(new LinearLayoutManager(this));
        rvFolder.setHasFixedSize(true);
        mFolderAdapter = new FolderAdapter(mFolderList);
        rvFolder.setAdapter(mFolderAdapter);

        mModel.getFolderLiveData().observe(this, new Observer<List<Folder>>() {
            @Override
            public void onChanged(List<Folder> folders) {
                mFolderAdapter.addFolderList(folders);

            }
        });

    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_profile, fragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_settings:
                Intent intent = new Intent(this, SharedPreferencesActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_exit:
                //this.finish();
                intent = new Intent(this, AuthActivity.class);
                startActivity(intent);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean switchOnOff = pref.getBoolean("pref_color_switch", false);
        if (switchOnOff==true){
            String colorString = pref.getString("pref_color", "");

            if (colorString.contains("Белый"))
                fragmentContainer.setBackgroundColor(getResources().getColor(R.color.white));
            else if (colorString.contains("Фиолетовый"))
                fragmentContainer.setBackgroundColor(getResources().getColor(R.color.purple));
            else if (colorString.contains("Персиковый"))
                fragmentContainer.setBackgroundColor(getResources().getColor(R.color.peach));
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search_item) {
            Toast.makeText(this, "search", Toast.LENGTH_LONG).show();
        }
        else if (id == R.id.settings_item) {
            Toast.makeText(this, "settings", Toast.LENGTH_LONG).show();
        }
        return true;
    }*/
}


