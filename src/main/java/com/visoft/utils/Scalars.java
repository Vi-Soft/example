package com.visoft.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.bson.types.ObjectId;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

/**
 * @author vlad
 *
 */
public class Scalars {

	public static GraphQLScalarType dateTime = new GraphQLScalarType("DateTime",
			"DataTime scalar", new Coercing() {

				@Override
				public String serialize(Object input) {
					// serialize the ZonedDateTime into string on the way out
					return ((ZonedDateTime) input)
							.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
				}

				@Override
				public Object parseValue(Object input) {
					return serialize(input);
				}

				@Override
				public ZonedDateTime parseLiteral(Object input) {
					// parse the string values coming in
					if (input instanceof StringValue) {
						return ZonedDateTime
								.parse(((StringValue) input).getValue());
					} else {
						return null;
					}
				}
			});
	public static GraphQLScalarType objectId = new GraphQLScalarType("ObjectId",
			"ObjectId scalar", new Coercing() {

				@Override
				public String serialize(Object input) {
					// serialize the ObjectId into string on the way out
					return ((ObjectId) input).toString();
				}

				@Override
				public Object parseValue(Object input) {
					return serialize(input);
				}

				@Override
				public ObjectId parseLiteral(Object input) {
					// parse the string values coming in
					if (input instanceof StringValue) {
						return new ObjectId(((StringValue) input).toString());

					} else {
						return null;
					}
				}
			});
}