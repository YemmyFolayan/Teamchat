package com.bonusly.bot;

import java.io.IOException;

import org.json.JSONException;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.teamchat.client.sdk.TeamchatAPI;
import com.teamchat.client.sdk.chatlets.PrimaryChatlet;

public class Authorizer {
//	public void getCredentials(TeamchatAPI api) {
//		api.perform(api
//				.context()
//				.currentRoom()
//				.post((new PrimaryChatlet().setQuestion(
//						"Fill in your Bonusly Credentials.").setReplyScreen(
//						api.objects()
//								.form()
//								.addField(
//										api.objects().input().label("Email")
//												.name("email"))
//								.addField(
//										api.objects().input().label("Password")
//												.name("pwd"))).alias("oncreds"))));
//	}

	public String getAccessToken(String email, String pwd) throws IOException,
			JSONException {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\n    \"email\":\""
				+ email + "\",\n    \"password\":\"" + pwd + "\"\n}");
		Request request = new Request.Builder()
				.url("https://bonus.ly/api/v1/sessions").post(body)
				.addHeader("content-type", "application/json").build();

		Response response = client.newCall(request).execute();
		return response.header("X-Bonusly-Authentication-Token");
		
	}

}
