package com.example.attendance_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Calendar;

public class Teacher_add_event extends AppCompatActivity implements AdapterView.OnItemClickListener {
    EditText e1, e2, e3, e4,e5,e6;
    Button b1;
    RadioButton r1,r2,r3;
    String event, weekdays, venue,date,hour,c;
    String namespace = "http://dbcon/";
    String method = "";
    String soapAction = "";
    String url = "";
    SharedPreferences sh;
    DatePickerDialog datePickerDialog;
    String[] events, time, dte,ven,cat,hr, eid,val;
    ListView l;
    public static String logid, eids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen


        setContentView(R.layout.activity_teacher_add_event);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        logid = sh.getString("logid", "");
        l = (ListView) findViewById(R.id.Teacher_vevent);
        l.setOnItemClickListener(this);

        e1 = (EditText) findViewById(R.id.event);
        e2 = (EditText) findViewById(R.id.time);
        e3 = (EditText) findViewById(R.id.date);
        e4 =(EditText) findViewById(R.id.hour);
        r1=findViewById(R.id.rb1);
        r2=findViewById(R.id.rb2);
        r3=findViewById(R.id.rb3);

        e5 =(EditText) findViewById(R.id.venue);

        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Teacher_add_event.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                e3.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });


        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

        } catch (Exception e) {
// TODO: handle exception
        }
        url = sh.getString("url", "");
        b1 = (Button) findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                event = e1.getText().toString();
                weekdays = e2.getText().toString();
                date = e3.getText().toString();
                hour = e4.getText().toString();
                venue = e5.getText().toString();
                if (r1.isChecked()) {
                    c = "flexible";

                } else if (r2.isChecked()) {
                    c = "fixed";
                } else {
                    c = "onetime";
                }


                if (event.equalsIgnoreCase("")) {
                    e1.setError("");
                    e1.setFocusable(true);
                } else if (weekdays.equalsIgnoreCase("")) {
                    e2.setError("");
                    e2.setFocusable(true);
                } else if (date.equalsIgnoreCase("")) {
                    e3.setError("");
                    e3.setFocusable(true);
                }

//                Toast.makeText(Registration.this, "", Toast.LENGTH_SHORT).show();
                else {

                    try {
                        method = "Teacher_add_event";
                        soapAction = namespace + method;
                        SoapObject sop = new SoapObject(namespace, method);
                        sop.addProperty("logid", Login.lid);
                        sop.addProperty("Event", event);
                        sop.addProperty("Time", weekdays);
                        sop.addProperty("date", date);
                        sop.addProperty("hour", hour);
                        sop.addProperty("Venue", venue);
                        sop.addProperty("Category", c);


                        SoapSerializationEnvelope env = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        env.setOutputSoapObject(sop);
                        Toast.makeText(getApplicationContext(), "uuuuuuu", Toast.LENGTH_SHORT).show();
                        HttpTransportSE hp = new HttpTransportSE(sh.getString("url", ""));
                        hp.call(soapAction, env);
                        // Toast.makeText(getApplicationContext(), "kkkkkkkkk", Toast.LENGTH_SHORT).show();

                        String result = env.getResponse().toString();
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        if (result.equals("failed")) {
                            Toast.makeText(getApplicationContext(), "Post Not Added.!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "POst Added Successfully.!", Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor ed = sh.edit();
                            ed.putString("logid", result);
                            ed.commit();
                            startActivity(new Intent(getApplicationContext(), Teacher_add_event.class));
                        }
                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), e + "", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        try {
            Toast.makeText(getApplicationContext(), "Haiiii", Toast.LENGTH_LONG).show();
            method = "teacher_view_event";
            soapAction = namespace + method;
            SoapObject sop = new SoapObject(namespace, method);
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


                        val[z] = "Event : " + events[z] + "\nTime : " + time[z] + "\nDate : " + dte[z]+ "\nVenue : " + ven[z]+ "\nCategory : " + cat[z] + "\nHours : " + hr[z]+  "\n";
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
        final CharSequence[] items1 = {"view request", "Cancel"};

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Teacher_add_event.this);
        builder.setItems(items1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items1[item].equals("view request"))
                {
                    startActivity(new Intent(getApplicationContext(),Teacher_view_request.class));
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
