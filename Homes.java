package com.example.attendance_management;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendance_management.databinding.ActivityHomesBinding;

public class Homes extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomesBinding binding;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        binding = ActivityHomesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHomes.toolbar);
        binding.appBarHomes.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_homes);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        if(sh.getString("usertype","").equals("student"))
        {
            navigationView.getMenu().findItem(R.id.request).setVisible(true);
//            navigationView.getMenu().findItem(R.id .track).setVisible(true);

//            navigationView.getMenu().findItem(R.id.hours).setVisible(true);
//            navigationView.getMenu().findItem(R.id.enq).setVisible(true);
            navigationView.getMenu().findItem(R.id.absent).setVisible(true);
            navigationView.getMenu().findItem(R.id.external).setVisible(true);
            navigationView.getMenu().findItem(R.id.absen).setVisible(true);
            navigationView.getMenu().findItem(R.id.enquiry).setVisible(true);
            navigationView.getMenu().findItem(R.id.noti).setVisible(true);
            navigationView.getMenu().findItem(R.id.calendar).setVisible(true);
            navigationView.getMenu().findItem(R.id.profile).setVisible(true);

//            navigationView.getMenu().findItem(R.id.admin_view_exam_attended).setVisible(true);
            navigationView.getMenu().findItem(R.id.logout).setVisible(true);
        }else if(sh.getString("usertype","").equals("teacher")){
            navigationView.getMenu().findItem(R.id.event).setVisible(true);
            navigationView.getMenu().findItem(R.id.events).setVisible(true);
            navigationView.getMenu().findItem(R.id.att).setVisible(true);
            navigationView.getMenu().findItem(R.id.venq).setVisible(true);
            navigationView.getMenu().findItem(R.id.report).setVisible(true);
            navigationView.getMenu().findItem(R.id.external_activities).setVisible(true);
//            navigationView.getMenu().findItem(R.id.reports).setVisible(true);
            navigationView.getMenu().findItem(R.id.profiles).setVisible(true);
            navigationView.getMenu().findItem(R.id.log_out).setVisible(true);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.request) {
                    startActivity(new Intent(getApplicationContext(), Student_view_event.class));
                }
//                else if (item.getItemId() == R.id.track) {
//                    startActivity(new Intent(getApplicationContext(), Events_upcoming.class));
//                }
                else if (item.getItemId() == R.id.absent) {
                    startActivity(new Intent(getApplicationContext(), Student_view_absent.class));
                }else if (item.getItemId() == R.id.absen) {
                    startActivity(new Intent(getApplicationContext(), Student_view_hour.class));
                } else if (item.getItemId() == R.id.external) {
                    startActivity(new Intent(getApplicationContext(), External_activities.class));
                }
                else if (item.getItemId() == R.id.enquiry) {
                    startActivity(new Intent(getApplicationContext(), Student_send_enquiry.class));
                }
                else if (item.getItemId() == R.id.noti) {
                    startActivity(new Intent(getApplicationContext(), Student_view_noti.class));
                } else if (item.getItemId() == R.id.profile) {
                    startActivity(new Intent(getApplicationContext(), Student_view_profile.class));
                }
                else if (item.getItemId() == R.id.enq) {
                    startActivity(new Intent(getApplicationContext(), Student_send_enquiry.class));
                }
                else if (item.getItemId() == R.id.logout) {
                    startActivity(new Intent(getApplicationContext(), Login.class));
                } else if (item.getItemId() == R.id.event) {
                    startActivity(new Intent(getApplicationContext(), Teacher_add_event.class));
                }
                else if (item.getItemId() == R.id.events) {
                    startActivity(new Intent(getApplicationContext(), Teacher_view_event.class));
                } else if (item.getItemId() == R.id.att) {
                    startActivity(new Intent(getApplicationContext(), Teacher_view_accepted_request.class));
                } else if (item.getItemId() == R.id.report) {
                    startActivity(new Intent(getApplicationContext(), Teacher_view_ar_student.class));
                } else if (item.getItemId() == R.id.external_activities) {
                    startActivity(new Intent(getApplicationContext(), Teacher_view_extraactivity.class));
                }
                else if (item.getItemId() == R.id.profiles) {
                    startActivity(new Intent(getApplicationContext(), Teacher_view_profile.class));
                } else if (item.getItemId() == R.id.venq) {
                    startActivity(new Intent(getApplicationContext(), Teacher_view_enquiry.class));
                } else if (item.getItemId() == R.id.log_out) {
                    startActivity(new Intent(getApplicationContext(), Login.class));
                }



                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homes, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_homes);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}