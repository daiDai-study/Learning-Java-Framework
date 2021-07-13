package org.daistudy.spring.boot.datasource.dynamic.routing.service;

import org.daistudy.spring.boot.datasource.dynamic.routing.entity.TestUser;

import java.util.List;

public interface TestUserService {

    List<TestUser> list();

    List<TestUser> list(String db);
}
