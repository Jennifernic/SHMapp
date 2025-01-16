package com.example.attendance_management;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Teacher_view_extraactivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    EditText e1, e2, e3, e6;
    Button b1;
    String name, desc, hrs;
    ListView l1;
    String[] pid, event, des, hour, imgs, stat, val,sn;
    String namespace = "http://dbcon/";
    String method = "";
    String soapAction = "";
    String url = "";
    ImageButton e4;
    public static String p_id, sid;
    SharedPreferences sh;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view_extraactivity);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        l1 = findViewById(R.id.extra);
        l1.setOnItemClickListener(this);
        try {

//            Toast.makeText(getApplicationContext(), "Haiiii", Toast.LENGTH_LONG).show();
            method = "teacher_view_external";
            soapAction = namespace + method;
            SoapObject sop = new SoapObject(namespace, method);
//                sop.addProperty("join_req_id",Company_view_joinrequest.join_req_id);
            SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            env.setOutputSoapObject(sop);

            HttpTransportSE hp = new HttpTransportSE(sh.getString("url", ""));
            hp.call(soapAction, env);
//            Toast.makeText(getApplicationContext(), "ddddd", Toast.LENGTH_LONG).show();
            String result = env.getResponse().toString();
//            Toast.makeText(getApplicationContext(), "ddddd"+result, Toast.LENGTH_LONG).show();
            if (result.equals("na")) {
                Toast.makeText(getApplicationContext(), "No posts to show you.!", Toast.LENGTH_LONG).show();
            } else {
                String[] temp1 = result.split("\\$");
                if (temp1.length > 0) {
                    inItArraySize(temp1.length);
                    for (int z = 0; z < temp1.length; z++) {
                        String[] temp2 = temp1[z].split("\\#");
                        pid[z] = temp2[0];
                        event[z] = temp2[1];
                        des[z] = temp2[2];
                        hour[z] = temp2[3];

                        imgs[z] = temp2[4];
                        stat[z] = temp2[5];
                        sn[z] = temp2[6];


                        val[z] = "Student Name: "+ sn[z]+"\nEventname: " + event[z] + "\nDescription: " + des[z] + "\nhour : " + hour[z] + "\nImage : " + imgs[z];
                    }
                }
//
                Custimage ci = new Custimage(this, event, des, hour, imgs,sn);
                l1.setAdapter(ci);
            }
        } catch (Exception e) {
        }


    }


    private void inItArraySize(int length) {
        pid = new String[length];
        event = new String[length];
        des = new String[length];
        hour = new String[length];

        imgs = new String[length];
        stat = new String[length];
        sn = new String[length];

        val = new String[length];
    }





    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        p_id=pid[i];
        sid=stat[i];
        AlertDialog.Builder builder = null;
        if (sid.equalsIgnoreCase("pending")) {
            final CharSequence[] items1 = {"verify", "Cancel"};


            builder = new AlertDialog.Builder(Teacher_view_extraactivity.this);
            builder.setItems(items1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {

                    if (items1[item].equals("verify")) {
                        try {
                            method = "teacher_verify";
                            soapAction = namespace + method;
                            SoapObject sop = new SoapObject(namespace, method);
                            sop.addProperty("activity_id",p_id );
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

                    } else if (items1[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }

            });
        }

        builder.show();
    }


//    public void onBackPressed() {
//        // TODO Auto-generated method stub
//        super.onBackPressed();
//        Intent b = new Intent(getApplicationContext(), Login.class);
//        startActivity(b);
//    }

}
