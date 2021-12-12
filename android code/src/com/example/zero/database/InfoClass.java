package com.example.zero.database;

public class InfoClass {
	public int _id;
	public String number;
	public String location;
	public String uri;
	public String favorite;
	
	public InfoClass(){}
	
	public InfoClass(int _id, String number , String location , String uri , String favorite){
		this._id = _id;
		this.number = number;
		this.location = location;
		this.uri = uri;
		this.favorite = favorite;
	}
	
}