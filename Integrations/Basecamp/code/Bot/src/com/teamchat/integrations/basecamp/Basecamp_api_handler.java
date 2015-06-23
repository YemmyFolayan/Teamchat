/**
 * 
 */
package com.teamchat.integrations.basecamp;

//basic classes for json parsing help
import java.util.ArrayList;
import java.util.Iterator;

import com.basecamp.classes.*;
//json parsing
import com.google.gson.Gson;

/**
 * @author Puranjay Jain
 *
 */
public class Basecamp_api_handler {
	private Requests_handler rh = new Requests_handler();
	private Basecamp_basics bb;
	private Gson gson = new Gson();
	// user agent string
	private String ua = "Teamchat (http://www.teamchat.com/en/)";

	// init constructor
	public Basecamp_api_handler(Basecamp_basics bb) {
		// TODO Auto-generated constructor stub
		this.bb = bb;
	}

	// public methods for api access
	// return active project list (names)
	public String[] getActiveProjects() {
		ArrayList<String> data = new ArrayList<String>();
		try {
			String response = rh.sendGet_auth(bb.getHref() + "/projects.json",
					ua, "", bb.getAccess_token());
			// put response in token class
			Project[] projects = gson.fromJson(response, Project[].class);
			for (Project project : projects) {
				data.add(project.getName());
			}
			String[] info = data.toArray(new String[data.size()]);
			return info;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
