package com.example.zero;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class Intro extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        getActionBar().hide();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
        	public void run(){
        		finish();
        	}
        },1500);//�ڵ鷯
    }
}
