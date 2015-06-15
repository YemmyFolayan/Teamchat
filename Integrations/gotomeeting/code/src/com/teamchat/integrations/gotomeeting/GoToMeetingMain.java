package com.teamchat.integrations.gotomeeting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.teamchat.client.annotations.OnAlias;
import com.teamchat.client.annotations.OnKeyword;
import com.teamchat.client.sdk.Room;
import com.teamchat.client.sdk.TeamchatAPI;
import com.teamchat.client.sdk.chatlets.PrimaryChatlet;
import com.teamchat.client.sdk.chatlets.TextChatlet;
import com.teamchat.integrations.gotomeeting.model.GoToMeeting;
import com.teamchat.integrations.gotomeeting.Token;

public class GoToMeetingMain
{
	private static final String bot = "bot-gotomeeting@webaroo.com";
	private static final String password = "pkJ1ayxh";
	private static TeamchatAPI api;
	private static Room r;
	private String[] values = new String[10];
	Meeting meet=new Meeting();
	
	
	static HashMap<String, GoToMeeting> gotoMeetingUsers;
	boolean exists = false;
	private String teamchatUserEmail, email, pwd, accTok, groupID;

	@OnKeyword("meeting")
	public void onMeeting(TeamchatAPI api) throws Exception
	{
		groupID = api.context().currentRoom().getId();
		teamchatUserEmail = api.context().currentSender().getEmail();
		
		if(new GetProperties().check(teamchatUserEmail)) {
			
			String str[]=new GetProperties().pull(teamchatUserEmail);
			accTok=new Token().getAccessToken(str[0], str[1]);
			
			new Meeting().showOptions(api,teamchatUserEmail);
		}
		
		if(!new GetProperties().check(teamchatUserEmail))
			new Token().getCredentials(api, teamchatUserEmail);
		
		
	}

	@OnAlias("oncreds")
	public void OnCredentials(TeamchatAPI api) throws Exception
	{
		email = api.context().currentReply().getField("email");
		pwd = api.context().currentReply().getField("pwd");
		new GetProperties().push(teamchatUserEmail, email, pwd);
		try
		{
			accTok = new Token().getAccessToken(email, pwd);
			new GetProperties().push(teamchatUserEmail, email, pwd);
			new Meeting().showOptions(api,teamchatUserEmail);

		} catch (Exception e)
		{
			api.perform(api.context().create().setName("p2p").add(teamchatUserEmail).post(new TextChatlet("Wrong Credentials or you are not registered with GoToMeeting. Kindly register at http://www.gotomeeting.com")));
			new Token().getCredentials(api, teamchatUserEmail);
		}

			
	}

	public static void main(String args[])
	{

		//gotoMeetingUsers = new HashMap<String, GoToMeeting>();
		api = TeamchatAPI.fromFile("config.json").setEmail(bot).setPassword(password).startReceivingEvents(new GoToMeetingMain());
	}
	
	@OnAlias("functions")
	public void getFunction(TeamchatAPI api) throws ClientProtocolException, IOException, JSONException, URISyntaxException
	{
		String function = new Meeting().function(api);

		if (function.equals("Instant Meeting"))
			new Meeting().instantMeeting(api,accTok,groupID);
		if (function.equals("View All Previous Meetings"))
			new Meeting().viewMeetings(api, accTok);
		if (function.equals("View Meetings by ID"))
			new Meeting().viewMeetingsbyID(api);
		if (function.equals("Schedule a Meeting"))
			new Meeting().scheduleMeeting(api);

	}
	
	@OnAlias("schedule")
	public void onSchedule(TeamchatAPI api) throws ClientProtocolException, IOException, JSONException, URISyntaxException, ParseException
	{
		values=meet.createMeeting(api, accTok);
		values = meet.reportSchedule1(api, accTok);
		for (String out : values)
			System.out.println(out);
		api.perform(api.context().currentRoom().post(new PrimaryChatlet().setQuestion("Meeting Scheduled Successfully")));
		postScheduleURL(api);
		
	}

	public void postScheduleURL(TeamchatAPI api) throws ClientProtocolException, IOException, URISyntaxException, JSONException, ParseException
	{
		meet.postSchedule(api, accTok, values,groupID);
	}
	
	@OnAlias("meetingid")
	public void getMeetingByID(TeamchatAPI api) throws IOException, JSONException
	{
		String id = api.context().currentReply().getField("id2");
		System.out.println("check");
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("https://api.citrixonline.com:443/G2M/rest/meetings/" + id);
		request.setHeader("Authorization", accTok);
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		StringBuilder sb = new StringBuilder();
		while ((line = rd.readLine()) != null)
			sb.append(line);
		sb.deleteCharAt(0);
		sb.deleteCharAt(sb.length() - 1);
		String output = sb.toString();

		JSONObject j = new JSONObject(output);
		String start = (String) j.get("startTime");
		int part = (int) j.get("maxParticipants");
		String status = (String) j.get("status");
		String subject = (String) j.get("subject");
		String meetingType = (String) j.get("meetingType");
		String end = (String) j.get("endTime");

		api.perform(api
				.context()
				.currentRoom()
				.post(new PrimaryChatlet().setQuestionHtml("<html><head><style>table, th, td {" + "border: 1px solid black;" + "border-collapse: collapse;}th, td {" + "padding: 5px;}</style></head><body>" + "<table style='width:100%'>" + "<tr>" + "<td><b>Start Time</b></td>" + "<td>" + start
						+ "</td>" + "</tr>" + "<tr>" + "<td><b>Max Participants</b></td>" + "<td>" + part + "</td>" + "</tr>" + "<tr>" + "<td><b>Status</b></td>" + "<td>" + status + "</td>" + "</tr>" + "<tr>" + "<td><b>Subject</b></td>" + "<td>" + subject + "</td>" + "</tr>" + "<tr>"
						+ "<td><b>Meeting Type</b></td>" + "<td>" + meetingType + "</td>" + "</tr>" + "<tr>" + "<td><b>End Time</b></td>" + "<td>" + end + "</td>" + "</tr>" + "</table></body></html>")));

	}
}
