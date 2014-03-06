package team2awesome.subsystem.sensor;

public class SensorField {
	
	private String fieldName = "";
	
	public SensorField(String f)
	{
		this.fieldName = f;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
