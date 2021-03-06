
package web.asana.servelet;

import com.google.gson.Gson;

/**
 * @author Aniruddha varshney
 *
 */
public class Token {
	private String refresh_token,access_token,token_type;
	private int expires_in;
	private Data data;
	public Token(int expires_in, String refresh_token, String access_token,
			String token_type, Data data) {
		super();
		this.expires_in = expires_in;
		this.refresh_token = refresh_token;
		this.access_token = access_token;
		this.token_type = token_type;
		this.data = data;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public Token(){
		
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	//convert data into array
	public String toDataArray(Data data){
		Gson gson = new Gson();
		return gson.toJson(data);
	}
}
