package com.networknt.hackernews.user;

/**
 * @author vlad
 *
 */
public class AuthData {

	private String email;
	private String passwd;

	public AuthData() {
		super();
	}

	public AuthData(final String email, final String passwd) {
		super();
		this.email = email;
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPassword(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return "AuthData [email=" + email + ", password=" + passwd + "]";
	}

}
