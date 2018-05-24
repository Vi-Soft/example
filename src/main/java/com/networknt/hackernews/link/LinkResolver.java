package com.networknt.hackernews.link;

import com.networknt.hackernews.user.User;
import com.networknt.hackernews.user.UserRepository;

/**
 * @author vlad
 *
 */
public class LinkResolver {

	/**
	 * @param userId
	 * @return User
	 */
	public static User postedBy(final String userId) {
		User user = null;
		if (userId != null) {
			user = UserRepository.findById(userId);
		}

		return user;
	}

}
