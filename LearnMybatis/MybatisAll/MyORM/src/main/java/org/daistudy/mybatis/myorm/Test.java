package org.daistudy.mybatis.myorm;

import org.daistudy.mybatis.myorm.annotation.Select;
import org.daistudy.mybatis.myorm.jdbc.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

interface StudentMapper{
    @Select("select * from test_stu where id = #{id}")
    Student selectById(int id);
}

public class Test {
    public static void main(String[] args) {
        StudentMapper studentMapper = (StudentMapper)Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{StudentMapper.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Map<String, Object> mapMethodParameter = mapMethodParameter(method, args);
                System.out.println("mapMethodParameter = " + mapMethodParameter);
                return null;
            }
        });
        Student student = studentMapper.selectById(1);
        System.out.println("student = " + student);
    }

    private static Map<String, Object> mapMethodParameter(Method method, Object[] args){
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < method.getParameters().length; i++) {
            Parameter parameter = method.getParameters()[i];
            map.put(parameter.getName(), args[i]);
        }
        return map;
    }
}
