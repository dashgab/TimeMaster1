package com.example.timemaster;

//import android.app.Fragment;
import androidx.fragment.app.Fragment;

        import android.os.Bundle;
        import android.view.MenuItem;
        import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
        import com.google.android.material.navigation.NavigationView;


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

        //тулбар
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //меню навигации слева
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                 this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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
                    case R.id.bottom_nav_graph:
                        setFragment(GraphFragment.newInstance());
                        break;
                }
                return true;
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

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


