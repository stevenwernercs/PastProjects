package team2awesome.subsystem.gui;

import javax.servlet.annotation.WebServlet;

import team2awesome.subsystem.database.DBRule;
import team2awesome.subsystem.rule.Rule;
import team2awesome.subsystem.rule.RuleInfo;
import team2awesome.subsystem.sensor.Sensor;

/**
 * Servlet implementation class Server
 */
@WebServlet("/GUIRule")
public class GUIRule extends GUI
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIRule() {
	}
	
	public String setRule(String ownerEmail, String ruleName, String description, int ruleDuration, int ruleDefinition
			, String leftBoundSensor, String leftBoundFormula, String rightBoundSensor, String rightBoundFormula
			, String premiseSensor, String premiseFormula, String booleanSensor, String booleanFormula, boolean isShared)
	{	
		RuleInfo newRule = new RuleInfo(ownerEmail, ruleName, description, ruleDuration, ruleDefinition
				, leftBoundFormula, rightBoundFormula
				, premiseFormula, booleanFormula, isShared, false);
		
		String errMsg = newRule.validErrMsg();
		
		if(errMsg==null)
		{
			if(DBRule.set(newRule))
				return TRUE;
			return "We are sorry, there was a Database Error." + //com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException:
					"<br/> It is possible that that rule is owned by another account" +
					"<br/>Please try again, this time try a different name.";
		}
		else
		{
			System.out.println(errMsg);
			return errMsg;
		}
	}
	
	public String printRuleArray(String id)
	{
		return Rule.printRuleArray(id);
	}
	
	public String printSensorArray()
	{
		return Sensor.printSensorArray();
	}
}
