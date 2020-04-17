package org.daistudy.simpleshiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;

public interface SimpleShiro {
    default Subject getSubject(){
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(getRealm());
        SecurityUtils.setSecurityManager(securityManager);
        return SecurityUtils.getSubject();
    }

    Realm getRealm();
}
