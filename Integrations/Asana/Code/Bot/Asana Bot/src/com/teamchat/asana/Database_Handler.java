package com.teamchat.asana;

/**
 * 
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Aniruddha Varshney
 *
 */
public class Database_Handler {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private static config_handler config = new config_handler();
	private static String DB_URL = "jdbc:mysql://localhost/Bot?user=tcinterns&password=PakyovBosh7";

	Database_Handler() {
		if (config.isEmpty()) {
			config.init_bot_Properties();
		}
	}

	// get asana api's basic stuff
	public Asana_basics GetBasicStuff(String email) {
		Asana_basics bb = new Asana_basics();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(DB_URL);
			statement = connect.createStatement();
			resultSet = statement
					.executeQuery("select * from asana_authorized where email = '"
							+ email + "'");
			resultSet.next();
			bb.setAccess_token(resultSet.getString("access_token"));
			bb.setEmail(resultSet.getObject("email").toString());
			bb.setExpires_in(resultSet.getObject("expires_in").toString());
			bb.setRefresh_token(resultSet.getObject("refresh_token").toString());
			return bb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	}

	// check if data exists in the server against that email
	public boolean isasana_authorized(String email) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(DB_URL);
			statement = connect.createStatement();
			resultSet = statement
					.executeQuery("select email from asana_authorized where email='"
							+ email + "'");
			// check if result set is empty or not
			while (resultSet.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		// default case
		return false;
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}