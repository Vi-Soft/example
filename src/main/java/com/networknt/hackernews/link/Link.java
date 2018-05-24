package com.networknt.hackernews.link;

/**
 * @author vlad
 *
 */
public class Link {

	public static final String URL = "url";
	public static final String DESCRIPTION = "description";
	public static final String POSTED_BY = "postedBy";

	private final String id;
	private final String url;
	private final String description;
	private final String postedBy;

	public Link(final String url, final String description, final String userId) {
		this(null, url, description, userId);
	}

	public Link(final String id, final String url, final String description, final String userId) {
		super();
		this.id = id;
		this.url = url;
		this.description = description;
		this.postedBy = userId;
	}

	public String getUrl() {
		return this.url;
	}

	public String getDescription() {
		return this.description;
	}

	public String getId() {
		return id;
	}

	public String getPostedBy() {
		return postedBy;
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", url=" + url + ", description=" + description + ", postedBy=" + postedBy + "]";
	}

}
