package team2awesome.subsystem.profile;

import java.util.Date;
import java.util.LinkedList;

import team2awesome.subsystem.database.DBInterface;
import team2awesome.subsystem.database.DBProfile;
import team2awesome.subsystem.profile.Mail;

public class Profile {
	
	private String errorMsg = null;
	private String uniqueSessionID; 
	private String name;
	private String title;
	private String affiliation;
	private String email;
	private String password;
	private String phone;
	private int notification;	//notification preference 0-2
	private int alert;			//alert frequency 0-2
	
	//Derived attributes
	private int AttemptFailed = 0;
	private Date AttemptDate = new Date();;
	private Date lastActive = new Date();
	private LinkedList<LinkedList<String>> listOfMonitoredAlerts = new LinkedList<LinkedList<String>>();
	
	public Profile(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}
	
	public Profile(String name, String title, String affiliation, String email, String password, String phoneNumber, int notificationPreference, int alertFrequency)
	{	
		this.name = name;
		this.title = title;
		this.affiliation = affiliation;
		this.email = email;
		this.password = password;
		this.phone = phoneNumber;
		this.notification = notificationPreference;
		this.alert = alertFrequency;
	}
	
	public static Profile loginProfile(String email, String pasword)
	{
		Profile p = DBProfile.get(email);
		if(p.password.equals(pasword))
		{
			p.AttemptDate = new Date();
			p.lastActive = new Date();
			return p;
		}
		return null;
	}
	
	public static Profile getProfile(String email)
	{
		return DBProfile.get(email);
	}
	
	public boolean verifyPassword(String password)
	{
		System.out.print(this.password + "=?=" + password + "; ");
		return this.password.equals(password);
	}
	
	
	//gets and sets
	public String getErrorMsg() {return errorMsg;}
	public String getName(){return name;}
	public String getTitle(){return title;}
	public String getAffiliation(){return affiliation;}
	public String getEmail(){return email;}
	public String getPassword(){return password;}  //shouldnt use publicly
	public String getPhone(){return phone;}
	public int getNotification(){return notification;}
	public int getAlert(){return alert;}
	public int getAttempts() {return this.AttemptFailed;}
	
	public void setName(String n){this.name = n;}
	public void setTitle(String n){this.title = n;}
	public void setAffiliation(String n){this.affiliation = n;}
	public void setEmail(String n){this.email = n;}
	public void setPassword(String n){this.password = n;}
	public void setPhone(String n){this.phone = n;}
	public void setNotification(int n){this.notification = n;}
	public void setAlert(int n){this.alert = n;}

	/**
	 * Incraments attempt counter
	 * Sets last attempt date
	 * @return AttemptFailed<=ATTEMPT_MAX
	 */
	public boolean incramentAttempts() 
	{
		//TODO remove other stale from list
		if(timeout())
			this.AttemptFailed=0; //reset counter stale..
		else
			this.AttemptFailed+=1;
		this.AttemptDate = new Date();
		
		return AttemptFailed<=DBInterface.ATTEMPT_MAX;
	}

	/**
	 * used for clearing the attempted list
	 * @param now
	 * @return (now.getTime()-this.AttemptDate.getTime())>ATTEMPT_TIMEOUT
	 */
	public boolean timeout() 
	{
		Date now = new Date();
		return (now.getTime()-this.AttemptDate.getTime())>DBInterface.ATTEMPT_TIMEOUT;
	}

	public static String printProfileArray(String email) 
	{
		Profile profile = getProfile(email);
		return "\tprofile[1] = [\""+
   				profile.name+"\", \""+
   				profile.title+"\", "+
   				profile.affiliation+", \""+
   				profile.email+"\", \""+
   				profile.phone+"\"];\n";
	}

	public Date getDateLoggedIn() 
	{
		return this.AttemptDate;
	}

	public Date getDateLastActive() 
	{
		return lastActive;
	}

	/**
	 * @return the uniqueSessionID
	 */
	public String getUniqueSessionID() {
		return uniqueSessionID;
	}

	/**
	 * @param uniqueSessionID the uniqueSessionID to set
	 */
	public void setUniqueSessionID(String uniqueSessionID) {
		this.uniqueSessionID = uniqueSessionID;
	}

	public boolean update() 
	{
		Date now = new Date();
		if((now.getTime()-lastActive.getTime())>DBInterface.SESSION_TIMEOUT)
			return false;
		lastActive = now;
		return true;
	}

	public String [] toStringArray() 
	{
		return new String[] {name, title, affiliation, email, password, (phone!=null ? phone : ""), notification+"", alert+""};
	}

	public boolean updateProfile() 
	{
		return DBProfile.set(this);
	}

	public static boolean profileExists(String emailAddr) 
	{	
		return DBProfile.profileExists(emailAddr);
	}

	/**
	 * Possible to recieve 3 emails for one anomally if they set it that way
	 * only for the first anomally of the monitor and rule and anomally it self
	 * @param monitorName
	 * @param ruleName
	 */
	public void sendAlert(String monitorName, String ruleName, Mail mail) 
	{
		//<tr><td>Each Anomaly Detected:      </td><td><input type="checkbox" id="f1" name="f1" value="4" <?php echo $freq[0]?>/><br></td></tr>
		//<tr><td>Once for Each Rule Violated:</td><td><input type="checkbox" id="f2" name="f2" value="2" <?php echo $freq[1]?>/><br></td></tr>
		//<tr><td>Once per Monitor:           </td><td><input type="checkbox" id="f3" name="f3" value="1" <?php echo $freq[2]?>/><br></td></tr>
		
		System.out.println("ALERT FREQ: "+this.alert);
		try
		{
		if((this.alert&1)==1)
		{
			boolean send = true;
			for(LinkedList<String> monitors : this.listOfMonitoredAlerts)
				if(monitors.getFirst().equals(monitorName))
				{
					send = false;
					break;
				}

			if(send)
			{
				mail.sendOff(this.email, "Monitor Alert Once Per Monitor", "Monitor '"+monitorName+"': a rule has been violated.");
				LinkedList<String> temp = new LinkedList<String>();
				temp.addFirst(monitorName);
				temp.addLast(ruleName);
				this.listOfMonitoredAlerts.add(temp);
				System.out.println("1 TO: "+this.email+"\t\nMonitor Alert Once Per Monitor\n\t\tMonitor '"+monitorName+"': a rule has been violated.");
			}
		}
		if((this.alert&2)==2)
		{
			boolean send = true;
			int i = 0;
			for(LinkedList<String> monitors : this.listOfMonitoredAlerts)
			{
				if(monitors.getFirst().equals(monitorName))
				{
					for(String rule : monitors)
						if(rule.equals(ruleName)) //assume rule is not same name as monitor. //TODO restructure
						{
							send = false;
							break;
						}
					break;
				}
				i++;
			}
			if(send)
			{
				mail.sendOff(this.email, "Monitor Alert Each Rule Violated", "Monitor '"+monitorName+"' : Rule '"+ruleName+"' has been violated at least once.");
				if(i==this.listOfMonitoredAlerts.size())
				{	//monitor not in list
					LinkedList<String> temp = new LinkedList<String>();
					temp.addFirst(monitorName);
					temp.addLast(ruleName);
					this.listOfMonitoredAlerts.add(temp);
				}
				else
				{	//found but not yet in
					this.listOfMonitoredAlerts.get(i).addLast(ruleName);;
				}
				System.out.println("2 TO: "+this.email+"\t\nMonitor Alert Each Rule Violated\n\t\tMonitor '"+monitorName+"' : Rule '"+ruleName+"' has been violated at least once.");
			}
		}
		if((this.alert&4)==4)
		{	//no check
			mail.sendOff(this.email, "Monitor Alert Each Anomally Detected", "Monitor '"+monitorName+"' : Rule '"+ruleName+"' has been violated.");
			System.out.println("4 TO: "+this.email+"\t\nMonitor Alert Each Anomally Detected\n\t\tMonitor '"+monitorName+"' : Rule '"+ruleName+"' has been violated.");
		}
		}
		catch (Exception e)
		{
			System.out.println("What the hell!");
			e.printStackTrace();
		}
	}

}
