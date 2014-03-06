package team2awesome.subsystem.database;

import java.util.LinkedList;

public class Data {
	
	String sensorName = "";
	Time startTime = null;
	Time endTime = null;
	LinkedList<Datum> datumList = new LinkedList<Datum>();

	public static final int HISTORICAL_FLAG = 1;
	public static final int NEARREALTIME_FLAG = 1;
	
	public Data(String sensorName, Time startTime, Time endTime,
			LinkedList<Datum> datumList) {
		super();
		this.sensorName = sensorName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.datumList = datumList;
	}
	
	public String getSensorName() {
		return sensorName;
	}
	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public LinkedList<Datum> getDatumList() {
		return datumList;
	}
	public void setDatumList(LinkedList<Datum> datumList) {
		this.datumList = datumList;
	}
	public static int getHistoricalFlag() {
		return HISTORICAL_FLAG;
	}
	public static int getNearrealtimeFlag() {
		return NEARREALTIME_FLAG;
	}
	
	
	

}
