package team2awesome.subsystem.rule;

import java.util.Collection;
import java.util.HashSet;

import team2awesome.subsystem.database.DBRule;

public class RuleInfo {
	 
	protected HashSet<String> sensorSet = new HashSet<String>(); //hold all sensors needed to run this rule
	protected String ownerEmail;
	protected String name;
	protected String description;
	protected int durationType;
	protected int definitionType;
	
	//private String lboundSensor;
	//private String premiseSensor;
	//private String rboundSensor;
	//private String booleanSensor;
	
	protected Formula lboundFormula;
	protected Formula rboundFormula;
	protected Formula premiseFormula;
	protected Formula booleanFormula;
	protected boolean isShared;
	
	public RuleInfo(String ownerEmail, String ruleName, String description, int ruleDuration, int ruleDefinition
			, String leftBoundFormula, String rightBoundFormula, String premiseFormula, String booleanFormula
			, boolean isShared, boolean isSubclass)
	{
		this.ownerEmail = ownerEmail;
		this.name = ruleName;
		this.description = description;
		
		this.durationType = ruleDuration;
		this.definitionType = ruleDefinition;
		
		this.isShared = isShared;
		
		if(!isSubclass)
		{
			this.lboundFormula = new Formula(leftBoundFormula);
			this.rboundFormula = new Formula(rightBoundFormula);
			this.premiseFormula = new Formula(premiseFormula);
			this.booleanFormula = new Formula(booleanFormula);
		}
		
		//this.lboundSensor = leftBoundSensor;
		//this.rboundSensor = rightBoundSensor;
		//this.premiseSensor = premiseSensor;
		//this.booleanSensor = booleanSensor;
	}
	
	//VALIDATE FORMULAS AND GIVE ERROR FEED BACK
	public String validErrMsg() 
	{
		String errMsg = 
				(err(1) ? (lboundFormula.getErrMsg() !=null ? "   Left Boundary Sub-formula"     + lboundFormula.getErrMsg()  + "<br/>": "") : "")+
				(err(2) ? (rboundFormula.getErrMsg() !=null ? "   Right Boundary Sub-formula"    + rboundFormula.getErrMsg()  + "<br/>": "") : "")+
				(err(3) ? (premiseFormula.getErrMsg()!=null ? "   Premise Sub-formula" 		     + premiseFormula.getErrMsg() + "<br/>": "") : "")+
				(err(4) ? (booleanFormula.getErrMsg()!=null ? "   Boolean Statement Sub-formula" + booleanFormula.getErrMsg() + "<br/>": "") : "");
		if(errMsg.length()==0)
			return null;
		return "Errors must be corrected:<br/>" + errMsg;
	}
	
	private boolean err(int i) 
	{
		switch (i)
		{
			case 1: return (this.durationType==2 || this.durationType==4 || this.durationType==5); 
			case 2: return (this.durationType==3 || this.durationType==4 || this.durationType==5); 
			case 3: return this.definitionType==5;
			case 4: 
			default: return true;
		}
	}

	public static RuleInfo getRuleInfo(String ownerEmail, String ruleName)
	{
		return DBRule.getRuleInfo(ownerEmail, ruleName);
	}
	
	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return durationType;
	}

	public void setDuration(int duration) {
		this.durationType = duration;
	}

	public int getDefinition() {
		return definitionType;
	}

	public void setDefinition(int definition) {
		this.definitionType = definition;
	}

	public String getLboundFormula() {
		return this.lboundFormula.getFormula();
	}

	public String getRboundFormula() {
		return this.rboundFormula.getFormula();
	}

	public String getPremiseFormula() {
		return this.premiseFormula.getFormula();
	}

	public String getBooleanFormula() {
		return this.booleanFormula.getFormula();
	}
	
	public boolean isShared() {
		return isShared;
	}
	
	public void setShared(boolean isShared) {
		this.isShared = isShared;
	}
	
	public boolean addSensor(String[] sensor)
	{
		return this.sensorSet.add(sensor[0]+"."+sensor[1]+"@"+sensor[2]);
	}
	
	public boolean addAllSensors(Collection<String> sensors)
	{
		for(String sensor : sensors)
			this.sensorSet.add(sensor);
		return true;
	}
	
	public HashSet<String> getSensorList()
	{
		return this.sensorSet;
	}
	
	public String [][] getSensorArray()
	{
		String [][] result = new String[this.sensorSet.size()][];
		int i=0;
		for(String each : this.sensorSet)
		{
			String [] temp1 = each.split("\\.");
			String [] temp2 = temp1[1].split("@");
			result[i++]=new String[]{temp1[0],temp2[0],temp2[1]};
		}
		
		return result;
	}
}
