package org.fastcampus.community_feed.post.application;

import org.fastcampus.community_feed.post.application.dto.CreatePostRequestDto;
import org.fastcampus.community_feed.post.application.dto.LikeRequestDto;
import org.fastcampus.community_feed.post.application.dto.UpdatePostRequestDto;
import org.fastcampus.community_feed.post.application.interfaces.LikeRepository;
import org.fastcampus.community_feed.post.application.interfaces.PostRepository;
import org.fastcampus.community_feed.post.domain.Post;
import org.fastcampus.community_feed.post.domain.content.Content;
import org.fastcampus.community_feed.post.domain.content.PostContent;
import org.fastcampus.community_feed.user.application.UserService;
import org.fastcampus.community_feed.user.domain.User;

public class PostService {
    private final UserService userService;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(UserService userService, PostRepository postRepository, LikeRepository likeRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post no found"));
    }

    public Post createPost(CreatePostRequestDto dto) {
        User author = userService.getUser(dto.userId());
        Content content = new PostContent(dto.content());
        Post post = new Post(null, author, content, dto.state());
        return postRepository.save(post);
    }

    public Post updatePost(UpdatePostRequestDto dto){
        Post post = this.getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        post.updatePost(user, post.getContent(), post.getState());
        return postRepository.save(post);

    }

    public void likePost(Long id, Long userId){
        Post post = getPost(id);
        User user = userService.getUser(userId);

        if(likeRepository.checkList(post, user)){
            return;
        }

        post.like(user);
        likeRepository.like(post,user);
    }


    public void unLikePost(LikeRequestDto dto){
        Post post = getPost(dto.targetId());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkList(post, user)){
            post.unlike();
            likeRepository.unLike(post,user);
        }
    }

}
