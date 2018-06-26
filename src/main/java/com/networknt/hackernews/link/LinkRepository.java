package com.networknt.hackernews.link;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.networknt.hackernews.user.User;
import com.networknt.hackernews.user.UserRepository;
import com.visoft.utils.Const;
import com.visoft.utils.DBUtils;

import graphql.GraphQLException;

/**
 * @author vlad
 *
 */
public class LinkRepository {

	private static final MongoCollection<Link> links = DBUtils.DB
			.getCollection("links", Link.class);

	/**
	 * @param id
	 * @return Link
	 */
	public static Link findById(final String id) {
		Link link = links.find(eq(Const._ID, new ObjectId(id))).first();
		return link;
	}

	/**
	 * @return List<Link>
	 */
	public static List<Link> getAllLinks(
			final LinkedHashMap<String, String> filterData, final Number limit,
			final Number skip) {

		Optional<Bson> mongoFilter = Optional.ofNullable(filterData)
				.map(LinkRepository::buildLinkFilter);
		List<Link> allLinks = new ArrayList<>();
		FindIterable<Link> selectedLinks= mongoFilter.map(links::find)
				.orElseGet(links::find);
		for (Link link : selectedLinks.skip(skip.intValue())
				.limit(limit.intValue())) {
			allLinks.add(link);
		}
		return allLinks;
	}

	/**
	 * @param link
	 * @return Link
	 */
	public static Link saveLink(final Link link) {
		links.insertOne(link);
		return link;
	}

	/**
	 * @param url
	 * @param description
	 * @param token
	 * @return Link
	 */
	public static Link createLink(final String url, final String description,
			final String token) {

		User user = UserRepository.findByToken(token);
		if (user == null) {
			throw new GraphQLException("The user is not authorised");
		}
		Link newLink = new Link(url, description, user.getId().toString());
		return LinkRepository.saveLink(newLink);
	}

	// build a Bson from filtering a getAllLinks
	private static Bson buildLinkFilter(
			final LinkedHashMap<String, String> filterData) {

		Bson result = null;

		String descriptionPattern = filterData.get(Const.DESCRIPTION_CONTAINS);
		String urlPattern = filterData.get(Const.URL_CONTAINS);

		Bson descriptionCondition = null;
		Bson urlCondition = null;

		if (descriptionPattern != null && !descriptionPattern.isEmpty()) {
			descriptionCondition = regex(Link.DESCRIPTION,
					".*" + descriptionPattern + ".*", "i");
		}

		if (urlPattern != null && !urlPattern.isEmpty()) {
			urlCondition = regex(Link.URL, ".*" + urlPattern + ".*", "i");
		}

		if (descriptionCondition != null && urlCondition != null) {
			result = and(descriptionCondition, urlCondition);
		}

		result = descriptionCondition != null ? descriptionCondition
				: urlCondition;
		return result;
	}

}
