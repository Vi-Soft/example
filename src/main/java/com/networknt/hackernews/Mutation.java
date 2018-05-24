package com.networknt.hackernews;

import com.networknt.hackernews.link.Link;
import com.networknt.hackernews.link.LinkRepository;
import com.networknt.hackernews.user.SigninPayload;
import com.networknt.hackernews.user.User;
import com.networknt.hackernews.user.UserRepository;
import com.networknt.hackernews.vote.Vote;
import com.networknt.hackernews.vote.VoteRepository;
import com.networknt.utils.Const;
import com.networknt.utils.HttpUtils;

import graphql.schema.DataFetcher;

/**
 * @author vlad
 *
 */
public interface Mutation {

	DataFetcher<Vote> createVote = env -> VoteRepository.createVote(
			env.getArgument(Vote.USER_ID), env.getArgument(Vote.LINK_ID));

	DataFetcher<Link> createLink = env -> LinkRepository.createLink(
			env.getArgument(Const.URL), env.getArgument(Const.DESCRIPTION),
			HttpUtils.getTokenFromExchange(env.getContext()));

	DataFetcher<User> createUser = env -> UserRepository.createUser(
			env.getArgument(Const.NAME), env.getArgument(Const.AUTH_PROVIDER));

	DataFetcher<SigninPayload> signinUser = env -> UserRepository
			.signinUser(env.getArgument(Const.AUTH_PROVIDER));
}
