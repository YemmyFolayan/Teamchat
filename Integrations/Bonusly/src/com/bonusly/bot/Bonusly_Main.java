package com.bonusly.bot;

import java.io.IOException;

import com.teamchat.client.annotations.OnAlias;
import com.teamchat.client.annotations.OnKeyword;
import com.teamchat.client.sdk.TeamchatAPI;
import com.teamchat.client.sdk.chatlets.PrimaryChatlet;
import com.teamchat.client.sdk.chatlets.TextChatlet;

public class Bonusly_Main {
	

	@OnKeyword("help")
	public void ConnectAndHelp(TeamchatAPI api) throws IOException {
		api.perform(api.context()
				.currentRoom()
				.post(new PrimaryChatlet()
						.setQuestionHtml("HI, This is Bonusly Bot.</br>You may proceed to use your <u>Bonusly account</u> after you login. "
								+ "<br />"
								+ "Enter the following commands to use your account:"
								+ "<br />" + "")));
		api.perform(api
				.context()
				.currentRoom()
				.post((new PrimaryChatlet().setQuestion(
						"Fill in your Bonusly Credentials.").setReplyScreen(
						api.objects()
								.form()
								.addField(
										api.objects().input().label("Email")
												.name("email"))
								.addField(
										api.objects().input().label("Password")
												.name("pwd"))).alias("oncreds"))));

	}

	@OnAlias("oncreds")
	public void oncreds(TeamchatAPI api) throws IOException {

		String email = api.context().currentReply().getField("email");
		String pwd = api.context().currentReply().getField("pwd");
		Authorizer auth = new Authorizer();
		String Token = auth.getAccessToken(email, pwd);
		String TeamchatEmail = api.context().currentReply().senderEmail();
		if (Token != null || Token != "") {
			api.perform(api.context().currentRoom()
					.post(new TextChatlet("You are authenticated")));
		}

		Database_Handler db = new Database_Handler();
		db.StorageHandler(email, Token, TeamchatEmail);

		
	}
}