package team2awesome.subsystem.sensor;

public class Location {
	
	private int latitudeDegree = 0;
	private int latitudeMinute = 0;
	private int latitudeSecond = 0;
	private int longitudeDegree = 0;
	private int longitudeMinute = 0;
	private int longitudeSecond = 0;
	
	//derived attributes
	private float latFULL;
	private float lonFULL;
	
	public Location(int latitudeDegree, int latitudeMinute, int latitudeSecond,
			int longitudeDegree, int longitudeMinute, int longitudeSecond) 
	{
		this.latitudeDegree = latitudeDegree;
		this.latitudeMinute = latitudeMinute;
		this.latitudeSecond = latitudeSecond;
		this.longitudeDegree = longitudeDegree;
		this.longitudeMinute = longitudeMinute;
		this.longitudeSecond = longitudeSecond;
		
		this.setLatFULL((float)(this.latitudeDegree+this.latitudeMinute/60.0+this.longitudeSecond/3600.0));
		this.setLonFULL((float)(this.longitudeDegree+this.longitudeMinute/60.0+this.longitudeSecond/3600.0));
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

	/**
	 * @return the lonFULL
	 */
	public float getLonFULL() {
		return lonFULL;
	}

	/**
	 * @param lonFULL the lonFULL to set
	 */
	public void setLonFULL(float lonFULL) {
		this.lonFULL = lonFULL;
	}

	/**
	 * @return the latFULL
	 */
	public float getLatFULL() {
		return latFULL;
	}

	/**
	 * @param latFULL the latFULL to set
	 */
	public void setLatFULL(float latFULL) {
		this.latFULL = latFULL;
	}
}
