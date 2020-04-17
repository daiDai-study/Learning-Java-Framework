package org.daistudy.simpleshiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;

public class DefaultSimpleShiro implements SimpleShiro {
    @Override
    public Realm getRealm() {
        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
        simpleAccountRealm.addAccount("zhangsan", "123456!a", "admin");
        return simpleAccountRealm;
    }
}
