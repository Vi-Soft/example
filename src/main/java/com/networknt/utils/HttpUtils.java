package com.networknt.utils;

import graphql.GraphQLException;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.HeaderValues;

/**
 * @author vlad
 *
 */
public final class HttpUtils {

	private HttpUtils() {
		
	}
	
	/**
	 * @param exchange
	 * @return String
	 */
	public static String getTokenFromExchange(final HttpServerExchange exchange) {
		HeaderMap headerMap = exchange.getRequestHeaders();
		HeaderValues headerValues = headerMap.get("Authorization");
		if (headerValues.isEmpty()) {
			throw new GraphQLException("The user is not authorised");
		}
		String token = headerValues.getFirst();
		return token.replace("Bearer ", "");
	}
}
