package org.daistudy.simpleshiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Shiro简单示例")
class SimpleShiroTest {

    @DisplayName("SimpleAccountRealm")
    @Test
    void testDefaultSimpleShiro(){
        new Thread(() -> {
            Subject subject = new DefaultSimpleShiro().getSubject();
            testAuthentication(subject);
            testAuthorization(subject, false);
        }).start();
    }

    @DisplayName("IniRealm")
    @Test
    void testIniSimpleShiro(){
        new Thread(() -> {
            Subject subject = new IniSimpleShiro().getSubject();
            testAuthentication(subject);
            testAuthorization(subject, true);
        }).start();
    }

    @DisplayName("CustomRealm")
    @Test
    void testCustomSimpleShiro(){
        new Thread(() -> {
            Subject subject = new CustomSimpleShiro().getSubject();
            testAuthentication(subject);
            testAuthorization(subject, true);
        }).start();
    }

    @DisplayName("Subject只有一个")
    @Test
    void testSubject(){
        // 原因在于 SecurityUtils 中同一个线程中只能有一个 SecurityManager 和一个 Subject
        Subject subject1 = new DefaultSimpleShiro().getSubject();
        Subject subject2 = new IniSimpleShiro().getSubject();
        Subject subject3 = new CustomSimpleShiro().getSubject();
        assertEquals(subject1, subject2);
        assertEquals(subject1, subject3);
    }

    /**
     * 身份认证
     * @param subject 主体
     */
    void testAuthentication(Subject subject){

        assertThrows(AuthenticationException.class, ()->subject.login(new UsernamePasswordToken("zhangsan1", "123456!a")));
        assertThrows(AuthenticationException.class, ()->subject.login(new UsernamePasswordToken("zhangsan", "123456!")));

        subject.login(new UsernamePasswordToken("zhangsan", "123456!a"));
        assertTrue(subject.isAuthenticated());

        subject.logout();
        assertFalse(subject.isAuthenticated());
    }

    /**
     * 授权
     * @param subject 主体
     */
    void testAuthorization(Subject subject, boolean isPermitted){
        subject.login(new UsernamePasswordToken("zhangsan", "123456!a"));
        assertTrue(subject.isAuthenticated());

        // 角色鉴权
        assertTrue(subject.hasRole("admin"));
        assertFalse(subject.hasRole("user"));

        // 权限鉴权
        if(isPermitted){
            assertTrue(subject.isPermitted("user:add"));
            assertFalse(subject.isPermitted("user:edit"));
        }
    }
}