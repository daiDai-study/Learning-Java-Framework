package org.daistudy.spring.boot.datasource.dynamic.routing.mapper;

import org.daistudy.spring.boot.datasource.dynamic.routing.entity.TestUser;

import java.util.List;

public interface TestUserMapper {
    List<TestUser> list();
}
