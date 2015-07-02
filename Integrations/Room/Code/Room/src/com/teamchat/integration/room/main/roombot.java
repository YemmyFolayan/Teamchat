package com.teamchat.integration.room.main;

import com.teamchat.client.annotations.OnAlias;
import com.teamchat.client.annotations.OnKeyword;
import com.teamchat.client.sdk.Form;
import com.teamchat.client.sdk.Room;
import com.teamchat.client.sdk.TeamchatAPI;
import com.teamchat.client.sdk.chatlets.PrimaryChatlet;

/*
 Author : Anuj Arora.Web2
 */

public class roombot {
	@OnKeyword(value = "Room")
	public void Room(TeamchatAPI api) {
		Form fo = api.objects().form();
		fo.addField(api.objects().input().name("Name").label("Name:"));
		fo.addField(api.objects().input().name("roomlink").label("Room Link:"));
		fo.addField(api.objects().input().name("EmailId").label("EmailId:"));
		api.perform(api
				.context()
				.currentRoom()
				.post(new PrimaryChatlet()
						.setQuestionHtml(
								"<center><a href=\"https://room.co/#/\" target=\"_blank\"><img src=\"http://www.yellowpages.rs/komitent_multimedia/93200/93245/logo/room-logo.gif\" border=\"0\" height=\"200\" width=\"200\" ></a></center>")
						.setReplyScreen(fo).setReplyLabel("Enter")
						.alias("adduser")));
	}

	@OnAlias(value = "adduser")
	public void adduser(TeamchatAPI api) {
		String Name = api.context().currentReply().getField("Name");
		String EmailId = api.context().currentReply().getField("EmailId");
		String roomlink = api.context().currentReply().getField("roomlink");
		Room roo = api.context().create().setName("RoomBot").add(EmailId);
		api.perform(roo.post(new PrimaryChatlet()
				.setQuestionHtml("<h4><b>Room Details:</b></h4>"
						+ "<ul type=\"square\"; style=\"color:#359FD8\";><li><a1 style=\"color:black\";><b>Contact Person- </b></a1><a2 style=\"color:#359FD8\";>"
						+ Name
						+ "</a2></li></ul>"
						+ "<br /><b>Click on the link below to join the Video Chat.</b>"
						+ "<br /><a href=\"" + roomlink
						+ "\"><b style=\"color:#359FD8\";>Go To Room</b></a>")));
	}

	@OnKeyword(value = "help")
	public void help(TeamchatAPI api) {
		api.perform(api
				.context()
				.currentRoom()
				.post(new PrimaryChatlet()
						.setQuestionHtml("<br /><b>Hi, I'm ROOM Bot.</b>"
								+ "<br />I'll help you to interact with your friends or relatives with the help of video chat."
								+ "<br />Write a command <a><b>\"Room\"</b></a> for extracting the link."
								+ "<br /> After that just click on the image and it will redirect you to a page, Where you will find a Start video chat button."
								+ "<br />Clicking on it will redirect you to a page Where you will find a link , Copy that link and paste it in the reply with the Email Id of the person you want to interact with.")));
	}
}
