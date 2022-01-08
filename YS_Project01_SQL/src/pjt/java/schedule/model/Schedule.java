package pjt.java.schedule.model;

import java.io.Serializable;

public class Schedule implements Serializable{
	
	//field 
	private int no;
	private String title;
	private String sdate;
	private String memo;
	
	//constructors
	public Schedule () {}	//기본 생성자
	
	public Schedule (String title, String sdate, String memo) {	//생성자 - 파라미터 3개
		this.title = title;
		this.sdate = sdate;
		this.memo = memo;
	}
	
	public Schedule (int no, String title, String sdate, String memo) {	//생성자 - 파라미터 4개
		this.no = no;
		this.title = title;
		this.sdate = sdate;
		this.memo = memo;
	}
	
	
	//getter & setter
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	//toString
	public String toString() {		
		return "TITLE : " + title + " | DATE : " + sdate + " | MEMO : " + memo + "| NO : " + no;
	}
	
	
}
