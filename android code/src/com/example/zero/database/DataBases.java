package com.example.zero.database;

import android.provider.BaseColumns;

// DataBase Table
public final class DataBases {
	
	public static final class CreateDB implements BaseColumns{
		public static final String NUMBER = "number";
		public static final String LOCATION = "location";
		public static final String URI = "uri";
		public static final String FAVORITE = "favorite";
		public static final String _TABLENAME = "favorite";
		public static final String _CREATE = 
			"create table "+_TABLENAME+"(" 
					+_ID+" integer primary key autoincrement, "
					+NUMBER+" text not null , "
					+LOCATION+" text not null , " 
					+URI+" text not null , " 
					+FAVORITE+" text not null );";
	}
}
