package org.daistudy.mybatis.onjava.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.daistudy.mybatis.onjava.config.DataSourceFactory;
import org.daistudy.mybatis.onjava.entity.Student;
import org.daistudy.mybatis.onjava.mapper.StudentMapper;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

public class TestDemo {
    public static void main(String[] args) {
        DataSource dataSource = DataSourceFactory.getDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment developmentEnvironment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(developmentEnvironment);
        configuration.getTypeAliasRegistry().registerAliases("org.daistudy.mybatis.onjava.entity");
        // addMapper 放在 Configuration 设置的最后，防止错误
        configuration.addMapper(StudentMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
//            Student user = (Student)sqlSession.selectOne("org.daistudy.mybatis.onxml.mapper.StudentMapper.selectById", 1);
            final StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
            final Student user = studentMapper.selectById(1);
            System.out.println("user = " + user);
        }

        try(SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)){
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            for (int i = 1; i <= 4; i++) {
                mapper.updateAge(i);
            }
            sqlSession.commit();
        }
    }
}
