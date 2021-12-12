package com.example.zero;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
//import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.content.Context;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class Jakjeon_dong extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jakjeon_dong);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Button bt1 = (Button)findViewById(R.id.button1);
		bt1.setOnClickListener(this);
		Button bt2 = (Button)findViewById(R.id.button2);
		bt2.setOnClickListener(this);
		Button bt3 = (Button)findViewById(R.id.button3);
		bt3.setOnClickListener(this);
		Button bt4 = (Button)findViewById(R.id.button4);
		bt4.setOnClickListener(this);
		Button bt5 = (Button)findViewById(R.id.button5);
		bt5.setOnClickListener(this);
		
		setTitle("인천시-계양구-작전동");
		
		
	}

	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.button1:
			Intent intent1 = new Intent(this,PCreservation.class);
			intent1.putExtra("name","pc방1(계양-작전)");//여긴 이름을 보냅니다.
			startActivity(intent1);			
			break;

		case R.id.button2:
			Intent intent2 = new Intent(this,PCreservation.class);
			intent2.putExtra("name","pc방2(계양-작전)");//여긴 이름을 보냅니다.
			startActivity(intent2);		
			break;
				
		case R.id.button3:
			Intent intent3 = new Intent(this,PCreservation.class);
			intent3.putExtra("name","pc방3(계양-작전)");//여긴 이름을 보냅니다.
			startActivity(intent3);		
			break;
				
		case R.id.button4:
			Intent intent4 = new Intent(this,PCreservation.class);
			intent4.putExtra("name","pc방4(계양-작전)");//여긴 이름을 보냅니다.
			startActivity(intent4);		
			break;
		case R.id.button5:
			Intent intent5 = new Intent(this,PCreservation.class);
			intent5.putExtra("name","pc방5(계양-작전)");//여긴 이름을 보냅니다.
			startActivity(intent5);		
			break;
		}		
		
		
		
		
		
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jakjeon_dong, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// NavUtils.navigateUpFromSameTask(this); 
			finish();
			return true;
	}
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
