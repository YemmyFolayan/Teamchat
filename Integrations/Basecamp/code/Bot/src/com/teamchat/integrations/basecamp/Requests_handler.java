/**
 * 
 */
package com.teamchat.integrations.basecamp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Puranjay Jain
 *
 */
public class Requests_handler {
	//private methods and helper classes
	
	// HTTP GET request which is authorized
	public String sendGet_auth(String url, String User_agent,
			String urlParameters, String token) throws Exception {
		// url example http://www.google.com/search
		// urlParameters example q=Search&browser=chrome
		URL obj = null;
		if (urlParameters.isEmpty()) {
			obj = new URL(url);
		} else {
			obj = new URL(url + "?" + urlParameters);
		}
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", User_agent);
		con.setRequestProperty("Authorization", "Bearer " + token);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
}
