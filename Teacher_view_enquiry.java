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

public class Teacher_view_enquiry extends AppCompatActivity implements AdapterView.OnItemClickListener {
    SharedPreferences sh;
    String method = "";
    String namespace = "http://dbcon/";
    String soapAction = "";
    String[] enq, reply,eid,name,date,val,fname;
    public static String eids;
    ListView l;
    public static String cid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_teacher_view_enquiry);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


//        e1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//

        l = (ListView) findViewById(R.id.venq);
        l.setOnItemClickListener(this);
        try {

            method = "teacher_view_enquiry";
            soapAction = namespace + method;
            SoapObject sop = new SoapObject(namespace, method);
            SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            env.setOutputSoapObject(sop);
            HttpTransportSE hp = new HttpTransportSE(sh.getString("url", ""));
            hp.call(soapAction, env);
            String result = env.getResponse().toString();
//            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            if (result.equals("failed")) {
                Toast.makeText(getApplicationContext(), "No posts to show you.!", Toast.LENGTH_LONG).show();
            } else {
                String[] temp1 = result.split("\\$");
                if (temp1.length > 0) {
                    inItArraySize(temp1.length);
                    for (int z = 0; z < temp1.length; z++) {
                        String[] temp2 = temp1[z].split("\\#");
                        eid[z] = temp2[0];
                        fname[z] = temp2[1];
                        enq[z] = temp2[2];
                        reply[z] = temp2[3];
                        date[z] = temp2[4];

                        val[z] = "Name : " + fname[z]+"\nEnquiry : " + enq[z] + "\nReply : " + reply[z] + "\nDate : " + date[z] +  "\n";
                    }
                }
                ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, val);
                l.setAdapter(aa);
            }
        } catch (Exception e) {
        }
    }

    //    internship,details,no_of_months,amount,no_of_seats
    public void inItArraySize(int len)
    {
        eid = new String[len];
        fname = new String[len];

        enq = new String[len];
        reply = new String[len];
        date = new String[len];

        val = new String[len];
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        eids=eid[i];
        final CharSequence[] items1 = {"Reply", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Teacher_view_enquiry.this);
        builder.setItems(items1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items1[item].equals("Reply"))
                {
                    startActivity(new Intent(getApplicationContext(),company_send_reply.class));
                }
                else if (items1[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
//

    }
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b = new Intent(getApplicationContext(), Homes.class);
        startActivity(b);
    }
    }
