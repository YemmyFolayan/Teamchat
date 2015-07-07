package com.teamchat.integration.giphy.bot;

import java.io.IOException;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class giphyintegrator {

	
 public String getimages(String keyword) throws IOException{
   	  
   	  String err = "Error";
         	
	
			
			OkHttpClient client = new OkHttpClient();

			Request request = new Request.Builder()
			  .url("http://api.giphy.com/v1/gifs/search?api_key=dc6zaTOxFJmzC&q="+keyword)
			  .get()
			  .build();

			Response response = client.newCall(request).execute();
			
			 if (!response.isSuccessful())
			 {
				 return err;
				 
			 }
				 
			 else
			return response.body().string();
	}
}
