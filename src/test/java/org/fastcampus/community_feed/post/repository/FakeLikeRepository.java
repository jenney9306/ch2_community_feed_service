package org.fastcampus.community_feed.post.repository;

import org.fastcampus.community_feed.post.application.interfaces.LikeRepository;
import org.fastcampus.community_feed.post.domain.Post;
import org.fastcampus.community_feed.post.domain.comment.Comment;
import org.fastcampus.community_feed.user.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FakeLikeRepository implements LikeRepository {

    private final Map<Post, Set<User>> postLike = new HashMap<>();
    private final Map<Comment, Set<User>> commentLike = new HashMap<>();

    @Override
    public boolean checkList(Post post, User user) {
        if(postLike.get(post) == null){
            return false;
        }
        return postLike.get(post).contains(user);
    }

    @Override
    public void like(Post post, User user) {
        Set<User> users = postLike.get(post);
        if(users == null){
            users = Set.of(user);
        } else {
            users.add(user);
        }
        postLike.put(post, users);

    }

    @Override
    public void unLike(Post post, User user) {
        Set<User> users = postLike.get(post);
        if(users == null){
            return;
        }

        users.remove(user);
        postLike.put(post, users);
    }

    @Override
    public boolean checkList(Comment comment, User user) {
        if(commentLike.get(comment) == null){
            return false;
        }
        return commentLike.get(comment).contains(user);    }

    @Override
    public void like(Comment comment, User user) {
        Set<User> users = commentLike.get(comment);
        if(users == null){
            users = Set.of(user);
        } else {
            users.add(user);
        }
        commentLike.put(comment, users);

    }

    @Override
    public void unLike(Comment comment, User user) {
        Set<User> users = commentLike.get(comment);
        if(users == null){
            return;
        }
        users.remove(user);
        commentLike.put(comment, users);
    }
}
