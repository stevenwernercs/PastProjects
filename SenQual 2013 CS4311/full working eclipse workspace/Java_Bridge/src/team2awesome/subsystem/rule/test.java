package team2awesome.subsystem.rule;

import team2awesome.subsystem.profile.Mail;



public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
		//RuleProcessed test = RuleProcessed.getRuleProcessed("dummy@aweso.me", "MonitorName", "plantrule-ensureunder55");
		//GUIDash dash = new GUIDash();
		//GUIDash.addToCompleted(test.runSessionInSeperateThread()); 
		//dash.printWholePage("dummy@aweso.me");
		
		//GUIMonitor n = new GUIMonitor();
		//System.out.println(n.printMonitorWithRules("carlo@aweso.me"));
		new Mail().sendOff("sdwerneremail@gmail.com", "test", "test");
	}
}
