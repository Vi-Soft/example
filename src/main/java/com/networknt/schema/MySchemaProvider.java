package com.networknt.schema;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.networknt.graphql.router.SchemaProvider;
import com.networknt.hackernews.Mutation;
import com.networknt.hackernews.Query;
import com.networknt.hackernews.link.Link;
import com.networknt.hackernews.vote.Vote;
import com.visoft.utils.Const;
import com.visoft.utils.FileUtil;
import com.visoft.utils.Scalars;

import graphql.GraphQLException;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

/**
 * @author vlad
 *
 */
public final class MySchemaProvider implements SchemaProvider {

	private static final Logger logger = LoggerFactory
			.getLogger(MySchemaProvider.class);
	private static final String ROOT_SCHEMA_NAME = "schema.graphqls";
	private static final String USER_SCHEMA_NAME = "userSchema.graphqls";
	private static final String LINK_SCHEMA_NAME = "linkSchema.graphqls";
	private static final String VOTE_SCHEMA_NAME = "voteSchema.graphqls";

	@Override
	public GraphQLSchema getSchema() {
		SchemaParser schemaParser = new SchemaParser();

		TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();

		typeRegistry.merge(schemaParser.parse(loadSchema(ROOT_SCHEMA_NAME)));
		typeRegistry.merge(schemaParser.parse(loadSchema(USER_SCHEMA_NAME)));
		typeRegistry.merge(schemaParser.parse(loadSchema(LINK_SCHEMA_NAME)));
		typeRegistry.merge(schemaParser.parse(loadSchema(VOTE_SCHEMA_NAME)));

		RuntimeWiring wiring = buildRuntimeWiring();
		return new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
	}

	private static RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring().scalar(Scalars.dateTime)
				.scalar(Scalars.objectId)
				.type(Query.class.getSimpleName(), typeWiring -> typeWiring
						.dataFetcher(Const.ALL_LINKS, Query.allLinks)
						.dataFetcher(Const.ALL_USERS, Query.allUsers)
						.dataFetcher(Const.FIND_LINK_BY_ID, Query.findLinkById))
				.type(Link.class.getSimpleName(),
						typeWiring -> typeWiring.dataFetcher(Link.POSTED_BY,
								Query.postedBy))
				.type(Vote.class.getSimpleName(),
						typeWiring -> typeWiring
								.dataFetcher(Const.LINK_QUERY, Query.link)
								.dataFetcher(Const.USER_QUERY, Query.user))
				.type(Mutation.class.getSimpleName(), typeWiring -> typeWiring
						.dataFetcher(Const.CREATE_LINK, Mutation.createLink)
						.dataFetcher(Const.CREATE_USER, Mutation.createUser)
						.dataFetcher(Const.SIGNIN_USER, Mutation.signinUser)
						.dataFetcher(Const.CREATE_VOTE, Mutation.createVote))
				.build();
	}

	private static String loadSchema(final String schemaFile) {
		try (InputStream is = MySchemaProvider.class.getClassLoader()
				.getResourceAsStream(schemaFile)) {
			return FileUtil.readInputStream(is);
		} catch (Exception e) {
			logger.error("IOException:", e);
			throw new GraphQLException("Error on reading graphql schema.");
		}
	}

}