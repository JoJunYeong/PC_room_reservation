package com.example.zero;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


public class WebView_1 extends Activity {
	 WebView webview;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view_1);
		
		 webview = (WebView)findViewById(R.id.webview);
	        webview.setWebViewClient(new WebClient()); // 응룡프로그램에서 직접 url 처리
	        WebSettings set = webview.getSettings();
	        set.setJavaScriptEnabled(true);
	        set.setBuiltInZoomControls(true);
	        webview.loadUrl("http://www.google.com");
	         
	   //     findViewById(R.id.btnStart).setOnClickListener(onclick);
		
		
		
		
		
	}


		
	//	 OnClickListener onclick =new OnClickListener() {
	         
		//        @Override
		   //     public void onClick(View v) {
		 //           System.out.println("클릭");
		  //          String url= null;
		//            EditText add = (EditText)findViewById(R.id.add);
		 //           url = add.getText().toString();
		 //           webview.loadUrl(url);           
		 //       }
		//    };
	
		    class WebClient extends WebViewClient {
		        public boolean shouldOverrideUrlLoading(WebView view, String url) {
		            view.loadUrl(url);
		            return true;
		        }
		    }
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web_view_1, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
