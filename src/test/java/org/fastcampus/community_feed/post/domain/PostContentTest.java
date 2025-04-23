package org.fastcampus.community_feed.post.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.fastcampus.community_feed.post.domain.content.PostContent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {
    @Test
    void givenContentLengthIsOkWhenCreatePostContentThenNotThrowError() {
        // given
        String content = "this is a test content";

        // when, then
        assertDoesNotThrow(() -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsOverLimitCreatePostContentThenThrowError() {
        // given
        String content = "a".repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsOverLimitAndKoreanCreatePostContentThenThrowError() {
        // given
        String content = "뷁".repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }


    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "국", "삵"})
    void givenContentLengthIsOverLimitAndKoreanCreate_ThenThrowError(String koreanWord) {
        // given
        String content = koreanWord.repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsUnderLimitCreatePostContentThenThrowError() {
        // given
        String content = "abcd";

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsEmptyLimitCreatePostContentThenThrowError() {
        // given
        String content = "";

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsNullLimitCreatePostContentThenThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new PostContent(null));
    }
}
