package com.networknt.hackernews.vote;

import java.time.ZonedDateTime;

import org.bson.types.ObjectId;

/**
 * @author vlad
 *
 */
public class Vote {

	public static final String CREATED_AT = "createdAt";
	public static final String USER_ID = "userId";
	public static final String LINK_ID = "linkId";

	private ObjectId id;
	private ZonedDateTime createdAt;
	private String userId;
	private String linkId;

	public Vote() {

	}

	public Vote(final ZonedDateTime createdAt, final String userId,
			final String linkId) {
		this.id = ObjectId.get();
		this.createdAt = createdAt;
		this.userId = userId;
		this.linkId = linkId;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public ObjectId getId() {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((linkId == null) ? 0 : linkId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vote other = (Vote) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (linkId == null) {
			if (other.linkId != null)
				return false;
		} else if (!linkId.equals(other.linkId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", createdAt=" + createdAt + ", userId="
				+ userId + ", linkId=" + linkId + "]";
	}

}
