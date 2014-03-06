package team2awesome.subsystem.sensor;

public class SensorField {
	
	private String fieldName = "";
	private String unit = "";
	
	public SensorField(String f, String u)
	{
		if(f==null || f.equals("<field name>"))
			f="";
		if(u==null || u.equals("<unit measure>"))
			u="";
		this.fieldName = f;
		this.unit = u;
	}
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}

