package com.example.zero;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zero.database.*;



public class ZERO extends Activity implements ActionBar.TabListener {	

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
	 int curYear, curMonth, curDay, curHour, curMinute, curNoon, curSecond;
    Calendar c;
	 String tag1 = null;
	 String noon = "";
	 String week = "";
	 Date curMillis;
	 String version = "0.101";//////////////////////////////////////////////////???????????? ??????
	 
	 
	 
	 
	 								String update_date ="8??? 29???";
	 
	 
	 
	 
	 
	 EditText edtname, edtbirth, edtpass,edtpass2;
		Button joinok , joincancle;
		ListView list;
		ArrayList<String> data;
		ArrayAdapter<String> adapter;
		 
		 private static final String SERVER_ADDRESS = "http://211.191.135.72";
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zero);
        data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
        
        
       
        startActivity(new Intent(this, 
    			Intro.class));
       
        
		c = Calendar.getInstance();
	    //  curMillis = c.getTime();
	      curYear = c.get(Calendar.YEAR);
	      curMonth = c.get(Calendar.MONTH)+1;
	      curDay = c.get(Calendar.DAY_OF_MONTH); 
	      curHour = c.get(Calendar.HOUR_OF_DAY);
	      curNoon = c.get(Calendar.AM_PM);
	      switch (c.get(Calendar.DAY_OF_WEEK)){
	      case 1:
	          week = "???";
	          break;
	      case 2:
	          week ="???";
	          break;
	      case 3:
	          week = "???";
	          break;
	      case 4:
	          week = "???";
	          break;
	      case 5:
	          week = "???";
	          break;
	      case 6:
	          week = "???";
	          break;
	      case 7:
	          week = "???";
	          break;
	      }
	     if(week.equals("???")||week.equals("???")){
	      AlertDialog.Builder gg=new AlertDialog.Builder(this);
	        gg.setTitle("????????????");
	        gg.setMessage(update_date+"?????? ??? ?????? PC?????? ???????????? ?????????????????????. \n???????????? ??????????????? ?????? ????????? ????????? ??????????????? ?????? ?????? ???????????? ?????? ??????????????? ????????????.");//////////???????????? ??????
	        gg.setIcon(R.drawable.ic_launcher);
	        gg.setPositiveButton("??????",null);
	        gg.show();
	     }
	      /*
	      try{//////////////////////////////?????? ?????? / ??????
				URL url = new URL(SERVER_ADDRESS + "/version_check.php?"
						
						);					
				url.openStream();		
				String result = getXmlData("version_check.xml", "result");
				
				if(result.equals(version))
				{	
				}
				else
				{
					 AlertDialog.Builder gg=new AlertDialog.Builder(this);
				        gg.setTitle("????????????");
				        gg.setMessage("??? ?????? PC?????? ???????????? ?????????????????????. \n??????????????? ???????????? ??????????????? ?????? ?????? ???????????? ?????? ??????????????? ????????????.");//////////???????????? ??????
				        gg.setIcon(R.drawable.ic_launcher);
				        gg.setPositiveButton("??????",null);
				        gg.show();
				}
				
	      }catch(Exception e) {
	    	  Toast.makeText(this, "????????? ????????? ???????????????.", Toast.LENGTH_SHORT).show();
			}
			*/
	       
        	
	        
        
        //Set up the favorite DB 
        DBinfo db = new DBinfo(this);
        

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(),getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }
    
  

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.zero, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	 startActivity(new Intent(this, 
         			Company.class));
        	
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    @SuppressLint("DefaultLocale")
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
    	
    	Context mContext;

        public SectionsPagerAdapter(Context context , android.app.FragmentManager fm) {
            super(fm);
            mContext = context;
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
        	 switch (position) {
 	        case 0:
 	            return new First();
 	        case 1:
 	            return new Second();
 	        case 2:
 	            return new Third();
 	    }
        	 return null;

        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */

        public static class PlaceholderFragment extends Fragment {
            private static final String ARG_SECTION_NUMBER = "section_number";
            public static PlaceholderFragment newInstance(int sectionNumber) {
                PlaceholderFragment fragment = new PlaceholderFragment();
                Bundle args = new Bundle();
                args.putInt(ARG_SECTION_NUMBER, sectionNumber);
                fragment.setArguments(args);
                return fragment;
            }

            public PlaceholderFragment() {
            }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_zero, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

		public static Fragment newInstance(int i) {
			// TODO Auto-generated method stub
			return null;
		}

		
		
		/////////////////////////////////////////////////////////////////////////////// ???????????????????????? ???????????? ??????????????????????????? ????????????????????????????????????????????????
		int count = 0;
		
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event)
		{
			
			if(keyCode==KeyEvent.KEYCODE_BACK)
			{
				if(count>0)
				{
				finish();
				}
				else
				{
					Toast.makeText(this, "?????? ??? ????????? ???????????????.", Toast.LENGTH_SHORT).show();
					count++;
				}
			}
			
			return false;
			
		}
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////
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
			
		    private ArrayList<String> getXmlDataList(String filename, String str) { //??????????????? ???????????????????????????????????? ?????????????????????????????????????????? ArrayList<String>????????? ??????????????????
				String rss = SERVER_ADDRESS + "/";
				ArrayList<String> ret = new ArrayList<String>();
				
				try { //XML ????????????????????? ?????????????????? ??????????????????
					XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser xpp = factory.newPullParser();
					URL server = new URL(rss + filename);
					InputStream is = server.openStream();
					xpp.setInput(is, "UTF-8");
					
					int eventType = xpp.getEventType();
					
					while(eventType != XmlPullParser.END_DOCUMENT) {
						if(eventType == XmlPullParser.START_TAG) {
							if(xpp.getName().equals(str)) { //???????????? ????????????????????? str ?????????????????????????????? ?????????????????? ???????????????
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
			
		
}

