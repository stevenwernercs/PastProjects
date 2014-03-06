package team2awesome.subsystem.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import team2awesome.subsystem.database.DBInterface;



import com.mysql.jdbc.Statement;

public class XDBFileStorage extends DBInterface{
	//keeps track of data all historicaL data
	
	public final String START_HOUR_DAY = "00:00:00";
	public final String END_HOUR_DAY = "23:45:00";
	public final int ALL_DAY_FLAG = 1;
	public final int PARTIAL_DAY_FLAG = 2;
	
	protected boolean appendToDay(String sensorName, String field, String unit, double reading, String date)
	{
		try
		{
			Connection conn = super.openConnection();
			Statement state = (Statement) conn.createStatement();
			
			String query = "";
			
			if(hasBeenSet(sensorName, field, unit, date))
			{
				query = "UPDATE filestorage SET readings = CONCAT(readings, ',"+reading+"') "
						+ " WHERE sensor_name = '"+sensorName+"' AND field = '"+field+"' AND unit = '"+unit+"' AND calendar_date = '"+date+"';";
			}
			else
			{
				query = "INSERT INTO filestorage VALUES('"+sensorName+"', '"+field+"', '"+unit+"','"+date+"', '"+reading+"');";
			}
			
			state.execute(query);
			
			conn.close();
			return true;
			
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}
		return false;
	}
	public LinkedList<Datum> getAllDay(String[] sensorName, String date)
	{	
		return getPartialDay(sensorName, date + " " + this.START_HOUR_DAY, date + " " + this.END_HOUR_DAY);
	}
	public LinkedList<Datum> getPartialToEnd(String[] sensorName, String startTimeStamp)
	{	
		Scanner scanner = new Scanner(startTimeStamp);
		scanner.useDelimiter(" ");
		
		String date = scanner.next();
		return getPartialDay(sensorName, startTimeStamp, date + " " + END_HOUR_DAY);
	}
	public LinkedList<Datum> getStartToPartial(String[] sensorName, String endTimeStamp)
	{
		Scanner scanner = new Scanner(endTimeStamp);
		scanner.useDelimiter(" ");
		
		String date = scanner.next();
		return getPartialDay(sensorName, date + " " + this.START_HOUR_DAY, endTimeStamp);
	}
	public static DataSet[] getAllSensorData(String ownerEmail, String ruleName, String dnl, String monitorName, String[][] sensorNames)
	{
		DataSet dataset[] = new DataSet[sensorNames.length];
		
		for(int i = 0; i < dataset.length; i++)
		{
			
			LinkedList<String> startAndEnd = getStartAndEndTimes(sensorNames[i]);
			LinkedList<Datum> datumsList = getPartialDay(sensorNames[i], startAndEnd.getFirst() + " 00:00:00", startAndEnd.getLast() + " 23:45:00");
			
			int counter = 0;
			Datum datumArray[] = new Datum[datumsList.size()];
			for(Iterator<Datum> liter = datumsList.iterator(); liter.hasNext(); counter++)
			{
				datumArray[counter] = liter.next();
			}
			
			dataset[i] = new DataSet(ownerEmail, ruleName, dnl, monitorName, sensorNames[i], new Time(startAndEnd.getFirst() + " 00:00:00"), new Time(startAndEnd.getLast()+ " 23:45:00"), datumArray);
			
		}
		
		return dataset;
	}
	private static LinkedList<String> getStartAndEndTimes(String[] sensor)
	{
		LinkedList<String> startAndEnd = new LinkedList<String>();
		
		try
		{
			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();
			//TODO AND feild = sensor[1] AND unit = sensor[2]
			String query = "SELECT calendar_date FROM filestorage WHERE sensor_name = '"+sensor[0]+"' AND field = '"+sensor[1]+"' AND unit = '"+sensor[2]+"';";
			
			ResultSet result = state.executeQuery(query);
			result.first();
			
			while(true)
			{
				startAndEnd.add(result.getString(1));
				
				if(result.next())
					continue;
				else
					break;

			}
			
		}
		catch (Exception e)
		{
			
		}
		
		return startAndEnd;
	}
	public static LinkedList<Datum> getPartialDay(String[] sensorName, String startTimeStamp, String endTimeStamp)
	{
		Time startTime = new Time(startTimeStamp);
		Time endTime = new Time(endTimeStamp);
		
		try
		{
			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();
			//TODO AND feild = sensor[1] AND unit = sensor[2]
			String query = "SELECT readings, calendar_date FROM filestorage WHERE sensor_name = '"+sensorName[0]+"' AND field = '"+sensorName[1]+"' AND unit = '"+sensorName[2]+"' AND calendar_date BETWEEN '"+startTime.getDate()+"' and '"+endTime.getDate()+"';";
			
			ResultSet result = state.executeQuery(query);
			result.first();
			
			LinkedList<String> readings = new LinkedList<String>();
			LinkedList<String> calendar_date = new LinkedList<String>();
			
			//add them all to a list
			while(true)
			{
				readings.add(result.getString(1));
				calendar_date.add(result.getString(2));
				
				if(result.next())
					continue;
				else
					break;

			}
			
			//iterate through list			
			LinkedList<Datum> datumList = new LinkedList<Datum>();
			Time timeTracker;
			for(int i = 0; i < readings.size(); i++)
			{
				
				//set up date
				String date = calendar_date.get(i);
				
				Scanner dateScanner = new Scanner(date);
				dateScanner.useDelimiter("-");
				int year = dateScanner.nextInt();
				int month = dateScanner.nextInt();
				int day = dateScanner.nextInt();
				
				timeTracker = new Time(month, day, year);
				
				//add them to datulList as datums
				Scanner scanner = new Scanner(readings.get(i));
				scanner.useDelimiter(",");
				while(scanner.hasNext())
				{
					double reading = scanner.nextDouble();
					
					datumList.add(new Datum(reading, timeTracker.getTimeStamp(), 0, sensorName));
					timeTracker.incriment15Mins();
					if (timeTracker.isDayDone())
						break;
				}
				
			}
			
			
			//make sure its within the time specified;
			for(Iterator<Datum> liter = datumList.iterator(); liter.hasNext(); )
			{
				Datum datum = liter.next();
				int datumHour = datum.getTimeStamp().getHour();
				int datumMinute = datum.getTimeStamp().getMinute();
				int startHour = startTime.getHour();
				int startMinute = startTime.getMinute();
				
				if(datumHour != startHour || datumMinute != startMinute)
				{
					liter.remove();
				}
				if(datumHour == startHour && datumMinute == startMinute)
				{
					break;
				}
			}
			
			//find the last index
			int lastIndex = 0;
			for(Iterator<Datum> liter = datumList.descendingIterator(); liter.hasNext(); lastIndex++)
			{
				Datum datum = liter.next();
				int datumHour = datum.getTimeStamp().getHour();
				int datumMinute = datum.getTimeStamp().getMinute();
				int endHour = endTime.getHour();
				int endMinute = endTime.getMinute();
				if(datumHour == 23 && datumMinute == 30)
					break;
				if(datumHour == endHour && datumMinute == endMinute)
				{
					break;
				}
			}
			
			
			int subListSize = datumList.size() - lastIndex;
			//get the sublist
			LinkedList<Datum> resultList = new LinkedList<Datum>();
			for(int count = 0; count < subListSize; count++)
			{
				
				resultList.add(datumList.get(count));
			}
			System.out.println("SOURCE SIZE: "+resultList.size());
			return resultList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	private boolean hasBeenSet(String sensorName, String field, String unit,  String date)
	{
		try
		{
			Connection conn = super.openConnection();
			Statement state = (Statement) conn.createStatement();
			
			String query = "SELECT sensor_name, calendar_date FROM filestorage WHERE sensor_name = '"+sensorName+"' AND field = '"+field+"' AND unit = '"+unit+"' AND calendar_date = '"+date+"';";
			
			ResultSet result = state.executeQuery(query);
			result.first();
			
			String resultName = result.getString(1);
			String resultDate = result.getString(2);
			
			if(resultName.equals(sensorName) && resultDate.equals(date))
			{
				conn.close();
				return true;
			}
			
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}