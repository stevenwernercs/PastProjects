package team2awesome.subsystem.gui;

//import javax.servlet.ServletConfig;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import php.java.servlet.RemoteHttpServletContextFactory;

/**
 * Servlet implementation class Server
 */
@WebServlet("/GUI")
public class GUI extends HttpServlet 
{
	
	private 			static 	boolean	debug			= true;
	private 					int 	user_count 		= 0;
	public		final 	static 	String	TRUE 			= "TRUE";
	public 		final 	static 	String	FALSE 			= "FALSE";
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GUI() 
    {
        super();
    }

    //SEVERE: The web application [/Java_Bridge] is still processing a request that has yet to finish. 
    //This is very likely to create a memory leak. You can control the time allowed for requests to finish 
    //by using the unloadDelay attribute of the standard Context implementation
    
    //----------------------------------------------------------------------------------------
    
	//ONE LOCATION TO SAVE HEADER..
	public String header(String title, String id, String validate, String debugPHP)
	{
		boolean noCss = false;
		if(!GUIStatic.hasID(id))
		{
			if(validate.equals(TRUE)) //validate
			{
				System.out.println("BOOT THE JUMPER!");
				return "BOOT";
			}
			else
				noCss = true;
		}
		
		String header = ""+
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"+
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">"+ 
				"<head>"+
				"<title>" + title + " Page</title>";
		
		String css =	""+
				
				"<style type=\"text/css\">"+ 
				""+
				"#navbar ul {"+ 
					"margin: 0;"+ 
					"padding: 5px;"+ 
					"list-style-type: none;"+ 
					"text-align: left;"+ 
					"background-color: #000;"+ 
				"}"+ 
				""+
				"#navbar ul li {"+  
					"display: inline;"+ 
				"}"+ 
 
				"#navbar ul li a {"+ 
					"text-decoration: none;"+ 
					"padding: .2em 1em;"+ 
					"color: #fff; "+
					"background-color: #000;"+ 
				"}"+ 
				
				"#navbar ul li a:hover {"+ 
					"color: #000;"+ 
					"background-color: #fff;"+ 
				"}"+ 
 
				//"-->"+ 
				"</style>"+ 
				"<div id=\"navbar\">"+ 
					"<ul>"+ 
						"<li><a href=\"home.php\">Home</a></li>"+
						"<li><a href=\"dashboard.php\">Dashboard</a></li>"+ 
						"<li><a href=\"monitor.php\">Monitor</a></li>"+
						"<li><a href=\"rule.php\">Rules</a></li>"+ 
						"<li><a href=\"sensors.php\">Sensors</a></li>"+
						"<li><a href=\"profile.php\">Profile</a></li>"+
					"</ul>"+
  				"</div>"+	
				"";
		
		if(isDebug())
		{
			//System.out.println(user_count);
			return 	debugPHP + 
					"---------------------start java debug---------------------<br/>"+
					"count=" + user_count++ + "<br/>" +
					GUIStatic.printLoggedin() + "<br/>" +
					GUIStatic.printAttempted() + "<br/>" +
					"----------------------end java debug----------------------<br/>" +
					header + (noCss ? "" : css);
		}
		return header + (noCss ? "" : css);
	}
	
	
	
	//----------------------------------------------------------------------------------------
   
	/*
	/**
	 * @see Servlet#init(ServletConfig)
	 
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see Servlet#getServletInfo()
	 
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	*/
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.getWriter().write(	"Servlet is running!\n\n" +
									"Requests may begin");
		
	}

	/*
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	*/
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RemoteHttpServletContextFactory ctx = new RemoteHttpServletContextFactory(this, 
				  getServletContext(), request, request, response);

				response.setHeader("X_JAVABRIDGE_CONTEXT", ctx.getId());
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");

				try { 
				  ctx.handleRequests(request.getInputStream(), response.getOutputStream()); 
				} finally { 
				  ctx.destroy(); 
				}
	}

	public static boolean isDebug() {
		return debug;
	}

	public static void setDebug(boolean debug) {
		GUI.debug = debug;
	}

	/*
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
	 
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doTrace(HttpServletRequest, HttpServletResponse)
	 
	protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	*/
	
	//----------------------------------------------------------------------------------------


}

	
