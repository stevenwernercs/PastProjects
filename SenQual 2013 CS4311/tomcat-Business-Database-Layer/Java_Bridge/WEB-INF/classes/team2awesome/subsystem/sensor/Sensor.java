package team2awesome.subsystem.sensor;

public class Sensor {
	
	private String identifier = "";
	private String serialNumber = "";
	private String type = "";
	private String manufacturer = "";
	private String locationDescription = "";
	private double precision = 0;
	private double accuracy = 0;
	
	private Location location = null;
	private SensorField[] fields = new SensorField[4];
	
	
	
	public Sensor(String identifier, String serialNumber, String type,
			String manufacturer, String locationDescription, double precision,
			double accuracy, Location location, SensorField[] fields) {
		super();
		this.identifier = identifier;
		this.serialNumber = serialNumber;
		this.type = type;
		this.manufacturer = manufacturer;
		this.locationDescription = locationDescription;
		this.precision = precision;
		this.accuracy = accuracy;
		this.location = location;
		this.fields = fields;
	}
	
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getLocationDescription() {
		return locationDescription;
	}
	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}
	public double getPrecision() {
		return precision;
	}
	public void setPrecision(double precision) {
		this.precision = precision;
	}
	public double getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public SensorField[] getFields() {
		return fields;
	}
	public void setFields(SensorField[] fields) {
		this.fields = fields;
	}
	
	
	

}
