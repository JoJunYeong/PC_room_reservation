package com.example.zero;


import java.sql.Date;
import java.util.Calendar;

import com.example.zero.WebView_1.WebClient;
import com.example.zero.database.InfoClass;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
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
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class PCreservation extends Activity implements OnClickListener {
	
	WebView webview;
	private Menu menu;// change favorite button
	boolean favorite = true;// favorite statement
	DBinfo db;//use DB
	InfoClass info = null;
	String number = "";
	String name = "";//각 영화관별 구별
	boolean back = false;//바로가기 검색후 구별
	long millis = System.currentTimeMillis();
	TextView textView;

	
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pcreservation);
		getActionBar().setDisplayHomeAsUpEnabled(true);			
		Toast.makeText(this, "해당PC방 사정으로 모든 좌석이 보이지 않을수 있습니다.", Toast.LENGTH_SHORT).show();
		
	
		
		String time_h=DateFormat.format("hh", millis).toString();
		String time_m=DateFormat.format("mm(ddd)", millis).toString();
		String time_s=DateFormat.format("ss", millis).toString();
		String time_a=DateFormat.format("a", millis).toString();
	//	Toast.makeText(this, time_m , Toast.LENGTH_SHORT).show();
		
		if(time_a.equals("오후"))
		{
			if(time_h.equals("11"))
			{
				  AlertDialog.Builder gg=new AlertDialog.Builder(this);
			      gg.setTitle("공지사항");
			      gg.setMessage("매일 밤 11시부터 아침8시까지 서버안정화가 진행중이므로 이용에 제한이 있을 수 있습니다.");//////////업데이트 날짜
			      gg.setIcon(R.drawable.ic_launcher);
			      gg.setPositiveButton("확인",null);
			      gg.show();
				  
			}
		}
		if(time_a.equals("오전"))
		{
			if(time_h.equals("12"))
			{
				AlertDialog.Builder gg=new AlertDialog.Builder(this);
			      gg.setTitle("공지사항");
			      gg.setMessage("매일 밤 11시부터 아침8시까지 서버안정화가 진행중이므로 이용에 제한이 있을 수 있습니다.");//////////업데이트 날짜
			      gg.setIcon(R.drawable.ic_launcher);
			      gg.setPositiveButton("확인",null);
			      gg.show();
			}
			else if(time_h.equals("01"))
			{
				AlertDialog.Builder gg=new AlertDialog.Builder(this);
			      gg.setTitle("공지사항");
			      gg.setMessage("매일 밤 11시부터 아침8시까지 서버안정화가 진행중이므로 이용에 제한이 있을 수 있습니다.");//////////업데이트 날짜
			      gg.setIcon(R.drawable.ic_launcher);
			      gg.setPositiveButton("확인",null);
			      gg.show();
			}
			else if(time_h.equals("02"))
			{
				AlertDialog.Builder gg=new AlertDialog.Builder(this);
			      gg.setTitle("공지사항");
			      gg.setMessage("매일 밤 11시부터 아침8시까지 서버안정화가 진행중이므로 이용에 제한이 있을 수 있습니다.");//////////업데이트 날짜
			      gg.setIcon(R.drawable.ic_launcher);
			      gg.setPositiveButton("확인",null);
			      gg.show();
			}
			else if(time_h.equals("03"))
			{
				AlertDialog.Builder gg=new AlertDialog.Builder(this);
			      gg.setTitle("공지사항");
			      gg.setMessage("매일 밤 11시부터 아침8시까지 서버안정화가 진행중이므로 이용에 제한이 있을 수 있습니다.");//////////업데이트 날짜
			      gg.setIcon(R.drawable.ic_launcher);
			      gg.setPositiveButton("확인",null);
			      gg.show();
			}	
			else if(time_h.equals("04"))
			{
				AlertDialog.Builder gg=new AlertDialog.Builder(this);
			      gg.setTitle("공지사항");
			      gg.setMessage("매일 밤 11시부터 아침8시까지 서버안정화가 진행중이므로 이용에 제한이 있을 수 있습니다.");//////////업데이트 날짜
			      gg.setIcon(R.drawable.ic_launcher);
			      gg.setPositiveButton("확인",null);
			      gg.show();
			}	
			else if(time_h.equals("05"))
			{
				AlertDialog.Builder gg=new AlertDialog.Builder(this);
			      gg.setTitle("공지사항");
			      gg.setMessage("매일 밤 11시부터 아침8시까지 서버안정화가 진행중이므로 이용에 제한이 있을 수 있습니다.");//////////업데이트 날짜
			      gg.setIcon(R.drawable.ic_launcher);
			      gg.setPositiveButton("확인",null);
			      gg.show();
			}	
			else if(time_h.equals("06"))
			{
				AlertDialog.Builder gg=new AlertDialog.Builder(this);
			      gg.setTitle("공지사항");
			      gg.setMessage("매일 밤 11시부터 아침8시까지 서버안정화가 진행중이므로 이용에 제한이 있을 수 있습니다.");//////////업데이트 날짜
			      gg.setIcon(R.drawable.ic_launcher);
			      gg.setPositiveButton("확인",null);
			      gg.show();
			}	
			else if(time_h.equals("07"))
			{
				AlertDialog.Builder gg=new AlertDialog.Builder(this);
			      gg.setTitle("공지사항");
			      gg.setMessage("매일 밤 11시부터 아침8시까지 서버안정화가 진행중이므로 이용에 제한이 있을 수 있습니다.");//////////업데이트 날짜
			      gg.setIcon(R.drawable.ic_launcher);
			      gg.setPositiveButton("확인",null);
			      gg.show();
			}	
				
		}

		
		
		
		
		//intent 결과 등록
		Intent intent = getIntent();
		back = intent.getBooleanExtra("back", false);
		name = intent.getStringExtra("name");//여기서 이름을 받아옵니다
		
		//search DB
		db = new DBinfo(this);
		info = db.searchAllData(db.searchID(name));
		number = info.number;
		
		webview = (WebView)findViewById(R.id.webview);
		webview.setWebViewClient(new WebClient()); // 응룡프로그램에서 직접 url 처리
		WebSettings set = webview.getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);
		//webview.loadUrl("http://www.google.com");///////////////////////////////주소넣는 곳
		webview.loadUrl(info.uri);//DB에등록된 영화관 이동
		
		
		
		
		
		setTitle(name);//액션바 이름 수정	
	}
		
		
		
	  class WebClient extends WebViewClient {
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            view.loadUrl(url);
	            return true;
	        }
	    }
	  
	  
	  
	 
	
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
		
		getMenuInflater().inflate(R.menu.pcreservation, menu);
				
		this.menu = menu;
		
		//디비에서 즐겨찾기인지 아닌지 검색후 버튼 표현
		favorite = db.searchFavorite(name);		
		if(favorite) menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_menu_like)); 
		else menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_menu_public));

				
		
	    return super.onCreateOptionsMenu(menu);
	}


	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case android.R.id.home:
			// NavUtils.navigateUpFromSameTask(this); 
		//	if(!back) {
				finish();
				return true;
		//	}
		//	Intent i = new Intent(PCreservation.this,ZERO.class);
		//	startActivity(i);
		//	finish();
		//	return true;
		}		

      switch (item.getItemId()) {
           case R.id.menu_refresh:
        	//   finish();
        	//   Intent intent2 = new Intent(PCreservation.this,PCreservation.class);
        	//   intent2.putExtra("name",name);
        	//   intent2.putExtra("back",back);
       		//startActivity(intent2);
        	   webview.loadUrl(info.uri);
                break;
            case R.id.menu_reservation:
            	Intent intent = new Intent(PCreservation.this,ReservationPopup3.class);
            	intent.putExtra("number",number);
        		startActivity(intent);
        		
              break ;
          case R.id.menu_like:
        	  
        	  //즐겨찾기 활성화
        	  if(favorite) {
        		  //즐겨찾기 인경우            	  
        		  //버튼활성화 터치시 버튼 비활성화(DB수정)
        		  menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_menu_public));
        		  favorite=false;
        		  Toast.makeText(this, "즐겨찾기를 해제합니다.", Toast.LENGTH_SHORT).show();
        		  db.modifyFavorite(name,String.valueOf(favorite));
        	  }
        	  else {
        		  //즐겨찾기 아닌경우            	  
        		  //버튼 비활성화 터치시 활성화(DB수정)      		          		  
        		  menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_menu_like));
        		  favorite=true;
        		  Toast.makeText(this, "즐겨찾기를 추가합니다.", Toast.LENGTH_SHORT).show();
        		  db.modifyFavorite(name,String.valueOf(favorite));
        	  }       	          	          	  
        	  
        	  break;        	  
            default:
                return super.onOptionsItemSelected(item);
        }
       
      return true;
        
        
    }






	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	
	


	
	
}

