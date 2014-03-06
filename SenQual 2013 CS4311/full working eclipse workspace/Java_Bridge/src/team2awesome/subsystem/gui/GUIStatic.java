package team2awesome.subsystem.gui;

import java.util.LinkedList;

import javax.servlet.annotation.WebServlet;

import team2awesome.subsystem.profile.Profile;

@WebServlet("/GUIStatic")
public class GUIStatic extends GUI{

	public static final boolean SINGELTON = false;
	
	private static 	LinkedList<Profile> loggedIN = new LinkedList<Profile>();
	private static 	LinkedList<Profile> attempts = new LinkedList<Profile>();
	
	private static final long serialVersionUID = 1L;

	public GUIStatic() {
	}

	protected static String printAttempted() {
		StringBuilder attemptedLoggin = new StringBuilder("FAILED ATTEMPTS:");
		for(Profile p : GUIStatic.attempts)
			attemptedLoggin.append(" " + p.getEmail() + ":" + p.getAttempts());
		return attemptedLoggin.toString();		
	}

	protected static String printLoggedin() {
		StringBuilder activeLoggin = new StringBuilder("ACTIVE USERS:");
		for(Profile p : GUIStatic.loggedIN)
			activeLoggin.append(" " + p.getUniqueSessionID());
		return activeLoggin.toString();	
	}

	public static String error(String error)
	{		
		return error==null ? "no error string yet" : error;
	}
	
	public static String logoutID(String id) 
	{
		if(id==null)
			return FALSE;
		
		try
		{
			loggedIN.remove(indexOfLoggedIN(id));
		}
		catch (IndexOutOfBoundsException e)
		{return FALSE;}
		return TRUE;
		
	}
	
	private static int  indexOfLoggedIN(String id)
	{
		boolean found = false;
		int index=0;
		for(Profile p : loggedIN)
		{
			if(p.getUniqueSessionID().equals(id))
			{
				found = true;
				break;
			}
			else
				index++;
		}
		return (found ? index : -1);
		
	}
	
	public static String getEmailFromID(String id)
	{
		return (id==null ? "" : id.split(":")[0]);
	}
	
	public static Profile retrieveFromAttempts(String email) 
	{
		for(Profile profile : attempts)
			if(profile.getEmail().equals(email))
					return profile;
		return null;
	}

	public static boolean attemptedRemove(Profile p) 
		{return attempts.remove(p);}

	public static void addLogged(Profile p, String id) 
	{
		p.setUniqueSessionID(id);
		loggedIN.add(p);			//logged on users..
	}

	public static void attemptedAdd(Profile p) 
		{attempts.add(p);}

	//in case cookies have not been cleared but not in system...
	public static String bootALL(String id) 
	{
		if(id!=null)
		{
			System.out.println(":"+id);
			return TRUE;
		}
		return FALSE; 
	}

	public static String debugToggle()
	{
		setDebug(!isDebug());
		return "Debug is now " + (isDebug() ? "ON" : "OFF");
	}

	public static String getActivity(String newID, String newEmail) 
	{
		String attemptMsg = "";
		int attempt_count = -1;
		for(Profile each : attempts)
		{
			if(each.getEmail().equals(newEmail))
			{
				attempt_count = each.getAttempts() - 1;  //subtract this successful attempt
				switch (attempt_count)
				{
					case 0 	: attemptMsg+=	"<br/>There were no failed login attempts since you last logged on"; break;
					case 1 	: attemptMsg+=	"<br/>There was one failed login attempt at: " + each.getDateLastActive(); break;
					default : attemptMsg+=	"<br/>There were " + attempt_count + " failed login attemps since you last logged on." +
										"<br/> Last attempt was at " + each.getDateLoggedIn().toString();
				}
				break;
			}
		}
		String otherUsersMsg = "";
		int count = 0;
		for(Profile each : loggedIN)
		{
			if(each.getEmail().equals(newEmail) && !(each.getUniqueSessionID().equals(newID)))
				otherUsersMsg+="<br/>"+(count++==0?"":"Instance "+count+":") + " logged in at: " + each.getDateLoggedIn().toString() + " with last activity at: " + each.getDateLastActive().toString();
		}
		System.out.println("count: " + count + ": " + attemptMsg);
		System.out.println("attempt_count: " + attempt_count + ": " + otherUsersMsg);
		if(count==0 && attempt_count<1)
			return null;
		if(count==0)
			return attemptMsg;
		if(count==1)
			return attemptMsg + "<br/>This account is currently logged in elsewhere. Having " + otherUsersMsg;
		return  attemptMsg + "<br/>This account is currently logged in from " + count + " other access points. <br/>" +
				"Instance 1:" + otherUsersMsg;
	}

	public static Profile getProfileByID(String id)
	{
		if(id==null || id.length()==0)
			return null;
		for(Profile p : loggedIN)
		{
			if(p.getUniqueSessionID().equals(id))
				return p;
		}
		return null;
	}
	
	public static boolean upDateActivity(String id) throws Exception
	{
		Profile temp = getProfileByID(id);
		if(temp!=null)
			return temp.update();
		throw new Exception();  //not logged on
	}

	public static boolean getNumberLoggedIN(String newID, String newEmail) 
	{
		for(Profile each : loggedIN)
			if(each.getEmail().equals(newEmail) && !(each.getUniqueSessionID().equals(newID)))
				return true;
		return false;
	}
	
}
