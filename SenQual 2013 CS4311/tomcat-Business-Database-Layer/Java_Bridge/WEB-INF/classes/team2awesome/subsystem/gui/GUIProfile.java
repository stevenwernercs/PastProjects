package team2awesome.subsystem.gui;

import javax.servlet.annotation.WebServlet;

import team2awesome.subsystem.database.DB;
import team2awesome.subsystem.profile.Profile;

/**
 * Servlet implementation class Server
 */
@WebServlet("/GUIProfile")
public class GUIProfile extends GUI
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIProfile() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String logout(String id)
	{
		return GUIStatic.logoutID(id);
	}
	
	/**Once logged in we can create an instance of user and keep in memory until logged out..
	 *  
	 * @param email
	 * @param password
	 * @return
	 */
	public String login(String id, String email, String password, String existingUser) 
	{
		System.out.print("Loggin attempted: " + email + "; result= ");
		
		logout(existingUser);
		
		//TODO associate user with unique ID so session is unique....
		
		//get the whole profile
		Profile p = null;
		boolean fromDB = false;
		
		//----get if already on..
		if (GUIStatic.hasEmail(id)) 
		{
			System.out.println("email already logged on somewhere");
			return DB.ERROR_LOGGED;
		}
		
		//----get if in failed..
		p=GUIStatic.retrieveFromAttempts(id);
		
		//----get from database else
		if(p==null)
		{
			p = Profile.getProfile(email);
			if(p!=null && p.getErrorMsg()!=null)
				return loginDBConnection();	
			fromDB=true;
		}
		
		if(p!=null)
		{
			if(!p.incramentAttempts())//always incrament attempts
				return loginMaxFail(p);
			if(p.verifyPassword(password))	
				return loginSuccess(p, fromDB, id);
			loginFailedWrongPW(p, fromDB);  //FAIL:same result to user
		}
		else
			loginFailedNoExist();			//FAIL:same result to user
		return DB.ERROR_WRONG;
	}


	private String loginMaxFail(Profile p) 
	{
		System.out.println("Loggin attempted MAX: " + p.getEmail() + " #" + p.getAttempts());
		return DB.ERROR_MAX_ATTEMPTS;
	}


	private String loginSuccess(Profile p, boolean fromDB, String id) 
	{
		System.out.println("Success");
		//TODO---------would be good time to purge stale attempts... Date now = new Date();
		if(!fromDB)
			GUIStatic.attemptedRemove(p);
		GUIStatic.addLogged(p, id);
		return TRUE;
	}
	
	private void loginFailedWrongPW(Profile p, boolean fromDB) 
	{
		if(fromDB)						//else its there already
			GUIStatic.attemptedAdd(p);			//keep failed users
		System.out.println("Failed, wrong password");
	}
	private String loginDBConnection() 
	{
		System.out.println("Failed, Server Timeout.. ensure SERVER is up");
		return DB.ERROR_TIMEOUT;
	}
	private void loginFailedNoExist() {
		// TODO Auto-generated method stub
		System.out.println("Failed, non-existant");
	}
}