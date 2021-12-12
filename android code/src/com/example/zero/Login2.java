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
import android.os.StrictMode;
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

public class Login2 extends Activity {

	EditText edtname, edtpass , edtbirth;
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
	

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle("로그인");
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		
		joinok = (Button)findViewById(R.id.ok);
	    joincancle = (Button)findViewById(R.id.cancle);
	    edtname = (EditText)findViewById(R.id.editname);
	    edtbirth = (EditText)findViewById(R.id.editbirth);
	    edtpass = (EditText)findViewById(R.id.editpass);
        check=(TextView)findViewById(R.id.textView1);
        data = new ArrayList<String>();
       
        joincancle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Login2.this.finish();
			}
		});

        	joinok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(edtname.getText().toString().equals("")||edtbirth.getText().toString().equals("")||
						edtpass.getText().toString().equals("")){
					Toast.makeText(Login2.this, "다 적어주세요.", Toast.LENGTH_SHORT).show();
					return;
					
				}
				runOnUiThread(new Runnable() {
					public void run() {
						
						String name = edtname.getText().toString();
						String birth = edtbirth.getText().toString();
						String password = edtpass.getText().toString();
						
						try{
							
							URL url = new URL(SERVER_ADDRESS + "/login.php?"
							+ "name=" + URLEncoder.encode(name,"UTF-8")
							+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
							+ "&password=" + URLEncoder.encode(password,"UTF-8"));
						
							url.openStream();
							
							String result = getXmlData("login.xml", "result");
							
							if(result.equals("1")) { //result �쒓렇媛믪씠 1�쇰븣 �깃났							
								
								Toast.makeText(Login2.this, "로그인 성공", Toast.LENGTH_SHORT).show();
								
								edtname.setText("");
								edtbirth.setText("");
								edtpass.setText("");
								
								Login2.this.finish();
								
								
								
								
								
								Intent intent = new Intent(Login2.this,Reservation_cancle.class);
								intent.putExtra("name",name);
								intent.putExtra("birth",birth);
								intent.putExtra("password",password);//마찬가지로 name을 putExtra합니다.					
								startActivity(intent);
								
								
								
							
								
								
							}
							else{
								Toast.makeText(Login2.this, "로그인 실패 ( 제대로 입력해 주세요.)", Toast.LENGTH_SHORT).show();
								
							}
						}
						catch(Exception e){
							Toast.makeText(Login2.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
							Log.e("Error", e.getMessage());	
						}// try catch 臾�醫낅즺
					} //run 醫낅즺
					
					
				});
				
			}
		});
	    
        
        
        
        
        
        
        
        
        
        
		
	} // onCreate 臾�醫낅즺 
	
	
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
				Toast.makeText(Login2.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
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
				Toast.makeText(Login2.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
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
