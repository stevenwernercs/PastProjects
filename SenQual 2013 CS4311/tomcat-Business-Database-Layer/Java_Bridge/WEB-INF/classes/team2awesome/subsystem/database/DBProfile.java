package team2awesome.subsystem.database;

import java.sql.DriverManager;
import java.sql.ResultSet;

import team2awesome.subsystem.profile.Profile;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class DBProfile extends DB{

	public static Profile get(String email)
	{
		Connection conn = null;		
		try {
			Class.forName(DRIVER);
			
			conn = (Connection) DriverManager.getConnection(DBNAME, DB_USER_NAME, DB_PASSWORD);
			Statement state = (Statement) conn.createStatement();
			String changeDB = "use " + DB_USER_NAME + ";";
			String query = "select * from profile where email = '"+email+"';";
			state.execute(changeDB);
			ResultSet result = state.executeQuery(query);
			result.first();
			
			String n = result.getString(1);
			String title = result.getString(2);
			String aff = result.getString(3);
			email = result.getString(4);
			String pass = result.getString(5);
			String phone = result.getString(6);
			int note = Integer.parseInt(result.getString(7));
			int alert = Integer.parseInt(result.getString(8));
			
			conn.close();
			return new Profile(n, title, aff, email, pass, phone, note, alert);
			
		}catch (com.mysql.jdbc.exceptions.jdbc4.CommunicationsException e){
			
			System.out.println("Request unable to connect to Database");
			return new Profile(ERROR_TIMEOUT);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error retrieving email <" + email + "> from profile table");
		}
		
		return null;
	}
	
	
	public static boolean set(Profile profileInfo)
	{
		Connection conn = null;
		
		try {
			Class.forName(DRIVER);
			
			conn = (Connection) DriverManager.getConnection(DBNAME, DB_USER_NAME, DB_PASSWORD);
			
			String name = profileInfo.getName() + "";
			String title = profileInfo.getTitle() + "";
			String affiliation = profileInfo.getAffiliation() + "";
			String email = profileInfo.getEmail() + "";
			String password = profileInfo.getPassword() + "";
			String phoneNumber = profileInfo.getPhone() + "";
			String notification = profileInfo.getNotification() + "";
			String alert = profileInfo.getAlert() + "";
			
			String changeDB = "use " + DB_USER_NAME + ";";
			
			//sets string query
			String query = "";
			//checks if profile already exists
			if(profileExists(profileInfo.getEmail()))
			{
				//profile exists, we update the database
				query = "UPDATE profile SET name = '"+name+"', title = '"+title+"', affiliation = '"+affiliation+"', email = '"+email+"', password = '"+password+"', "
						+ "phone_number = '"+phoneNumber+"', notification_preference = "+notification+", alert_frequency = "+alert+" WHERE email = '"+email+"';";
			}
			else
			{
				//profile doesnt exist, we insert new values
				query = "INSERT INTO profile VALUES('"+name+"', '"+title+"', '"+affiliation+"', '"+email+"', '"+password+"', '"+phoneNumber+"', "+notification+", "+alert+");";
			}
			//System.out.println("query is: " + query);
			
			Statement state = (Statement) conn.createStatement();
			state.execute(changeDB);
			/*TODO int success =*/ state.executeUpdate(query);
			
			//System.out.println("successfull");
						
			
			conn.close();
			return true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("error saving profile to table");
			return false;
		}
	}
	
	
	public static boolean profileExists(String email)
	{
		Connection conn = null;
			
		try {
			Class.forName(DRIVER);
			
			conn = (Connection) DriverManager.getConnection(DBNAME, DB_USER_NAME, DB_PASSWORD);
			Statement state = (Statement) conn.createStatement();
			String changeDB = "use " + DB_USER_NAME + ";";
			String query = "select * from profile where email = '"+email+"';";
			state.execute(changeDB);
			ResultSet result = state.executeQuery(query);
			result.first();
			
			String emailRetrieved = result.getString(4);
			if (emailRetrieved.equals(email))
			{
				conn.close();
				return true;
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		return false;
	}

}