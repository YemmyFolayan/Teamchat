package web.asana.servelet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;

/**
 * Servlet implementation class Redirect
 */
@WebServlet(description = "get the verfication code from this url", urlPatterns = { "/Redirect_url" })
public class Redirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Redirect() {
		super();		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// TODO Do required initialization
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	// HTTP POST request
	private void sendPost(String url, String User_agent, String urlParameters)
			throws Exception {
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", User_agent);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		// urlParameters must be in this format
		// "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// print result

		Gson gson = new Gson();
		Token token = (Token) gson.fromJson(response.toString(), Token.class);
		System.out.println(token.getAccess_token());
		Database_Handler db = new Database_Handler();
		db.StorageHandler(token, token.getData().getEmail());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// https://app.asana.com/-/oauth_authorize?client_id=37903488157876&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2FAsana_Server%2FRedirect_url&response_type=code
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String user_agent = request.getHeader("User-Agent"), code = request
				.getParameter("code"), client_id = "37903488157876",
				client_secret = "a86c434154e32414631fbaf5fdff040d",
				redirect_uri = "http://integration.teamchat.com:8086/Asana_Bot/Redirect_url";
		PrintWriter out = response.getWriter();
		try {
//			out.println(code);
			sendPost("https://app.asana.com/-/oauth_token", user_agent,
					"client_id=" + client_id + "&redirect_uri=" + redirect_uri
							+ "&client_secret=" + client_secret
							+ "&grant_type=authorization_code" + "&code="
							+ code + "&response_type=token");
		} catch (Exception e) {
			// TODO: handle exception
			out.println(e);
		}
		out.println("<script>window.close();</script>");
		// TODO retrieve CLIENT_ID ,etc from db
	}

}
