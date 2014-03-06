package team2awesome.subsystem.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedList;

import team2awesome.subsystem.profile.Profile;
import team2awesome.subsystem.rule.Rule;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class DBRule extends DB{
	
	public Rule get(Profile owner, String ruleName)
	{
		return this.get(owner.getEmail(), ruleName);
	}
	public Rule get(String ownerEmail, String ruleName)
	{
		Connection conn = null;
		
		try {
			Class.forName(DRIVER);
			
			conn = (Connection) DriverManager.getConnection(DBNAME, DB_USER_NAME, DB_PASSWORD);
			Statement state = (Statement) conn.createStatement();
			String changeDB = "use sespring2;";
			String query = "select * from rule where owner_email = '"+ownerEmail+"' and name = '"+ruleName+"';";
			state.execute(changeDB);
			ResultSet result = state.executeQuery(query);
			result.first();
			
			String owner = result.getString(1);
			String nameOfRule = result.getString(2);
			String description = result.getString(3);
			int duration = Integer.parseInt(result.getString(4));
			int definition = Integer.parseInt(result.getString(5));
			String lbsensor = result.getString(6);
			String lbformula = result.getString(7);
			String rbsensor = result.getString(8);
			String rbformula = result.getString(9);
			String psensor = result.getString(10);
			String pformula = result.getString(11);
			String bsensor = result.getString(12);
			String bformula = result.getString(13);
			
			conn.close();
			return new Rule(owner, nameOfRule, description, duration, definition, lbsensor, lbformula, rbsensor, rbformula, psensor, pformula, bsensor, bformula);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println("error retrieving rule with email <" + ownerEmail + "> and rule name <"+ruleName+"> from rule table");
		}
		
		return null;
	}
	
	public boolean set(Rule rule)
	{
		Connection conn = null;
		
		try {
			Class.forName(DRIVER);
			
			conn = (Connection) DriverManager.getConnection(DBNAME, DB_USER_NAME, DB_PASSWORD);
			
			
			String owner = rule.getOwnerEmail();
			String nameOfRule = rule.getName();
			String description = rule.getDescription();
			int duration = rule.getDuration();
			int definition = rule.getDefinition();
			String lbsensor = rule.getLboundSensor();
			String lbformula = rule.getLboundFormula();
			String rbsensor = rule.getRboundSensor();
			String rbformula = rule.getRboundFormula();
			String psensor = rule.getPremiseSensor();
			String pformula = rule.getPremiseFormula();
			String bsensor = rule.getBooleanSensor();
			String bformula = rule.getBooleanFormula();
			
			String changeDB = "use sespring2;";
			String query = "";
			if(this.ruleExists(owner, nameOfRule))
			{
				//rule exists so update the values
				
				query = "UPDATE rule SET owner_email = '"+owner+"', name = '"+nameOfRule+"', description = '"+description+"', duration = "+duration+", definition = "+definition+","
						+ " lbound_sensor = '"+lbsensor+"', lbound_formula = '"+lbformula+"',"
						+ " rbound_sensor = '"+rbsensor+"', rbound_formula = '"+rbformula+"',"
						+ " premise_sensor = '"+psensor+"', premise_formula = '"+pformula+"',"
						+ " boolean_sensor = '"+bsensor+"', boolean_formula = '"+bformula+"' "
						+ " WHERE owner_email = '"+owner+"' AND name = '"+nameOfRule+"';";
			}
			else
			{
				//rule doesnt exist so insert new values
				query = "INSERT INTO rule VALUES('"+owner+"', '"+nameOfRule+"', '"+description+"', "+duration+", "+definition+","
						+ " '"+lbsensor+"', '"+lbformula+"', "+" '"+rbsensor+"', '"+rbformula+"', "+" '"+psensor+"', '"+pformula+"', "+" '"+bsensor+"', '"+bformula+"' );";
			}
			
			//System.out.println("query is: " + query);
			
			Statement state = (Statement) conn.createStatement();
			state.execute(changeDB);
			/*TODO int success =*/ state.executeUpdate(query);
						
			
			conn.close();
			return true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error saving rule to table");
			//e.printStackTrace();
			return false;
		}
	}
	
	public boolean ruleExists(Profile owner, String ruleName)
	{
		return this.ruleExists(owner.getEmail(), ruleName);
	}
	public boolean ruleExists(String ownerEmail, String ruleName)
	{
		Connection conn = null;
		
		try {
			Class.forName(DRIVER);
			
			conn = (Connection) DriverManager.getConnection(DBNAME, DB_USER_NAME, DB_PASSWORD);
			Statement state = (Statement) conn.createStatement();
			String changeDB = "use sespring2;";
			String query = "select * from rule where owner_email = '"+ownerEmail+"' and name = '"+ruleName+"';";
			state.execute(changeDB);
			ResultSet result = state.executeQuery(query);
			result.first();
			

			String nameOfRule = result.getString(2);
			
			conn.close();
			
			if(nameOfRule.equals(ruleName))
				return true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		return false;
	}
	public String[] allRuleNames(Profile owner)
	{
		return this.allRuleNames(owner.getEmail());
	}
	public String[] allRuleNames(String ownerEmail)
	{
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVER);
			
			conn = (Connection) DriverManager.getConnection(DBNAME, DB_USER_NAME, DB_PASSWORD);
			Statement state = (Statement) conn.createStatement();
			String changeDB = "use sespring2;";
			String query = "select name from rule where owner_email = '"+ownerEmail+"';";
			//System.out.println("query for getting all rules\n" + query);
			state.execute(changeDB);
			ResultSet result = state.executeQuery(query);
			result.first();			
			
			LinkedList<String> listName = new LinkedList<String>();
			
			while(true)
			{
				listName.add(result.getString(1));
				if(result.next())
					continue;
				else
					break;

			}
			
			String[] ruleNames = new String[listName.size()];
			int i = 0;
			for(Iterator<String> liter = listName.iterator(); liter.hasNext(); i++)
			{
				ruleNames[i] = liter.next();
			}
			
			conn.close();
			return ruleNames;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return null;
	}

}
