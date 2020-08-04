package org.daistudy.mybatis.onxml.demo;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.daistudy.mybatis.onxml.entity.Student;

import java.io.IOException;
import java.io.InputStream;

public class TestDemo {
    public static void main(String[] args) throws IOException {
        final InputStream mybatisConfig = Resources.getResourceAsStream("mybatis-config.xml");
        final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(mybatisConfig);
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            // 短名称如果全局唯一也可以作为一个单独的引用
            Student user = (Student)sqlSession.selectOne("selectById", 1);
            // 全限定名
//            Student user = (Student)sqlSession.selectOne("org.daistudy.mybatis.onxml.mapper.StudentMapper.selectById", 1);
//            final StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
//            final Student user = studentMapper.selectById(1);
            System.out.println("user = " + user);
        }
    }
}
