package com.networknt.hackernews.user;

/**
 * @author vlad
 *
 */
public class SigninPayload {

	private final String token;
	private final User user;

	public SigninPayload(final String token, final User user) {
		super();
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "SigninPayload [token=" + token + ", user=" + user + "]";
	}

}
