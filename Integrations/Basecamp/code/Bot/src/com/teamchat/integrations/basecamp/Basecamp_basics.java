/**
 * 
 */
package com.teamchat.integrations.basecamp;

/**
 * @author intern19
 *
 */
public class Basecamp_basics {
	private String email, href, expires_in, refresh_token, access_token;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
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

	public Basecamp_basics(){
		
	}
	
	public Basecamp_basics(String email, String href, String expires_in,
			String refresh_token, String access_token) {
		super();
		this.email = email;
		this.href = href;
		this.expires_in = expires_in;
		this.refresh_token = refresh_token;
		this.access_token = access_token;
	}
}
