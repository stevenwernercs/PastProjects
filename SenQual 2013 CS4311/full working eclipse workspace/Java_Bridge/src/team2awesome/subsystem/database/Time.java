package team2awesome.subsystem.database;

import java.util.Scanner;

public class Time {
	
	private int hour = 0;
	private int minute = 0;
	
	private int month = 0;
	private int day = 0;
	private int year = 0;
	
	public Time (int month, int day, int year)
	{
		this.month = month;
		this.day = day;
		this.year = year;
	}
	public Time(String timeStamp)
	{
		Scanner scanner = new Scanner(timeStamp);
		scanner.useDelimiter(" ");
		String date = scanner.next();
		String time = scanner.next();
		
		Scanner dateScanner = new Scanner(date);
		dateScanner.useDelimiter("-");
		this.year = dateScanner.nextInt();
		this.month = dateScanner.nextInt();
		this.day = dateScanner.nextInt();
		
		Scanner timeScanner = new Scanner(time);
		timeScanner.useDelimiter(":");
		this.hour = timeScanner.nextInt();
		this.minute = timeScanner.nextInt();
		
	}
	public String getTimeStamp()
	{
		return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00";
	}
	public String getTimeShort()
	{
		return year + "/" + (month<10 ? "0"+month : month ) + "/" + (day<10 ? "0"+day : day ) + " " + (hour<10 ? "0"+hour : hour ) + ":" + (minute<10 ? "0"+minute : minute );
	}
	public String getDate()
	{
		return year + "-" + month + "-" + day;
	}
	public void incriment15Mins()
	{
		minute += 15;
		
		if(minute >= 60)
		{
			hour++;
			minute = 0;
		}
	}
	
	public boolean isDayDone()
	{
		if (hour == 24)
		{
			return true;
		}
		
		return false;
	}
	
	public double getTimeDecimal()
	{
		double decimal = 0;
		
		//we use cross multiply to get the ration of minutes to seconds to 2 deciaml places
		//minute     minuteAsDecimal
		//------  x  ---------------
		//  60             1
		double minuteAsDecimal = (minute*1) / 60;
		
		return decimal = hour + minuteAsDecimal;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	

}
