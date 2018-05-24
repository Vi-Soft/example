package com.networknt.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @author vlad
 *
 */
public interface Const {

	String DB_NAME = "hackernews";
	MongoDatabase MONGO = new MongoClient().getDatabase(DB_NAME);

	String Query = "Query";
	String Mutation = "Mutation";

	String _ID = "_id";
	
	String LINK_QUERY = "link";
	String ALL_LINKS = "allLinks";
	String FIND_LINK_BY_ID = "findLinkById";
	String CREATE_LINK = "createLink";
	
	String USER_QUERY = "user";
	String SIGNIN_USER = "signinUser";
	String CREATE_USER = "createUser";
	String AUTH = "auth";
	String AUTH_PROVIDER = "authProvider";
	String NAME = "name";
	String EMAIL = "email";
	String PASSWD = "passwd";
	String URL = "url";
	String DESCRIPTION = "description";
	String ALL_USERS = "allUsers";
	
	String CREATE_VOTE = "createVote";
	
	String DESCRIPTION_CONTAINS = "description_contains";
	String URL_CONTAINS = "url_contains";
	
	String FILTER = "filter";
	String SKIP = "skip";
	String LIMIT = "limit";
}
