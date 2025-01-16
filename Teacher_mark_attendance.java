package com.example.attendance_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Teacher_mark_attendance extends AppCompatActivity {
Button b1,b2;
String pst,abst;
    SharedPreferences sh;
    String method = "";
    String namespace = "http://dbcon/";
    String soapAction = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen


        setContentView(R.layout.activity_teacher_mark_attendance);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1=(Button) findViewById(R.id.present);
        b2=(Button) findViewById(R.id.absent);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    method = "teacher_mark_attendance_present";
                    soapAction = namespace + method;
                    SoapObject sop = new SoapObject(namespace, method);
                    sop.addProperty("request_id",Teacher_view_accepted_request.rids);
//                            Toast.makeText(getApplicationContext(), " failed.!join_req_id::"+join_req_id, Toast.LENGTH_LONG).show();

                    SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    env.setOutputSoapObject(sop);
                    HttpTransportSE hp = new HttpTransportSE(sh.getString("url", ""));
                    hp.call(soapAction, env);
                    String result = env.getResponse().toString();
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                    if (result.equals("failed")) {
                        Toast.makeText(getApplicationContext(), "attendance marked  failed.!", Toast.LENGTH_LONG).show();
                    } else {
//                        Toast.makeText(getApplicationContext(), " attendance marked  successfully.!", Toast.LENGTH_LONG).show();

                        startActivity(new Intent(getApplicationContext(), Teacher_view_ar_student.class));
                    }


                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();

                }



            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    method = "teacher_mark_attendance_absent";
                    soapAction = namespace + method;
                    SoapObject sop = new SoapObject(namespace, method);
                    sop.addProperty("request_id",Teacher_view_accepted_request.rids);
//                            Toast.makeText(getApplicationContext(), " failed.!join_req_id::"+join_req_id, Toast.LENGTH_LONG).show();

                    SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    env.setOutputSoapObject(sop);
                    HttpTransportSE hp = new HttpTransportSE(sh.getString("url", ""));
                    hp.call(soapAction, env);
                    String result = env.getResponse().toString();
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                    if (result.equals("failed")) {
                        Toast.makeText(getApplicationContext(), "attendance marked  failed.!", Toast.LENGTH_LONG).show();
                    } else {
//                        Toast.makeText(getApplicationContext(), " attendance marked  successfully.!", Toast.LENGTH_LONG).show();

                        startActivity(new Intent(getApplicationContext(), Teacher_view_ar_student.class));
                    }


                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();

                }


            }
        });
    }
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b = new Intent(getApplicationContext(), Homes.class);
        startActivity(b);
    }
}
