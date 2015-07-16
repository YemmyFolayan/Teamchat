package com.teamchat.integration.instagram.properties;

import java.io.IOException;
import java.util.Properties;

public class DBProperty {

	String dbuser="null";
	String dbpass="null";
	
	public static Properties loadPropertyFromClasspath(String fileName, Class<?> type) throws IOException
	{

		Properties prop = new Properties();
		prop.load(type.getClassLoader().getResourceAsStream(fileName));
		return prop;

	}
	
	public void loadParams()
	{
	    Properties props = new Properties();
	    try {
			props=loadPropertyFromClasspath("database.properties", DBProperty.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	 
	    dbuser = props.getProperty("dbuser", "null");
	    dbpass = props.getProperty("dbpass", "null");
	}
	
	public String getDBUser()
	{
		return dbuser;
	}
	
	public String getDBPass()
	{
		return dbpass;
	}
	
	
}
