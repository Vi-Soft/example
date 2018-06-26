package com.networknt.hackernews;

import java.util.List;

import com.networknt.hackernews.link.Link;
import com.networknt.hackernews.link.LinkRepository;
import com.networknt.hackernews.link.LinkResolver;
import com.networknt.hackernews.user.User;
import com.networknt.hackernews.user.UserRepository;
import com.networknt.hackernews.vote.Vote;
import com.networknt.hackernews.vote.VoteResolver;
import com.visoft.utils.Const;

import graphql.schema.DataFetcher;

/**
 * @author vlad
 *
 */
public interface Query {

	DataFetcher<List<Link>> allLinks = env -> LinkRepository.getAllLinks(
			env.getArgument(Const.FILTER), env.getArgument(Const.LIMIT),
			env.getArgument(Const.SKIP));

	DataFetcher<List<User>> allUsers = env -> UserRepository.getAllUsers();

	DataFetcher<Link> findLinkById = env -> LinkRepository
			.findById(env.getArgument(Const._ID).toString());

	DataFetcher<User> findUserById = env -> UserRepository
			.findById(env.getArgument(Const._ID));

	DataFetcher<User> postedBy = env -> LinkResolver
			.postedBy( ((Link)env.getSource()).getPostedBy());

	DataFetcher<User> user = env -> VoteResolver
			.user(((Vote) env.getSource()).getUserId());

	DataFetcher<Link> link = env -> VoteResolver
			.link(((Vote) env.getSource()).getUserId());

}
