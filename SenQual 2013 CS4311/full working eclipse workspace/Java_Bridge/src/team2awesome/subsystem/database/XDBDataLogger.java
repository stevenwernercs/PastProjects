package team2awesome.subsystem.database;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class XDBDataLogger extends DBInterface{
	//logs near real time data
	public boolean set(String sensorName, String field, String unit, double reading, String timeStamp, String date)
	{
		try
		{
			Connection conn = super.openConnection();
			Statement state = (Statement) conn.createStatement();
	
			String query = "";
			
			if(rowAlreadySet(sensorName, field, unit))
			{
				XDBFileStorage storage = new XDBFileStorage();
				storage.appendToDay(sensorName, field, unit, reading, date);
				query = "UPDATE datalogger SET reading = "+reading+", time_stamp = '"+timeStamp+"'"
						+ " WHERE sensor_name = '"+sensorName+"' AND field = '"+field+"' AND unit = '"+unit+"';";
			}
			else
			{
				query  = "INSERT INTO datalogger VALUES('"+sensorName+"', '"+field+"', '"+unit+"', "+reading+", '"+timeStamp+"');";
			}
			
			state.execute(query);
			
			conn.close();
			
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	public double get(String sensorName)
	{
		try
		{
			Connection conn = super.openConnection();
			Statement state = (Statement) conn.createStatement();
			
			String query = "SELECT reading FROM datalogger WHERE sensor_name = '"+sensorName+"';";
			
			ResultSet result = state.executeQuery(query);
			result.first();
			
			double reading = result.getDouble(1);
			
			conn.close();
			return reading;
		}
		catch(Exception e)
		{
			//e.printStackTrace();
		}
		
		return 0;
	}
	public boolean rowAlreadySet(String sensorName, String field, String unit)
	{
		try
		{
			Connection conn = super.openConnection();
			Statement state = (Statement) conn.createStatement();
			
			String query = "SELECT sensor_name FROM datalogger WHERE sensor_name = '"+sensorName+"' AND field = '"+field+"' AND unit = '"+unit+"';";
			
			ResultSet result = state.executeQuery(query);
			result.first();
			
			String resultName = result.getString(1);
			
			if(resultName.equals(sensorName))
			{
				return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		
		
		return false;
	}

}
