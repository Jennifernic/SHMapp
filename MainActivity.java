package com.example.attendance_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button b1;
SharedPreferences sh;
String ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen


        setContentView(R.layout.activity_main);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        SharedPreferences.Editor ed = sh.edit();
        ip="192.168.225.114";
//        Toast.makeText(getApplicationContext(), "ip"+ip, Toast.LENGTH_LONG).show();

        ed.putString("ip", ip);

        ed.putString("url", "http://"+ip+":8080/SHM/attendance?wsdl");

        ed.putString("namespace", "http://dbcon/");
        ed.commit();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        }, 3000);

    }
}
