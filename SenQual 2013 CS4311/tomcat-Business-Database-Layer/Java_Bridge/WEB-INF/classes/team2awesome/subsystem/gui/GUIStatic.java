package team2awesome.subsystem.gui;

import java.util.LinkedList;

import javax.servlet.annotation.WebServlet;

import team2awesome.subsystem.profile.Profile;

@WebServlet("/GUIStatic")
public class GUIStatic extends GUI{

	private static 	LinkedList<Profile> loggedIN = new LinkedList<Profile>();
	private static 	LinkedList<Profile> attempts = new LinkedList<Profile>();
	private static	LinkedList<String> loggedID = new LinkedList<String>();
	
	private static final long serialVersionUID = 1L;

	public GUIStatic() {
		// TODO Auto-generated constructor stub
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
			activeLoggin.append(" " + p.getEmail());
		activeLoggin.append("<br/ IDs: >");
		for(String id : GUIStatic.loggedID)
			activeLoggin.append(" " + id);
		return activeLoggin.toString();	
	}

	public static String error(String error)
	{		
		return error==null ? "no error string yet" : error;
	}

	public static boolean hasID(String id) 
	{
		return loggedID.contains(id);
	}
	
	//TODO optimize
	/**
	 * takes new id (email:#.#)
	 * and looks for matching emails ignoring the #.# unique code
	 * @param id
	 * @return
	 */
	public static boolean hasEmail(String id) 
	{
		String [] givenParts = id.split(":");
		for(String each : loggedID)
		{
			String[] foundParts=each.split(":");
			if(foundParts[0].equals(givenParts[0]))
				return true;
		}
		return false;
	}

	public static String logoutID(String id) 
	{
		if(id==null)
			return FALSE;
		String idParts[]=id.split(":");
		String email=idParts[0];
		int i=0;
		for(Profile p : loggedIN)
			if(p.getEmail().equals(email))
				break;
			else
				i++;
		try
		{
			loggedIN.remove(i);
			if(!loggedID.remove(id))
				throw new IndexOutOfBoundsException();
		}
		catch (IndexOutOfBoundsException e)
		{return FALSE;}
		return TRUE;
		
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
		loggedIN.add(p);			//logged on users..
		loggedID.add(id);
	}

	public static void attemptedAdd(Profile p) 
		{attempts.add(p);}

	//in case cookies have not been cleared but not in system...
	public static String bootALL(String id) 
	{
		if(id!=null)
		{	
			for(String each : loggedID)
				if(each.equals(id))
					return FALSE;
			System.out.println(":"+id);
		}
		return FALSE; 
	}

	public static String debugToggle()
	{
		setDebug(!isDebug());
		return "Debug is now " + (isDebug() ? "ON" : "OFF");
	}
	
	
}
