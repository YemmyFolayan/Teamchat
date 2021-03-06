package com.teamchat.integration.currencyconvertor;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import jxl.Sheet;
import jxl.Workbook;

import com.teamchat.client.annotations.OnAlias;
import com.teamchat.client.annotations.OnKeyword;
import com.teamchat.client.sdk.Field;
import com.teamchat.client.sdk.Form;
import com.teamchat.client.sdk.TeamchatAPI;
import com.teamchat.client.sdk.chatlets.PrimaryChatlet;

public class CurrencyConvertorBot
{
	@OnKeyword("help")
	public void help(TeamchatAPI api)
	{
		api.perform(api.context().currentRoom().post(new PrimaryChatlet().setQuestionHtml(Utility.help)));
	}

	@OnKeyword("convert")
	public void convert(TeamchatAPI api) throws Exception
	{
		Form f = api.objects().form();
		Field cFrom = api.objects().select().label("From").name("fromCountry");
		Field cTo = api.objects().select().label("To").name("toCountry");
		Workbook workbook = Workbook.getWorkbook(new File(CurrencyConvertorBot.class.getClassLoader().getResource("Country_Codes.xls").getFile()));
		Sheet sheet = workbook.getSheet(0);
		for (int i = 1; i < sheet.getColumn(0).length; i++)
		{
			cFrom.addOption(sheet.getCell(0, i).getContents() + " - " + sheet.getCell(1, i).getContents());
			cTo.addOption(sheet.getCell(0, i).getContents() + " - " + sheet.getCell(1, i).getContents());
		}
		workbook.close();
		
		f.addField(cFrom).addField(cTo);
		f.addField(api.objects().input().label("Amount").name("amount"));

		api.perform(api.context().currentRoom().post(new PrimaryChatlet().setQuestionHtml("Select Country").setReplyScreen(f).setReplyLabel("Select").alias("convertit")));
	}

	@OnAlias("convertit")
	public void converted(TeamchatAPI api) throws Exception
	{
		String cFrom = api.context().currentReply().getField("fromCountry");
		String cTo = api.context().currentReply().getField("toCountry");
		String amount = api.context().currentReply().getField("amount");
		
		String url = "https://currencyconverter.p.mashape.com/?from=" + cFrom.substring(0,3) + "&from_amount=" + amount + "&to=" + cTo.substring(0,3);

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("X-Mashape-Key", "");
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
			String from = json.getString("from");
			String to = json.getString("to");
			Double fromAm = json.getDouble("from_amount");
			Double toAm = json.getDouble("to_amount");
			
			api.perform(api.context().currentRoom().post(new PrimaryChatlet().setQuestionHtml("<center><img src='http://integration.teamchat.com/sol/bot-images/currency.png' width='150' /></center>"
					+ "<div><p style='padding-left:5px; padding-right:5px;align:center; margin-top:5px'><b>From country: </b>" + from + "<br/><b>To Country: </b>" + to +  ""
							+ "<br/><b>From Amount: </b>" + fromAm + "<br/><b>To Amount: </b>" + toAm + "</p></div>")));
		}
		else
		{
			api.perform(api.context().currentRoom().post(new PrimaryChatlet().setQuestionHtml("<center><img src='http://integration.teamchat.com/sol/bot-images/currency.png' width='150' /></center>"
					+ "<div><p style='padding-left:5px; padding-right:5px;align:center; margin-top:5px'>Sorry! no results.</p></div>")));
		}
	}
}
