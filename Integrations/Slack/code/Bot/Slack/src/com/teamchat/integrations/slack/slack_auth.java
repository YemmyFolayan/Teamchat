//INTEGRATION: SLACK

//STATUS:USED

package com.teamchat.integrations.slack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.util.Properties;

/**
 * Servlet implementation class slack_auth
 */
@WebServlet("/slack_auth")
public class slack_auth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public slack_auth() {
	
	}

	public static String email = new String();
	public static File file;
	public static String code;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		System.err.println(email);
		
		System.out.println(request.getParameter("code"));
		
		code = request.getParameter("code");
		
		SlackDB.saveCode(email, code);
		System.err.println("Saved code to database");
		
		System.err.println("In servlet");
		Slack.code=code;
		Slack.wait = 1;
		
		
		
		PrintWriter out = response.getWriter();
		
		out.println("<script>window.close();</script>");
		
	//	System.out.println(request.getHeaderNames());
		
		
		/*	String filename = file.getName();
			System.err.println(filename + "from slack_auth");
			Properties prop = new Properties();
			prop.setProperty(email, code);
			FileOutputStream fileOut = new FileOutputStream(file);
			prop.store(fileOut, null);
			fileOut.close();
			*/
		
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String token = request.getParameter("token");
		System.out.println(request.getRequestURI());
		System.out.println(token + "in do");
		
		//process(request, response);
	}

/*	public void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println(request.getParameter("code"));
		String code = request.getParameter("code");
		System.err.println("Inside servlet");
		System.out.println(request.getHeaderNames());
		
		// Save the code in a properties file
		
		try {
			Properties prop = new Properties();
			prop.setProperty("code", code);
			File file = new File("code.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			prop.store(fileOut, null);
			fileOut.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
*/
	}


