package team2awesome.subsystem.gui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import php.java.servlet.RemoteHttpServletContextFactory;

/**
 * Servlet implementation class Server
 * Super of all GUI Interface classes
 * this is who the 'JavaBridge' communicates to
 */
@WebServlet("/GUI")
public class GUI extends HttpServlet 
{
	//Class Vars
	private 			static 	boolean	debug			= false;
	private 					int 	user_count 		= 0;
	public		final 	static 	String	TRUE 			= "TRUE";
	public 		final 	static 	String	FALSE 			= "FALSE";
	public 		final 	static 	String	EXPIRED 		= "EXPIRED";
	
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
	public String[] validate(String id)
	{
		//element 1 = error flags
		//element 2 = normal output
		String[] output = new String[2];
		
		try 
		{
			output[0] = (GUIStatic.upDateActivity(id) ? TRUE : EXPIRED);
		}
		catch (Exception e) 
		{
			output[0] = FALSE;
		}
		
		
		if(isDebug())
		{
			//System.out.println(user_count);
			output[1] =	 
					"---------------------start java debug---------------------<br/>"+
					"count=" + user_count++ + "<br/>" +
					GUIStatic.printLoggedin() + "<br/>" +
					GUIStatic.printAttempted() + "<br/>" +
					"----------------------end java debug----------------------<br/>";
		}
		return output;
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

	
