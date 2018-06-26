package com.networknt.hackernews.vote;

import static com.mongodb.client.model.Filters.eq;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;
import com.visoft.utils.DBUtils;

/**
 * @author vlad
 *
 */
public class VoteRepository {

	public static final String VOTES_MONGO_COLLECTION = "votes";

	private static final MongoCollection<Vote> votes = DBUtils.DB
			.getCollection(VOTES_MONGO_COLLECTION, Vote.class);

	/**
	 * @param userId
	 * @return List<Vote>
	 */
	public static List<Vote> findByUserId(final String userId) {
		List<Vote> list = new ArrayList<>();
		for (Vote vote : votes.find(eq(Vote.USER_ID, userId))) {
			list.add(vote);
		}
		return list;
	}

	/**
	 * @param linkId
	 * @return List<Vote>
	 */
	public static List<Vote> findByLinkId(final String linkId) {
		List<Vote> list = new ArrayList<>();
		for (Vote vote : votes.find(eq(Vote.LINK_ID, linkId))) {
			list.add(vote);
		}
		return list;
	}

	/**
	 * @param vote
	 * @return Vote
	 */
	public static Vote saveVote(final Vote vote) {
		if (vote != null) {
			votes.insertOne(vote);
		}
		return vote;
	}

	/**
	 * @param userId
	 * @param linkId
	 * @return Vote
	 */
	public static Vote createVote(final String userId, final String linkId) {
		ZonedDateTime now = Instant.now().atZone(ZoneOffset.UTC);
		return VoteRepository.saveVote(new Vote(now, userId, linkId));
	}

}
