package org.daistudy.springframework.ioc.java.bean;

import javafx.application.Application;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class UserAware implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String sayHello(){
        final boolean hasUserDao = this.applicationContext.containsBean("userDao");
        if (hasUserDao) {
            final UserDao userDao = this.applicationContext.getBean("userDao", UserDao.class);
            return userDao.hello();
        }
        return "bean 'userDao' is not existed.";
    }
}
