/* Author: Prakhar Vashisht

 * Date: May 28, 2015
 * 
 * Description:
 * When a user send the keyword 'dataentry', he gets a data entry form which has following 3 fields:
   		1. Area - this has 4 options Mumbai, Rest of Maharashtra, Gujarat and Goa
      	2. Sales
		3. Product Code
   If in the response, sales is less than 1000 for Goa or less than 2000 for any of the others, it sends 
   a form asking for the reason.
   After getting the reason, it creates a room between bot, admin and user mentioning the reason and product 
   code of the product
*/

package com.teamchat.exceprep2;

import com.teamchat.client.sdk.Room;
import com.teamchat.client.sdk.TeamchatAPI;
import com.teamchat.client.sdk.chatlets.PrimaryChatlet;
import com.teamchat.client.sdk.chatlets.TextChatlet;
import com.teamchat.client.sdk.impl.TeamchatAPIImpl;
import com.teamchat.client.annotations.OnKeyword;
import com.teamchat.client.annotations.OnAlias;

public class ExceptionReporting2 {

	public static final String bot="bot888@outlook.com";
	public static final String password="bazingacooper04";
	public static final String admin="prakhar888.v@gmail.com";
	public static String sales="";
	public static String area="";
	public static String code="";
	
	/* This method recognizes the keyword and creates a chatlet with above specified form */
	@OnKeyword("dataentry")
	public void onDataEntry(TeamchatAPI api) {
		api.perform(
				api.context().currentRoom().post(
						new PrimaryChatlet()
						.setQuestion("Please enter the sales of your region. ")
						.setReplyScreen(api.objects().form()
								.addField(api.objects().select().label("Area").name("area")
										.addOption("Mumbai")
										.addOption("Gujarat")
										.addOption("Goa")
										.addOption("Rest of Maharashtra")
										)
								.addField(api.objects().input().label("Product Code").name("code"))
								.addField(api.objects().input().label("Sales").name("sales"))
								
								)
								.alias("exceprep2")));
								
	}
	
	/* This method fetches the entries from the form and check validity of the entries */
	@OnAlias("exceprep2")
	public void onEntry(TeamchatAPI api){
		
		code=api.context().currentReply().getField("code");
		area = api.context().currentReply().getField("area");
		sales = api.context().currentReply().getField("sales");
		int sale=Integer.parseInt(sales);
		
		if(area.equals("Goa")){
				if(sale<1000){
					getReason(api); // getReason method called when sales are less than desired			
						}
		}
		
		else{
			if(sale<2000){
				getReason(api);  //getReason method called when sales are less than desired
				}
		}
	}
	
	/* This method is executed when sales values are less than desired and
	 * creates a form to fetch the reason for low sales */
	public static void getReason(TeamchatAPI api){
		
		api.perform(api.context().currentRoom().post(
				new PrimaryChatlet()
				.setQuestion("Sales in your region appear to be low. Please mention the reason.")
				.setReplyScreen(api.objects().form()
						.addField(api.objects().input().label("Reason").name("reason"))
						)
				.alias("reasson")
				));
	}
	
	/* This method fetches the reason for low sales and saves it in a variable */
	@OnAlias("reasson")
	public static void onReason(TeamchatAPI api){
		
		String reason=api.context().currentReply().getField("reason");
		String user=api.context().currentReply().senderEmail();
		
		Room r=api.context().create().setName("Report Summary")
				.add(admin)
				.add(user);
		
		api.perform(r.post(new TextChatlet("Below threshold sales of " + code
											+ " of Rs. " + sales
											+ " due to " + reason)));
	
		System.out.println("Successfully new Room created");
	}
	
	/* Main method */
	public static void main(String[] args) throws Exception {
		TeamchatAPIImpl.fromFile("config.json")
		.setEmail(bot)
		.setPassword(password)
		.startReceivingEvents(new ExceptionReporting2());
		}

}
