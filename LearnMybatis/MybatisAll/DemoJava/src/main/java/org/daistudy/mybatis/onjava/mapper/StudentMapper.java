package org.daistudy.mybatis.onjava.mapper;

import org.apache.ibatis.annotations.Select;
import org.daistudy.mybatis.onjava.entity.Student;

public interface StudentMapper {
    @Select("select * from test_stu where id = #{id}")
    Student selectById(Integer id);

    @Select("update test_stu set age = 12 where id = #{id}")
    Student updateAge(Integer id);
}
