package com.networknt.hackernews.link;

import com.networknt.hackernews.user.User;
import com.networknt.hackernews.user.UserRepository;

/**
 * @author vlad
 *
 */
public class LinkResolver {

	/**
	 * @param id
	 * @return User
	 */
	public static User postedBy(final String id) {
		User user = null;
		if (id != null) {
			user = UserRepository.findById(id);
		}

		return user;
	}

}
