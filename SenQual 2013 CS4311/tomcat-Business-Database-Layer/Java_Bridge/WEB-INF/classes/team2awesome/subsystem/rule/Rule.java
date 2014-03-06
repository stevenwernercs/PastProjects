package team2awesome.subsystem.rule;

public class Rule {
	private String ownerEmail;
	private String name;
	private String description;
	private int duration;
	private int definition;
	private String lboundSensor;
	private String lboundFormula;
	private String rboundSensor;
	private String rboundFormula;
	private String premiseSensor;
	private String premiseFormula;
	private String booleanSensor;
	private String booleanFormula;
	
	public Rule(String ownerEmail, String ruleName, String description, int ruleDuration, int ruleDefinition
			, String leftBoundSensor, String leftBoundFormula, String rightBoundSensor, String rightBoundFormula
			, String premiseSensor, String premiseFormula, String booleanSensor, String booleanFormula)
	{
		this.ownerEmail = ownerEmail;
		this.name = ruleName;
		this.description = description;
		this.duration = ruleDuration;
		this.definition = ruleDefinition;
		this.lboundSensor = leftBoundSensor;
		this.lboundFormula = leftBoundFormula;
		this.rboundSensor = rightBoundSensor;
		this.rboundFormula = rightBoundFormula;
		this.premiseSensor = premiseSensor;
		this.premiseFormula = premiseFormula;
		this.booleanSensor = booleanSensor;
		this.booleanFormula = booleanFormula;
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
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getDefinition() {
		return definition;
	}

	public void setDefinition(int definition) {
		this.definition = definition;
	}

	public String getLboundSensor() {
		return lboundSensor;
	}

	public void setLboundSensor(String lboundSensor) {
		this.lboundSensor = lboundSensor;
	}

	public String getLboundFormula() {
		return lboundFormula;
	}

	public void setLboundFormula(String lboundFormula) {
		this.lboundFormula = lboundFormula;
	}

	public String getRboundSensor() {
		return rboundSensor;
	}

	public void setRboundSensor(String rboundSensor) {
		this.rboundSensor = rboundSensor;
	}

	public String getRboundFormula() {
		return rboundFormula;
	}

	public void setRboundFormula(String rboundFormula) {
		this.rboundFormula = rboundFormula;
	}

	public String getPremiseSensor() {
		return premiseSensor;
	}

	public void setPremiseSensor(String premiseSensor) {
		this.premiseSensor = premiseSensor;
	}

	public String getPremiseFormula() {
		return premiseFormula;
	}

	public void setPremiseFormula(String premiseFormula) {
		this.premiseFormula = premiseFormula;
	}

	public String getBooleanSensor() {
		return booleanSensor;
	}

	public void setBooleanSensor(String booleanSensor) {
		this.booleanSensor = booleanSensor;
	}

	public String getBooleanFormula() {
		return booleanFormula;
	}

	public void setBooleanFormula(String booleanFormula) {
		this.booleanFormula = booleanFormula;
	}
	
	

}
