package org.daistudy.springbootshiro.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.daistudy.springbootshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomRealm extends AuthorizingRealm {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取权限认证信息
     * @param principalCollection 用户身份信息
     * @return 权限认证信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.getRolesByUsername(username));
        authorizationInfo.setStringPermissions(userService.getPermissionsByUsername(username));
        return authorizationInfo;
    }

    /**
     * 获取身份认证信息
     * @param authenticationToken 用户身份信息
     * @return 身份认证信息
     * @throws AuthenticationException 认证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = userService.getPasswordByUsername(username);
        if(null == password){
            throw new AccountException("用户名错误");
        }else if(!password.equals(new String((char[]) authenticationToken.getCredentials()))){
            throw new AccountException("密码错误");
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
