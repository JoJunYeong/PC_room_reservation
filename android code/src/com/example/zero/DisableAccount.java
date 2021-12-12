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
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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




import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
 



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 




import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;















import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
 








import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;








import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class DisableAccount extends Activity {

	EditText edtname, edtbirth, edtpass;
	Button joinok , joincancle;
	ListView list;
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	 
	 private static final String SERVER_ADDRESS = "http://211.191.135.72";
	 
	 
	    /** Called when the activity is first created. */
	   
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_disable_account);
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        setTitle("회원탈퇴");
	        
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    
	    joinok = (Button)findViewById(R.id.ok);
	    joincancle = (Button)findViewById(R.id.cancle);
	    edtname = (EditText)findViewById(R.id.editname);
	    edtbirth = (EditText)findViewById(R.id.editbirth);
	    edtpass = (EditText)findViewById(R.id.editpass);
	    data = new ArrayList<String>();
       
	    
	    
	    joincancle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DisableAccount.this.finish();
			}
		});
	    
	    joinok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(edtname.getText().toString().equals("")||edtbirth.getText().toString().equals("")||
						edtpass.getText().toString().equals("")){
					Toast.makeText(DisableAccount.this, "모두 적어주세요.", Toast.LENGTH_SHORT).show();
					return;
					
				}
				runOnUiThread(new Runnable() {
					public void run() {
						
						String name = edtname.getText().toString();
						String birth = edtbirth.getText().toString();
						String password = edtpass.getText().toString();
						
						try{
							URL url = new URL(SERVER_ADDRESS + "/disable_account.php?"
							+ "name=" + URLEncoder.encode(name,"UTF-8")
							+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
							+ "&password=" + URLEncoder.encode(password,"UTF-8"));
							
							url.openStream();
							
							
							
							String result = getXmlData("disableresult.xml", "result");
							
							
							if(result.equals("1")) { //result �쒓렇媛믪씠 1�쇰븣 �깃났
								
									
									
									try{
									URL url2 = new URL(SERVER_ADDRESS + "/disable_account2.php?"
											+ "name=" + URLEncoder.encode(name,"UTF-8")
											+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
											+ "&password=" + URLEncoder.encode(password,"UTF-8"));
											
									url2.openStream();
									
									String result2 = getXmlData("disableresult2.xml", "result");
							
									if(result2.equals("1")){
										
										try{
											URL url3 = new URL(SERVER_ADDRESS + "/disable_success.php?"
													+ "name=" + URLEncoder.encode(name,"UTF-8")
													+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
													+ "&password=" + URLEncoder.encode(password,"UTF-8"));
													
											url3.openStream();
											
											String result3 = getXmlData("disable_success.xml", "result");
										if(result3.equals("0"))
										{
										Toast.makeText(DisableAccount.this, "탈퇴완료!", Toast.LENGTH_SHORT).show();
									
										edtname.setText("");
										edtbirth.setText("");
										edtpass.setText("");
									
										DisableAccount.this.finish();
										}
										else
											Toast.makeText(DisableAccount.this, "시스템 오류. 탈퇴실패", Toast.LENGTH_SHORT).show();
										
										}
										catch(Exception e){
											Toast.makeText(DisableAccount.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
											Log.e("Error", e.getMessage());	}
										
										
										
									}
									else
										Toast.makeText(DisableAccount.this, "시스템 오류. 탈퇴실패", Toast.LENGTH_SHORT).show();
									}
									catch(Exception e){
										Toast.makeText(DisableAccount.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
										Log.e("Error", e.getMessage());	}
								
							}
							else
								Toast.makeText(DisableAccount.this, "입력한 정보가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
							
						}
						catch(Exception e){
							Toast.makeText(DisableAccount.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
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
