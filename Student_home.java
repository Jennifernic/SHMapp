package com.example.attendance_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Student_home extends AppCompatActivity {
    ImageButton b1,b2,b3,b4;
    SharedPreferences sh;
    String method = "";
    String namespace = "http://dbcon/";
    String soapAction = "";
    String[] sid,event,hour,date;
    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen


        setContentView(R.layout.activity_student_home);
     b1=(ImageButton) findViewById(R.id.user_events);
    b2=(ImageButton) findViewById(R.id.user_request);
        b3=(ImageButton) findViewById(R.id.user_logout);
b4=findViewById(R.id.profile);
        b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(),Student_view_event.class));
        }
    });
        b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(),Student_view_request.class));
        }
    });
        b3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(),Login.class));
        }
    });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Student_view_profile.class));
            }
        });


    sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    l = (ListView) findViewById(R.id.lv67);


        try {
        Toast.makeText(getApplicationContext(), "Haiiii", Toast.LENGTH_LONG).show();
        method = "student_view_hour";
        soapAction = namespace + method;
        SoapObject sop = new SoapObject(namespace, method);
//        sop.addProperty("event_id",eids);
        sop.addProperty("sid",sid );

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
                    sid[z]=temp2[0];
                    event[z]=temp2[1];
                    date[z] = temp2[2];
                    hour[z] = temp2[3];


                }
            }
            ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, hour);
            l.setAdapter(aa);
        }
    } catch (Exception e) {
    }
}

    //    internship,details,no_of_months,amount,no_of_seats
    public void inItArraySize(int len) {
        sid = new String[len];
        event=new String[len];
        date=new String[len];
        hour = new String[len];

    }

    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Student_home.class);
        startActivity(b);
    }
}