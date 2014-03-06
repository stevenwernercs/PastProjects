package team2awesome.subsystem.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import team2awesome.subsystem.monitor.Monitor;
import team2awesome.subsystem.sensor.Location;
import team2awesome.subsystem.sensor.Sensor;
import team2awesome.subsystem.sensor.SensorField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBSensor extends DBInterface{
	
	public static Sensor get(String identifier)
	{		
		try 
		{			
			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();
			String query = "select * from sensor where identifier = '" + identifier+"';";
			ResultSet result = state.executeQuery(query);
			result.first();

			return makeSensorFromResult(result);
			
		} catch (Exception e) {
			System.out.println("error retrieving sensor with identieifer <" + identifier + "> from sensor table");
			//e.printStackTrace();
		}
		
		return null;
	}
	
	private static Sensor makeSensorFromResult(ResultSet result) throws Exception 
	{
		String sensorIdentifier = result.getString(1);
		String serialNumber = result.getString(2);
		String type = result.getString(3);
		String manufacturer = result.getString(4);
		String locDescription = result.getString(5);
		double precision = result.getDouble(6);
		double accuracy = result.getDouble(7);
		String field1 = result.getString(8);
		String field1_unit = result.getString(9);
		String field2 = result.getString(10);
		String field2_unit = result.getString(11);
		String field3 = result.getString(12);
		String field3_unit = result.getString(13);
		String field4 = result.getString(14);
		String field4_unit = result.getString(15);
		int latDeg = result.getInt(16);
		int latMin = result.getInt(17);
		int latSec = result.getInt(18);
		int lonDeg = result.getInt(19);
		int lonMin = result.getInt(20);
		int lonSec = result.getInt(21);
		
		SensorField sf1 = new SensorField(field1, field1_unit);
		SensorField sf2 = new SensorField(field2, field2_unit);
		SensorField sf3 = new SensorField(field3, field3_unit);
		SensorField sf4 = new SensorField(field4, field4_unit);
		
		SensorField[] fields = {sf1, sf2, sf3, sf4};
		
		Location location = new Location(latDeg, latMin, latSec, lonDeg, lonMin, lonSec);
		
		return new Sensor(sensorIdentifier, serialNumber, type, manufacturer, locDescription, precision, accuracy, location, fields);	
	}

	public static boolean set(Sensor sensor)
	{		
		String query = "";
		try {			
			Connection conn = openConnection();
			
			String identifier = sensor.getIdentifier();
			String serialNumber = sensor.getSerialNumber();
			String type = sensor.getType();
			String manufacturer = sensor.getManufacturer();
			String locDescription = sensor.getLocationDescription();
			double precisionValue = sensor.getPrecision();
			double accuracy = sensor.getAccuracy();
			
			SensorField[] fields = sensor.getFields();
			String field1 = fields[0].getFieldName();
			String field2 = fields[1].getFieldName();
			String field3 = fields[2].getFieldName();
			String field4 = fields[3].getFieldName();
			
			String unit1 = fields[0].getUnit();
			String unit2 = fields[1].getUnit();
			String unit3 = fields[2].getUnit();
			String unit4 = fields[3].getUnit();
			
			Location location = sensor.getLocation();
			int latDeg = location.getLatitudeDegree();
			int latMin = location.getLatitudeMinute();
			int latSec = location.getLatitudeSecond();
			int lonDeg = location.getLongitudeDegree();
			int lonMin = location.getLongitudeMinute();
			int lonSec = location.getLongitudeSecond();
			
			//String query = "";
			if(sensorExists(sensor.getIdentifier()))
			{
				//sensor exists so update the values
				query = "UPDATE sensor SET identifier = '"+identifier+"', serial_number = '"+serialNumber+"', type = '"+type+"', manufacturer = '"+manufacturer+"', locDescription = '"+locDescription+"', precisionValue = "+precisionValue+", accuracy = "+accuracy+", "
						+ "field1 = '"+field1+"', field1_unit = '"+unit1+"', field2 = '"+field2+"', field2_unit = '"+unit2+ "', field3 = '"+field3+"', field3_unit = '"+unit3+ "', field4 = '"+field4+"', field4_unit = '"+unit4+"', latDegree = "+latDeg+", latMinute = "+latMin+", latSecond = "+latSec+", longDegree = "+lonDeg+", longMinute = "+lonMin+", longSecond = "+lonSec+""
						+ " WHERE identifier = '" + identifier + "';";
			}
			else
			{
				//sensor doesnt exist so insert new values
				query = "INSERT INTO sensor VALUES('"+identifier+"', '"+serialNumber+"', '"+type+"', '"+manufacturer+"', '"+locDescription+"', "+precisionValue+", "+accuracy+", "
						+ "'"+field1+"', '"+unit1+"', '"+field2+"', '"+unit2+"', '"+field3+"', '"+unit3+"', '"+field4+"', '"+unit4+"', "
						+ " "+latDeg+", "+latMin+", "+latSec+", "+lonDeg+", "+lonMin+", "+lonSec+");";
			}
			
			//System.out.println("query is: " + query);
			
			Statement state = (Statement) conn.createStatement();
			/*int success =*/ state.executeUpdate(query);
						
			
			conn.close();
			
			
			class MyThread implements Runnable 
			{
				String identifier;
				String field1;
				String unit1;
				String field2;
				String unit2;
				String field3;
				String unit3;
				String field4;
				String unit4;
				
				public MyThread(String identifier, String field1, String unit1, String field2, String unit2, String field3, String unit3, String field4, String unit4)
				{
					this.identifier	= identifier; 
					this.field1		=field1;
					this.unit1		=unit1;
					this.field2		=field2;
					this.unit2		=unit2;
					this.field3		=field3;
					this.unit3		=unit3;
					this.field4		=field4;
					this.unit4		=unit4;
				}
				
				public void run() 
				{
					System.out.println("Child: thread is RUNNING!!!");
			     	System.out.println("######Making data1");
			     	createFauxData(identifier, field1, unit1);
			     	System.out.println("######Making data2");
					createFauxData(identifier, field2, unit2);
					System.out.println("######Making data3");
					createFauxData(identifier, field3, unit3);
					System.out.println("######Making data4");
					createFauxData(identifier, field4, unit4);
				}
			}
			
			Runnable r = new MyThread( identifier,  field1,  unit1,  field2,  unit2,  field3,  unit3,  field4,  unit4);
			new Thread(r).start();
			System.out.println("Parent: thread is RUNNING!!!");
			
			
			
			return true;
			
		} catch (Exception e) {
			System.out.println("error saving sensor to table");
			System.out.println(query);
			e.printStackTrace();
			return false;
		}
	}
	public static boolean createFauxData(String sensorName, String field, String unit)
	{
		if(field.equals(""))
			return false;
		//check if its been set already
		XDBDataLogger logger = new XDBDataLogger();
		if( logger.rowAlreadySet(sensorName, field, unit) == false)
		{
			//decide type (or number of sine wave peaks)
			int type = 1;
			if(field.equals("temperature"))
				type = 1;
			else if(field.equals("wind"))
				type = 3;
			else if(field.equals("light"))
				type = 1;
			else if(field.equals("rain"))
				type = 2;
			
			setDayTemperature(sensorName, field, unit,30,65,2013, 11, 23, type);
			setDayTemperature(sensorName, field, unit,40,75,2013, 11, 22, type);
			setDayTemperature(sensorName, field, unit,50,85,2013, 11, 21, type);
			
		}
		
		return false;
	}
	
	//implementation, can ignore code
	private static void setDayTemperature(String sensorName, String field, String unit, double minRange, double maxRange, int year, int month, int day, int type)
	{
		//create a time instance
		XDBDataLogger logger = new XDBDataLogger();
		
		for(Time timeOfDay = new Time(month,day,year); timeOfDay.isDayDone() == false; timeOfDay.incriment15Mins())
		{
			double timeDecimal = timeOfDay.getTimeDecimal();
			double fauxSensorValue = getSineValue(minRange, maxRange, timeDecimal, type);
			
			logger.set(sensorName, field, unit, fauxSensorValue, timeOfDay.getTimeStamp(), timeOfDay.getDate());
			System.out.println("set " + timeOfDay.getTimeStamp() + " with reading " + fauxSensorValue);
		}
	}
	private static double getSineValue(double min, double max, double timeDecimal, int type)
	{
		double x = (timeDecimal * Math.PI*2) / 24;
		
		double midPoint = (max-min);
		double verticalShift = midPoint /2 ;
		double multiplier = (midPoint - 10) / 2;
		
		double temp = (multiplier * Math.sin((type*x) - (Math.PI / 2))) + verticalShift;
		double randomNumber = Math.random() * 5;
		
		return temp + randomNumber + min;
	}
	public static boolean sensorExists(String identifier)
	{
		
		try {
			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();
			String query = "select * from sensor where identifier = '"+identifier+"';";
			ResultSet result = state.executeQuery(query);
			result.first();
			

			String sensorIdentifier = result.getString(1);
			
			conn.close();
			
			if(sensorIdentifier.equals(identifier))
				return true;
			
		} catch (Exception e) {}
		
		return false;
	}

	public static Sensor[] getSensorArray()
	{
		
		try {
			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();
			String query = "select * from sensor;";
			ResultSet result = state.executeQuery(query);
			if(!result.first())
			{
				System.out.println("No Sensors at all!!?");
				return null;
			}
			
			LinkedList<Sensor> list = new LinkedList<Sensor>();
			
			while(true)
			{	
				list.add(makeSensorFromResult(result));
				if(!result.next())
					break;
			}
			
			return list.toArray(new Sensor[list.size()]);
			
		} catch (Exception e) {
		}
		
		return null;
	}
	
	
}
