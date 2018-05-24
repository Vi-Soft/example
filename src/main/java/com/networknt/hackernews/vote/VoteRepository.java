package com.networknt.hackernews.vote;

import static com.mongodb.client.model.Filters.eq;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.networknt.utils.Const;

/**
 * @author vlad
 *
 */
public class VoteRepository {

	public static final String VOTES_MONGO_COLLECTION = "votes";

	private static final MongoCollection<Document> votes = Const.MONGO
			.getCollection(VOTES_MONGO_COLLECTION);

	/**
	 * @param userId
	 * @return List<Vote>
	 */
	public static List<Vote> findByUserId(final String userId) {
		List<Vote> list = new ArrayList<>();
		for (Document doc : votes.find(eq(Vote.USER_ID, userId))) {
			list.add(vote(doc));
		}
		return list;
	}

	/**
	 * @param linkId
	 * @return List<Vote>
	 */
	public static List<Vote> findByLinkId(final String linkId) {
		List<Vote> list = new ArrayList<>();
		for (Document doc : votes.find(eq(Vote.LINK_ID, linkId))) {
			list.add(vote(doc));
		}
		return list;
	}

	/**
	 * @param vote
	 * @return Vote
	 */
	public static Vote saveVote(final Vote vote) {
		Vote result = null;
		if (vote != null) {
			Document doc = new Document();
			doc.append(Vote.USER_ID, vote.getUserId());
			doc.append(Vote.LINK_ID, vote.getLinkId());
			doc.append(Vote.CREATED_AT, Scalars.dateTime.getCoercing()
					.serialize(vote.getCreatedAt()));
			votes.insertOne(doc);
			result = new Vote(doc.get(Const._ID).toString(),
					vote.getCreatedAt(), vote.getUserId(), vote.getLinkId());
		}
		return result;
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

	private static Vote vote(final Document doc) {
		Vote vote = null;
		if (doc != null) {
			vote = new Vote(doc.get(Const._ID).toString(),
					ZonedDateTime.parse(doc.getString(Vote.CREATED_AT)),
					doc.getString(Vote.USER_ID), doc.getString(Vote.LINK_ID));
		}
		return vote;
	}

}
