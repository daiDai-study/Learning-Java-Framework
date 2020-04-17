package org.daistudy.simpleshiro;

import sun.net.util.IPAddressUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserService {
    Map<String, String> userMap;
    Map<String, Set<String>> roleMap;
    Map<String, Set<String>> permissionMap;

    {
        userMap = new HashMap<>();
        roleMap = new HashMap<>();
        permissionMap = new HashMap<>();
        userMap.put("zhangsan", "123456!a");
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        roleMap.put("zhangsan", roles);
        Set<String> permissions = new HashSet<>();
        permissions.add("user:add");
        permissionMap.put("zhangsan", permissions);
    }


    public String getPasswordByUsername(String username){
        return userMap.get(username);
    }

    public Set<String> getRolesByUsername(String username){
        return roleMap.get(username);
    }

    public Set<String> getPermissionsByUsername(String username){
        return permissionMap.get(username);
    }
}
