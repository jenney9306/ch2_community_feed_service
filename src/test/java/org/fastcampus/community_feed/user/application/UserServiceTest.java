package org.fastcampus.community_feed.user.application;

import org.fastcampus.community_feed.user.application.interfaces.UserRepository;
import org.fastcampus.community_feed.user.interfaces.FakeUserRepository;
import org.junit.jupiter.api.Test;

class UserServiceTest {
    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);

}
