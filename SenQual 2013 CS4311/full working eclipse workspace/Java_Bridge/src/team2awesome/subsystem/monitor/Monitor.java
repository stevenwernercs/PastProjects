package team2awesome.subsystem.monitor;

import java.util.LinkedList;

import team2awesome.subsystem.database.DBMonitor;
import team2awesome.subsystem.database.DataSet;
import team2awesome.subsystem.database.XDBFileStorage;
import team2awesome.subsystem.gui.GUIDash;
import team2awesome.subsystem.profile.Mail;
import team2awesome.subsystem.rule.RuleProcessed;

public class Monitor {
	
	//attributes of a monitor
	private String owner = "";
	private String name = "";
	
	//add all rule name names
	private LinkedList<String> ruleNames = new LinkedList<String>();
	
	//add all rules shared
	//convetion is "rule_name" followed by "owner_email" element in list
	private LinkedList<String> sharedRules = new LinkedList<String>();

	private LinkedList<MonitoredData> processedList = new LinkedList<MonitoredData>();
	
	public Monitor(String owner, String name, LinkedList<String> ruleNames, LinkedList<String> sharedRules, LinkedList<MonitoredData> processedList) {
		this.owner = owner;
		this.name = name;
		this.ruleNames = ruleNames;
		this.sharedRules = sharedRules;
		this.processedList = processedList;
	}
	
	public Monitor(String owner, String name, String[][] monitorWithRules) 
	{
		this.owner = owner;
		this.name = name;
		LinkedList<String> allRules = new LinkedList<String>();
		
		for(String[] eachMonitor : monitorWithRules)
		{
			boolean firstIdx = true;
			for(String eachRule : eachMonitor)
				if(firstIdx)
				{
					if(name.equals(eachRule))
						firstIdx = false;
					else
						continue;
				}
				else
					allRules.add(eachRule);
		}
		this.ruleNames=allRules;
	}

	public LinkedList<MonitoredData> getProcessedList() {
		return processedList;
	}

	public void setProcessedList(LinkedList<MonitoredData> processedList) {
		this.processedList = processedList;
	}

	public LinkedList<String> getSharedRules() {
		return sharedRules;
	}

	public void setSharedRules(LinkedList<String> sharedRules) {
		this.sharedRules = sharedRules;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<String> getRuleNames() {
		return ruleNames;
	}

	public void setRuleNames(LinkedList<String> ruleNames) {
		this.ruleNames = ruleNames;
	}
	
	public static Monitor[] buildSessions(String owner, String monitorStr[], String[][] monitorWithRules)
	{
		Monitor[] monitors = new Monitor[monitorStr.length];
		System.out.println(owner+"="+monitorStr.length);
	
		for(int i = 0; i < monitors.length; i++)
		{
			//initiate monitor-> monitors[i]
			System.out.println(monitorStr[i]);
			monitors[i] = new Monitor(owner, monitorStr[i], monitorWithRules);	
		}
		return monitors;
	}

	public void run(Mail mail) 
	{
		for(String ruleName : this.ruleNames)
		{
			RuleProcessed rule = RuleProcessed.getRuleProcessed(this.owner, this.name, ruleName, mail);
			if(rule==null)
			{
				System.out.println("Failed to create Rule... " + ruleName);
				return;
			}
			DataSet[] done = rule.runSessionInSeperateThread();
			System.out.println("DONE SUBMIT TO DATABASE");
			DBMonitor.setAllHistoricalDataSet(this.owner, done);
			GUIDash.addToCompleted(done);
		}
		return;
	}
}

