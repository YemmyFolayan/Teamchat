package com.teamchat.integration.factory;

import org.json.JSONObject;

public class ParseJSONAuth {
  public static String getToken(String jsonstring) {
	  String token="";
	  try {
	  JSONObject json = new JSONObject(jsonstring);
	  JSONObject auth=json.getJSONObject("access_token");
	  token=auth.getString("token");
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
	  return token;
  }
}
