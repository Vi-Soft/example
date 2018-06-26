package com.networknt.hackernews.link;

import org.bson.types.ObjectId;

/**
 * @author vlad
 *
 */
public class Link {

	public static final String URL = "url";
	public static final String DESCRIPTION = "description";
	public static final String POSTED_BY = "postedBy";

	private ObjectId _id;
	private String url;
	private String description;
	private String postedBy;

	public Link() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Link(final String url, final String description, final String postedBy) {
		super();
		this._id = ObjectId.get();
		this.url = url;
		this.description = description;
		this.postedBy = postedBy;
	}

	public ObjectId getId() {
		return _id;
	}

	public void setId(ObjectId _id) {
		this._id = _id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((postedBy == null) ? 0 : postedBy.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Link other = (Link) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (postedBy == null) {
			if (other.postedBy != null)
				return false;
		} else if (!postedBy.equals(other.postedBy))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Link [_id=" + _id + ", url=" + url + ", description="
				+ description + ", postedBy=" + postedBy + "]";
	}

}
