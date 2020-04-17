package org.daistudy.springbootshiro.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void getPasswordByUsername() {
        assertEquals("123456!a", userService.getPasswordByUsername("zhangsan"));
        assertEquals("123456!a", userService.getPasswordByUsername("lisi"));
        assertNull(userService.getPasswordByUsername("wangwu"));
    }

    @Test
    void getRolesByUsername() {
        assertArrayEquals(new String[]{"admin", "user"}, userService.getRolesByUsername("zhangsan").toArray());
        assertArrayEquals(new String[]{"user"}, userService.getRolesByUsername("lisi").toArray());
        assertNull(userService.getRolesByUsername("wangwu"));
    }

    @Test
    void getPermissionsByUsername() {
        assertArrayEquals(new String[]{"user:add"}, userService.getPermissionsByUsername("zhangsan").toArray());
        assertNull(userService.getPermissionsByUsername("lisi"));
        assertNull(userService.getPermissionsByUsername("wangwu"));
    }
}