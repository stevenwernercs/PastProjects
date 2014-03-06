package team2awesome.subsystem.database;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedList;

import team2awesome.subsystem.monitor.Monitor;
import team2awesome.subsystem.monitor.MonitoredData;
import team2awesome.subsystem.rule.RuleInfo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBMonitor extends DBInterface {
	
	public static final int HISTORICAL_FLAG = 1;
	public static final int NEARREALTIME_FLAG = 2;
	
	public boolean deleteRuleFromMonitor(String ownerEmail, String monitor, String rule)
	{
		try
		{
			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();
			
			String query = "DELETE FROM contains WHERE owner_email = '"+ownerEmail+"' AND monitor_name = '"+monitor+"' and rule_name = '"+rule+"';";
			state.execute(query);
			
			query = "DELETE FROM processed WHERE owner_email = '"+ownerEmail+"' AND name = '"+monitor+"' AND rule_name = '"+rule+"';";
			state.execute(query);
			
			return true;
		}
		catch(Exception e)
		{
			
		}
		
		return false;
	}
	public DataSet[] getHistoricalDataSet(String ownerEmail, String monitor)
	{
		LinkedList<DataSet> datasetList = new LinkedList<DataSet>();
		DataSet datasetArray[] = null;
		try
		{
			Connection conn = super.openConnection();
			Statement state = (Statement) conn.createStatement();
			
			String query = "SELECT rule_name, raw_historical FROM processed WHERE owner_email = '"+ownerEmail+"' AND name = '"+monitor+"';";
			
			ResultSet result = state.executeQuery(query);
			result.first();
			
			while(true)
			{
				String ruleName = result.getString(1);
				String rawDataSet = result.getString(2);
				
				datasetList.add(new DataSet(rawDataSet));
				
				if(result.next())
					continue;
				else
					break;

			}
			
			conn.close();
			
			//convert to array
			datasetArray = new DataSet[datasetList.size()];
			Iterator<DataSet> liter = datasetList.iterator();
			for(int i = 0; i < datasetArray.length; i++)
			{
				datasetArray[i] = liter.next();
			}
		}
		catch(Exception e)
		{
			
		}
		
		return datasetArray;
	}
	public static boolean setAllHistoricalDataSet(String ownerEmail, DataSet[] dataset)
	{
		try
		{
			for(int i = 0; i < dataset.length; i++)
			{
				setHistoricalDataSet(ownerEmail, dataset[i]);
			}	
			return true;
		}
		catch (Exception e)
		{
			
		}
		
		return false;
	}
	public static boolean setHistoricalDataSet(String ownerEmail, DataSet dataset)
	{
		try
		{
			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();
			
			String monitor = dataset.getMonitor();
			
			String rule = dataset.getRule();
			
			
			String query = "INSERT INTO processed VALUES('"+ownerEmail+"','"+monitor+"','"+rule+"','"+dataset.getRaw()+"','','','');";
			
			state.execute(query);
			
			conn.close();
			return true;
		}
		catch(Exception e)
		{
			
		}
		return false;
	}
	
	public LinkedList<MonitoredData> getAllMonitoredData(String ownerEmail, String monitorName)
	{
		LinkedList<MonitoredData> list = new LinkedList<MonitoredData>();
		
		if(monitorExists(ownerEmail, monitorName));
		{
			try
			{
				Connection conn = super.openConnection();
				Statement state = (Statement) conn.createStatement();
				
				String query = "SELECT rule_name, raw_historical, historical_anomaly, raw_nrt, nrt_anomaly"
						+ " from processed"
						+ " where owner_email = '"+ownerEmail+"' AND name = '"+monitorName+"';";
				
				ResultSet result = state.executeQuery(query);
				result.first();
				
				while(true)
				{
					String ruleName = result.getString(1);
					String rawHist = result.getString(2);
					String histAnom = result.getString(3);
					String rawNRT = result.getString(4);
					String nrtAnom = result.getString(5);
					
					list.add(new MonitoredData(ruleName, rawHist, histAnom, rawNRT, nrtAnom));
					
					if(result.next())
						continue;
					else
						break;

				}
				
				return list;
			}
			catch(Exception e)
			{
				
			}
		}
		
		return list;
	}
	public boolean setMonitoredData(String ownerEmail, String monitorName, MonitoredData monData)
	{	
		return this.setMonitoredData(ownerEmail, monitorName, monData.getRuleName(), monData.getRawHistorical(), monData.getHistoricalAnomaly(), monData.getRawNearRealTime(), monData.getNearRealTimeAnomaly());
	}
	public boolean setMonitoredData(String ownerEmail, String monitorName, String ruleName, String rawHist, String histAnom, String rawNRT, String nrt_anom)
	{	
		if(monitorExists(ownerEmail, monitorName));
		{
			try
			{
				Connection conn = super.openConnection();
				Statement state = (Statement) conn.createStatement();
				
				String query = "INSERT INTO processed VALUES('"+ownerEmail+"','"+monitorName+"',"
						+ " '"+ruleName+"','"+rawHist+"', '"+histAnom+"',"
						+ " '"+rawNRT+"', '"+nrt_anom+"');";
				
				state.execute(query);
				
				
				conn.close();
				return true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public Monitor get(String ownerEmail, String monitorName)
	{
		if(monitorExists(ownerEmail, monitorName))
		{
			LinkedList<String> rules = getAllRules(ownerEmail, monitorName);
			LinkedList<String> sharedRules = getAllSharedRules(ownerEmail, monitorName);
			LinkedList<MonitoredData> processedList = getAllMonitoredData(ownerEmail, monitorName);
			return new Monitor(ownerEmail, monitorName, rules, sharedRules, processedList);
		}
		
		return null;
	}
	public boolean setMonitor(String ownerEmail, String monitorName)
	{
		try
		{
			Connection conn = super.openConnection();
			Statement state = (Statement) conn.createStatement();
			
			String query = "INSERT INTO monitor VALUES('"+ownerEmail+"', '"+monitorName+"');";
			
			state.execute(query);
			conn.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean share(String ownerEmail, Monitor monitor, String shareeEmail)
	{
		try
		{
			//make sure the monitor exists
			if(DBMonitor.monitorExists(ownerEmail, monitor.getName() ) == false)
				return false;
			
			//make sure the sharee profile exists
			if(DBProfile.profileExists(shareeEmail) == false)
				return false;
			
			//make sure sharee doesnt have that monitor name takken
			if(DBMonitor.monitorExists(shareeEmail, monitor.getName()) == true)
				return false;
			
			//copy monitor;
			this.setMonitor(shareeEmail, monitor.getName());
			
			//copy rules in monitor
			//we do not copy shared rules because they do not need to be copied
			LinkedList<String> rules = monitor.getRuleNames();
			for(Iterator<String> liter = rules.iterator(); liter.hasNext(); )
			{
				RuleInfo ruleInfo = DBRule.getRuleInfo(ownerEmail, liter.next());
				ruleInfo.setOwnerEmail(shareeEmail);
				//copy only unshared rules
				if(ruleInfo.isShared() == false)
					DBRule.set(ruleInfo);
			}
			
			Connection conn = super.openConnection();
			Statement state = (Statement) conn.createStatement();
			String query = "";
			
			//copy contains table
			//get all contains table
			query = "SELECT rule_name, rule_owner_email FROM contains WHERE owner_email = '"+ownerEmail+"' AND monitor_name = '"+monitor.getName()+"';";
			ResultSet result = state.executeQuery(query);
			result.first();
			LinkedList<String> ruleNameAndOwner = new LinkedList<String>();
			while(true)
			{
				ruleNameAndOwner.add(result.getString(1));
				ruleNameAndOwner.add(result.getString(2));
				if(result.next())
					continue;
				else
					break;
			}
			//add all of the ruleNames
			DBRule ruleDB = new DBRule();
			for(Iterator<String> liter = ruleNameAndOwner.iterator(); liter.hasNext(); )
			{
				String ruleName = liter.next();
				String ruleOwner = liter.next();
				
				//if this is a shared rule then we copy the rule owner
				//else it is not shared so since we copied the rule, sharee's email replaces rule owner
				if(ruleDB.isShared(ruleOwner, ruleName))
				{
					//shared rule, so we copy shared rule owner
					query = "INSERT INTO contains VALUES('"+shareeEmail+"','"+monitor.getName()+"','"+ruleName+"','"+ruleOwner+"');";
				}
				else
				{
					//non shared rules (that we already copied) are added to monitor as sharee's own
					query = "INSERT INTO contains VALUES('"+shareeEmail+"','"+monitor.getName()+"','"+ruleName+"','"+shareeEmail+"');";
				}
				
				state.execute(query);
			}
			
			//copy processed (same as contains
			//get all processed table
			query = "SELECT rule_name, raw_historical, historical_anomaly, raw_nrt, nrt_anomaly FROM processed WHERE owner_email = '"+ownerEmail+"' AND name = '"+monitor.getName()+"';";
			result = state.executeQuery(query);
			result.first();
			LinkedList<String> processedTable = new LinkedList<String>();
			while(true)
			{
				processedTable.add(result.getString(1));
				processedTable.add(result.getString(2));
				processedTable.add(result.getString(3));
				processedTable.add(result.getString(4));
				processedTable.add(result.getString(5));
				
				if(result.next())
					continue;
				else
					break;
			}
			//add all of the processed table rows
			for(Iterator<String> liter = processedTable.iterator(); liter.hasNext(); )
			{
				String ruleName = liter.next();
				String rawHist = liter.next();
				String histAnom = liter.next();
				String rawNrt = liter.next();
				String nrtAnom = liter.next();
				
				query = "INSERT INTO processed VALUES('"+shareeEmail+"','"+monitor.getName()+"','"+ruleName+"','"+rawHist+"','"+histAnom+"','"+rawNrt+"','"+nrtAnom+"');";
				state.execute(query);
			}
			
			//done sharing
			conn.close();
			return true;
		}
		catch(Exception e)
		{
			//TODO
			//revert back to the way it was
			String query = "DELETE FROM monitor WHERE owner_email = '' AND name = '';";
			query = "DELETE FROM contains WHERE owner_email = '' AND monitor_name = '';";
			query = "DELETE from processed WHERE owner_email = '' AND name = '';";
			//delete all added rules :[ 
			e.printStackTrace();
		}
		return false;
	}
	public static LinkedList<String> getAllRules(String ownerEmail, String monitorName)
	{
		LinkedList<String> rules = new LinkedList<String>();
		
		//if the monitor doesnt exist, then we return empty linked list
		if(monitorExists(ownerEmail, monitorName) == false)
		{
			return rules;
		}
		
		try
		{
			Connection conn = openConnection();
			Statement state = (Statement)conn.createStatement();
			
			String query = "SELECT rule_name FROM contains WHERE owner_email = '"+ownerEmail+"' AND monitor_name = '"+monitorName+"' AND rule_owner_email = '"+ownerEmail+"';";
			
			ResultSet result = state.executeQuery(query);
			result.first();
			
			while(true)
			{
				rules.add(result.getString(1));
				if(result.next())
					continue;
				else
					break;

			}
			
			conn.close();
		}catch(Exception e)
		{
			
		}
		
		return rules;
	}
	public static LinkedList<String> getAllSharedRules(String ownerEmail, String monitorName)
	{
		LinkedList<String> ruleShared = new LinkedList<String>();
		
		//if the monitor doesnt exist, then we return empty linked list
		if(monitorExists(ownerEmail, monitorName) == false)
		{
			return ruleShared;
		}
		
		try
		{
			Connection conn = openConnection();
			Statement state = (Statement)conn.createStatement();
			
			String query = "SELECT rule_name, rule_owner_email FROM contains WHERE owner_email = '"+ownerEmail+"' AND monitor_name = '"+monitorName+"' AND rule_owner_email != '"+ownerEmail+"';";
			
			ResultSet result = state.executeQuery(query);
			result.first();
			
			while(true)
			{
				ruleShared.add(result.getString(1));
				ruleShared.add(result.getString(2));
				if(result.next())
					continue;
				else
					break;

			}
			
			conn.close();
		}catch(Exception e)
		{
			
		}
		
		return ruleShared;
	}
	
	public boolean rename(String ownerEmail, String oldMonitorName, String newMonitorName)
	{
		try
		{
			Connection conn = super.openConnection();
			Statement state = (Statement) conn.createStatement();
			
			//if new monitor name already exists then return false
			if(monitorExists(ownerEmail, newMonitorName))
				return false;
			
			String query = "UPDATE monitor SET owner_email = '"+ownerEmail+"', name = '"+newMonitorName+"'"
					+ " WHERE owner_email = '" + ownerEmail + "' AND name = '"+oldMonitorName+"';";
			
			
			state.executeUpdate(query);
			
			query = "UPDATE contains SET owner_email = '"+ownerEmail+"', monitor_name = '"+newMonitorName+"'"
					+ " WHERE owner_email = '" + ownerEmail + "' AND monitor_name = '"+oldMonitorName+"';";
			
			state.executeUpdate(query);
			
			query = "UPDATE processed SET owner_email = '"+ownerEmail+"', name = '"+newMonitorName+"'"
					+ " WHERE owner_email = '" + ownerEmail + "' AND name = '"+oldMonitorName+"';";
			
			state.executeUpdate(query);
			
			conn.close();
		}catch(Exception e)
		{
		}
		
		return false;
	}
	public static LinkedList<String> getAllMonitorNames(String ownerEmal)
	{
		LinkedList<String> nameList = new LinkedList<String>();
		
		try
		{
			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();
			
			String query = "SELECT name FROM monitor WHERE owner_email = '"+ownerEmal+"';";
			
			ResultSet result = state.executeQuery(query);
			result.first();
			
			while(true)
			{
				String montorName = result.getString(1);
				
				nameList.add(montorName);
				
				if(result.next())
					continue;
				else
					break;

			}
			
			conn.close();
			
		}
		catch(Exception e)
		{
			
		}
		
		return nameList;
	}
	public boolean setMonitor(Monitor monitor)
	{
		try
		{
			this.setMonitorName(monitor.getOwner(), monitor.getName());
			
			LinkedList<String> rules = monitor.getRuleNames();
			for(Iterator<String> liter = rules.iterator(); liter.hasNext(); )
			{
				String ruleName = liter.next();
				this.addARule(monitor.getOwner(), monitor.getName(), ruleName, monitor.getOwner());
			}
			
			LinkedList<String> sharedRules = monitor.getSharedRules();
			for(Iterator<String> liter = sharedRules.iterator(); liter.hasNext(); )
			{
				String ruleName = liter.next();
				String ruleOwner = liter.next();
				this.addARule(monitor.getOwner(), monitor.getName(), ruleName, ruleOwner);
			}
			
			LinkedList<MonitoredData> processedList = monitor.getProcessedList();
			this.setMonitoredData(monitor.getOwner(), monitor.getName(), processedList.getFirst());
			/*for(Iterator<MonitoredData> liter = processedList.iterator(); liter.hasNext(); )
			{
				MonitoredData processedData = liter.next();
				this.setMonitoredData(monitor.getOwner(), monitor.getName(), processedData);
			}*/
			
			return true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	public static String[][] getMonitorWithRules(String ownerEmail)
	{
		
		LinkedList<String> monitorNames = getAllMonitorNames(ownerEmail);
		String[][]monitorWithRules = new String[monitorNames.size()][];
		
		//gets all rules for each monitor
		int counter = 0;
		for(Iterator<String> liter = monitorNames.iterator(); liter.hasNext(); )
		{
			String monitor = liter.next();
			LinkedList<String> ruleList = getAllRules(ownerEmail, monitor);
			LinkedList<String> sharedRules = getAllSharedRules(ownerEmail, monitor);
			
			String rules[] = new String[ruleList.size() + (sharedRules.size()/2)+1];
			rules[0] = monitor;
			int i;
			for(i=1; i < ruleList.size()+1; i++)
			{
				rules[i] = ruleList.get(i-1);
			}
			
			for(int j = 0; j < sharedRules.size(); j++)
			{
				rules[i++] = sharedRules.get(j);
				j++;
			}
			
			monitorWithRules[counter] =  new String[rules.length];
			monitorWithRules[counter] =  rules;
			counter++;
			
		}
		
		return monitorWithRules;
		
	}
	public boolean setMonitorName(String ownerEmail, String monitorName)
	{
		try{
			
			Connection conn = super.openConnection();
			
			Statement state = (Statement) conn.createStatement();
			
			String query = "INSERT INTO monitor VALUES('"+ownerEmail+"', '"+monitorName+"');";
			state.execute(query);
			
			return true;
			
		}catch (Exception e)
		{
		}
		
		return false;
	}
	public boolean addARule(String ownerEmail, String monitorName, String ruleName, String ruleOwner)
	{
		try{
			Connection conn = super.openConnection();
			
			Statement state = (Statement) conn.createStatement();
			
			String query = "INSERT INTO processed VALUES('"+ownerEmail+"','"+monitorName+"','"+ruleName+"','','','','');";
			state.execute(query);
			
			query = "INSERT INTO contains VALUES('"+ownerEmail+"', '"+monitorName+"', '"+ruleName+"', '"+ruleOwner+"');";
			state.execute(query);
			return true;
			
		}catch (Exception e)
		{
		}
		
		return false;
	}
	public static boolean monitorExists(String ownerEmail, String name)
	{
		try{
			
			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();
			
			String query = "SELECT owner_email, name FROM monitor WHERE owner_email = '"+ownerEmail+"' AND name = '"+name+"';";
			
			ResultSet result = state.executeQuery(query);
			result.first();
			
			String ownerResult = result.getString(1);
			String nameResult = result.getString(2);
			
			if(ownerResult.equals(ownerEmail) && nameResult.equals(name))
				return true;
			else
				return false;
			
			
		}catch(Exception e)
		{
		}
		
		return false;
	}

}
