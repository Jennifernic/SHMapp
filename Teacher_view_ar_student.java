package com.example.attendance_management;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Teacher_view_ar_student extends AppCompatActivity implements AdapterView.OnItemClickListener {
    SharedPreferences sh;
    String method = "";
    String namespace = "http://dbcon/";
    String soapAction = "";
    String[] fn, pl,ph,em,val,rid;
    ListView l;
    String rids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_teacher_view_ar_student);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l = (ListView) findViewById(R.id.teacher_ar_student);
        l.setOnItemClickListener(this);
        try {
//            Toast.makeText(getApplicationContext(), "Haiiii", Toast.LENGTH_LONG).show();
            method = "teacher_view_ar_students";
            soapAction = namespace + method;
            SoapObject sop = new SoapObject(namespace,method);
            sop.addProperty("eventid", Teacher_view_accepted_request.eventids);

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
                        rid[z] = temp2[4];

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
        rid = new String[len];
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        rids=rid[i];
        final CharSequence[] items1 = {"Present","Absent","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Teacher_view_ar_student.this);
        builder.setItems(items1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items1[item].equals("Present")) {

                    try {
                        method = "teacher_mark_attendance_present";
                        soapAction = namespace + method;
                        SoapObject sop = new SoapObject(namespace, method);
                        sop.addProperty("request_id", rids);
//                            Toast.makeText(getApplicationContext(), " failed.!join_req_id::"+join_req_id, Toast.LENGTH_LONG).show();

                        SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        env.setOutputSoapObject(sop);
                        HttpTransportSE hp = new HttpTransportSE(sh.getString("url", ""));
                        hp.call(soapAction, env);
                        String result = env.getResponse().toString();
//                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                        if (result.equals("failed")) {
                            Toast.makeText(getApplicationContext(), "attendance marked  failed.!", Toast.LENGTH_LONG).show();
                        } else {
//                        Toast.makeText(getApplicationContext(), " attendance marked  successfully.!", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(getApplicationContext(), Teacher_view_ar_student.class));
                        }


                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();

                    }
                } else if (items1[item].equals("Absent")) {
                    try {
                        method = "teacher_mark_attendance_absent";
                        soapAction = namespace + method;
                        SoapObject sop = new SoapObject(namespace, method);
                        sop.addProperty("request_id", rids);
//                            Toast.makeText(getApplicationContext(), " failed.!join_req_id::"+join_req_id, Toast.LENGTH_LONG).show();


                        SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        env.setOutputSoapObject(sop);
                        HttpTransportSE hp = new HttpTransportSE(sh.getString("url", ""));
                        hp.call(soapAction, env);
                        String result = env.getResponse().toString();
//                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                        if (result.equals("failed")) {
                            Toast.makeText(getApplicationContext(), "attendance marked  failed.!", Toast.LENGTH_LONG).show();
                        } else {
//                        Toast.makeText(getApplicationContext(), " attendance marked  successfully.!", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(getApplicationContext(), Teacher_view_ar_student.class));
                        }


                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();

                    }


                } else if (items1[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}










