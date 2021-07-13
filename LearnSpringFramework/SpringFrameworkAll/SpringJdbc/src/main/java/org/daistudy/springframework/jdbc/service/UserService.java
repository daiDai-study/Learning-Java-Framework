package org.daistudy.springframework.jdbc.service;

import org.daistudy.springframework.jdbc.dao.UserDao;
import org.daistudy.springframework.jdbc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ExceptionService exceptionService;

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    @Transactional
    public int update(String username, String address, int userId){
        int count = userDao.updateUserName(username, userId);
        int exception = 1/0;
        count += userDao.updateAddress(address, userId);
        return count;
    }

    @Transactional
    public int insert(User user){
        int count = userDao.insert(user);
        exceptionService.exception();
//        try {
//            exceptionService.exception();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        int exception = 1/0;
        return count;
    }
}
