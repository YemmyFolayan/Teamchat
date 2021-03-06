package com.teamchat.asana;

import org.json.JSONObject;

import web.asana.servelet.Data;

import com.google.gson.Gson;
import com.teamchat.client.sdk.Field;
import com.teamchat.client.sdk.TeamchatAPI;

public class GetWorkspace {
	
	public Field getWorkspace(String token, TeamchatAPI api) {
		final String USER_AGENT = "Mozilla/5.0";
		String URL = "https://app.asana.com/api/1.0/workspaces/";
		String URL_parameter = "";
		SendGet sg = new SendGet();
		Field f = null;
		try {
			String jsonData = sg.sendGet(URL, USER_AGENT, URL_parameter, token);
			JSONObject jsonObj = new JSONObject(jsonData);
			Gson gson = new Gson();
			Data[] workspaces = gson.fromJson(jsonObj.get("data").toString(),
					Data[].class);
			// post chatlet asking for the workspace to user
			f = api.objects().select().name("workspace_name")
					.label("Workspace");
			for (Data workspace : workspaces) {
				f.addOption(workspace.getName() + "-" + workspace.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}
}