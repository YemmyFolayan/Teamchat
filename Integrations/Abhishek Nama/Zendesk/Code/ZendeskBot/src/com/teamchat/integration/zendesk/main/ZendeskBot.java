package com.teamchat.integration.zendesk.main;

//Using zendesk client form - https://github.com/cloudbees/zendesk-java-client (maven project added)
import java.io.FileInputStream;
import java.util.Properties;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Comment;
import org.zendesk.client.v2.model.Ticket;

/*
 import org.zendesk.client.v2.model.Trigger;
 import org.zendesk.client.v2.model.Trigger.Condition;
 import org.zendesk.client.v2.model.Trigger.Conditions;
 import org.zendesk.client.v2.model.Action;
 import org.zendesk.client.v2.model.targets.UrlTarget;
 */






// Teamchat sdk
import com.teamchat.client.annotations.OnAlias;
import com.teamchat.client.annotations.OnKeyword;
import com.teamchat.client.sdk.Form;
import com.teamchat.client.sdk.TeamchatAPI;
import com.teamchat.client.sdk.chatlets.PrimaryChatlet;

public class ZendeskBot {
	String roomId, formId, ticketId, requesterId, comment;
	Zendesk zd;
	long tarId;
	DBHandler ticketData = new DBHandler();

	@OnKeyword("support")
	public void support(TeamchatAPI api) {
		zd = new Zendesk.Builder("https://teamchat.zendesk.com") // Zendesk account id
				.setUsername("savio.dmello@teamchat.com") // email of zendesk account
				.setPassword("webaroo").build(); // or setToken("0cf1d94606")

		// Setting up Trigger
		// trigger ();

		Form f = api.objects().form();
		f.addField(api.objects().input().label("Subject").name("subject"));
		f.addField(api.objects().input().label("Comment").name("comment"));

		PrimaryChatlet prime = new PrimaryChatlet();
		prime.setQuestion("Enter your query").setReplyScreen(f)
				.setReplyLabel("Enter").setDetailsLabel("Queries")
				.showDetails(true).alias("query");

		api.perform(api.context().currentRoom().post(prime));
	}

	@OnAlias("query")
	public void query(TeamchatAPI api) {
		zd = new Zendesk.Builder("https://teamchat.zendesk.com") // Zendesk account id
				.setUsername("savio.dmello@teamchat.com") // email of zendesk account
				.setPassword("webaroo").build(); // or setToken("0cf1d94606")

		String sub = api.context().currentReply().getField("subject");
		String com = api.context().currentReply().getField("comment");

		// Setting comment
		Comment c = new Comment();
		c.setBody(com);

		// Creating a new ticket, administrator privilege is required
		Ticket ticket = new Ticket();
		ticket.setSubject(sub);
		ticket.setComment(c);

		ticket = zd.createTicket(ticket);
		ticketId = String.valueOf(ticket.getId());
		requesterId = String.valueOf(ticket.getRequesterId());

		Form f = api.objects().form();
		f.addField(api.objects().input().label("Comment").name("upCom"));

		PrimaryChatlet prime = new PrimaryChatlet();
		prime.setQuestionHtml("<ul><li>Ticket - " + ticketId + "</li><li>Subject - " + sub + "</li><li>Comment - " + com + "</li></ul>")
				.setReplyScreen(f).setReplyLabel("Update")
				.setDetailsLabel("Update").showDetails(true).alias("update");

		api.perform(api.context().currentRoom().post(prime));

		roomId = api.context().currentRoom().getId();
		formId = prime.getFormId();
		comment = com;

		ticketData.setData(roomId, formId, ticketId, requesterId, comment);

		// Saving ticketid with formid for further replies
		// api.data().addField (formId, "RoomID", roomId);
		// api.data().addField (formId, "FormID", formId);
		// api.data().addField (formId, "TicketID", ticketId);
		// api.data().addField (formId, "RequesterID", requesterId);
	}

	@OnAlias("update")
	public void update(TeamchatAPI api) {
		zd = new Zendesk.Builder("https://teamchat.zendesk.com") // Zendesk account id
				.setUsername("savio.dmello@teamchat.com") // email of zendesk account
				.setPassword("webaroo").build(); // or setToken("0cf1d94606")

		String upCom = api.context().currentReply().getField("upCom");

		// Getting existing ticket id associated with formid
		String[] data = ticketData.getData(api.context().currentReply()
				.getFormId());
		String ticketId = data[2];

		// Creating or getting existing ticket by it id, Only Admin
		Ticket t = zd.getTickets(Long.parseLong(ticketId)).get(0);
		Comment c = new Comment();
		c.setBody(upCom);
		t.setComment(c);

		// updating existing ticket in out database
		zd.updateTicket(t);
		System.out.println("Tested");
	}

	public static void main(String[] args) {
		String path = "/data/solutions-config/workflow-data/zendesk/zendesk-config.properties";
		Properties configProps = new Properties();
		
		System.out.println("Path - " + path);
		
		try {
			configProps = loadPropertyFileFromDisk(path);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("BotEmail - " + configProps.getProperty("botemail"));
		
		TeamchatAPI api = TeamchatAPI.fromFile("teamchat.data").setEmail(configProps.getProperty("botemail").trim()).setPassword(configProps.getProperty("botpass").trim()).setEndPoint(configProps.getProperty("endpoint").trim());
		api.startReceivingEvents(new ZendeskBot());
	}
	
	public static Properties loadPropertyFileFromDisk(String filePath) throws Exception
	{
		String os = System.getProperty("os.name");
		if (os.toLowerCase().contains("windows"))
		{
			filePath = "d:" + filePath;
		}
		Properties configProp = new Properties();
		FileInputStream fIS = new FileInputStream(filePath);
		configProp.load(fIS);
		fIS.close();
		return configProp;
	}
}
