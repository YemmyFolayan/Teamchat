package com.teamchat.integrations.googleanalytics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

public class GAConnect {

	JSONObject json;
	String client_id;
	String client_secret;
	String redirecturi;
	public String sname = "";
	GoogleCredential credentials;

	public GAConnect() {
		// TODO Auto-generated constructor stub
		client_id = PropertiesFile.getValue("client_id");
		client_secret = PropertiesFile.getValue("client_secret");
		redirecturi = PropertiesFile.getValue("redirect_url");
	}

	public int getaccesstoken(String acode, String sname)
			throws ClientProtocolException, IOException {

		HttpPost httppost = new HttpPost(
				"https://accounts.google.com/o/oauth2/token");
		HttpClient httpclient = new DefaultHttpClient();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>(5);
		nvps.add(new BasicNameValuePair("code", acode));
		nvps.add(new BasicNameValuePair("client_id", client_id));
		nvps.add(new BasicNameValuePair("client_secret", client_secret));
		nvps.add(new BasicNameValuePair("redirect_uri", redirecturi));
		nvps.add(new BasicNameValuePair("grant_type", "authorization_code"));
		UrlEncodedFormEntity sd = new UrlEncodedFormEntity(nvps,
				HTTP.DEF_CONTENT_CHARSET);
		httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		httppost.setEntity(sd);
		System.out.println("executing request " + httppost.getRequestLine());
		System.out.println("request body=\n"
				+ EntityUtils.toString(httppost.getEntity()));
		HttpResponse response = httpclient.execute(httppost);
		System.out.println(response.getStatusLine());

		int resp_code = response.getStatusLine().getStatusCode();
		if (resp_code == 200) {

			BufferedReader in = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			while ((line = in.readLine()) != null) {
				sb.append(line + "\n");
			}
			in.close();
			System.out.println(sb);
			try {
				json = new JSONObject(sb.toString());
				System.out.println("Access Token="
						+ json.getString("access_token"));
				System.out
						.println("Token Type=" + json.getString("token_type"));
				System.out.println("Expires in=" + json.getInt("expires_in"));
				System.out.println("Refresh Token="
						+ json.getString("refresh_token"));
			
				ManageDB.update(sname, json.getString("access_token"),
						json.getString("refresh_token"));
				String room_id=ManageDB.getRoomID(sname);
				new GAnalyticsBot().postMsg(
						"Successfully connected to Google Analytics!!", room_id);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resp_code;
		}
		return resp_code;

	}

}