package team2awesome.subsystem.database;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class DataSet {
	
	private String ownerEmail = "";
	private String rule = "";
	private String monitor = "";
	private String[] sensorName = null;
	private Time startTime = null;
	private Time endTime = null;
	private Date timeRan = null;
	
	private Datum[] datums;
	private String ndl;

	public static final int HISTORICAL_FLAG = 1;
	public static final int NEARREALTIME_FLAG = 1;
	
	public DataSet(String ownerEmail, String rule, String ndl, String monitor,String[] sensorName, Time startTime, Time endTime, Datum[] datums) {
		super();
		this.setOwnerEmail(ownerEmail);
		this.rule = rule;
		this.ndl = ndl;
		this.monitor = monitor;
		this.sensorName = sensorName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.datums = datums;
	}
	public DataSet(String rawData)
	{
		//divide it up into string
		Scanner scanner = new Scanner(rawData);
		scanner.useDelimiter("=");
		String rule = scanner.next();
		String monitor = scanner.next();
		String sensorName = scanner.next();
		Time startTime = new Time(scanner.next());
		Time endTime = new Time(scanner.next());
		String rawDatums = scanner.next();
		
		//divide up datums
		LinkedList<String> rawDatumList = new LinkedList<String>();
		Scanner datumsScanner = new Scanner(rawDatums);
		datumsScanner.useDelimiter(",");
		while(datumsScanner.hasNext())
		{
			rawDatumList.add(datumsScanner.next());
		}
		
		//create datums
		Datum datums[] = new Datum[rawDatumList.size()];
		Iterator<String> liter = rawDatumList.iterator();
		for(int i = 0; i < rawDatumList.size(); i++)
		{
			String raw = liter.next();
			Scanner rawDivider = new Scanner(raw);
			rawDivider.useDelimiter(";");
			double reading = rawDivider.nextDouble();
			String timeStamp = rawDivider.next();
			int flag = rawDivider.nextInt();
			datums[i] = new Datum(reading, timeStamp, flag, parseSensorCombo(sensorName));
		}
		
		this.rule = rule;
		this.monitor = monitor;
		this.sensorName = parseSensorCombo(sensorName);
		this.startTime = startTime;
		this.endTime = endTime;
		this.datums = datums;
	}
	
	
	private String[] parseSensorCombo(String sensorName) 
	{
		String [] result = new String[3];
		String [] temp = sensorName.split("@");
		result[2] = temp[1];
		temp = sensorName.split("\\.");
		result[0] = temp[0];
		result[1] = temp[1];
		
		return result;
	}
	public String getRule() {
		return rule;
	}


	public void setRule(String rule) {
		this.rule = rule;
	}


	public String getMonitor() {
		return monitor;
	}


	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}


	public Datum[] getDatums() {
		return datums;
	}


	public void setDatums(Datum[] datums) {
		this.datums = datums;
	}


	public String[] getSensorName() {
		return sensorName;
	}
	public void setSensorName(String[] sensorName) {
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
	
	public String getRaw()
	{
		String raw = this.rule + "=" + this.monitor + "=" + this.sensorName[0]+"."+this.sensorName[1]+"@"+this.sensorName[2] + "=" + this.startTime.getTimeStamp() + "=" + this.getEndTime().getTimeStamp() + "=";
		
		for(int i = 0; i < this.datums.length; i++)
		{
			if(i != 0)
			{
				raw = raw + "," + this.datums[i].getReading() + ";" + this.datums[i].getTimeStamp().getTimeStamp() + ";" + this.datums[i].getFlag();
			}
			else
			{
				raw = raw +this.datums[i].getReading() + ";" + this.datums[i].getTimeStamp().getTimeStamp() + ";" + this.datums[i].getFlag();
			}
		}
		
		return raw;
	}

	public String toString()
	{
		/*
		<span id="chartContainerJan">January Column 2D Chart will load here</span>
	    <span id="chartContainerFeb">February Column 2D Chart will load here</span>
	    <span id="chartContainerMarch">March Pie Chart will load here</span>
	    <span id="gridContainerJan">January Grid will load here</span>

	    <script type="text/javascript"><!--

	      var JanChart = new FusionCharts("FusionCharts/Column2D.swf", "JanChartId",
	        "400", "300", "0");
	      JanChart.setXMLUrl("Data-Jan.xml");
	      JanChart.render("chartContainerJan");

	      var JanGrid = new FusionCharts("FusionCharts/SSGrid.swf", "JanGridId", 
	        "400", "300", "0");
	      JanGrid.setXMLUrl("Data-Jan.xml");
	      JanGrid.render("gridContainerJan");

	      var FebChart = new FusionCharts("FusionCharts/Column2D.swf", "FebChartId", 
	        "400", "300", "0");
	      FebChart.setXMLUrl("Data-Feb.xml");
	      FebChart.render("chartContainerFeb");

	      var MarChart = new FusionCharts("FusionCharts/Pie3D.swf", "MarChartId",
	        "400", "300", "0");
	      MarChart.setXMLUrl("Data-Mar.xml");
	      MarChart.render("chartContainerMarch"); 
	      */
		return null;
	}

	/**
	 * @return the ownerEmail
	 */
	public String getOwnerEmail() {
		return ownerEmail;
	}

	/**
	 * @param ownerEmail the ownerEmail to set
	 */
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	/**
	 * @return the timeRan
	 */
	public Date getTimeRan() {
		return timeRan;
	}
	/**
	 * @param date the timeRan to set
	 */
	public void setTimeRan(java.util.Date date) {
		this.timeRan = date;
	}
	public String getNDL() {
		return this.ndl;
	}
}
