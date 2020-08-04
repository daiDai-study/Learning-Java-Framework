package org.daistudy.mybatis.myorm.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<Student> students = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://10.177.97.180:1433;DatabaseName=plp_private", "sqluser", "123456!a")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from test_stu")) {
                    while(resultSet.next()){
                        final int id = resultSet.getInt("id");
                        final String name = resultSet.getString("name");
                        final int age = resultSet.getInt("age");
                        students.add(new Student(id, name, age));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("students = " + students);
    }
}
