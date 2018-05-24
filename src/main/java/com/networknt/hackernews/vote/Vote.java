package com.networknt.hackernews.vote;

import java.time.ZonedDateTime;

/**
 * @author vlad
 *
 */
public class Vote {

	public static final String CREATED_AT = "createdAt";
	public static final String USER_ID = "userId";
	public static final String LINK_ID = "linkId";

	private final String id;
	private final ZonedDateTime createdAt;
	private final String userId;
	private final String linkId;

	public Vote(final ZonedDateTime createdAt, final String userId,
			final String linkId) {
		this(null, createdAt, userId, linkId);
	}

	public Vote(final String id, final ZonedDateTime createdAt,
			final String userId, final String linkId) {
		this.id = id;
		this.createdAt = createdAt;
		this.userId = userId;
		this.linkId = linkId;
	}

	public String getId() {
		return id;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public String getUserId() {
		return userId;
	}

	public String getLinkId() {
		return linkId;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", createdAt=" + createdAt + ", userId="
				+ userId + ", linkId=" + linkId + "]";
	}

}
