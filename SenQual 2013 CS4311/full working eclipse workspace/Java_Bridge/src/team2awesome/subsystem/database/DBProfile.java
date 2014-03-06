package team2awesome.subsystem.database;

import java.sql.ResultSet;

import team2awesome.subsystem.profile.Profile;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class DBProfile extends DBInterface{
		
	public static Profile get(String email)
	{
		
		try 
		{
			//get connection
			Connection conn = openConnection();
				
			Statement state = (Statement) conn.createStatement();
			String query = "select * from profile where email = '"+email+"';";
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
				
			//close connection
			conn.close();
			return new Profile(n, title, aff, email, pass, phone, note, alert);
				
		}
		catch (com.mysql.jdbc.exceptions.jdbc4.CommunicationsException e){
			
			System.out.println("Request unable to connect to Database");
			System.out.println("Failed, Server Timeout.. ensure SERVER is up");
			return new Profile(ERROR_TIMEOUT);
			
		}catch (Exception e) {
				System.out.println("error retrieving email <" + email + "> from profile table, Probably wrong");
				return new Profile(ERROR_WRONG);
		}
	}
		
		
	public static boolean set(Profile profileInfo)
	{		
		try 
		{	
			Connection conn = openConnection();
			
			String name = profileInfo.getName() + "";
			String title = profileInfo.getTitle() + "";
			String affiliation = profileInfo.getAffiliation() + "";
			String email = profileInfo.getEmail() + "";
			String password = profileInfo.getPassword() + "";
			String phoneNumber = profileInfo.getPhone() + "";
			String notification = profileInfo.getNotification() + "";
			String alert = profileInfo.getAlert() + "";
				
			String changeDB = "use sespring2;";
				
			//sets string query
			String query;
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
			state.executeUpdate(query);
				
			//System.out.println("successfull");
							
				
			conn.close();
				return true;
				
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			System.out.println("error saving profile to table");
			return false;
		}
	}
		
		
	public static boolean profileExists(String email)
	{	
		try 
		{
			Connection conn = openConnection();
			Statement state = (Statement) conn.createStatement();
			String query = "select * from profile where email = '"+email+"';";
			ResultSet result = state.executeQuery(query);
			result.first();
			
			String emailRetrieved = result.getString(4);
			if (emailRetrieved.equals(email))
			{
				conn.close();
				return true;
			}			
				
		}
		catch (Exception e) 
		{
		}
		return false;
	}

}