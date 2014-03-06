package team2awesome.subsystem.gui;

import javax.servlet.annotation.WebServlet;

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
		// TODO Auto-generated constructor stub
	}
	
	public boolean setRule(String rule_name, String rule_ID, int duration, String lbound_formula, String rbound_formula, 
			int definition, String premise_formula, String boolean_formula, boolean share, String user_email, boolean overwrite)
	{	//TODO handle race condition..
		if(checkUnique(rule_name, rule_ID, overwrite))
		{
			System.out.println("setting rule: " + rule_name + " " + rule_ID);
			return true;
		}
		System.out.println("fail to set rule: " + rule_name + " " + rule_ID);
		return false;
	}
	
	private boolean checkUnique(String rule_name, String rule_ID, boolean overwrite)
	{
		if(overwrite)
			return true;
		return true;
	}

}
