package com.networknt.hackernews.user;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.networknt.utils.Const;

import graphql.GraphQLException;

/**
 * @author vlad
 *
 */
public class UserRepository {

	public static final String USERS_MONGO_COLLECTION = "users";

	private static final MongoCollection<Document> users = Const.MONGO
			.getCollection(USERS_MONGO_COLLECTION);

	public static List<User> getAllUsers() {
		List<User> allUsers = new ArrayList<>();

		for (Document doc : users.find()) {

			allUsers.add(user(doc));
		}
		return allUsers;
	}

	/**
	 * @param id
	 * @return User
	 */
	public static User findById(final String id) {
		Document doc = users.find(eq(Const._ID, new ObjectId(id))).first();
		return user(doc);
	}

	/**
	 * @param name
	 * @param authDataMap
	 * @return User
	 */
	public static User createUser(final String name,
			final LinkedHashMap<String, String> authDataMap) {

		String email = authDataMap.get(Const.EMAIL);
		String passwd = authDataMap.get(Const.PASSWD);

		User newUser = new User(name, email, passwd);
		return UserRepository.saveUser(newUser);
	}

	/**
	 * @param authDataMap
	 * @return SigninPayload
	 */
	public static SigninPayload signinUser(
			final LinkedHashMap<String, String> authDataMap) {

		String email = authDataMap.get(Const.EMAIL);
		String passwd = authDataMap.get(Const.PASSWD);

		User user = UserRepository.findByEmail(email);
		if (user.getPasswd().equals(passwd)) {
			return new SigninPayload(user.getId(), user);
		}
		throw new GraphQLException("Invalid credentials");
	};

	/**
	 * @param email
	 * @return User
	 */
	public static User findByEmail(final String email) {
		Document doc = users.find(eq(Const.EMAIL, email)).first();
		return user(doc);
	}

	/**
	 * @param env
	 * @return User
	 */
	public static User findByToken(final String token) {
		return findById(token);
	}

	/**
	 * @param user
	 * @return User
	 */
	public static User saveUser(final User user) {
		Document doc = new Document();
		doc.append(Const.NAME, user.getName());
		doc.append(Const.EMAIL, user.getEmail());
		doc.append(Const.PASSWD, user.getPasswd());
		users.insertOne(doc);
		return new User(doc.get(Const._ID).toString(), user.getName(),
				user.getEmail(), user.getPasswd());
	}

	private static User user(final Document doc) {
		return new User(doc.get(Const._ID).toString(),
				doc.getString(Const.NAME), doc.getString(Const.EMAIL),
				doc.getString(Const.PASSWD));
	}

	private static Bson buildFilter(final UserFilter filter) {
		String namePattern = filter.getNameContains();
		String emailPattern = filter.getEmailContains();

		Bson nameCondition = null;
		Bson emailCondition = null;

		if (namePattern != null && !namePattern.isEmpty()) {
			nameCondition = regex(Const.NAME, ".*" + namePattern + ".*", "i");
		}

		if (emailPattern != null && !emailPattern.isEmpty()) {

			emailCondition = regex(Const.EMAIL, ".*" + emailPattern + ".*",
					"i");
		}

		if (nameCondition != null && emailCondition != null) {
			return and(nameCondition, emailCondition);
		}

		return nameCondition != null ? nameCondition : emailCondition;
	}
}
