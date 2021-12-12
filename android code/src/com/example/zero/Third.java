package com.example.zero;

import java.util.Locale;

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



public class Third extends Fragment {
	Context mContext;
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
    	View view=inflater.inflate(R.layout.fragment_third, container, false);
        // Inflate the layout for this fragment
    	
    	
    	view.findViewById(R.id.Button02).setOnClickListener(onClickListener);
    	view.findViewById(R.id.Button01).setOnClickListener(onClickListener2);
    	view.findViewById(R.id.button1).setOnClickListener(onClickListener3);
    	view.findViewById(R.id.button2).setOnClickListener(onClickListener4);
    	view.findViewById(R.id.button3).setOnClickListener(onClickListener5);
    	view.findViewById(R.id.button4).setOnClickListener(onClickListener6);
    	
        return view;
    }
    
    
    
     
    public View.OnClickListener onClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			 Intent intent = new Intent(getActivity(),Consent.class); 
		//	intent.putExtra("user", (CharSequence) new UserInfo(mEditName.getText().toString(), Integer.parseInt(mEditAge.getText().toString()), mEditEamil.getText().toString()));
			startActivity(intent);
		}
	};
	
	 public View.OnClickListener onClickListener2 = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(getActivity(),DisableAccount.class); 
			//	intent.putExtra("user", (CharSequence) new UserInfo(mEditName.getText().toString(), Integer.parseInt(mEditAge.getText().toString()), mEditEamil.getText().toString()));
				startActivity(intent);
			}
		};

		public View.OnClickListener onClickListener3 = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(getActivity(),ProgramVersion.class); 
			//	intent.putExtra("user", (CharSequence) new UserInfo(mEditName.getText().toString(), Integer.parseInt(mEditAge.getText().toString()), mEditEamil.getText().toString()));
				startActivity(intent);
			}
		};
		
		public View.OnClickListener onClickListener4 = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(getActivity(),Login.class); 
			//	intent.putExtra("user", (CharSequence) new UserInfo(mEditName.getText().toString(), Integer.parseInt(mEditAge.getText().toString()), mEditEamil.getText().toString()));
				startActivity(intent);
			}
		};
		public View.OnClickListener onClickListener5 = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(getActivity(),Menual.class); 
			//	intent.putExtra("user", (CharSequence) new UserInfo(mEditName.getText().toString(), Integer.parseInt(mEditAge.getText().toString()), mEditEamil.getText().toString()));
				startActivity(intent);
			}
		};
		public View.OnClickListener onClickListener6 = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(getActivity(),Login2.class); 
			//	intent.putExtra("user", (CharSequence) new UserInfo(mEditName.getText().toString(), Integer.parseInt(mEditAge.getText().toString()), mEditEamil.getText().toString()));
				startActivity(intent);
			}
		};
		
		
}

