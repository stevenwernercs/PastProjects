package team2awesome.subsystem.sensor;

public class Location {
	
	private int latitudeDegree = 0;
	private int latitudeMinute = 0;
	private int latitudeSecond = 0;
	private int longitudeDegree = 0;
	private int longitudeMinute = 0;
	private int longitudeSecond = 0;
	
	public Location(int latitudeDegree, int latitudeMinute, int latitudeSecond,
			int longitudeDegree, int longitudeMinute, int longitudeSecond) {
		super();
		this.latitudeDegree = latitudeDegree;
		this.latitudeMinute = latitudeMinute;
		this.latitudeSecond = latitudeSecond;
		this.longitudeDegree = longitudeDegree;
		this.longitudeMinute = longitudeMinute;
		this.longitudeSecond = longitudeSecond;
	}

	public int getLatitudeDegree() {
		return latitudeDegree;
	}

	public void setLatitudeDegree(int latitudeDegree) {
		this.latitudeDegree = latitudeDegree;
	}

	public int getLatitudeMinute() {
		return latitudeMinute;
	}

	public void setLatitudeMinute(int latitudeMinute) {
		this.latitudeMinute = latitudeMinute;
	}

	public int getLatitudeSecond() {
		return latitudeSecond;
	}

	public void setLatitudeSecond(int latitudeSecond) {
		this.latitudeSecond = latitudeSecond;
	}

	public int getLongitudeDegree() {
		return longitudeDegree;
	}

	public void setLongitudeDegree(int longitudeDegree) {
		this.longitudeDegree = longitudeDegree;
	}

	public int getLongitudeMinute() {
		return longitudeMinute;
	}

	public void setLongitudeMinute(int longitudeMinute) {
		this.longitudeMinute = longitudeMinute;
	}

	public int getLongitudeSecond() {
		return longitudeSecond;
	}

	public void setLongitudeSecond(int longitudeSecond) {
		this.longitudeSecond = longitudeSecond;
	}
	
	

}
