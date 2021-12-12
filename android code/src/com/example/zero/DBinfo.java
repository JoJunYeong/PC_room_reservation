package com.example.zero;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.zero.database.*;


public class DBinfo {
	
	private String TAG = "DBinfo.class";
	DbOpenHelper mDbOpenHelper;
	private InfoClass mInfoClass;
	private ArrayList<InfoClass> mInfoArray;
	private Cursor mCursor;
	static int statement = 0;
	static int statementReservation = 0;
	
	public DBinfo(Context context) {
		// TODO Auto-generated constructor stub
		mDbOpenHelper = new DbOpenHelper(context);
        mDbOpenHelper.open();
                	
        mInfoArray = new ArrayList<InfoClass>();        
        doWhileCursorToArray();        
        for(InfoClass i : mInfoArray){
        	//Log.d(TAG, "ID = " + i._id);
        	//Log.d(TAG, "location = " + i.location);
        	//Log.d(TAG, "uri = " + i.uri);
        	//Log.d(TAG, "favorite = " + i.favorite);
        }
		
	}
	
	public void doWhileCursorToArray(){		
		mCursor = null;
		mCursor = mDbOpenHelper.getAllColumns();
		//Log.e(TAG, "COUNT = " + mCursor.getCount());		
		while (mCursor.moveToNext()) {        	
			mInfoClass = new InfoClass(
					mCursor.getInt(mCursor.getColumnIndex("_id")),
					mCursor.getString(mCursor.getColumnIndex("number")),
					mCursor.getString(mCursor.getColumnIndex("location")),
					mCursor.getString(mCursor.getColumnIndex("uri")),
					mCursor.getString(mCursor.getColumnIndex("favorite"))
					);			
			mInfoArray.add(mInfoClass);
		}		
		mCursor.close();
	}
	
	/*
	 *  search "ID" statement for using "location" 
	 */
	public int searchID(String location){
		for(InfoClass i : mInfoArray){
			if(i.location.equals(location)) {
				return i._id;
			}
			/*
        	Log.d(TAG, "ID = " + i._id);
        	Log.d(TAG, "location = " + i.location);
        	Log.d(TAG, "uri = " + i.uri);
        	Log.d(TAG, "favorite = " + i.favorite);
        	*/
        }
		return 0;
	}
	
	/*
	 *  search "favorite" statement for using "location" 
	 */
	public boolean searchFavorite(String location){
		for(InfoClass i : mInfoArray){
			if(i.location.equals(location)) {
				return Boolean.parseBoolean(i.favorite);
			}
			/*
        	Log.d(TAG, "ID = " + i._id);
        	Log.d(TAG, "location = " + i.location);
        	Log.d(TAG, "uri = " + i.uri);
        	Log.d(TAG, "favorite = " + i.favorite);
        	*/
        }
		return false;
	}
	
	/*
	 *  modify "favorite" statement for using "location" 
	 */
	public void modifyFavorite(String location,String favorite) {
		for(InfoClass i : mInfoArray){
			if(i.location.equals(location)) {
				mDbOpenHelper.updateColumn(i._id,i.number , i.location, i.uri, favorite);
				break;
			}			
        }
		
	}
	
	/*
	 *  search DATA statement for using "ID" 
	 */
	public InfoClass searchAllData(int id){
		for(InfoClass i : mInfoArray){
			if(id == i._id) {
				return i;
			}
			/* DB접근 내역
        	Log.d(TAG, "ID = " + i._id);
        	Log.d(TAG, "location = " + i.location);
        	Log.d(TAG, "uri = " + i.uri);
        	Log.d(TAG, "favorite = " + i.favorite);
        	*/
        }
		InfoClass tmp = null;
		return tmp;
	}
	
	/*
	 * return array list size
	 */
	public int searchDBLength() {
		return mInfoArray.size();
	}

}
