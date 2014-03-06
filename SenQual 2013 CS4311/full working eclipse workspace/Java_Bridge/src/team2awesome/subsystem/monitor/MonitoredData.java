package team2awesome.subsystem.monitor;

public class MonitoredData {
	
	private String ruleName = "";
	private String rawHistorical = "";
	private String historicalAnomaly = "";
	private String rawNearRealTime = "";
	private String nearRealTimeAnomaly = "";
	
	public MonitoredData(String ruleName, String rawHistorical,	String historicalAnomaly, String rawNearRealTime, String nearRealTimeAnomaly) 
	{
		this.ruleName = ruleName;
		this.rawHistorical = rawHistorical;
		this.historicalAnomaly = historicalAnomaly;
		this.rawNearRealTime = rawNearRealTime;
		this.nearRealTimeAnomaly = nearRealTimeAnomaly;
	}
	
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRawHistorical() {
		return rawHistorical;
	}
	public void setRawHistorical(String rawHistorical) {
		this.rawHistorical = rawHistorical;
	}
	public String getHistoricalAnomaly() {
		return historicalAnomaly;
	}
	public void setHistoricalAnomaly(String historicalAnomaly) {
		this.historicalAnomaly = historicalAnomaly;
	}
	public String getRawNearRealTime() {
		return rawNearRealTime;
	}
	public void setRawNearRealTime(String rawNearRealTime) {
		this.rawNearRealTime = rawNearRealTime;
	}
	public String getNearRealTimeAnomaly() {
		return nearRealTimeAnomaly;
	}
	public void setNearRealTimeAnomaly(String nearRealTimeAnomaly) {
		this.nearRealTimeAnomaly = nearRealTimeAnomaly;
	}
	
	

}

