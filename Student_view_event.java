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
import android.widget.RadioButton;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Student_view_event extends AppCompatActivity implements AdapterView.OnItemClickListener {
    SharedPreferences sh;
    String method = "";
    String namespace = "http://dbcon/";
    String soapAction = "";
    String[] events, time, date, eid,dte,ven,cat,hr,val;
    ListView l;
    RadioButton r1,r2,r3;
    public static String eids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_student_view_event);
        r1 = (RadioButton) findViewById(R.id.radioButton);
        r2 = (RadioButton) findViewById(R.id.radioButton1);
        r3 = (RadioButton) findViewById(R.id.radioButton2);

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast.makeText(getApplicationContext(), "Haiiii", Toast.LENGTH_LONG).show();
                    method = "student_view_event";
                    soapAction = namespace + method;
                    SoapObject sop = new SoapObject(namespace, method);
                    sop.addProperty("event_id",eids);
                    sop.addProperty("type","flexible");
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
                                events[z] = temp2[0];
                                time[z] = temp2[1];
                                dte[z] = temp2[2];
                                eid[z] = temp2[3];
                                ven[z] = temp2[4];
                                cat[z] = temp2[5];
                                hr[z] = temp2[6];

                                val[z] = "Event : " + events[z] + "\ntime : " + time[z] + "\nDate : " + dte[z]+ "\nVenue : " + ven[z]+ "\ncat : " + cat[z]+ "\nDate : " + dte[z] +  "\n";
                            }
                        }
                        ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, val);
                        l.setAdapter(aa);
                    }
                } catch (Exception e) {
                }
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast.makeText(getApplicationContext(), "Haiiii", Toast.LENGTH_LONG).show();
                    method = "student_view_event";
                    soapAction = namespace + method;
                    SoapObject sop = new SoapObject(namespace, method);
                    sop.addProperty("event_id",eids);
                    sop.addProperty("type","fixed");
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
                                events[z] = temp2[0];
                                time[z] = temp2[1];
                                dte[z] = temp2[2];
                                eid[z] = temp2[3];
                                ven[z] = temp2[4];
                                cat[z] = temp2[5];
                                hr[z] = temp2[6];

                                val[z] = "Event : " + events[z] + "\ntime : " + time[z] + "\nDate : " + dte[z]+ "\nVenue : " + ven[z]+ "\ncat : " + cat[z]+ "\nDate : " + dte[z] +  "\n";
                            }
                        }
                        ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, val);
                        l.setAdapter(aa);
                    }
                } catch (Exception e) {
                }
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast.makeText(getApplicationContext(), "Haiiii", Toast.LENGTH_LONG).show();
                    method = "student_view_event";
                    soapAction = namespace + method;
                    SoapObject sop = new SoapObject(namespace, method);
                    sop.addProperty("event_id",eids);
                    sop.addProperty("type","onetime");
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
                                events[z] = temp2[0];
                                time[z] = temp2[1];
                                dte[z] = temp2[2];
                                eid[z] = temp2[3];
                                ven[z] = temp2[4];
                                cat[z] = temp2[5];
                                hr[z] = temp2[6];

                                val[z] = "Event : " + events[z] + "\ntime : " + time[z] + "\nDate : " + dte[z]+ "\nVenue : " + ven[z]+ "\ncat : " + cat[z]+ "\nDate : " + dte[z] +  "\n";
                            }
                        }
                        ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, val);
                        l.setAdapter(aa);
                    }
                } catch (Exception e) {
                }
            }
        });


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l = (ListView) findViewById(R.id.student_event);
        l.setOnItemClickListener(this);

        try {
            Toast.makeText(getApplicationContext(), "Haiiii", Toast.LENGTH_LONG).show();
            method = "student_view_event";
            soapAction = namespace + method;
            SoapObject sop = new SoapObject(namespace, method);
            sop.addProperty("event_id",eids);
            sop.addProperty("type","nan");
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
                        events[z] = temp2[0];
                        time[z] = temp2[1];
                        dte[z] = temp2[2];
                        eid[z] = temp2[3];
                        ven[z] = temp2[4];
                        cat[z] = temp2[5];
                        hr[z] = temp2[6];

                        val[z] = "Event : " + events[z] + "\ntime : " + time[z] + "\nDate : " + dte[z]+ "\nVenue : " + ven[z]+ "\ncat : " + cat[z]+ "\nDate : " + dte[z] +  "\n";
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
        events = new String[len];
        time = new String[len];
        dte = new String[len];
        eid = new String[len];
        ven = new String[len];
        cat = new String[len];
        hr = new String[len];

        val = new String[len];

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        eids=eid[i];

        final CharSequence[] items1 = {"send request", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Student_view_event.this);
        builder.setItems(items1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items1[item].equals("send request")) {
                    try {
                        method = "student_send_request";
                        soapAction = namespace + method;
                        SoapObject sop = new SoapObject(namespace, method);
                        sop.addProperty("event_id",Student_view_event.eids);
                        sop.addProperty("logid", sh.getString("logid", ""));
//                            Toast.makeText(getApplicationContext(), " failed.!join_req_id::"+join_req_id, Toast.LENGTH_LONG).show();

                        SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        env.setOutputSoapObject(sop);
                        HttpTransportSE hp = new HttpTransportSE(sh.getString("url", ""));
                        hp.call(soapAction, env);
                        String result = env.getResponse().toString();
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                        if (result.equals("failed")) {
                            Toast.makeText(getApplicationContext(), "Sending  failed.!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), " send  successfully.!", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(getApplicationContext(), Student_view_event.class));
                        }


                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();

                    }
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