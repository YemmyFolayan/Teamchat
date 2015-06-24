package com.teamchat.integration.office365.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RedirectURL
 */
@WebServlet("/RedirectURL")
public class RedirectURL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectURL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CallBack==="+request.getParameter("state"));
		Office365Token ytc=new Office365Token();
		int resp_code=ytc.getAccessToken(request.getParameter("code"),request.getParameter("state"));
		response.setContentType("text/html");
		// New location to be redirected
		String site = "";
		if (resp_code <= 200) {
			site = "success.html";
		} else {
			site = "error.html";
		}
		// redirect to location
		response.sendRedirect(site);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
