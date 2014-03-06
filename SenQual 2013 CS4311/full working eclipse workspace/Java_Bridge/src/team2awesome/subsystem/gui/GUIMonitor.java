package team2awesome.subsystem.gui;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;

import team2awesome.subsystem.database.DBMonitor;
import team2awesome.subsystem.database.DBRule;
import team2awesome.subsystem.monitor.Monitor;
import team2awesome.subsystem.rule.RuleProcessed;

@WebServlet("/GUIMonitor")
public class GUIMonitor extends GUI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	////////////////////////////
	//adding and deleting rules
	////////////////////////////
	//java_context()->getServlet()->addRulesToMonitors($_SESSION['EMAIL'], $_POST['rule'], $_POST['monitors']);
	public void addRulesToMonitors(String owner, String rules[], String []monitors)
	{
		DBMonitor monitorDB = new DBMonitor();
		
		String[] ownedRules = DBRule.allRuleNames(owner);
		LinkedList<String> sharedRules = DBRule.getAllSharedRulesNames(owner);
		
		LinkedList<String> rulesToAdd = new LinkedList<String>();

		//find rules owned by owner
		if(ownedRules!=null)
		{
			for(int i = 0; i < ownedRules.length; i++)
			{
				for(int j = 0; j < rules.length; j++)
				{
					if(rules[j].equals(ownedRules[i]))
					{
						rulesToAdd.add(rules[j]);
						rulesToAdd.add(owner);
						rules[j] = "";
					}
				}
			}
		}
		
		//find rules that are shared
		if(sharedRules!=null)
		{
			for(Iterator<String> liter = sharedRules.iterator(); liter.hasNext();)
			{
				String sharedRule = liter.next();
				String sharedOwner = liter.next();
				for(int i = 0; i < rules.length; i++)
				{
					if(sharedRule.equals(rules[i]))
					{
						rulesToAdd.add(sharedRule);
						rulesToAdd.add(sharedOwner);
					}
				}
			}
		}
		
		for(Iterator<String> liter = rulesToAdd.iterator(); liter.hasNext(); )
		{
			String ruleName = liter.next();
			String sharedOwner = liter.next();
			
			for(int i = 0; i < monitors.length; i++)
			{
				monitorDB.addARule(owner, monitors[i], ruleName, sharedOwner);
			}
		}
		
	}
	//java_context()->getServlet()->deleteRulesFromMonitors($_POST['EMAIL'], $_POST['monitorAndRule']);
	public void deleteRulesFromMonitors(String owner, String[] monitorAndRule)
	{
		DBMonitor monitorDB = new DBMonitor();
		if(monitorAndRule==null)
			return;
		for(int i = 0; i < monitorAndRule.length; i++)
		{
			Scanner splitter = new Scanner(monitorAndRule[i]);
			splitter.useDelimiter(",");
			
			String monitor = splitter.next();
			String rule = splitter.next();
			
			monitorDB.deleteRuleFromMonitor(owner, monitor, rule);
		}
	}
	
	////////////////////////////
	//new, renaming or shaing a monitor
	////////////////////////////
	//java_context()->getServlet()->createNewMonitor($_SESSION['EMAIL'], $_POST['newMonitorName']);
	public void createNewMonitor(String owner, String newMonitorName)
	{
		DBMonitor monitorDB = new DBMonitor();
		
		monitorDB.setMonitorName(owner, newMonitorName);
	}
	//java_context()->getServlet()->renameAMonitor($_SESSION['EMAIL'], $_POST['monitors'], $_POST['newMonitorName']);
	public void renameAMonitor(String owner, String monitors[], String newMonitorName)
	{
		DBMonitor monitorDB = new DBMonitor();
		
		monitorDB.rename(owner, monitors[0], newMonitorName);
	}
	//java_context()->getServlet()->shareAMonitor($_SESSION['EMAIL'], $_POST['monitors'], $_POST['shareeEmail']);
	public void shareAMonitor(String owner, String monitors[], String shareeEmail)
	{
		DBMonitor monitorDB = new DBMonitor();
		
		if(monitors==null)
			return;
		for(int i = 0; i < monitors.length; i++)
		{
			Monitor monitorToShare = monitorDB.get(owner, monitors[i]);
			monitorDB.share(owner, monitorToShare, shareeEmail);
			
		}
	}
	
	/////////////////////////////////////////
	//session buttons NearRealTime and Logged Data
	///////////////////////////////////////
	//java_context()->getServlet()->sessionNearRealTime($_SESSION['EMAIL'], $_POST['monitors']);
	public void sessionNearRealTime(String owner, String monitors[])
	{
		if(monitors==null)
			return;
		
		System.out.println(owner+"="+monitors.length);
		
		for(int i = 0; i < monitors.length; i++)
		{
			//initiate monitor-> monitors[i]
			System.out.println(monitors[i]); 
		}
	}
	//java_context()->getServlet()->sessionHistorical($_SESSION['EMAIL'], $_POST['monitors']);
	public void sessionHistorical(String owner, String monitors[])
	{
		if(monitors==null)
			return;
		RuleProcessed.startSeperateThread(owner, monitors);
		//RuleProcessed.start(owner, monitors);
	}
	
	private String[][] getMonitorWithRules(String ownerEmail)
	{
		return DBMonitor.getMonitorWithRules(ownerEmail);
	}
	
	public String printMonitorWithRules(String ownerEmail)
	{
		String [][] monitorList = getMonitorWithRules(ownerEmail);
		StringBuilder string = new StringBuilder();
		if(monitorList!=null)
		{
		for (int i = 0; i < monitorList.length; i++)
		{
			String [] monitorInformation = monitorList[i];
			//echo start of monitor
			string.append("\t\t\t<li><label for=\"folder"+i+"\">"+monitorInformation[0]+"</label> <input type=\"checkbox\" name=\"monitors[]\" value=\""+monitorInformation[0]+"\"id=\"folder"+i+"\" /><ol>\n");
			//echo rules in monitor
			for(int j = 1; j < monitorInformation.length; j++)
			{
				string.append("\t\t<li><input type=\"checkbox\"name=\"monitorAndRule[]\" value=\""+monitorInformation[0]+","+monitorInformation[j]+"\" /> "+monitorInformation[j]+"</li>\n");
			}
			//close rules
			string.append("</ol>\n");
			//close monitor
			string.append("</li>\n");
		}}
		return string.toString();
	}
	
	public String printAllRules(String ownerEmail)
	{
		String [] rules = DBRule.bothRuleNames(ownerEmail);
		StringBuilder string = new StringBuilder();
		for(String each : rules)
			string.append("<option value=\""+each+"\" name=\"rule[]\"> "+each+" </option>\n");
		return string.toString();
	}
}
