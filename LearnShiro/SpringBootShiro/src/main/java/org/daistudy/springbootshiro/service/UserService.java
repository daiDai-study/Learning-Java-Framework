package org.daistudy.springbootshiro.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class UserService {
    Map<String, String> userMap;
    Map<String, Set<String>> roleMap;
    Map<String, Set<String>> permissionMap;

    {
        userMap = new HashMap<>();
        roleMap = new HashMap<>();
        permissionMap = new HashMap<>();
        userMap.put("zhangsan", "123456!a");
        userMap.put("lisi", "123456!a");
        Set<String> rolesOfzhangsan = new HashSet<>();
        rolesOfzhangsan.add("admin");
        rolesOfzhangsan.add("user");
        roleMap.put("zhangsan", rolesOfzhangsan);
        Set<String> rolesOflisi = new HashSet<>();
        rolesOflisi.add("user");
        roleMap.put("lisi", rolesOflisi);
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
