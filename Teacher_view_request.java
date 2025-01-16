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

public class Teacher_view_request extends AppCompatActivity implements AdapterView.OnItemClickListener {
    SharedPreferences sh;
    String method = "";
    String namespace = "http://dbcon/";
    String soapAction = "";
    String[] event, status, date, rid,val;
    ListView l;
    public static String rids,r_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen


        setContentView(R.layout.activity_teacher_view_request);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l = (ListView) findViewById(R.id.teacher_request);
        l.setOnItemClickListener(this);

        try {
            Toast.makeText(getApplicationContext(), "Haiiii", Toast.LENGTH_LONG).show();
            method = "teacher_view_request";
            soapAction = namespace + method;
            SoapObject sop = new SoapObject(namespace, method);
            sop.addProperty("logid", sh.getString("logid", ""));
            sop.addProperty("event_id",Teacher_add_event  .eids);

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
                        event[z] = temp2[0];
                        date[z] = temp2[1];
                        status[z] = temp2[2];
                        rid[z] = temp2[3];

                        val[z] = "Event : " + event[z] + "\nDate : " + date[z] + "\nStatus : " + status[z] +  "\n";
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
        status = new String[len];
        date = new String[len];
        rid = new String[len];

        val = new String[len];

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        rids = rid[i];
        r_status = status[i];
        AlertDialog.Builder builder = null;
        if (r_status.equalsIgnoreCase("pending")) {
            final CharSequence[] items1 = {"accept", "reject", "Cancel"};


            builder = new AlertDialog.Builder(Teacher_view_request.this);
            builder.setItems(items1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {

                    if (items1[item].equals("accept")) {
                        try {
                            method = "teacher_request_accept";
                            soapAction = namespace + method;
                            SoapObject sop = new SoapObject(namespace, method);
                            sop.addProperty("request_id", rids);
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
                                Toast.makeText(getApplicationContext(), " Accepted  successfully.!", Toast.LENGTH_LONG).show();

                                startActivity(new Intent(getApplicationContext(), Teacher_view_request.class));
                            }


                        } catch (Exception e) {

                            Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();

                        }

                    } else if (items1[item].equals("reject")) {
                        try {
                            method = "teacher_request_reject";
                            soapAction = namespace + method;
                            SoapObject sop = new SoapObject(namespace, method);
                            sop.addProperty("request_id", rids);
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
                                Toast.makeText(getApplicationContext(), " Rejected  successfully.!", Toast.LENGTH_LONG).show();

                                startActivity(new Intent(getApplicationContext(), Teacher_view_request.class));
                            }


                        } catch (Exception e) {

                            Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();

                        }


                    } else if (items1[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }

            });
        }

        builder.show();

    }
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b = new Intent(getApplicationContext(), Homes.class);
        startActivity(b);
    }
            }


