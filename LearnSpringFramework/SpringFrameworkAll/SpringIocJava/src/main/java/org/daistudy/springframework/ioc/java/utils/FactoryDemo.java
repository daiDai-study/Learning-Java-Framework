package org.daistudy.springframework.ioc.java.utils;

import org.daistudy.springframework.ioc.java.model.FactoryDemoBean;
import org.springframework.beans.factory.FactoryBean;

public class FactoryDemo implements FactoryBean<FactoryDemoBean> {
    @Override
    public FactoryDemoBean getObject() {
        return new FactoryDemoBean();
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryDemoBean.class;
    }
}
