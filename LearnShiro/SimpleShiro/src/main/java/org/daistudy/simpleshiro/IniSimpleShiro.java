package org.daistudy.simpleshiro;

import com.sun.org.omg.CORBA.InitializerSeqHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

import java.nio.channels.SeekableByteChannel;

public class IniSimpleShiro implements SimpleShiro {
    @Override
    public Realm getRealm() {
        return new IniRealm("classpath:shiro.ini");
    }
}
