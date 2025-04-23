package org.fastcampus.community_feed.post.application.dto;

public record CreateCommentRequestDto(Long postId, Long userId, String content) {
}
