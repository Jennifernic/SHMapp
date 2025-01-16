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

public class Teacher_view_accepted_request extends AppCompatActivity implements AdapterView.OnItemClickListener {

    SharedPreferences sh;
    String method = "";
    String namespace = "http://dbcon/";
    String soapAction = "";
    String[] event, status, date, rid,val,sid,lid,eventid;
    ListView l;
    public static String rids,sids,lids,eventids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen


        setContentView(R.layout.activity_teacher_view_accepted_request);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l = (ListView) findViewById(R.id.teacher_request);
        l.setOnItemClickListener(this);


        try {
            Toast.makeText(getApplicationContext(), "Haiiii", Toast.LENGTH_LONG).show();
            method = "teacher_view_accepted_request";
            soapAction = namespace + method;
            SoapObject sop = new SoapObject(namespace, method);
            sop.addProperty("logid", sh.getString("logid", ""));

            SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            env.setOutputSoapObject(sop);
            HttpTransportSE hp = new HttpTransportSE(sh.getString("url", ""));
            hp.call(soapAction, env);
            String result = env.getResponse().toString();
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            if (result.equals("failed")) {
                Toast.makeText(getApplicationContext(), "No posts to show you.!", Toast.LENGTH_LONG).show();
            } else {
                String[] temp1 = result.split("\\$");
                if (temp1.length > 0) {
                    inItArraySize(temp1.length);
                    for (int z = 0; z < temp1.length; z++) {
                        String[] temp2 = temp1[z].split("\\#");
                        eventid[z] = temp2[0];
                        event[z] = temp2[0];
                        date[z] = temp2[1];
//                        status[z] = temp2[2];
//                        rid[z] = temp2[3];
//                        sid[z] = temp2[4];
//                        lid[z] = temp2[5];


                        val[z] = "Event : " + event[z] + "\nDate : " + date[z] +  "\n";
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
        event = new String[len];
        eventid = new String[len];
        date = new String[len];
//        rid = new String[len];
//        sid = new String[len];
//        lid = new String[len];

        val = new String[len];

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        eventids=eventid[i];
        SharedPreferences.Editor ed = sh.edit();
        ed.putString("receiver_id", lids);
        ed.commit();

        final CharSequence[] items1 = {"view student","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Teacher_view_accepted_request.this);
        builder.setItems(items1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items1[item].equals("view student")) {

                    startActivity(new Intent(getApplicationContext(),Teacher_view_ar_student.class));
                }

                else if (items1[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b = new Intent(getApplicationContext(), Homes.class);
        startActivity(b);
    }
}