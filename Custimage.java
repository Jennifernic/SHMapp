package com.example.attendance_management;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Custimage extends ArrayAdapter<String>  {

	private Activity context;       //for to get current activity context
	SharedPreferences sh;

	private String[] event;
	private String[] des;
	private String[] hour;

	private String[] imgs;
	private String[] sn;



	public Custimage(Activity context, String[] event, String[] des, String[] hour , String[] imgs, String[] sn ) {
		//constructor of this class to get the values from main_activity_class

		super(context, R.layout.cust_images, imgs);


		this.context = context;
		this.event = event;
		this.des = des;
		this.hour = hour;


		this.imgs = imgs;
		this.sn = sn;


	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		//override getView() method
		LayoutInflater inflater = context.getLayoutInflater();
		View listViewItem = inflater.inflate(R.layout.cust_images, null, true);


		//cust_list_view is xml file of layout created in step no.2
		ImageView im = (ImageView) listViewItem.findViewById(R.id.cardImage);
		TextView t1=(TextView)listViewItem.findViewById(R.id.cardTitle);


		t1.setText("Student name:  "+sn[position]+"\nEvent name:  "+event[position]+"\nDescription : "+des[position]+"\nHour:"+hour[position]);
		sh=PreferenceManager.getDefaultSharedPreferences(getContext());


		String pth = "http://"+sh.getString("ip", "")+":8080/SHM/images/"+imgs[position];
		pth = pth.replace("~", "");


		Log.d("-------------", pth);
		Picasso.with(context)
				.load(pth)
				.placeholder(R.drawable.ic_baseline_home_24)
				.error(R.drawable.ic_baseline_home_24 ).into(im);
		return  listViewItem;

	}

	private TextView setText(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}