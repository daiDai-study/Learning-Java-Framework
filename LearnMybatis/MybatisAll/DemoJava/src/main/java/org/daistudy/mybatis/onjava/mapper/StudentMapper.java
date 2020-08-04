package org.daistudy.mybatis.onjava.mapper;

import org.apache.ibatis.annotations.Select;
import org.daistudy.mybatis.onjava.entity.Student;

public interface StudentMapper {
    @Select("select * from test_stu where id = #{id}")
    Student selectById(Integer id);
}
