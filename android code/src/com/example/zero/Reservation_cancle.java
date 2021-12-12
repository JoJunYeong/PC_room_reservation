package com.example.zero;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zero.PCreservation.WebClient;
import com.example.zero.database.InfoClass;

public class Reservation_cancle extends Activity implements OnClickListener {

	
	WebView webview;
	private Menu menu;// change favorite button
	boolean favorite = true;// favorite statement
	DBinfo db;//use DB
	InfoClass info = null;
	String number = "";
	String name = "";
	String Pc_name = "";
	String Pc_num = "";
	String birth = "";
	String time_table = "";
	String seat = "";
	String reservation_check = "";
	String reservation_check2 = "";
	String reservation_conform = "";
	String reservation_conform2 = "";
	String password = "";
//	String name = "";//각 영화관별 구별
	boolean back = false;//바로가기 검색후 구별
	String num = "2";
	
	
	TextView textView;
	private static final String SERVER_ADDRESS = "http://211.191.135.72";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservation_cancle);
		getActionBar().setDisplayHomeAsUpEnabled(true);		
		
		Intent intent = getIntent();
		birth = intent.getStringExtra("birth");
		name = intent.getStringExtra("name");
		password = intent.getStringExtra("password");//여기서 이름을 받아옵니다
		
		setTitle("예약취소 창");
		
		//////////////////////////////////////////////////////서버에서 받아와야 되는 것 : seat , 예약결과 , 예약일시
		//////////////////////////////////////////////////////내부서버에서 받아와야 되는 것 : 피씨방 이름
		
		
		
		try{//////////////////////////////예약 신청 / 취소
			URL url = new URL(SERVER_ADDRESS + "/reservation_check2.php?"
					+ "name=" + URLEncoder.encode(name,"UTF-8")
					+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
					);					
			url.openStream();		
			String result = getXmlData("reservation_check2.xml", "result");
			reservation_check2 = result;
		} catch(Exception e) {
			Toast.makeText(Reservation_cancle.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			Log.e("Error", e.getMessage());
		}
		
		
		
		try{//////////////////////////////예약 결과
			URL url = new URL(SERVER_ADDRESS + "/reservation_check.php?"
					+ "name=" + URLEncoder.encode(name,"UTF-8")
					+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
					);					
			url.openStream();		
			String result = getXmlData("reservation_check.xml", "result");
			reservation_check = result;
		} catch(Exception e) {
			Log.e("Error", e.getMessage());
		}
		
		
		try{//////////////////////////////seat_check
			URL url = new URL(SERVER_ADDRESS + "/seat_check.php?"
					+ "name=" + URLEncoder.encode(name,"UTF-8")
					+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
					);					
			url.openStream();		
			String result = getXmlData("seat_check.xml", "result");
			seat = result;
		} catch(Exception e) {
			Log.e("Error", e.getMessage());
		}
		
		
		try{//////////////////////////////예약 일시
			URL url = new URL(SERVER_ADDRESS + "/time_table.php?"
					+ "name=" + URLEncoder.encode(name,"UTF-8")
					+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
					);					
			url.openStream();		
			String result = getXmlData("time_table.xml", "result");
			time_table = result;
		} catch(Exception e) {
			Log.e("Error", e.getMessage());
		}
		
		
		try{//////////////////////////////PC방 번호
			URL url = new URL(SERVER_ADDRESS + "/PC_num.php?"
					+ "name=" + URLEncoder.encode(name,"UTF-8")
					+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
					);					
			url.openStream();		
			String result = getXmlData("PC_num.xml", "result");
			Pc_num = result;
		} catch(Exception e) {
			Log.e("Error", e.getMessage());
		}
		////////////////////////////////////////////////////////////////////////////////////////PC방 이름 정리시작 
		number=Pc_num;
		if(Pc_num.equals("1")) Pc_name="PC방1";
		else if(Pc_num.equals("2")) Pc_name = "PC방2";
		else if(Pc_num.equals("3")) Pc_name = "PC방3";
		else if(Pc_num.equals("4")) Pc_name = "PC방4";
		else if(Pc_num.equals("5")) Pc_name = "PC방5";
		
		
		
		
		
		////////////////////////////////////////////////////////////////////////////////////////PC방 이름 정리끝 
		
		if(reservation_check2.equals("1")) reservation_conform2="예약신청";
		else {
			Toast.makeText(Reservation_cancle.this, "예약기록이 없습니다.", Toast.LENGTH_SHORT).show();
			finish();
	}
		if(reservation_check.equals("1")) reservation_conform="성공";
		else {
			Toast.makeText(Reservation_cancle.this, "예약기록이 없습니다.", Toast.LENGTH_SHORT).show();
			finish();
	}
		TextView testView6 = (TextView)findViewById(R.id.textView1);
		testView6.setText(reservation_conform);
		TextView testView5 = (TextView)findViewById(R.id.textView2);
		testView5.setText(name);
		TextView testView4 = (TextView)findViewById(R.id.textView3);
		testView4.setText(birth);
		TextView testView3 = (TextView)findViewById(R.id.textView4);
		testView3.setText(Pc_name);
		TextView testView2 = (TextView)findViewById(R.id.textView5);
		testView2.setText(seat);
		TextView testView = (TextView)findViewById(R.id.textView7);
		testView.setText(time_table);
		
		
		
		Button bt1 = (Button)findViewById(R.id.ok);
		bt1.setOnClickListener(this);
		Button bt2 = (Button)findViewById(R.id.cancle);
		bt2.setOnClickListener(this);
		
		
		
		

		//webview.loadUrl("http://www.google.com");///////////////////////////////주소넣는 곳
	//	webview.loadUrl(info.uri);//DB에등록된 영화관 이동
		
		
		
		setTitle("예약 취소창");
		
	}
	
	
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.ok:
			try{//////////////////////////////예약 일시
				URL url = new URL(SERVER_ADDRESS + "/reservation_cancle.php?"
						+ "name=" + URLEncoder.encode(name,"UTF-8")
						+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
						+ "&password=" + URLEncoder.encode(password,"UTF-8")
						+ "&number=" + URLEncoder.encode(number,"UTF-8")
						);					
				url.openStream();		
			//	String result = getXmlData("reservation_cancle.xml", "result");
			} catch(Exception e) {
				Log.e("Error", e.getMessage());
			}
			Intent intent;
			intent = new Intent(Reservation_cancle.this, BackgroundSearch.class);
			intent.putExtra("name", name);
			intent.putExtra("birth", birth);
			intent.putExtra("password", password);
			intent.putExtra("seat", seat); 
			intent.putExtra("num", num); 
			startService(intent);
			Toast.makeText(Reservation_cancle.this, "전송 성공", Toast.LENGTH_SHORT).show();
			DBinfo.statement=0;
			Handler mHandler = new Handler(Looper.getMainLooper());////////////////////////////////예약에서 은근히 중요한 부분을 차지하는 수시검색
			mHandler.postDelayed(new Runnable() {
			         public void run() {
			        	 finish();  
			         }
			 }, 1000);
			
			
			
			
			break;
		case R.id.cancle:
			finish();		
			break;
			
		}
	}

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
		getMenuInflater().inflate(R.menu.reservation_check, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (item.getItemId()) {
		case android.R.id.home:
			// NavUtils.navigateUpFromSameTask(this); 
			
		//	Intent i = new Intent(Reservation_cancle.this,ZERO.class);
		//	startActivity(i);
			finish();
			return true;
		}		

		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	

	
	
	}