package team2awesome.subsystem.rule;

import java.util.Date;

import team2awesome.subsystem.database.DBMonitor;
import team2awesome.subsystem.database.DBRule;
import team2awesome.subsystem.database.DataSet;
import team2awesome.subsystem.database.Datum;
import team2awesome.subsystem.database.XDBFileStorage;
import team2awesome.subsystem.monitor.Monitor;
import team2awesome.subsystem.profile.Mail;
import team2awesome.subsystem.profile.Profile;

public class RuleProcessed extends Rule{

	String monitorName;
	private Mail mail;
	public RuleProcessed(String ownerEmail, String ruleName, String description
			, int ruleDuration, int ruleDefinition, String leftBoundFormula
			, String rightBoundFormula, String premiseFormula, String booleanFormula,
			boolean isShared, String monitorName, Mail mail) {
		super(ownerEmail, ruleName, description, ruleDuration, ruleDefinition
				, leftBoundFormula, rightBoundFormula
				, premiseFormula, booleanFormula, isShared);
		this.monitorName = monitorName;
		this.mail = mail;
	}

	//TODO to be done in seperate thread...
	
	
	public static RuleProcessed getRuleProcessed(String email, String monitorName, String ruleName, Mail mail)
	{
		RuleInfo ri = DBRule.getRuleInfo(email, ruleName);
		if(ri==null)
			return null;
		return new RuleProcessed(ri.ownerEmail, ri.name, ri.description, ri.durationType, ri.definitionType
				, ri.lboundFormula.getFormulaString(), ri.rboundFormula.getFormulaString()
				, ri.premiseFormula.getFormulaString(), ri.booleanFormula.getFormulaString()
				, ri.isShared, monitorName, mail);
	}
	
	public DataSet[] runSessionInSeperateThread()
	{
		String[][] allSensors = getSensorArray();
		
		for(String [] row : allSensors)
			System.out.println("SENSOR: "+row[0] + "." + row[1] + "@" + row[2]);
		
		DataSet[] data = XDBFileStorage.getAllSensorData(this.ownerEmail, this.name, this.description, this.monitorName, allSensors);
	
		Profile p = Profile.getProfile(this.ownerEmail);
		if(p==null)
			return null;
		
		System.out.println("RULE MADE");
		for(String[] elem : allSensors)
			System.out.println(elem[0]+"."+elem[1]+"@"+elem[2]);
		
		System.out.println("ruleDuration: "+durationType);
		
		System.out.println("Length of data:"+data[0].getDatums().length);
		
		//holder of result!
		Datum[][] finishedData = new Datum[data.length][];
		
		int i = 0;
		for(DataSet each :data)
			finishedData[i++] = new Datum[each.getDatums().length];
		
		
		Datum[][] holdSlice;

		i=0;
		int minLengthData = getMinLengthData(data);
		for(int sliceIndex=0; sliceIndex < minLengthData; sliceIndex++)
		{
			holdSlice = executeRuleCheck(getAllSlice(data, sliceIndex));
			alert(join(finishedData,i,holdSlice, minLengthData), p);
			i+=(holdSlice!=null?holdSlice.length:0);
		}
		join(finishedData,i,thisFromStartPotientialsAreNOTAnomally().toArray(new Datum[anamallyRegion.size()][]), minLengthData);
		
		i=0;
		Date sameForAll = new Date();
		for(DataSet each :data)
		{
			each.setDatums(finishedData[i++]);
			each.setTimeRan(sameForAll);
		}
		return data;
	}
	
	void alert(Datum[][] datum, Profile p) 
	{	//new thread
		if(datum==null)
			return;
		p.sendAlert(this.monitorName, this.name, this.mail);
		return;
	}

	private int getMinLengthData(DataSet[] data) 
	{
		int shortest = Integer.MAX_VALUE;
		for(DataSet each : data)
			if(each.getDatums().length<shortest)
				shortest = each.getDatums().length;
		return shortest;
	}

	private Datum[] getAllSlice(DataSet[] data, int sliceIndex) 
	{
		int i = 0;
		Datum[] result = new Datum[data.length];
		for(DataSet each : data)
			result[i++]=each.getDatums()[sliceIndex];
		return result;
	}

	private Datum[][] join(Datum[][] finisedData, int index, Datum[][] executeRuleCheck, int minLengthData) 
	{
		if(executeRuleCheck==null)
			return null;
		int lenF = index;
		int lenE = executeRuleCheck.length;
		System.out.println(lenF + " " + lenE);
		for(int i=0; i<finisedData.length; i++)
			for(int j=0; j<lenE; j++)
				finisedData[i][lenF+j]=executeRuleCheck[j][i];
		return executeRuleCheck;
	}
	
	public static void startSeperateThread(String owner, String monitors[])
	{
		
		class MyThread implements Runnable 
		{
			String owner;
			String[] monitors;
			Mail mail;
			
			public MyThread(String owner, String[] monitors)
			{
				this.owner = owner;
				this.monitors = monitors;
				this.mail = new Mail();
			}
			
			public void run() 
			{
				 try
		     		{
		     			String[][] monitorWithRules=DBMonitor.getMonitorWithRules(owner);
		     			Monitor[] monitorsToRun = Monitor.buildSessions(owner, monitors, monitorWithRules);
		     			for(Monitor each : monitorsToRun)
		     			{
		     				System.out.println("Child: thread is NOW RUNNING :" + each.getName());
		     				each.run(mail);
			     			System.out.println("######FINISHED RUNNING: "+owner+each.getName());
		     			}
		     		}
		     		catch(Exception e)
		     		{
		     			System.out.println("######FAIL SESSION: "+owner);
		     			e.printStackTrace();
		     			return;
		     		}
				 System.out.println("######SUCCESS SESSION: "+owner);
			}
		}
		
		Runnable r = new MyThread(owner, monitors);
		new Thread(r).start();
		System.out.println("Parent: thread is RUNNING!!!");
	}
	
	public static void start(String owner, String monitors[])
	{
		try
		{
		 	String[][] monitorWithRules=DBMonitor.getMonitorWithRules(owner);
		 	Monitor[] monitorsToRun = Monitor.buildSessions(owner, monitors, monitorWithRules);
		 	for(Monitor each : monitorsToRun)
		 	{
		 		System.out.println("NOW RUNNING :" + each.getName());
		 		each.run(new Mail());
	 			System.out.println("######FINISHED RUNNING: "+owner+each.getName());
		    }
		}
		catch(Exception e)
		{
			System.out.println("######FAIL SESSION: "+owner);
		  	e.printStackTrace();
		  	return;
		}
		System.out.println("######SUCCESS SESSION: "+owner);
	}
}
