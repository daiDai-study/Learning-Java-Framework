package org.daistudy.spring.boot.datasource.dynamic.routing.service.impl;

import org.daistudy.spring.boot.datasource.dynamic.routing.annotation.TargetDataSource;
import org.daistudy.spring.boot.datasource.dynamic.routing.entity.TestUser;
import org.daistudy.spring.boot.datasource.dynamic.routing.mapper.TestUserMapper;
import org.daistudy.spring.boot.datasource.dynamic.routing.service.TestUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestUserServiceImpl implements TestUserService {

    @Resource
    private TestUserMapper testUserMapper;

    @Override
    public List<TestUser> list() {
        return testUserMapper.list();
    }

    @Override
    @TargetDataSource("#db")
    public List<TestUser> list(String db) {
        return testUserMapper.list();
    }
}
