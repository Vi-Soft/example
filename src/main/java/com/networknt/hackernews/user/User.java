package com.networknt.hackernews.user;

/**
 * @author vlad
 *
 */
public class User {

	private final String id;
	private final String name;
	private final String email;
	private final String passwd;

	public User(final String name, final String email, final String passwd) {
		this(null, name, email, passwd);
	}

	public User(final String id, final String name, final String email,
			final String passwd) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.passwd = passwd;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPasswd() {
		return passwd;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email
				+ ", password=" + passwd + "]";
	}

}
