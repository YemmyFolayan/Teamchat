package com.pivotal.bot;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.teamchat.client.annotations.OnAlias;
import com.teamchat.client.annotations.OnKeyword;
import com.teamchat.client.sdk.Field;
import com.teamchat.client.sdk.Form;
import com.teamchat.client.sdk.TeamchatAPI;
import com.teamchat.client.sdk.chatlets.PrimaryChatlet;
import com.teamchat.client.sdk.chatlets.TextChatlet;

public class Pivotal_Main {

	private static String token;

	static Database_Handler db = new Database_Handler();
	Pivotal_basics pb;
	static String email;
	final String USER_AGENT = "Mozilla/5.0";
	static public long pid;

	@OnKeyword("help")
	public void Pivotal(TeamchatAPI api) throws IOException {
		api.perform(api
				.context()
				.currentRoom()
				.post(new PrimaryChatlet()
						.setQuestionHtml("<br/>Hi! This is Pivotal Tracker Bot. I am going to guide you how to use me to do thinks in Tracker.<br/>Type the following commands to:<br/>1)Createproject: To create project<br/>2)Deleteproject: To delete project<br/>3)Createstory: To add story<br/>4)Deletestory: To delete story<br/>5)Notification: To get notifications<br/>6)Logout: To log out<br/>BUT FIRST YOU HAVE TO LOGIN!")));
		email = api.context().currentSender().getEmail();
		if (db.isAuthorized(email)) {
			// get the basic info

			api.perform(api
					.context()
					.currentRoom()
					.post(new PrimaryChatlet()
							.setQuestionHtml("Hi, you may proceed to use your <u>Pivotal account</u>. ")));
			// welcome message and continue
		} else {
			// display the button so the user can begin authentication

			api.perform(api
					.context()
					.currentRoom()
					.post(new PrimaryChatlet()
							.setQuestion(
									"Follow the instructions given below.We need you to get your access token one time")
							.setQuestion(
									"Open your Pivotal account.Click on your username in the top right.Click on Profile. Scroll down to API TOKEN. Copy the api token from the api token field and paste below")));

			Form f = api.objects().form();
			f.addField(api.objects().input().name("token")
					.label("Paste Token here"));
			api.perform(api
					.context()
					.currentRoom()
					.post(new PrimaryChatlet()
							.setQuestionHtml("Paste your token")
							.setReplyScreen(f).setReplyLabel("Enter")
							.alias("got_token")));
		}
	}

	@OnAlias("got_token")
	public static void got_token(TeamchatAPI api) {
		token = api.context().currentReply().getField("token");
		db.StorageHandler(email, token);

	}

	@OnKeyword("Createproject")
	public void createProject(TeamchatAPI api) throws IOException {
	
		Database_Handler db = new Database_Handler();
		pb = db.GetBasicStuff(api.context().currentSender().getEmail());
		GetAccount ga = new GetAccount();
		Field f = null;
		f = ga.getAccount(pb.getAccess_token(), api);
		PrimaryChatlet chtlet = new PrimaryChatlet().setQuestion(
				"Fill in details of the project").setReplyScreen(
				api.objects()
						.form()
						.addField(
								api.objects().input().label("Project Name")
										.name("project_name")).addField(f));

		chtlet.alias("createproject");

		api.performPostInCurrentRoom(chtlet);

	}

	@OnAlias("createproject")
	public void create_project(TeamchatAPI api) throws IOException {
		String ProjectName = api.context().currentReply()
				.getField("project_name");

		String[] Account = api.context().currentReply()
				.getField("account_name").split("-");
		long id = Long.valueOf(Account[Account.length - 1]);
		System.out.println(id);
		String URL = "https://www.pivotaltracker.com/services/v5/projects", URL_parameter = "name="
				+ ProjectName + "&account_id=" + id;
		new SendPost().sendPost(URL, USER_AGENT, URL_parameter,
				pb.getAccess_token());
		api.perform(api.context().currentRoom()
				.post(new TextChatlet("Project Created!")));

	}

	@OnKeyword("Deleteproject")
	public void deleteProject(TeamchatAPI api) throws ClientProtocolException,
			IOException {
		Database_Handler db = new Database_Handler();
		pb = db.GetBasicStuff(api.context().currentSender().getEmail());
		GetProjects gp = new GetProjects();
		Field f = null;
		f = gp.getProject(pb.getAccess_token(), api);

		api.perform(api
				.context()
				.currentRoom()
				.post(new PrimaryChatlet()
						.setQuestion("Select project to be deleted")
						.setReplyScreen(api.objects().form().addField(f))
						.alias("deleteproject")));

	}

	@OnAlias("deleteproject")
	public void delete_project(TeamchatAPI api) throws IOException {

		String[] Project = api.context().currentReply()
				.getField("project_name").split("-");
		long id = Long.valueOf(Project[Project.length - 1]);
		String URL = "https://www.pivotaltracker.com/services/v5/projects/"
				+ id;
		String URL_parameter;
		URL_parameter = "";
		SendDelete sd = new SendDelete();
		sd.sendDelete(URL, USER_AGENT, URL_parameter, pb.getAccess_token());
		api.perform(api.context().currentRoom()
				.post(new TextChatlet("Project Deleted!")));

	}

	@OnKeyword("Createstory")
	public void createStory(TeamchatAPI api) {
		Database_Handler db = new Database_Handler();
		pb = db.GetBasicStuff(api.context().currentSender().getEmail());

		GetProjects gp = new GetProjects();
		Field f_pid = null;
		f_pid = gp.getProject(pb.getAccess_token(), api);

		api.perform(api
				.context()
				.currentRoom()
				.post(new PrimaryChatlet()
						.setQuestion("Fill in details of the story")
						.setReplyScreen(
								api.objects()
										.form()
										.addField(
												api.objects().input()
														.label("Story Name:")
														.name("story_name"))
										.addField(
												api.objects().input()
														.label("Description")
														.name("notes"))
										.addField(f_pid)).alias("createstory")));

	}

	@OnAlias("createstory")
	public void create_story(TeamchatAPI api) {
		String StoryName = api.context().currentReply().getField("story_name");
		String Notes = api.context().currentReply().getField("notes");

		String[] Project = api.context().currentReply()
				.getField("project_name").split("-");
		long pid = Long.valueOf(Project[Project.length - 1]);

		String URL = "https://www.pivotaltracker.com/services/v5/projects/"
				+ pid + "/stories";
		String URL_parameter;
		URL_parameter = "name=" + StoryName + "&description=" + Notes;
		SendPost sp = new SendPost();
		try {
			sp.sendPost(URL, USER_AGENT, URL_parameter, pb.getAccess_token());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		api.perform(api.context().currentRoom()
				.post(new TextChatlet("Story Created!")));
		;
	}

	@OnKeyword("Deletestory")
	public void deleteStory(TeamchatAPI api) {
		Database_Handler db = new Database_Handler();
		pb = db.GetBasicStuff(api.context().currentSender().getEmail());
		GetProjects gp = new GetProjects();
		Field f = null;
		f = gp.getProject(pb.getAccess_token(), api);

		api.perform(api
				.context()
				.currentRoom()
				.post(new PrimaryChatlet()
						.setQuestion("Select project in which the story exists")
						.setReplyScreen(api.objects().form().addField(f))
						.alias("selectedproject")));

	}

	@OnAlias("selectedproject")
	public void select_story(TeamchatAPI api) {
		String[] Project = api.context().currentReply()
				.getField("project_name").split("-");
		pid = Long.valueOf(Project[Project.length - 1]);
		String URL = "https://www.pivotaltracker.com/services/v5/projects/"
				+ pid + "/stories";
		String URL_parameter = "";
		SendGet sg = new SendGet();
		Field field_story = null;
		try {
			String jsonData = sg.sendGet(URL, USER_AGENT, URL_parameter,
					pb.getAccess_token());

			Gson gson = new Gson();
			Data[] Story = gson.fromJson(jsonData, Data[].class);

			field_story = api.objects().select().name("story_name")
					.label("Story");
			for (Data story : Story) {
				field_story.addOption(story.getName() + "-" + story.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		api.perform(api
				.context()
				.currentRoom()
				.post(new PrimaryChatlet()
						.setQuestion("Select story to be deleted")
						.setReplyScreen(
								api.objects().form().addField(field_story))
						.alias("deleteselectedstory")));
	}

	@OnAlias("deleteselectedstory")
	public void delete_task(TeamchatAPI api) {

		String[] Project = api.context().currentReply().getField("story_name")
				.split("-");
		long id = Long.valueOf(Project[Project.length - 1]);
		String URL = "https://www.pivotaltracker.com/services/v5/projects/"
				+ pid + "/stories/" + id;
		String URL_parameter;
		URL_parameter = "";
		SendDelete sd = new SendDelete();
		sd.sendDelete(URL, USER_AGENT, URL_parameter, pb.getAccess_token());
		api.perform(api.context().currentRoom()
				.post(new TextChatlet("Story Deleted!")));

	}

	@OnKeyword("Notification")
	public void Notification(TeamchatAPI api) {

		String URL = "https://www.pivotaltracker.com/services/v5/my/notifications";
		String URL_parameter = "envelope=true";
		Database_Handler db = new Database_Handler();
		pb = db.GetBasicStuff(api.context().currentSender().getEmail());
		SendGet sg = new SendGet();
		// list of messages
		String responseHTML = "";
		@SuppressWarnings("unused")
		Field f = null;
		try {
			String jsonData = sg.sendGet(URL, USER_AGENT, URL_parameter,
					pb.getAccess_token());
			@SuppressWarnings("unused")
			Gson gson = new Gson();
			JSONArray jsonArray = new JSONObject(jsonData).getJSONArray("data");
			for (int i = 0; i < jsonArray.length(); i++) {
				responseHTML += jsonArray.getJSONObject(i).get("message")
						.toString()
						+ "<br />";
			}
			System.out.println("Notifications are:" + responseHTML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		api.perform(api.context().currentRoom()
				.post(new PrimaryChatlet().setQuestionHtml(responseHTML)));
	}
	@OnKeyword("Logout")
	public void Logout(TeamchatAPI api){
		Database_Handler db = new Database_Handler();
		db.DeleteData(api.context().currentSender().getEmail());
		api.perform(api.context().currentRoom()
				.post(new TextChatlet("You have been logged out.")));
	}
}
