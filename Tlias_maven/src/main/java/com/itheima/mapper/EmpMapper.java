package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select count(*) from emp")
//    public Long count();
//
//    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time" +
//            " from emp limit #{start},#{pageSize}")
//    public List<Emp> page(Integer start ,Integer pageSize);


//    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time" +
//            " from emp")

//    根据姓名性别等查询
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

//    根据多个id继续删除
    void delete(List<Integer> ids);

//    新增员工
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            "value(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


//    根据id查询
    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time" +
            " from emp where id=#{id}")
    Emp getById(Integer id);


//    根据选择更新的字段进行更新
    void update(Emp emp);


//    查询是否存在这样的用户名密码用于验证用户名密码是否正确
    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getByUsernameAndPassword(Emp emp);


//    根据部门id删除员工
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDepId(Integer deptId);
}
