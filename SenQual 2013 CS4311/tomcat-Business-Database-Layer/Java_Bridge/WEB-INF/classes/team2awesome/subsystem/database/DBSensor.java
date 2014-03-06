package team2awesome.subsystem.database;

import java.sql.DriverManager;
import java.sql.ResultSet;

import team2awesome.subsystem.sensor.Location;
import team2awesome.subsystem.sensor.Sensor;
import team2awesome.subsystem.sensor.SensorField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBSensor {
	
	public Sensor get(String identifier)
	{
		Connection conn = null;
		String driver= "com.mysql.jdbc.Driver";
		String dbName = "jdbc:mysql://earth.cs.utep.edu";
		String name = "sespring2";
		String password = "cs4311!sespring2";
		
		try {
			Class.forName(driver);
			
			conn = (Connection) DriverManager.getConnection(dbName, name, password);
			Statement state = (Statement) conn.createStatement();
			String changeDB = "use sespring2;";
			String query = "select * from sensor where identifier = " + identifier+";";
			state.execute(changeDB);
			ResultSet result = state.executeQuery(query);
			result.first();
			
			String sensorIdentifier = result.getString(1);
			String serialNumber = result.getString(2);
			String type = result.getString(3);
			String manufacturer = result.getString(4);
			String locDescription = result.getString(5);
			double precision = result.getDouble(6);
			double accuracy = result.getDouble(7);
			String field1 = result.getString(8);
			String field2 = result.getString(9);
			String field3 = result.getString(10);
			String field4 = result.getString(11);
			int latDeg = result.getInt(12);
			int latMin = result.getInt(13);
			int latSec = result.getInt(14);
			int lonDeg = result.getInt(15);
			int lonMin = result.getInt(16);
			int lonSec = result.getInt(17);
			
			SensorField sf1 = new SensorField(field1);
			SensorField sf2 = new SensorField(field2);
			SensorField sf3 = new SensorField(field3);
			SensorField sf4 = new SensorField(field4);
			
			SensorField[] fields = {sf1, sf2, sf3, sf4};
			
			Location location = new Location(latDeg, latMin, latSec, lonDeg, lonMin, lonSec);
			
			conn.close();
			return new Sensor(sensorIdentifier, serialNumber, type, manufacturer, locDescription, precision, accuracy, location, fields);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println("error retrieving sensor with identieifer <" + ownerEmail + "> and rule name <"+ruleName+"> from rule table");
		}
		
		return null;
	}
	
	public boolean set(Sensor sensor)
	{
		Connection conn = null;
		String driver= "com.mysql.jdbc.Driver";
		String dbName = "jdbc:mysql://earth.cs.utep.edu";
		String dbUserName = "sespring2";
		String dbPassword = "cs4311!sespring2";
		
		try {
			Class.forName(driver);
			
			conn = (Connection) DriverManager.getConnection(dbName, dbUserName, dbPassword);
			
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
			
			Location location = sensor.getLocation();
			int latDeg = location.getLatitudeDegree();
			int latMin = location.getLatitudeMinute();
			int latSec = location.getLatitudeSecond();
			int lonDeg = location.getLongitudeDegree();
			int lonMin = location.getLongitudeMinute();
			int lonSec = location.getLongitudeSecond();
			
			String changeDB = "use sespring2;";
			String query = "";
			if(sensorExists(sensor.getIdentifier()))
			{
				//sensor exists so update the values
				query = "UPDATE sensor SET identifier = '"+identifier+"', serial_number = '"+serialNumber+"', type = '"+type+"', manufacturer = '"+manufacturer+"', locDescription = '"+locDescription+"', precisionValue = "+precisionValue+", accuracy = "+accuracy+", "
						+ "field1 = '"+field1+"', field2 = '"+field2+"', field3 = '"+field3+"', field4 = '"+field4+"', latDegree = "+latDeg+", latMinute = "+latMin+", latSecond = "+latSec+", longDegree = "+lonDeg+", longMinute = "+lonMin+", longSecond = "+lonSec+""
						+ " WHERE identifier = '" + identifier + "';";
			}
			else
			{
				//sensor doesnt exist so insert new values
				query = "INSERT INTO sensor VALUES('"+identifier+"', '"+serialNumber+"', '"+type+"', '"+manufacturer+"', '"+locDescription+"', "+precisionValue+", "+accuracy+", "
						+ "'"+field1+"', '"+field2+"', '"+field3+"', '"+field4+"', "
						+ " "+latDeg+", "+latMin+", "+latSec+", "+lonDeg+", "+lonMin+", "+lonSec+");";
			}
			
			//System.out.println("query is: " + query);
			
			Statement state = (Statement) conn.createStatement();
			state.execute(changeDB);
			/*TODO int success =*/ state.executeUpdate(query);
						
			
			conn.close();
			return true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error saving sensor to table");
			//e.printStackTrace();
			return false;
		}
	}
	public boolean sensorExists(String identifier)
	{
		Connection conn = null;
		String driver= "com.mysql.jdbc.Driver";
		String dbName = "jdbc:mysql://earth.cs.utep.edu";
		String name = "sespring2";
		String password = "cs4311!sespring2";
		
		try {
			Class.forName(driver);
			
			conn = (Connection) DriverManager.getConnection(dbName, name, password);
			Statement state = (Statement) conn.createStatement();
			String changeDB = "use sespring2;";
			String query = "select * from sensor where identifier = '"+identifier+"';";
			state.execute(changeDB);
			ResultSet result = state.executeQuery(query);
			result.first();
			

			String sensorIdentifier = result.getString(1);
			
			conn.close();
			
			if(sensorIdentifier.equals(identifier))
				return true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		return false;
	}

}
