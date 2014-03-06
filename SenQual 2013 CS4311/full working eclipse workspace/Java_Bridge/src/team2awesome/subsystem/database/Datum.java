package team2awesome.subsystem.database;

public class Datum {

	Time timeStamp = null;
	private double reading;
	private int flag = 0;
	private String sensorName;
	private String fieldName;
	private String unit;
	 
	Datum(double reading, Time timeStamp)
	{
		this.reading = reading;
		this.timeStamp = timeStamp;
	}
	Datum(double reading, String timeStamp)
	{
		this.reading = reading;
		this.timeStamp = new Time(timeStamp);
	}
	Datum(double reading, Time timeStamp, int flag)
	{
		this.reading = reading;
		this.timeStamp = timeStamp;
		this.flag = flag;
	}
	Datum(double reading, String timeStamp, int flag, String [] sensorInfo)
	{
		this.reading = reading;
		this.timeStamp = new Time(timeStamp);
		this.flag = flag;
		this.sensorName=sensorInfo[0];
		this.fieldName=sensorInfo[1];
		this.unit=sensorInfo[2];
	}

	/**
	 * start 				= 1
	 * reset 				= 2
	 * anomally				= 4
	 * Potiential anomally 	= 8
	 * @param flag
	 * @return
	 */
	public boolean addFlag(int flag)
	{
		this.flag += flag;
		return true;
	}
	
	public boolean resetFlag(int flag)
	{
		this.flag = 0;
		return true;
	}
	
	public Time getTime()
	{
		return this.timeStamp;
	}
	
	public double getReading() {
		return reading;
	}

	public void setReading(double reading) {
		this.reading = reading;
	}

	public Time getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Time timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getString()
	{
		return this.reading + ";" + this.timeStamp.getTimeStamp() + ";" + flag;
	}
	/**
	 * @return the sensorName
	 */
	public String getSensorName() {
		return sensorName;
	}
	/**
	 * @param sensorName the sensorName to set
	 */
	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String print()
	{
		return this.sensorName +"." + this.fieldName + "@" + this.unit + " | " + this.timeStamp.getTimeShort() + " | " + this.flag + " | " + this.reading;
	}
}
