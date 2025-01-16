package com.example.attendance_management;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Login extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2,b3;
    String username,password;
    SharedPreferences sh;
    TextView t1;

    String namespace = "http://dbcon/";
    String method = "";
    String soapAction = "";
    String url = "";
    public static String lid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_login);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText)findViewById(R.id.uname);
        e2=(EditText)findViewById(R.id.pwd);
        b1=(Button) findViewById(R.id.btn_lgn);
        try {
            if(android.os.Build.VERSION.SDK_INT>9)
            {
                StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

        } catch (Exception e) {
// TODO: handle exception
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=e1.getText().toString();
                password=e2.getText().toString();
                if (username.equalsIgnoreCase(""))
                {
                    e1.setError("");
                    e1.setFocusable(true);
                }
                else if(password.equalsIgnoreCase(""))
                {
                    e2.setError("");
                    e2.setFocusable(true);
                }


                else{
                    try {
                        method="login";
                        soapAction = namespace + method;
                        SoapObject sop = new SoapObject(namespace, method);
                        sop.addProperty("username", username);
                        sop.addProperty("password", password);

//                        Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();


                        SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        env.setOutputSoapObject(sop);
                        HttpTransportSE hp = new HttpTransportSE(sh.getString("url",""));
                        hp.call(soapAction, env);
                        String result = env.getResponse().toString();
                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                        if (result.equals("failed")) {
                            Toast.makeText(getApplicationContext(), "Login failed.!", Toast.LENGTH_LONG).show();
                        } else {
                            String[] temp1 = result.split("\\#");
                            lid=temp1[0];
                            Toast.makeText(getApplicationContext(), "Login success.!", Toast.LENGTH_LONG).show();
                            SharedPreferences.Editor ed = sh.edit();
                            ed.putString("logid", temp1[0]);
                            ed.putString("usertype", temp1[1]);
                            ed.commit();
                            if(sh.getString("usertype","").equalsIgnoreCase("student")){
                                startActivity(new Intent(getApplicationContext(), Homes.class));

                            }else if(sh.getString("usertype","").equalsIgnoreCase("teacher")){
                                startActivity(new Intent(getApplicationContext(), Homes.class));


                            }
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
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit  :")
                .setMessage("Are you sure you want to exit..?")
                .setPositiveButton("Yes",new DialogInterface.OnClickListener()
                {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1)
                    {
                        // TODO Auto-generated method stub
                        Intent i=new Intent(Intent.ACTION_MAIN);
                        i.addCategory(Intent.CATEGORY_HOME);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }
                }).setNegativeButton("No",null).show();

    }




}