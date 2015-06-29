package com.teamchat.integration.zendesk.main;


import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Properties;

import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Comment;
import org.zendesk.client.v2.model.Ticket;
import org.zendesk.client.v2.model.SatisfactionRating;

/*
 import org.zendesk.client.v2.model.Trigger;
 import org.zendesk.client.v2.model.Trigger.Condition;
 import org.zendesk.client.v2.model.Trigger.Conditions;
 import org.zendesk.client.v2.model.Action;
 import org.zendesk.client.v2.model.tarsets.UrlTarget;
 */




import com.teamchat.client.annotations.OnAlias;
import com.teamchat.client.annotations.OnKeyword;
import com.teamchat.client.sdk.Chatlet;
import com.teamchat.client.sdk.Form;
import com.teamchat.client.sdk.Reply;
import com.teamchat.client.sdk.Room;
import com.teamchat.client.sdk.TeamchatAPI;
import com.teamchat.client.sdk.chatlets.PrimaryChatlet;
import com.teamchat.client.sdk.chatlets.TextChatlet;


public class Bot {
	
	String roomId, formId, ticketId, requesterId, comment;
	String contact,status,rating,Rcomment;
	
	Zendesk zd;
	long tarId;
	DBHandler ticketData = new DBHandler();
	


	@OnKeyword("tcsupport")
	public void support(TeamchatAPI api) {
		
		
		zd = new Zendesk.Builder("https://teamchat.zendesk.com") // Zendesk account id
				.setUsername("savio.dmello@teamchat.com") // email of zendesk account
				.setPassword("webaroo").build(); // or setToken("0cf1d94606")

		// Setting up Trigger
		// trigger ();

		Form f = api.objects().form();
		f.addField(api.objects().input().label("Subject").name("subject"));
		f.addField(api.objects().input().label("Comment").name("comment"));
		f.addField(api.objects().input().label("Contact No.").name("contact"));
		
		PrimaryChatlet prime = new PrimaryChatlet();
		prime.setQuestionHtml("<img src=\"http://p3.zdassets.com/hc/settings_assets/481529/200038422/Q2xRKEc55HY0Oxq8hxzRaw-teamchatsupportlogo.png\" height=\"90\" width=\"230\">"+"<br /><b>Enter your query below:</b>").setReplyScreen(f)
				.setReplyLabel("Enter").setDetailsLabel("Queries")
				.showDetails(true).alias("query");

		api.perform(api.context().currentRoom().post(prime));
	}
	
	@OnKeyword("tcfeed")
	public void feedbackform(TeamchatAPI api) {
			 
		 Form fo=api.objects().form();
		 
		 fo.addField(
					api.objects().input().name("TicketID").label("TicketID"));
		 
		 fo.addField(api.objects().select().name("Rating").label("Rating")
					.addOption("good")
					.addOption("bad"));
		 
		 fo.addField(
					api.objects().input().name("rcomment").label("Comment"));
		 		 
		 PrimaryChatlet prime = new PrimaryChatlet();
		
		 api.perform(api.context().currentRoom().post(prime.setQuestionHtml("<h3 style=\"color:black\";><b>Give Us Feedback</b></h3>"+"<br />We value your feedback and product suggestions. While we don't respond to suggestions directly, we do review them. If you'd like to give general feedback about Teamchat support, give your feedback here.")
							.setReplyScreen(fo)
							.setReplyLabel("Enter").alias("getdatafeed")));
		
	}
	
	@OnAlias("getdatafeed")
	public void getdatafeed(TeamchatAPI api) {
	
		zd = new Zendesk.Builder("https://teamchat.zendesk.com") // Zendesk account id
		.setUsername("savio.dmello@teamchat.com") // email of zendesk account
		.setPassword("webaroo").build(); // or setToken("0cf1d94606")
		
		 api.perform(api.context().currentRoom().post(new TextChatlet("Thank You !!")));
					
		 DBHandler datacng = new DBHandler();
		 
		String Tkt = api.context().currentReply().getField("TicketID");
		String rate = api.context().currentReply().getField("Rating");
     	String rcom = api.context().currentReply().getField("rcomment");
     	
     	System.out.println(Tkt);
     	System.out.println(rate);
     	System.out.println(rcom);
		
     	 try {
     		datacng.stmt.executeUpdate("UPDATE " + datacng.configProps.getProperty("dbname").trim() +"."+datacng.configProps.getProperty("tablename").trim() + " SET rating ='"+ rate +"'"+ ",Rcomment ='"+ rcom +"'"+" where ticketId='" + Tkt + "'");
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
     	
     	 
     	Ticket t = zd.getTicket(Long.parseLong(Tkt));
     	SatisfactionRating sr = new SatisfactionRating();
		sr.setScore(rate);
		sr.setComment(rcom);
		t.setSatisfactionRating(sr);

		
		// updating existing ticket in out database
		zd.updateTicket(t);
		System.out.println("Tested");
		     	 
	}
	
	
	@OnAlias("query")
	public void query(TeamchatAPI api) {
		zd = new Zendesk.Builder("https://teamchat.zendesk.com") // Zendesk account id
				.setUsername("savio.dmello@teamchat.com") // email of zendesk account
				.setPassword("webaroo").build(); // or setToken("0cf1d94606")

		String sub = api.context().currentReply().getField("subject");
		String com = api.context().currentReply().getField("comment");
     	String con = api.context().currentReply().getField("contact");
     	
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
		String stat = "open";
		String rat = "";
		String Rc = "";
		
		
		Form f = api.objects().form();
		f.addField(api.objects().input().label("Comment").name("upCom"));

		PrimaryChatlet prime = new PrimaryChatlet();
		prime.setQuestionHtml("<h4><b>Ticket Details:</b></h4>"+"<ul type=\"square\"; style=\"color:#359FD8\";><li><a1 style=\"color:black\";><b>Ticket ID- </b></a1><a2 style=\"color:#359FD8\";>" + ticketId + "</a2></li><li><a3 style=\"color:black\";><b>Requester ID- </b></a3><a4 style=\"color:#359FD8\";>" + requesterId + "</a4></li><li><a5 style=\"color:black\";><b>Subject - </b></a5><a6 style=\"color:#359FD8\";>" + sub + "</a6></li><li><a7 style=\"color:black\";><b>Comment - </b></a7><a8 style=\"color:#359FD8\";>" + com + "</a8></li></ul>")
				.setReplyScreen(f).setReplyLabel("Update")
				.setDetailsLabel("Update").showDetails(true).alias("update");

		api.perform(api.context().currentRoom().post(prime));

		roomId = api.context().currentRoom().getId();
		formId = prime.getFormId();
		comment = com;
		contact = con;
		status = stat;
		rating = rat;
		Rcomment = Rc;
		
		//System.out.println("TICKET ID"+ticketId);
	//	System.out.println("REQUESTER ID"+requesterId);
	//	System.out.println("FORM ID"+formId);
	//	System.out.println("ROOM ID"+roomId);
	//	System.out.println(status);
		ticketData.setData(roomId, formId, ticketId, requesterId, comment, contact, status, rating, Rcomment);

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
//		zd.getTicketComments(Long.parseLong(ticketId));
//		Room r = api.context().byId(roomId);
//		Chatlet cer = api.context().chatletById(formId);
//		Reply rep = cer.createReply().addField("upCom", upCom);
//		api.perform(r.reply(rep));
		
	}
	
	

	
//	public static void main(String[] args) {
//		String path = "/home/anuj-intern22/Desktop/gup/eclipse/ZendeskBot/data/zendesk-config.properties";
//		Properties configProps = new Properties();
//		
//		System.out.println("Path - " + path);
//		
//		try {
//			configProps = loadPropertyFileFromDisk(path);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		
//		System.out.println("BotEmail - " + configProps.getProperty("botemail"));
//		
//       //System.out.println(configProps.getProperty("endpoint"));
//		
////		TeamchatAPI api = TeamchatAPI.fromFile("teamchat1.data").setEmail(configProps.getProperty("botemail").trim()).setPassword(configProps.getProperty("botpass").trim()).setEndPoint(configProps.getProperty("endpoint").trim()).startReceivingEvents(new Bot());
//		TeamchatAPI api = TeamchatAPI.fromFile("teamchat1.data")
//				.setEmail(configProps.getProperty("botemail").trim())
//				.setPassword(configProps.getProperty("botpass").trim()).setEndPoint(configProps.getProperty("endpoint").trim()).startReceivingEvents(new Bot());
//	}
	
	public static Properties loadPropertyFileFromDisk(String filePath) throws Exception
	{
	
		Properties configProp = new Properties();
		FileInputStream fIS = new FileInputStream(filePath);
		configProp.load(fIS);
		fIS.close();
		return configProp;
	}
}
