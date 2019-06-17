package com.ivanfrescas.notes2gou;



import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout mainFrame;

    private HomeFragment homeFragment;
    private ToDoListFragment toDoListFragment;
    private CalendarFragment calendarFragment;
    private GroupFragment groupFragment;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFrame = findViewById(R.id.main_frame);
        bottomNavigationView = findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        toDoListFragment = new ToDoListFragment();
        calendarFragment = new CalendarFragment();
        groupFragment = new GroupFragment();
        settingsFragment = new SettingsFragment();

        setFragment(homeFragment);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home :
                        setFragment(homeFragment);

                       return true;

                    case R.id.nav_todolist :
                        setFragment(toDoListFragment);
                        return true;

                    case R.id.nav_calendar :
                        setFragment(calendarFragment);
                        return true;

                    case R.id.nav_group :
                        setFragment(groupFragment);
                        return true;

                    case R.id.nav_settings :
                        setFragment(settingsFragment);
                        return  true;

                    default:
                        return false;



                }


            }


        });

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }




}
