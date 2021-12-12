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

import android.app.FragmentTransaction;

import android.os.Bundle;

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

import android.app.FragmentTransaction;

import android.os.Bundle;

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


@SuppressWarnings("deprecation")
public class CreateAccount extends Activity  {
	

	EditText edtname, edtbirth, edtpass,edtpass2;
	Button joinok , joincancle;
	ListView list;
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	 
	 private static final String SERVER_ADDRESS = "http://211.191.135.72";
	 
	 
	    /** Called when the activity is first created. */
	   
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_create_account);
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        setTitle("회원가입");
	        
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    
	    joinok = (Button)findViewById(R.id.ok);
	    joincancle = (Button)findViewById(R.id.cancle);
	    edtname = (EditText)findViewById(R.id.editname);
	    edtbirth = (EditText)findViewById(R.id.editbirth);
	    edtpass = (EditText)findViewById(R.id.editpass);
	    edtpass2 = (EditText)findViewById(R.id.editpass2);
	    
	    data = new ArrayList<String>();
       
	    
	    joincancle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CreateAccount.this.finish();
			}
		});
	    
	    joinok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				if(edtname.getText().toString().equals("")||edtbirth.getText().toString().equals("")||
						edtpass.getText().toString().equals("")||edtpass2.getText().toString().equals("")){
					Toast.makeText(CreateAccount.this, "다 입력해주세요.", Toast.LENGTH_SHORT).show();
					return;
					
				}
				String password2 = edtpass.getText().toString();
				String password3 = edtpass2.getText().toString();
				if(edtpass.getText().toString().equals(password3)){
					
					
				}
				else
				{
					Toast.makeText(CreateAccount.this,"비밀번호 확인을 다시 해주세요", Toast.LENGTH_SHORT).show();
					return;
					
				}
				runOnUiThread(new Runnable() {
					public void run() {
						
						String name = edtname.getText().toString();
						String birth = edtbirth.getText().toString();
						String password = edtpass.getText().toString();
						
						try{//////////////////////////////예약 신청 / 취소
							URL url = new URL(SERVER_ADDRESS + "/create_search.php?"
									+ "name=" + URLEncoder.encode(name,"UTF-8")
									+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
									+ "&password=" + URLEncoder.encode(password,"UTF-8")
									);					
							url.openStream();		
							String result = getXmlData("insertsearch.xml", "result");
							
							if(result.equals("1"))
							{
								Toast.makeText(CreateAccount.this, "보안이 약합니다 비밀번호를 바꿔주세요.", Toast.LENGTH_SHORT).show();
								return;
								
							}
							
							
						} catch(Exception e) {
							Toast.makeText(CreateAccount.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						}
						
						
						
						
						
						
						
						
						
						
						try{
							URL url = new URL(SERVER_ADDRESS + "/create_account.php?"
							+ "name=" + URLEncoder.encode(name,"UTF-8")
							+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
							);
							
							url.openStream();
							
							String result = getXmlData("insertresult.xml", "result");
							
							if(result.equals("0")) { //result �쒓렇媛믪씠 1�쇰븣 �깃났
								

								try{
								URL url2 = new URL(SERVER_ADDRESS + "/create_account2.php?"
										+ "name=" + URLEncoder.encode(name,"UTF-8")
										+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
										+ "&password=" + URLEncoder.encode(password,"UTF-8"));
										
								url2.openStream();
								
								String result2 = getXmlData("insertresult2.xml", "result");
						
								if(result2.equals("1")){
									
									try{
										URL url3 = new URL(SERVER_ADDRESS + "/create_success.php?"
												+ "name=" + URLEncoder.encode(name,"UTF-8")
												+ "&birth=" + URLEncoder.encode(birth,"UTF-8")
												+ "&password=" + URLEncoder.encode(password,"UTF-8"));
												
										url3.openStream();
										
										String result3 = getXmlData("create_success.xml", "result");
									if(result3.equals("1"))
									{
									Toast.makeText(CreateAccount.this, "가입완료!", Toast.LENGTH_SHORT).show();
								
									edtname.setText("");
									edtbirth.setText("");
									edtpass.setText("");
								
									CreateAccount.this.finish();
									}
									else
										Toast.makeText(CreateAccount.this, "시스템 오류. 가입실패", Toast.LENGTH_SHORT).show();
									
									}
									catch(Exception e){
										Log.e("Error", e.getMessage());
										Toast.makeText(CreateAccount.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();}
								}
								else
									Toast.makeText(CreateAccount.this, "시스템 오류. 가입실패", Toast.LENGTH_SHORT).show();
								}
								catch(Exception e){
									Log.e("Error", e.getMessage());
									Toast.makeText(CreateAccount.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();}
							
						}
						else
							Toast.makeText(CreateAccount.this, "이름과 생일이 중복됩니다.", Toast.LENGTH_SHORT).show();
							
						}
						catch(Exception e){
							Log.e("Error", e.getMessage());
							Toast.makeText(CreateAccount.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
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
				Toast.makeText(CreateAccount.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
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
				Toast.makeText(CreateAccount.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				Log.e("Error", e.getMessage());
			}
			
			return ret;
	    }
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		login = (WebView) findViewById(R.id.login);

		  InputStream is = null;

		  String url = "http://127.0.0.1:8080/HttpClientServer/selectMain.jsp";
		  // �붿껌��url 臾몄옄�대줈 吏�젙

		  HttpClient httpclient = new DefaultHttpClient();
		  // DefaultHttpClient �댁슜�댁꽌 http�대씪�댁뼵���앹꽦
		  try {
		   String id = "java";
		   String pwd = "java";
		   ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		   // �대쫫�섍퀬 媛믪쓣 �띿쑝濡���옣�좎닔 �덈뒗 �뺥깭 (NameValuePair)
		   nameValuePairs.add(new BasicNameValuePair("id", id));
		   // BasicNameValuePair �대옒�ㅻ� �댁슜�섏뿬 �낅젰 id = java
		   nameValuePairs.add(new BasicNameValuePair("passwd", pwd));

		   String result = "";
		   HttpParams params = httpclient.getParams();
		   // �뚮씪誘명꽣瑜��살뼱��꽌
		   HttpConnectionParams.setConnectionTimeout(params, 5000);
		   // 5珥��댁긽 �곌껐���덈릺硫��딆뼱吏�쾶 (milliseconds �⑥쐞)

		   HttpPost httppost = new HttpPost(url);
		   // Post 諛⑹떇���붿껌

		   UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(
		     nameValuePairs, "UTF-8");
		   // �ㅺ뎅��泥섎━
		   httppost.setEntity(entityRequest);
		   // �뷀떚��吏�젙

		   HttpResponse response = httpclient.execute(httppost);
		   // �ㅽ뻾�섍퀬 寃곌낵 response濡�諛쏆븘�ㅺ린

		   HttpEntity entityResponse = response.getEntity();
		   // �뷀떚���살뼱�ㅺ린
		   is = entityResponse.getContent();
		   // �묐떟���곗씠�곕� �쎌쓣�섏엳���낅젰�ㅽ듃由��섏뼱��
		   BufferedReader reader = new BufferedReader(new InputStreamReader(
		     is, "UTF-8"), 8);
		   // �몄퐫��泥섎━ 踰꾪띁�쒕━���살뼱��
		   StringBuilder sb = new StringBuilder();
		   String line = null;
		   while ((line = reader.readLine()) != null) {
		    sb.append(line).append("\n");
		    // �쒕씪�몄뵫 �쎌뼱���ㅽ듃留�踰꾪띁���댁쓬
		   }
		   is.close();
		   // �명뭼�ㅽ듃由��レ쓬
		   result = sb.toString();
		   // �쒕쾭�먯꽌 諛쏆븘��臾몄옄��String�쇰줈 蹂�솚
		   login.loadData(result, "text/html", "UTF-8");
		   // 臾몄옄���밸럭濡�肉뚮┝

		  } catch (IOException e) {
		   e.printStackTrace();
		  } catch (Exception e) {
		   e.printStackTrace();
		  } finally {
		   httpclient.getConnectionManager().shutdown();
		   // httpclinet �レ쓬
		  }

	*/


		
		
		
		
	/*	
	
//踰꾪듉 �대┃
        
        btnSave.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // ��옣���뚮��꾨븣
        
                
                task = new loadJsp();
                task.execute();
                        
            }
        });
        
        
		 btnCancle.setOnClickListener(new OnClickListener() {
	         
	         @Override
	         public void onClick(View v)
	 		{
	 			CreateAccount.this.finish();
	 			
	 			
	 			
	 		}
	     });
		
		*/
		
		
		


	
	
	
	
	
	
	
	
		
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_account, menu);
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


	
	
	
	
	
	




