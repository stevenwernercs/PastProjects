package team2awesome.subsystem.database;

import java.sql.ResultSet;
import java.util.LinkedList;

import team2awesome.subsystem.profile.Profile;
import team2awesome.subsystem.rule.RuleInfo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBRule extends DBInterface {

	public static RuleInfo getRuleInfo(String email, String ruleName) {
		return getRuleInfo(email, ruleName, false);
	}

	public static String[] bothRuleNames(String ownerEmail)
	{
		String[] rules1 = allRuleNames(ownerEmail);
		LinkedList<String> rules2 = getAllSharedRulesNames(ownerEmail);
		boolean isName = true;
		String[] rules = new String [(rules1!=null ? rules1.length : 0)+(rules2!=null ? rules2.size()/2 : 0)];
		if(rules.length==0)
			return null;
		int i = 0;
		if(rules1!=null)
		{
			for(String each : rules1)
				rules[i++]=each;
		}
		if(rules2!=null)
		{
			for(String each : rules2)
				if(!(isName=!isName))
					rules[i++]=each;
		}
		return rules;
	}
	
	/*public static String[] bothRuleNames(String ownerEmail)
	{
		String[] rules1 = allRuleNames(ownerEmail);
		LinkedList<String> rules2 = getAllSharedRulesNames(ownerEmail);
		boolean isName = true;
		String[] rules = new String [(rules1!=null ? rules1.length : 0)+(rules2!=null ? rules2.size()/2 : 0)];
		if(rules.length==0)
			return null;
		int i = 0;
		if(rules1!=null)
		{
			for(String each : rules1)
				rules[i++]=each;
		}
		if(rules2!=null)
		{
			String ruleName_owner = "";
			for(String each : rules2)
				if(!(isName=!isName))
					ruleName_owner=each;
				else
					rules[i++]=ruleName_owner+":"+each;
		}
		return rules;
	}*/
	public boolean isShared(String ownerEmail, String ruleName)
	{
		try
		{
			Connection conn = super.openConnection();
			Statement state = (Statement) conn.createStatement();
			String query = "SELECT is_shared FROM rule WHERE owner_email = '"+ownerEmail+"' AND name = '"+ruleName+"';";
			ResultSet result = state.executeQuery(query);
			result.first();
			
			boolean isShared = result.getBoolean(1);
			
			return isShared;
			
		}catch (Exception e)
		{
			
		}
		
		return false;
	}
	public static RuleInfo getRuleInfo(String ownerEmail, String ruleName, boolean isSub) {

		try {
			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();
			String query = "select * from rule where owner_email = '"
					+ ownerEmail + "' and name = '" + ruleName + "';";
			ResultSet result = state.executeQuery(query);
			result.first();

			String owner = result.getString(1);
			String nameOfRule = result.getString(2);
			String description = result.getString(3);
			System.out.println("DES"+description);
			int duration = Integer.parseInt(result.getString(4));
			int definition = Integer.parseInt(result.getString(5));
			//String lbsensor = result.getString(6);
			String lbformula = result.getString(7);
			//String rbsensor = result.getString(8);
			String rbformula = result.getString(9);
			//String psensor = result.getString(10);
			String pformula = result.getString(11);
			//String bsensor = result.getString(12);
			String bformula = result.getString(13);
			boolean isShared = result.getBoolean(14);

			conn.close();
			return new RuleInfo(owner, nameOfRule, description, duration,
					definition, lbformula, rbformula, pformula, bformula, isShared, false);

		} catch (Exception e) {
			 System.out.println("error retrieving rule with email <" +
			 ownerEmail + "> and rule name <"+ruleName+"> from rule table");
			 e.printStackTrace();
		}

		return null;
	}

	public static boolean set(RuleInfo rule) {
		try {

			Connection conn = openConnection();

			String owner = rule.getOwnerEmail();
			String nameOfRule = rule.getName();
			String description = rule.getDescription();
			int duration = rule.getDuration();
			int definition = rule.getDefinition();
			// String lbsensor = rule.getLboundSensor();
			String lbformula = rule.getLboundFormula();
			if(lbformula==null) lbformula="";
			// String rbsensor = rule.getRboundSensor();
			String rbformula = rule.getRboundFormula();
			if(rbformula==null) rbformula="";
			// String psensor = rule.getPremiseSensor();
			String pformula = rule.getPremiseFormula();
			if(pformula==null) pformula="";
			// String bsensor = rule.getBooleanSensor();
			String bformula = rule.getBooleanFormula();
			if(bformula==null) bformula="";
			boolean isShared = rule.isShared();

			String query = "";
			if (ruleExists(owner, nameOfRule)) {
				// rule exists so update the values

				query = "UPDATE rule SET "
						+ "owner_email = '" + owner + "'"
						+ ", name = '" + nameOfRule + "'"
						+ ", description = '" + description + "'"
						+ ", duration = '" + duration + "'"
						+ ", definition = '" + definition  + "'"
						+ ", lbound_sensor = ''" /* lbsensor */
						+ ", lbound_formula = '" + lbformula + "'"
						+ ", rbound_sensor = ''" /* rbsensor */
						+ ", rbound_formula = '" + rbformula + "'"
						+ ", premise_sensor = ''" /* psensor */
						+ ", premise_formula = '" + pformula + "'"
						+ ", boolean_sensor = ''" /* bsensor */
						+ ", boolean_formula = '" + bformula + "'"
						+ ", is_shared = '" + (isShared?1:0) + "'"
						+ " WHERE owner_email = '" + owner + "' AND name = '"
						+ nameOfRule + "';";
			} else {
				// rule doesnt exist so insert new values
				query = "INSERT INTO rule VALUES('" + owner + "', '"
						+ nameOfRule + "', '" + description + "', '" + duration
						+ "', '" + definition + "', '" /* lbsensor */
						+ "', '" + lbformula + "', '" /* rbsensor */
						+ "', '" + rbformula + "', '" /* psensor */
						+ "', '" + pformula + "', '" /* bsensor */
						+ "', '" + bformula + "', '" + (isShared?1:0) + "' );";
			}

			System.out.println("query is: " + query);

			Statement state = (Statement) conn.createStatement();
			state.executeUpdate(query);

			conn.close();
			return true;

		} catch (Exception e) {
			System.out.println("error saving rule to table");
			e.printStackTrace();
			return false;
		}
	}

	public boolean ruleExists(Profile owner, String ruleName) {
		return DBRule.ruleExists(owner.getEmail(), ruleName);
	}

	public static boolean ruleExists(String ownerEmail, String ruleName) {
		try {

			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();
			String query = "select * from rule where owner_email = '"
					+ ownerEmail + "' and name = '" + ruleName + "';";
			ResultSet result = state.executeQuery(query);
			result.first();

			String nameOfRule = result.getString(2);

			conn.close();

			if (nameOfRule.equals(ruleName))
				return true;

		} catch (Exception e) {
		}

		return false;
	}

	public String[] allRuleNames(Profile owner) {
		return allRuleNames(owner.getEmail());
	}

	public static String[] allRuleNames(String ownerEmail) {
		
		String[] ruleNames = new String[0];

		try {

			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();
			String query = "select name from rule where owner_email = '"
					+ ownerEmail + "';";
			// System.out.println("query for getting all rules\n" + query);
			ResultSet result = state.executeQuery(query);
			result.first();

			LinkedList<String> listName = new LinkedList<String>();

			while (true) {
				listName.add(result.getString(1));
				if (result.next())
					continue;
				else
					break;

			}
			
			ruleNames = new String[listName.size()];
			int i = 0;
			for (String each : listName)
			{
				ruleNames[i++] = each;
			}

			conn.close();
			
			return ruleNames;

		} catch (Exception e) {
			// e.printStackTrace();
		}

		return null;
	}

	public static LinkedList<String> getAllSharedRulesNames(String ownerEmail) {
		LinkedList<String> sharedRules = new LinkedList<String>();

		try {
			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();

			String query = "SELECT name, owner_email FROM rule WHERE is_shared = true AND owner_email != '"
					+ ownerEmail + "';";
			System.out.println(query);
			ResultSet result = state.executeQuery(query);
			result.first();

			while (true) {
				// if result set is empty, it throws an exception and returns
				// empty sharedRules
				sharedRules.add(result.getString(1)); // adds rule name
				sharedRules.add(result.getString(2)); // adds owner email
				if (result.next())
					continue;
				else
					break;

			}

		} catch (Exception e) {
			// e.printStackTrace();
		}

		return sharedRules;
	}

	public static String[][] getOwnedRuleArray(String ownerEmail) {
		try {

			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();
			String query = "select * from rule where owner_email = '"
					+ ownerEmail + "';";
			ResultSet result = state.executeQuery(query);
			if (!result.first()) {
				System.out.println("No rules for " + ownerEmail);
				return null;
			}

			LinkedList<String[]> list = new LinkedList<String[]>();

			while (true) {
				// printf("\trules[%d] = [\"%s\", \"%s\", %s, \"%s\", \"%s\", %s, \"%s\", \"%s\", %s];\n",
				// $i, $row["name"], $row["lbound_sensor"], $row["duration"],
				// $row["lbound_formula"], $row["rbound_formula"],
				// $row["definition"], $row["premise_formula"],
				// $row["boolean_formula"], $row["rbound_sensor"]);
				list.add(new String[] { result.getString("name"),
						result.getString("lbound_sensor"),
						result.getString("duration"),
						result.getString("lbound_formula"),
						result.getString("rbound_formula"),
						result.getString("definition"),
						result.getString("premise_formula"),
						result.getString("boolean_formula"), result.getString("is_shared") });
				if (!result.next())
					break;

			}

			String[][] rules = new String[list.size()][];
			int i = 0;
			for (String[] each : list) {
				rules[i] = each;
				i++;
			}

			conn.close();
			return rules;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static String[][] getSharedRuleArray(String ownerEmail) {
		try {

			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();
			String query = "SELECT * FROM rule WHERE is_shared = true AND owner_email != '"
					+ ownerEmail + "';";
			ResultSet result = state.executeQuery(query);
			if (!result.first()) {
				System.out.println("No rules for " + ownerEmail);
				return null;
			}

			LinkedList<String[]> list = new LinkedList<String[]>();

			while (true) {
				// printf("\trules[%d] = [\"%s\", \"%s\", %s, \"%s\", \"%s\", %s, \"%s\", \"%s\", %s];\n",
				// $i, $row["name"], $row["lbound_sensor"], $row["duration"],
				// $row["lbound_formula"], $row["rbound_formula"],
				// $row["definition"], $row["premise_formula"],
				// $row["boolean_formula"], $row["rbound_sensor"]);
				list.add(new String[] { result.getString("name"),
						result.getString("lbound_sensor"),
						result.getString("duration"),
						result.getString("lbound_formula"),
						result.getString("rbound_formula"),
						result.getString("definition"),
						result.getString("premise_formula"),
						result.getString("boolean_formula"), result.getString("is_shared") });
				if (!result.next())
					break;

			}

			String[][] rules = new String[list.size()][];
			int i = 0;
			for (String[] each : list) {
				rules[i] = each;
				i++;
			}

			conn.close();
			return rules;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
