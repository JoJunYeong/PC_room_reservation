package com.example.zero.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper {

	private static final String DATABASE_NAME = "favorite.db";
	private static final int DATABASE_VERSION = 1;
	public static SQLiteDatabase mDB;
	private DatabaseHelper mDBHelper;
	private Context mCtx;

	private class DatabaseHelper extends SQLiteOpenHelper{

		// ?Éù?Ñ±?ûê
		public DatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		// ÏµúÏ¥à DBÎ•? ÎßåÎì§?ïå ?ïúÎ≤àÎßå ?ò∏Ï∂úÎêú?ã§.
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DataBases.CreateDB._CREATE);			
		}

		// Î≤ÑÏ†Ñ?ù¥ ?óÖ?ç∞?ù¥?ä∏ ?êò?óà?ùÑ Í≤ΩÏö∞ DBÎ•? ?ã§?ãú ÎßåÎì§?ñ¥ Ï§??ã§.
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME);
			onCreate(db);
		}
	}

	public DbOpenHelper(Context context){
		this.mCtx = context;
	}

	public DbOpenHelper open() throws SQLException{
		mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
		mDB = mDBHelper.getWritableDatabase();
		
		Cursor c = mDB.query(DataBases.CreateDB._TABLENAME, null,"_id="+String.valueOf(1),null, null, null, null, null);		
		if( c.getCount() != 1) {
			
			/** √ ±‚∞™ º≥¡§ */
			//                 ¡ˆø™          						 URI               favorite
			insertColumn("1","pcπÊ1(∞ËæÁ-¿€¿¸)","http://211.191.135.72/pc/pc1.jpg","true");
	        insertColumn("2","pcπÊ2(∞ËæÁ-¿€¿¸)","http://www.google.com","true");
	        insertColumn("3","pcπÊ3(∞ËæÁ-¿€¿¸)","http://www.nate.com","true");
	        insertColumn("4","pcπÊ4(∞ËæÁ-¿€¿¸)","http://www.daum.net","true");			
	        insertColumn("5","pcπÊ5(∞ËæÁ-¿€¿¸)","http://www.daum.net","true");
			
		}
		
		
		return this;
	}

	public void close(){
		mDB.close();
	}

	// Insert DB
	public long insertColumn(String number,String location, String uri, String favorite){
		ContentValues values = new ContentValues();
		values.put(DataBases.CreateDB.NUMBER, number);
		values.put(DataBases.CreateDB.LOCATION, location);
		values.put(DataBases.CreateDB.URI, uri);
		values.put(DataBases.CreateDB.FAVORITE, favorite);
		return mDB.insert(DataBases.CreateDB._TABLENAME, null, values);
	}

	// Update DB
	public boolean updateColumn(long id ,String number, String location, String uri, String favorite){
		ContentValues values = new ContentValues();
		values.put(DataBases.CreateDB.NUMBER, number);
		values.put(DataBases.CreateDB.LOCATION, location);
		values.put(DataBases.CreateDB.URI, uri);
		values.put(DataBases.CreateDB.FAVORITE, favorite);
		return mDB.update(DataBases.CreateDB._TABLENAME, values, "_id="+id, null) > 0;
	}

	// Delete ID
	public boolean deleteColumn(long id){
		return mDB.delete(DataBases.CreateDB._TABLENAME, "_id="+id, null) > 0;
	}
	
	// Delete Contact
	public boolean deleteColumn(String number){
		return mDB.delete(DataBases.CreateDB._TABLENAME, "contact="+number, null) > 0;
	}
	
	// Select All
	public Cursor getAllColumns(){
		return mDB.query(DataBases.CreateDB._TABLENAME, null,null, null, null, null, null, null);
	}

	// ID Ïª¨Îüº ?ñª?ñ¥ ?ò§Í∏?
	public Cursor getColumn(long id){
		Cursor c = mDB.query(DataBases.CreateDB._TABLENAME, null, 
				"_id="+id,null, null, null, null, null);
		if(c != null && c.getCount() != 0)
			c.moveToFirst();
		return c;
	}

	// ?ù¥Î¶? Í≤??Éâ ?ïòÍ∏? (rawQuery)
	public Cursor getMatchName(String name){
		Cursor c = mDB.rawQuery( "select * from address where name=" + "'"+ "'" + name + "'" , null);
		return c;
	}


}






