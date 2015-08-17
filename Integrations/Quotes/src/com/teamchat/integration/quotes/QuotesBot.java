package com.teamchat.integration.quotes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.teamchat.client.annotations.OnAlias;
import com.teamchat.client.annotations.OnKeyword;
import com.teamchat.client.sdk.Form;
import com.teamchat.client.sdk.TeamchatAPI;
import com.teamchat.client.sdk.chatlets.PrimaryChatlet;

public class QuotesBot
{
	@OnKeyword("help")
	public void help(TeamchatAPI api)
	{
		Form f = api.objects().form();
		f.addField(api.objects().select().label("Select category").addOption("Famous").addOption("Movies").addRegexValidation(".+", "please select one option").name("categoryType"));

		api.perform(api.context().currentRoom().post(new PrimaryChatlet().setQuestionHtml(Utility.help).setReplyScreen(f).setReplyLabel("Categories").alias("quotes")));
	}

	@OnKeyword("quotes")
	public void convert(TeamchatAPI api) throws Exception
	{
		Form f = api.objects().form();
		f.addField(api.objects().select().label("Select category").addOption("Famous").addOption("Movies").addRegexValidation(".+", "please select one option").name("categoryType"));

		api.perform(api.context().currentRoom().post(new PrimaryChatlet().setQuestionHtml(Utility.help).setReplyScreen(f).setReplyLabel("Categories").alias("quotes")));
	}

	@OnAlias("quotes")
	public void converted(TeamchatAPI api) throws Exception
	{
		String cat = api.context().currentReply().getField("categoryType");
		
		if (cat.equalsIgnoreCase("famous"))
		{
			String url = Utility.apiUrl.replace("__category", cat);
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("X-Mashape-Key", Utility.apiKey);
			con.setRequestProperty("Accept", "application/json");

			int responseCode = con.getResponseCode();

			if (responseCode == 200)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null)
				{
					response.append(inputLine);
				}
				in.close();

				JSONObject json = new JSONObject(response.toString());
				String quote = json.getString("quote");
				String author = json.getString("author");
				
				api.perform(api.context().currentRoom().post(new PrimaryChatlet().setQuestionHtml(Utility.response
								.replace("__quote", quote)
								.replace("__author", author)
								.replace("__category", cat))));
			}
			else
			{
				api.perform(api.context().currentRoom().post(new PrimaryChatlet().setQuestionHtml(Utility.sorry)));
			}
		}
		else if (cat.equalsIgnoreCase("movies"))
		{
			String url = Utility.apiUrl.replace("__category", cat);
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("X-Mashape-Key", Utility.apiKey);
			con.setRequestProperty("Accept", "application/json");

			int responseCode = con.getResponseCode();

			if (responseCode == 200)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null)
				{
					response.append(inputLine);
				}
				in.close();

				JSONObject json = new JSONObject(response.toString());
				String quote = json.getString("quote");
				String author = json.getString("author");
				
				api.perform(api.context().currentRoom().post(new PrimaryChatlet().setQuestionHtml(Utility.response
								.replace("__quote", quote)
								.replace("__author", author)
								.replace("__category", cat))));
			}
			else
			{
				api.perform(api.context().currentRoom().post(new PrimaryChatlet().setQuestionHtml(Utility.sorry)));
			}
		}
	}
}