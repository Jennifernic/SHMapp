package com.example.attendance_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Teacher_edit_event extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7;
    TextView t1;
    Button b1;
    String fn,ln,place,phone,email;
    SharedPreferences sh;
    String method="";
    String namespace="http://dbcon/";
    String soapAction="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_edit_event);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        t1=findViewById(R.id.textView3);
//        e1 = (EditText) findViewById(R.id.post);
//        e2 = (EditText) findViewById(R.id.qualification);
//        e3 = (EditText) findViewById(R.id.postfor);
//        e5 = (EditText) findViewById(R.id.date);
//        e4 = (EditText) findViewById(R.id.email);
//        b1 = (Button) findViewById(R.id.email);
//        e6=(EditText)findViewById(R.id.uname);
//        e7=(EditText)findViewById(R.id.pwd);
//        b1=(Button)findViewById(R.id.u_reg);


//        try {
//            method = "Edit_event";
//            soapAction = namespace + method;
//            SoapObject sop = new SoapObject(namespace, method);
//            sop.addProperty("event_id",Teacher_view_event.eids);
////                        sop.addProperty("firstname",fn);
////                        sop.addProperty("lastname",fn);
////                        sop.addProperty("place",place);
////                        sop.addProperty("phone",phone);
////                        sop.addProperty("email",email);
////                        sop.addProperty("username", username);
////                        sop.addProperty("password", password);
//
//            SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//            env.setOutputSoapObject(sop);
//            HttpTransportSE hp = new HttpTransportSE(sh.getString("url", ""));
//            hp.call(soapAction, env);
//            String result = env.getResponse().toString();
//            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
//            if (result.equals("failed")) {
//                Toast.makeText(getApplicationContext(), " failed.!", Toast.LENGTH_LONG).show();
//            } else {
//                String[] temp1 = result.split("\\#");
////                            lid=temp1[0];
////                            Toast.makeText(getApplicationContext(), "Login success.!", Toast.LENGTH_LONG).show();
//                t1.setText(temp1[0]);
//
//
//            }
//        } catch (Exception e) {
//
//            Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_LONG).show();
//
//        }
//    }
//


}
}