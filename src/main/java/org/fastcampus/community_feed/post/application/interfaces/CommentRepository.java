package org.fastcampus.community_feed.post.application.interfaces;

import org.fastcampus.community_feed.post.domain.comment.Comment;

import java.util.Optional;


public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
}
