package org.fastcampus.community_feed.user.application;

import org.fastcampus.community_feed.user.application.dto.CreateUserRequestDto;
import org.fastcampus.community_feed.user.application.interfaces.UserRepository;
import org.fastcampus.community_feed.user.domain.User;
import org.fastcampus.community_feed.user.interfaces.FakeUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser(){
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test","");

        //when
        User savedUser = userService.createUser(dto);

        //then
        User findUser = userService.getUser(savedUser.getId());
        assertEquals(savedUser.getId(), findUser.getId());
        assertEquals(dto.name(), findUser.getUserInfo().getName());
    }


}
