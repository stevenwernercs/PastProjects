package team2awesome.subsystem.database;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBInterface
{
	static final String DRIVER= "com.mysql.jdbc.Driver";
	static final String DBNAME = "jdbc:mysql://earth.cs.utep.edu?connectTimeout=3000";
	static final String DB_USER_NAME = "sespring2";
	static final String DB_PASSWORD = "cs4311!sespring2";
	
	public static final int ATTEMPT_MAX = 3;
	public static final long ATTEMPT_TIMEOUT = 1000*60*5; //5 minutes
	public static final long SESSION_TIMEOUT = 1000*60*45; //45 minutes
	
	public static final String ERROR_TIMEOUT = 
			"Connection Error, please ensure you are connected to the internet. (May require UTEP VPN)";
	public static final String ERROR_LOGGED = 
			"You are currently logged in on another computer.";
	public static final String ERROR_WRONG =
			"User name or password incorrect, please reenter."; //REQ67
	public static final String ERROR_MAX_ATTEMPTS = 
			"Account Locked: "+
			"The Number of failed Loggin attempts has been exceeded.<br/>" +
			"You must wait "+ATTEMPT_TIMEOUT+" minutes to log back in";
			//"An email has been sent to your account, please check your email for instructions on how to recover your account.";
	public static final String ERROR_DUP_ID = 
			"Were are sorry, PHP's 'unique ID' has been duplicated by random chance. We ask that you try one more time to log in.<br>" +
			"Maybe you should buy a lottery ticket too...";
	
	public static final String CONFIRM_LOGOUT = 
			", You have been logged out.";
	

	protected static Connection openConnection() throws Exception
	{
		
		Connection conn = null;
		String dbName = DBNAME;
		String name = DB_USER_NAME;
		String password = DB_PASSWORD;
		try 
		{
			Class.forName(DRIVER);
			conn = (Connection) DriverManager.getConnection(dbName, name, password);
			String changeDB = "use "+DB_USER_NAME+";";
			Statement state = (Statement) conn.createStatement();
			state.execute(changeDB);
			
			return conn;
		} 
		catch (com.mysql.jdbc.exceptions.jdbc4.CommunicationsException e)
		{
			throw e;
		}
		catch (SQLException e) 
		{
			System.out.println("error when driver is connecting to Database");
			 e.printStackTrace();
			throw e;
		} 
		catch (ClassNotFoundException e) 
		{
			//e.printStackTrace();
			throw e;
		}
	}

}
