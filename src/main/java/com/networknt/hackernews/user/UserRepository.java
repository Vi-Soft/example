package com.networknt.hackernews.user;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.visoft.utils.Const;
import com.visoft.utils.DBUtils;

import graphql.GraphQLException;

/**
 * @author vlad
 *
 */
public class UserRepository {

	public static final String USERS_MONGO_COLLECTION = "users";

	public static final IndexOptions indexOptions = new IndexOptions()
			.unique(true);
	private static final MongoCollection<User> users = DBUtils.DB
			.getCollection(USERS_MONGO_COLLECTION)
			.withDocumentClass(User.class);

	// email field must be unique
	public static final String emailIndex = users
			.createIndex(Indexes.ascending("email"), indexOptions);

	public static List<User> getAllUsers() {
		List<User> allUsers = new ArrayList<>();

		for (User user : users.find()) {

			allUsers.add(user);
		}
		return allUsers;
	}

	/**
	 * @param id
	 * @return User
	 */
	public static User findById(final String id) {
		User user = users.find(eq("_id", new ObjectId(id))).first();
		return user;
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
			return new SigninPayload(user.getId().toString(), user);
		}
		throw new GraphQLException("Invalid credentials");
	};

	/**
	 * @param email
	 * @return User
	 */
	public static User findByEmail(final String email) {
		User user = users.find(eq(Const.EMAIL, email)).first();
		return user;
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
		users.insertOne(user);
		return user;
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
