package team2awesome.subsystem.gui;

import javax.servlet.annotation.WebServlet;

import team2awesome.subsystem.database.DBInterface;
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
	
	}
	
	
	public String[] getProfileArray(String id)
	{
		Profile p = GUIStatic.getProfileByID(id);
		return (p!=null ? p.toStringArray() : null);
	}
	
	public String logout(String id)
	{
		return GUIStatic.logoutID(id);
	}
	
	
	//												//newID, newEmail, pw, oldID
	//$authenticate=java_context()->getServlet()->login($id, $_POST["email"], $_POST["password"], $_SESSION['ID']);
	/**Once logged in we can create an instance of user and keep in memory until logged out..
	 *  
	 * @param id      			newID
	 * @param email				newEmail
	 * @param password			
	 * @param existingUserID	oldID
	 * @return
	 */
	public String [] login(String newID, String password, String oldID) 
	{
		//element 1 = error flags
		//element 2 = normal output
		String [] msg = new String[]{FALSE, ""};
		String oldEmail = GUIStatic.getEmailFromID(oldID);
		String newEmail = GUIStatic.getEmailFromID(newID);
		
		System.out.print("Loggin attempted: " + newEmail + "; result= ");
		
		if(logout(oldID).equals(TRUE))
			addMsg(msg, "'"+oldEmail+"'" + DBInterface.CONFIRM_LOGOUT);
		
		//get the whole profile
		Profile p = null;
		boolean fromDB = false;
		
		//----get if already on..
		if (GUIStatic.getProfileByID(newID)!=null) 
		{
			System.err.println("RARE: Duplicate UNIQUE ID");
			return addMsg(msg, DBInterface.ERROR_DUP_ID);
		}
		if (getNumberLoggedIN(newID, newEmail)) 
		{
			if(GUIStatic.SINGELTON)
			{
				System.out.println(newEmail + ": attempt duplicate BLOCKED");
				return addMsg(msg, DBInterface.ERROR_LOGGED);
			}
			else
			{
				System.out.println(newEmail + ": attempt duplicate ALLOWED");
			}
		}
		
		//----get if in failed..
		p=GUIStatic.retrieveFromAttempts(newEmail);
		 
		//----get from database else
		if(p==null)
		{
			p = Profile.getProfile(newEmail);
			if(p!=null && p.getErrorMsg()!=null)
				return addMsg(msg, p.getErrorMsg());	
			fromDB=true;
		}
		
		if(p!=null)
		{
			if(!p.incramentAttempts())//always incrament attempts
				return addMsg(msg, loginMaxFail(p));
			if(p.verifyPassword(password))	
				return addMsg(notifyActivity(newID, newEmail), msg, loginSuccess(p, fromDB, newID));
			loginFailedWrongPW(p, fromDB);  //FAIL:same result to user
		}
		else
			loginFailedNoExist();			//FAIL:same result to user
		return addMsg(msg, DBInterface.ERROR_WRONG);
	}




	private boolean getNumberLoggedIN(String newID, String newEmail) 
	{
		return GUIStatic.getNumberLoggedIN(newID, newEmail);
	}


	private String notifyActivity(String newID, String newEmail) 
	{	//for loggedin user to see statistics
		return GUIStatic.getActivity(newID, newEmail);
	}


	/**
	 * to make error messages html friendly
	 * @param msg
	 * @param append
	 * @return
	 */
	private String [] addMsg(String[] msg, String append) 
	{	
		msg[1]+=(append!=null && append.length()!=0 ? append+"<br/>" : "");
		return msg;
	}
	private String[] addMsg(String notifyActivity, String[] msg, String flag) 
	{
		msg[0] = flag;
		msg[1]+=(notifyActivity!=null && notifyActivity.length()!=0 ? notifyActivity+"<br/>" : "");
		return msg;
	}


	private String loginMaxFail(Profile p) 
	{
		System.out.println("Loggin attempted MAX: " + p.getEmail() + " #" + p.getAttempts());
		return DBInterface.ERROR_MAX_ATTEMPTS;
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
	private void loginFailedNoExist() {
		System.out.println("Failed, non-existant");
	}
	
	public String printProfileArray(String email)
	{
		return Profile.printProfileArray(email);
	}
	
	//updateInfo($_SESSION['ID'], $_POST["oldPassword"], 
	//$_POST["userName"], $_POST["title"], $_POST["affiliation"], $_POST["emailAddr"], $_POST["newPassword1"],
	//$_POST["phoneNum"], $_POST["com"], $_POST["freq"]);
	public String[] updateInfo(String id, String currentPW, 
			String name, String title, String affiliation, String emailAddr, String newPassword1,
			String phoneNum, int com, int freq)
	{
		Profile p = GUIStatic.getProfileByID(id);
		if(p==null)
			return new String [] {"BOOT", ""};
		
		String [] msg = {FALSE, ""};
		if(!p.getName().equals(name)) {p.setName(name); addMsg(msg, "Name updated");}
		if(!p.getTitle().equals(title)) {p.setTitle(title); addMsg(msg, "Title updated");}
		if(!p.getAffiliation().equals(affiliation)) {p.setAffiliation(affiliation); addMsg(msg, "Affiliation updated");}
		if(newPassword1.length()!=0 && !p.getPassword().equals(newPassword1)) {p.setPassword(newPassword1); addMsg(msg, "Password Updated");}
		if(!p.getEmail().equals(emailAddr)) {p.setEmail(emailAddr); addMsg(msg, "Email updated"); msg[0]=TRUE;} //get new session
		if(!p.getPhone().equals(phoneNum)) {p.setPhone(phoneNum); addMsg(msg, "Phone Number updated");}
		if(p.getAlert()!=freq) {p.setAlert(freq); addMsg(msg, "Alert Frequencey updated");}
		if(p.getNotification()!=com) {p.setNotification(com); addMsg(msg, "Alert Meduim updated");}
		if(msg[1].length()==0)
		{
			msg[1]="No changes were specified";
		}
		if(!currentPW.equals(p.getPassword()))
		{
			msg[1]=msg[1].replaceAll("updated", "could not be updated");
			msg[1]="The 'Current password' entered does not match our records:<br/>"+msg[1];
			System.out.println(msg[0]+" "+msg[1]);
			return msg;
		}
		if(!p.updateProfile())
		{
			msg[1]=msg[1].replaceAll("updated", "failed to update");
			msg[1]="Failed to connect to Database:<br/>"+msg[1];
		}
		System.out.println(msg[0]+" "+msg[1]);
		return msg;
	}
	
	public boolean updateID(String oldID, String newID)
	{
		Profile p = GUIStatic.getProfileByID(oldID);
		if(p==null)
			return false;
		p.setUniqueSessionID(newID);
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//$update=java_context()->getServlet()->createNewPofile($_POST["userName"], $_POST["title"], 
		//	$_POST["affiliation"], $_POST["emailAddr"], $_POST["newPassword1"],
		//	$_POST["phoneNum"], $_POST["com"], $_POST["freq"]);
	public String [] createNewProfile(String userName, String title, 
				String affiliation, String emailAddr, String newPassword1,
				String phoneNum, String com, String freq)
	{
		if(Profile.profileExists(emailAddr))
			return new String [] {"DUP", ""};
		Profile p = new Profile(userName, title, affiliation, emailAddr, newPassword1, phoneNum, Integer.parseInt(com), Integer.parseInt(freq));
		if(p.updateProfile())
			return new String [] {"TRUE", ""};
		return new String [] {"FALSE", "Error creating new account in database."};
	}
}