package com.example.attendance_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Student_view_profile extends AppCompatActivity {
    SharedPreferences sh;
    String method = "";
    String namespace = "http://dbcon/";
    String soapAction = "";
    String[] sid,hour;
    ListView l;
    String[] fn, pl,ph,em,val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_student_view_profile);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l=findViewById(R.id.sprofile);
        try {
            Toast.makeText(getApplicationContext(), "Haiiii", Toast.LENGTH_LONG).show();
            method = "Student_view_profile";
            soapAction = namespace + method;
            SoapObject sop = new SoapObject(namespace, method);
            sop.addProperty("logid", sh.getString("logid", ""));
            SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            env.setOutputSoapObject(sop);
            HttpTransportSE hp = new HttpTransportSE(sh.getString("url", ""));
            hp.call(soapAction, env);
            String result = env.getResponse().toString();
            if (result.equals("failed")) {
                Toast.makeText(getApplicationContext(), "No posts to show you.!", Toast.LENGTH_LONG).show();
            } else {
                String[] temp1 = result.split("\\$");
                if (temp1.length > 0) {
                    inItArraySize(temp1.length);
                    for (int z = 0; z < temp1.length; z++) {
                        String[] temp2 = temp1[z].split("\\#");
                        fn[z] = temp2[0];
                        pl[z] = temp2[1];
                        ph[z] = temp2[2];
                        em[z] = temp2[3];

                        val[z] = "Student Name : " + fn[z] + "\nPlace : " + pl[z] + "\nPhone : " + ph[z] +"\nEmail:"+ em[z]  +"\n";
                    }
                }
                ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, val);
                l.setAdapter(aa);
            }
        } catch (Exception e) {
        }
    }

    //    internship,details,no_of_months,amount,no_of_seats
    public void inItArraySize(int len) {
        fn = new String[len];
        pl = new String[len];
        ph = new String[len];
        em = new String[len];
        val = new String[len];
    }

    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b = new Intent(getApplicationContext(), Homes.class);
        startActivity(b);
    }

    }
