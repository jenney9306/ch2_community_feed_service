package org.fastcampus.community_feed.user.application;

import org.fastcampus.community_feed.user.application.dto.CreateUserRequestDto;
import org.fastcampus.community_feed.user.application.dto.FollowUserRequestDto;
import org.fastcampus.community_feed.user.application.interfaces.UserRelationRepository;
import org.fastcampus.community_feed.user.application.interfaces.UserRepository;
import org.fastcampus.community_feed.user.domain.User;
import org.fastcampus.community_feed.user.interfaces.FakeUserRelationRepository;
import org.fastcampus.community_feed.user.interfaces.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRelationServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);

    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init(){
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);

        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwouser_whenFollow_thenUserFollowSaved(){
        //given
        userRelationService.follow(requestDto);

        //then
        assertEquals(1, user1.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());

    }


    @Test
    void givenCreateTwouser_whenAlreadyFollow_thenError(){
        //given
        userRelationService.follow(requestDto);

        //then
        assertThrows(IllegalArgumentException.class, () ->
        userRelationService.follow(requestDto));

    }

    @Test
    void givenCreateOneuser_whenFollow_thenError(){
        //given
        FollowUserRequestDto sameuser = new FollowUserRequestDto(user1.getId(), user1.getId());

        //then
        assertThrows(IllegalArgumentException.class, () ->
                userRelationService.follow(sameuser));

    }

}
