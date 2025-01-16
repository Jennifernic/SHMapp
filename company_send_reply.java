package com.example.attendance_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class company_send_reply extends AppCompatActivity {
EditText e1;
Button b1;
String reply;
SharedPreferences sh;
String method = "";
String namespace = "http://dbcon/";
String soapAction = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_send_reply);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText)findViewById(R.id.reply);
        b1=(Button)findViewById(R.id.btn_send);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reply=e1.getText().toString();
                if(reply.equalsIgnoreCase("true"))
                {
                    e1.setError("");
                    e1.setFocusable(true);
                }
                else{

                    try {
                        method="company_send_reply";
                        soapAction = namespace + method;
                        SoapObject sop = new SoapObject(namespace, method);
                        sop.addProperty("reply",reply);
                        sop.addProperty("complaint_id",Teacher_view_enquiry.eids);
                        sop.addProperty("logid",sh.getString("logid",""));
//                            Toast.makeText(getApplicationContext(), " failed.!join_req_id::"+join_req_id, Toast.LENGTH_LONG).show();

                        SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        env.setOutputSoapObject(sop);
                        HttpTransportSE hp = new HttpTransportSE(sh.getString("url",""));
                        hp.call(soapAction, env);
                        String result = env.getResponse().toString();
                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                        if (result.equals("na")) {
                            Toast.makeText(getApplicationContext(), " failed.!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "reply send  successfully.!", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(getApplicationContext(),Teacher_view_enquiry.class));
                        }


                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(),e+"",Toast.LENGTH_LONG).show();

                    }
                }


            }
        });
    }
    public void onBackPressed()

    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Homes.class);
        startActivity(b);
    }
}