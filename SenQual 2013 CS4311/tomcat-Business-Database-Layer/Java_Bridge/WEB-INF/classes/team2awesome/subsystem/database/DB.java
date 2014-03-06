package team2awesome.subsystem.database;

public class DB 
{
	static final String DRIVER= "com.mysql.jdbc.Driver";
	static final String DBNAME = "jdbc:mysql://earth.cs.utep.edu?connectTimeout=3000";
	static final String DB_USER_NAME = "sespring2";
	static final String DB_PASSWORD = "cs4311!sespring2";
	
	public static final String ERROR_TIMEOUT = 
			"<b>Connection Error, please ensure you are connected to the internet. (May require UTEP VPN)</b>";
	public static final String ERROR_LOGGED = 
			"<b>You are currently logged in on another computer</b>";
	public static final String ERROR_WRONG =
			"<b>Please retry, incorrect user-name and/or password</b>";
	public static final String ERROR_MAX_ATTEMPTS = 
			"<b>Account Locked</b>"+
			"The Number of failed Loggin attempts has been exceeded."+
			"An email has been sent to your account, please check your email for instructions on how to recover your account.";
}
