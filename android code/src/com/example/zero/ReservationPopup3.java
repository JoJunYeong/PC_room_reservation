package com.example.zero;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;
import android.renderscript.RenderScript.RSErrorHandler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
//import android.annotation.SuppressLint;


public class ReservationPopup3  extends Activity {
	
	private final String TAG ="ReservationPopup3.class";

	EditText edtname, edtpass , edtbirth, edtseat;
	TextView check;
	    Button joinok , joincancle ;
	ArrayList<String> array_id;
	//ArrayAdapter<String> adapter;
	ListView list;
	private static final String SERVER_ADDRESS = "http://211.191.135.72"; //�쒕쾭 IP瑜��꾩뿭蹂�닔濡�.
//	String uri="http://192.168.1.100/test/test.xml"; //�먰븯��留곹겕 �묒냽 
	String tagname, content; //xml���쒓렇���댁슜���닿린 �꾪븳 蹂�닔
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	
	 TextView        m100MsCountTv = null;
	 TextView        m1000MsCountTv = null;
 	 Timer           mTimer     = new Timer();    
	 TimerTask       m100MsCountTimerTask = null;
	 TimerTask       m1000MsCountTimerTask = null;

	 String name , birth , password , seat ,number;
	 int q=1;
	 Intent intent;
	 String num = "1";
	 static final int NAPNOTI = 1;
	 NotificationManager mNotiManager;	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservation_popup3);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		mNotiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		setTitle("예약창");
		
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    Intent intent = getIntent();
	    number = intent.getStringExtra("number");
	    
		joinok = (Button)findViewById(R.id.ok);
	    joincancle = (Button)findViewById(R.id.cancle);
	    edtname = (EditText)findViewById(R.id.editname);
	    edtbirth = (EditText)findViewById(R.id.editbirth);
	    edtpass = (EditText)findViewById(R.id.editpass);
	    edtseat = (EditText)findViewById(R.id.editseat);
        check=(TextView)findViewById(R.id.textView1);
        data = new ArrayList<String>();
     
        joincancle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ReservationPopup3.this.finish();
			}
		});

        	joinok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(edtname.getText().toString().equals("")||edtbirth.getText().toString().equals("")||
						edtpass.getText().toString().equals("")||edtseat.getText().toString().equals("")){
				//if(false){
					Toast.makeText(ReservationPopup3.this, "다 적어주세요.", Toast.LENGTH_SHORT).show();
					return;
					
				}
				runOnUiThread(new Runnable() {
					public void run() {
						
						 name = edtname.getText().toString();
						 birth = edtbirth.getText().toString();
						 password = edtpass.getText().toString();
						 seat = edtseat.getText().toString();
						
						try{
							URL url = new URL(SERVER_ADDRESS + "/login.php?"
							+ "name=" + URLEncoder.encode(name,"UTF-8")
							+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
							+ "&password=" + URLEncoder.encode(password,"UTF-8")
							+ "&seat=" + URLEncoder.encode(seat,"UTF-8")
							+ "&number=" + URLEncoder.encode(number,"UTF-8")
							);
						
							url.openStream();
							
							String result = getXmlData("login.xml", "result");
							
							
							if(result.equals("1")) { //result �쒓렇媛믪씠 1�쇰븣 �깃났							
								q=1;
								try{
									URL url_2 = new URL(SERVER_ADDRESS + "/reservation_wait.php?"
											+ "name=" + URLEncoder.encode(name,"UTF-8")
											+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
											+ "&password=" + URLEncoder.encode(password,"UTF-8")
											+ "&seat=" + URLEncoder.encode(seat,"UTF-8")
											+ "&number=" + URLEncoder.encode(number,"UTF-8"));
									
									url_2.openStream();
									}
									catch(Exception e){
										Toast.makeText(ReservationPopup3.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
										Log.e("Error", e.getMessage());	
									}// try catch 臾�醫낅즺 */
																
								
								Intent intent;
								intent = new Intent(ReservationPopup3.this, BackgroundSearch.class);
								intent.putExtra("name", name);
								intent.putExtra("birth", birth);
								intent.putExtra("password", password);
								intent.putExtra("seat", seat); 
								intent.putExtra("num", num); 
								startService(intent);
								DBinfo.statement=0;//debug
											
								
								Toast.makeText(ReservationPopup3.this, "전송 완료", Toast.LENGTH_SHORT).show();
								
								
								edtname.setText("");
								edtbirth.setText("");
								edtpass.setText("");
								edtseat.setText("");
								
								finish();
								/*
								ReservationPopup3.this.finish();//////////////////////여기 종료시키고 앱을 끄지말아달라는 페이지로 이동
								Intent intent2;
								intent2 = new Intent(ReservationPopup3.this, DoNot.class);								
								startActivity(intent2);
								*/							
								/* 백그라운드 서비스 실행(BackgroundSearch.class) *//*
								Intent startIntent = new Intent(ReservationPopup3.this, BackgroundSearch.class);
			                    startIntent.setAction("com.example.zero.startbackground");
			                    startService(startIntent);	*/
			                    	                    
			                    
								
							}
							else{
								Toast.makeText(ReservationPopup3.this, "로그인 실패 ( 제대로 입력해 주세요.)", Toast.LENGTH_SHORT).show();
								
							}
						}
						catch(Exception e){
							Toast.makeText(ReservationPopup3.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
							Log.e("Error", e.getMessage());	
						}// try catch 臾�醫낅즺
					} //run 醫낅즺
					
					
				});
				
			}
		});
	    
        
        
        
        
        
        
        
        
        
        
		
	} // onCreate 臾�醫낅즺 
	
	////////////////////////////////////////////////////////////////// 서비스가 해야 될 부분 시작
	
	@SuppressLint("NewApi")
	public void fail()
	{
		

		runOnUiThread(new Runnable() {
			
			public void run() {
				Intent intent = new Intent(ReservationPopup3.this, Done.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				PendingIntent content = PendingIntent.getActivity(ReservationPopup3.this,0, intent,0);
				
				
				Notification noti = new Notification.Builder(ReservationPopup3.this)
				.setTicker("예약 실패")
				.setContentTitle("예약 실패")
				.setContentText("예약에 실패했습니다!")
				.setSmallIcon(R.drawable.napalram)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(),  R.drawable.harubang))
				.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
				.setVibrate(new long[] {1000,1000,500,500,200,200,200,200,200,200})
				.setLights(0xff00ff00, 500, 500)
				.setContentIntent(content)
				.build();
				
				mNotiManager.notify(ReservationPopup3.NAPNOTI,noti);
				
				
			}
		});
		
		try{///////////////////////////////////////////////////////////////예약 초기화
			URL url_4 = new URL(SERVER_ADDRESS + "/reservation _end.php?"
					+ "name=" + URLEncoder.encode(name,"UTF-8")
					+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
					+ "&password=" + URLEncoder.encode(password,"UTF-8")
					+ "&seat=" + URLEncoder.encode(seat,"UTF-8"));
			
			url_4.openStream();
			}
			catch(Exception e){
				Toast.makeText(ReservationPopup3.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				Log.e("Error", e.getMessage());	
			}// try catch 臾�醫낅즺
		
	}
	
	
	@SuppressLint("NewApi")
	public void success()
	{
		
	
		runOnUiThread(new Runnable() {
			
			public void run() {
				Intent intent = new Intent(ReservationPopup3.this, Done.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				PendingIntent content = PendingIntent.getActivity(ReservationPopup3.this,0, intent,0);
				
				
				Notification noti = new Notification.Builder(ReservationPopup3.this)
				.setTicker("예약 성공")
				.setContentTitle("예약 성공")
				.setContentText("자리번호: "+ seat)
				.setSubText("이름: "+name + "생년월일: "+birth+ "자리번호: "+seat)
				.setSmallIcon(R.drawable.napalram)
				.setLargeIcon(BitmapFactory.decodeResource(getResources(),  R.drawable.harubang))
				.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
				.setVibrate(new long[] {1000,1000,500,500,200,200,200,200,200,200})
				.setLights(0xff00ff00, 500, 500)
				.setContentIntent(content)
				.build();
				
				mNotiManager.notify(ReservationPopup3.NAPNOTI,noti);
				
				
			}
		});
		
		try{///////////////////////////////////////////////////////////////예약 초기화
			URL url_4 = new URL(SERVER_ADDRESS + "/reservation_end.php?"
					+ "name=" + URLEncoder.encode(name,"UTF-8")
					+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
					+ "&password=" + URLEncoder.encode(password,"UTF-8")
					+ "&seat=" + URLEncoder.encode(seat,"UTF-8"));
			
			url_4.openStream();
			}
			catch(Exception e){
				Toast.makeText(ReservationPopup3.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				Log.e("Error", e.getMessage());	
			}// try catch 臾�醫낅즺
		
	}
	
	private void reservation_search()
	{		
		try{
			URL url_3 = new URL(SERVER_ADDRESS + "/reservation_search.php?"
					+ "name=" + URLEncoder.encode(name,"UTF-8")
					+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
					+ "&password=" + URLEncoder.encode(password,"UTF-8")
					+ "&seat=" + URLEncoder.encode(seat,"UTF-8"));
			
			url_3.openStream();
			}
			catch(Exception e){
				Toast.makeText(ReservationPopup3.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				Log.e("Error", e.getMessage());	
			}// try catch 臾�醫낅즺
		String result2 = getXmlData("reservation_search.xml", "result");
		Log.d("reservation",result2);
		if(result2.equals("1")) {

			try{///////////////////////////////////////////////////////////////예약 성공
				URL url_5 = new URL(SERVER_ADDRESS + "/reservation_success.php?"
						+ "name=" + URLEncoder.encode(name,"UTF-8")
						+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
						+ "&password=" + URLEncoder.encode(password,"UTF-8")
						+ "&seat=" + URLEncoder.encode(seat,"UTF-8"));
				
				url_5.openStream();
				}
				catch(Exception e){
					Log.e("Error", e.getMessage());	
				}// try catch 臾�醫낅즺
			String result3 = getXmlData("reservation_success.xml", "result");
			if(result3.equals("1")){
			success();
			q=2;
			}
			
			
			try{///////////////////////////////////////////////////////////////예약 실패
				URL url_6 = new URL(SERVER_ADDRESS + "/reservation_fail.php?"
						+ "name=" + URLEncoder.encode(name,"UTF-8")
						+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
						+ "&password=" + URLEncoder.encode(password,"UTF-8")
						+ "&seat=" + URLEncoder.encode(seat,"UTF-8"));
				
				url_6.openStream();
				}
				catch(Exception e){
					Toast.makeText(ReservationPopup3.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());	
				}// try catch 臾�醫낅즺
			String result4 = getXmlData("reservation_fail.xml", "result");
			if(result4.equals("1")){
			fail();
			/*
			try{///////////////////////////////////////////////////////////////예약 초기화
				URL url_4 = new URL(SERVER_ADDRESS + "/reservation_end.php?"
						+ "name=" + URLEncoder.encode(name,"UTF-8")
						+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
						+ "&password=" + URLEncoder.encode(password,"UTF-8")
						+ "&seat=" + URLEncoder.encode(seat,"UTF-8"));
				
				url_4.openStream();
				}
				catch(Exception e){
					Log.e("Error", e.getMessage());	
				}// try catch 臾�醫낅즺
			
			*/
			
		//	Toast.makeText(ReservationPopup3.this, "예약 완료", Toast.LENGTH_SHORT).show();
			
			q=2;}
			
		}
		
		
		
		if(q==1)///////////////////////////////////////////////////////재귀함수 호출로 예약확인을 무한루프를 돌린다 
		{
			Handler mHandler = new Handler();
			mHandler.postDelayed(new Runnable() {
			         public void run() {
			        	 reservation_search();  
			         }
			 }, 1000);	
		}		
		
			
		
		
		
	}
	
	
	
	
	////////////////////////////////////////////////////////서비스가 해야 할 일 끝
	
	
	
	

	
	
	
	 private String getXmlData(String filename, String str){
			String rss = SERVER_ADDRESS + "/";
			String ret = "";
			
			try{
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp = factory.newPullParser();
				URL server = new URL(rss + filename);
				InputStream is = server.openStream();
				xpp.setInput(is, "UTF-8");
				
				int eventType = xpp.getEventType();
				
				while(eventType != XmlPullParser.END_DOCUMENT) {
					if(eventType == XmlPullParser.START_TAG) {
						if(xpp.getName().equals(str)) {
							ret = xpp.nextText();
						}
					}
					eventType = xpp.next();
					
				}
			} catch(Exception e) {
				Log.e("Error", e.getMessage());
			}
			
			return ret;
		}
		
	
	  private ArrayList<String> getXmlDataList(String filename, String str) { //占승그곤옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쌨아울옙占쏙옙占쏙옙占쏙옙 ArrayList<String>占쏙옙 占쏙옙占쏙옙
			String rss = SERVER_ADDRESS + "/";
			ArrayList<String> ret = new ArrayList<String>();
			
			try { //XML 占식쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp = factory.newPullParser();
				URL server = new URL(rss + filename);
				InputStream is = server.openStream();
				xpp.setInput(is, "UTF-8");
				
				int eventType = xpp.getEventType();
				
				while(eventType != XmlPullParser.END_DOCUMENT) {
					if(eventType == XmlPullParser.START_TAG) {
						if(xpp.getName().equals(str)) { //占승깍옙 占싱몌옙占쏙옙 str 占쏙옙占쌘곤옙占쏙옙 占쏙옙占쏙옙 占쏙옙占�
							ret.add(xpp.nextText());
						}
					}
					eventType = xpp.next();
				}
			} catch(Exception e) {
				Log.e("Error", e.getMessage());
			}
			
			return ret;
	    }
	  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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


