package team2awesome.subsystem.gui;

import javax.servlet.annotation.WebServlet;

import team2awesome.subsystem.sensor.Location;
import team2awesome.subsystem.sensor.Sensor;
import team2awesome.subsystem.sensor.SensorField;

/**
 * Servlet implementation class Server
 */
@WebServlet("/GUISensor")
public class GUISensor extends GUI
{

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUISensor() {}
	
	public String printSensorArray()
	{
		return Sensor.printSensorArray();
	}
	
	public String [] updateSensor(String identifier, String serialNumber, String type,
			String locationDescription, String precisionStr, float latitudeDegree, float longitudeDegree, 
			String f1, String u1, String f2, String u2, String f3, String u3, String f4, String u4)
	{
		precisionStr=precisionStr.replace("%", "");
		float latitudeMinute = (latitudeDegree%1)*60;
		float latitudeSecond = (latitudeMinute%1)*60;
		float longitudeMinute = (longitudeDegree%1)*60;
		float longitudeSecond = (longitudeMinute%1)*60;
	
		Sensor s = Sensor.get(identifier);
		if(s==null)
		{	//new Sensor
			try
			{
				float precision = Float.parseFloat(precisionStr);
				Location l = new Location((int)latitudeDegree, (int)latitudeMinute, (int)latitudeSecond,
						(int)longitudeDegree, (int)longitudeMinute, (int)longitudeSecond);
		
				SensorField [] f = new SensorField[]{new SensorField(f1, u1),
						new SensorField(f2, u2), new SensorField(f3, u3),
						new SensorField(f4, u4)};
		
				s = new Sensor(identifier, serialNumber, type,
						"unknown", locationDescription, precision,
						.001, l, f);
			
				if(s.upload())
					return new String [] {TRUE, "New sensor with unique identifier '" + identifier + "' Created", identifier};
				return new String [] {FALSE, "Unable to create new sensor with unique identifier '" + identifier + "'"};
			}
			catch (Exception e)
			{
				return new String [] {FALSE, "Unable to create sensor with unique identifier '" + identifier + "'<br>" +
				"Please ensure the sensors 'precision' is a number (% sign optional)."};	
			}
		}
		else
		{//update Sensor
		try
		{
			float precision = Float.parseFloat(precisionStr);
			String [] msg = {TRUE, "", identifier};
			if(!s.getSerialNumber().equals(serialNumber)) {s.setSerialNumber(serialNumber); addMsg(msg, "Serial Number updated");}
			if(!s.getType().equals(type)) {s.setType(type); addMsg(msg, "Type updated");}
			if(!s.getLocationDescription().equals(locationDescription)) {s.setLocationDescription(locationDescription); addMsg(msg, "Location Description updated");}
			if(s.getPrecision()!=precision) {s.setPrecision(precision); addMsg(msg, "Precision updated");}
			if(s.getLocation().getLatitudeDegree()!=(int)latitudeDegree) {s.getLocation().setLatitudeDegree((int)latitudeDegree); addMsg(msg, "Latitude Degree updated");}
			if(s.getLocation().getLatitudeMinute()!=(int)latitudeMinute) {s.getLocation().setLatitudeMinute((int)latitudeMinute); addMsg(msg, "Latitude Minute updated");}
			if(s.getLocation().getLatitudeSecond()!=(int)latitudeSecond) {s.getLocation().setLatitudeSecond((int)latitudeSecond); addMsg(msg, "Latitude Second updated");}
			if(s.getLocation().getLongitudeDegree()!=(int)longitudeDegree) {s.getLocation().setLongitudeDegree((int)longitudeDegree); addMsg(msg, "Longitude Degree updated");}
			if(s.getLocation().getLongitudeMinute()!=(int)longitudeMinute) {s.getLocation().setLongitudeMinute((int)longitudeMinute); addMsg(msg, "Longitude Minute updated");}
			if(s.getLocation().getLongitudeSecond()!=(int)longitudeSecond) {s.getLocation().setLongitudeSecond((int)longitudeSecond); addMsg(msg, "Longitude Second updated");}
		
			SensorField [] fOld = s.getFields();
			SensorField [] fNew = new SensorField[] { new SensorField(f1, u1),
				new SensorField(f2, u2), new SensorField(f3, u3),
				new SensorField(f4, u4)};
		
			if(fOld.length!=4 || fNew.length!=4)
				System.out.println("ERROR EXPECT EQUAL TO 4");
		
			for(int i = 0; i<4; i++)
			{
				
				if(!(fOld[i].getFieldName().equals(fNew[i].getFieldName()))) {fOld[i].setFieldName(fNew[i].getFieldName()); addMsg(msg, "Field"+i+" Name updated");}
				if(!(fOld[i].getUnit().equals(fNew[i].getUnit()))) {fOld[i].setUnit(fNew[i].getUnit()); addMsg(msg, "Field"+i+" Unit updated");}
			}
		
			
			if(msg[1].length()==0)
			{
				msg[1]="No changes were specified";
			}
			else
			{
				msg[1]="<b>Udpated "+identifier+":</b><br>"+msg[1];
			}
			if(!s.upload())
			{
				msg[0]=FALSE;
				msg[1]=msg[1].replaceAll("updated", "failed to update");
				msg[1]="Failed to connect to Database<br/>"+msg[1];
			}
			System.out.println(msg[0]+" "+msg[1]);
			return msg;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new String [] {FALSE, "Unable to modify sensor with unique identifier '" + identifier + "'<br>" +
				"Please ensure the sensors 'precision' is a number (% sign optional)."};
		}
		}
	}
	private String [] addMsg(String[] msg, String append) 
	{	
		msg[1]+=append+"<br/>";
		return msg;
	}
}
