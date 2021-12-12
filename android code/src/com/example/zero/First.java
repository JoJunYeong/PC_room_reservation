package com.example.zero;

import java.util.Locale;

import com.example.zero.database.InfoClass;

import android.opengl.Visibility;
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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
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
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class First extends Fragment {
	Context mContext;
	View view;
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
    	view=inflater.inflate(R.layout.fragment_first, container, false);
        // Inflate the layout for this fragment
    	super.onCreate(savedInstanceState);
    	
    	
    	
    	
    	mContext = getActivity();
	    	
		DBinfo db = new DBinfo(mContext);
		setContentView(R.layout.fragment_first);
		//view.findViewById(R.id.Button02).setOnClickListener(onClickListener);//로그인화면 제거
		
		Button[] b = new Button[5];
		b[0] = (Button)view.findViewById(R.id.btn1);
		b[1] = (Button)view.findViewById(R.id.btn2);
		b[2] = (Button)view.findViewById(R.id.btn3);
		b[3] = (Button)view.findViewById(R.id.btn4);
		b[4] = (Button)view.findViewById(R.id.btn5);
		
		//각 라인별로 지역과 이동버튼 추가
		//DB확인 후에 표현
		//즐겨찾기를 모두 볼수있는 루틴
		int count = 0;
		for(int i=1;i<=db.searchDBLength();i++) {
			final InfoClass info = db.searchAllData(i);
			//Log.d("FIRST",info._id+info.location+info.uri+info.favorite);
			if(info.favorite.equals("true")) {
				b[count].setVisibility(View.VISIBLE);
				b[count].setText(info.location);//여기서 이름정보를 가져옵니다.
				b[count++].setOnClickListener(new OnClickListener() {				
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub					
					//	Toast.makeText(mContext,info.location+"과(와) 관련된 페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(First.this.mContext,PCreservation.class);
						intent.putExtra("back", true);
						intent.putExtra("number",info.number);
						intent.putExtra("name",info.location);//마찬가지로 name을 putExtra합니다.					
						startActivity(intent);
					}
				});					
			}					
		}
		    	
    	return view;    
    }    
    
	private void setContentView(int fragmentFirst) {
		// TODO Auto-generated method stub
		
	}
	
}
