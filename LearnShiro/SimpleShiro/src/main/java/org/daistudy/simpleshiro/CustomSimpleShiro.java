package org.daistudy.simpleshiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;

public class CustomSimpleShiro implements SimpleShiro {
    @Override
    public Realm getRealm() {
        return new MyRealm();
    }
}
