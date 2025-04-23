package org.fastcampus.community_feed.fake;

import org.fastcampus.community_feed.post.application.CommentService;
import org.fastcampus.community_feed.post.application.PostService;
import org.fastcampus.community_feed.post.application.interfaces.CommentRepository;
import org.fastcampus.community_feed.post.application.interfaces.LikeRepository;
import org.fastcampus.community_feed.post.application.interfaces.PostRepository;
import org.fastcampus.community_feed.post.repository.FakeCommentRepository;
import org.fastcampus.community_feed.post.repository.FakeLikeRepository;
import org.fastcampus.community_feed.post.repository.FakePostRepository;
import org.fastcampus.community_feed.user.application.UserRelationService;
import org.fastcampus.community_feed.user.application.UserService;
import org.fastcampus.community_feed.user.application.interfaces.UserRelationRepository;
import org.fastcampus.community_feed.user.application.interfaces.UserRepository;
import org.fastcampus.community_feed.user.interfaces.FakeUserRelationRepository;
import org.fastcampus.community_feed.user.interfaces.FakeUserRepository;

public class FakeObjectFactory {
    private static final UserRepository userRepository = new FakeUserRepository();
    private static final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository postRepository = new FakePostRepository();
    private static final CommentRepository commentRepository = new FakeCommentRepository();
    private static final LikeRepository likeRepository = new FakeLikeRepository();

    private static final UserService userService = new UserService(userRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);
    private static final PostService postService = new PostService(userService, postRepository, likeRepository);
    private static final CommentService commentService = new CommentService(userService, postService, commentRepository, likeRepository);

    private FakeObjectFactory(){}

    public static UserService getUserService(){
        return userService;
    }

    public static UserRelationService getUserRelationService(){
        return userRelationService;
    }

    public static PostService getPostService(){
        return postService;
    }
    public static CommentService getCommentService(){
        return commentService;
    }


}
