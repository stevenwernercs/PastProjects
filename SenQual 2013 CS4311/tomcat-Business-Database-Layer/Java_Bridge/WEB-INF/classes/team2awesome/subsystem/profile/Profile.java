package team2awesome.subsystem.profile;

import java.util.Date;

import team2awesome.subsystem.database.DBProfile;

public class Profile {
	
	private String errorMsg = null;
	//private String uniqueSessionID; 
	private String name;
	private String title;
	private String affiliation;
	private String email;
	private String password;
	private String phone;
	private int notification;	//notification preference
	private int alert;			//alert frequency
	
	//Derived attributes
	private static final int ATTEMPT_MAX = 3;
	private static final long ATTEMPT_TIMEOUT = 1000*60*5; //5 minutes
	
	private int AttemptFailed = 0;
	private Date AttemptDate;
	
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
			return p;
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
		this.AttemptFailed+=1;
		this.AttemptDate = new Date();
		
		return AttemptFailed<=ATTEMPT_MAX;
	}

	/**
	 * used for clearing the attempted list
	 * @param now
	 * @return (now.getTime()-this.AttemptDate.getTime())>ATTEMPT_TIMEOUT
	 */
	public boolean timeout(Date now) 
	{
		return (now.getTime()-this.AttemptDate.getTime())>ATTEMPT_TIMEOUT;
	}

}
