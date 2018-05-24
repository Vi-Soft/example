package com.networknt.hackernews.vote;

import com.networknt.hackernews.link.Link;
import com.networknt.hackernews.link.LinkRepository;
import com.networknt.hackernews.user.User;
import com.networknt.hackernews.user.UserRepository;

/**
 * @author vlad
 *
 */
public class VoteResolver {

	/**
	 * @param env
	 * @return User
	 */
	public static User user(final String userId) {
		User user = null;
		if (userId != null) {
			user = UserRepository.findById(userId);
		}

		return user;
	}

	/**
	 * @param env
	 * @return Link
	 */
	public static Link link(final String linkId) {
		Link link = null;
		if (linkId != null) {
			link = LinkRepository.findById(linkId);
		}

		return link;
	}
}
