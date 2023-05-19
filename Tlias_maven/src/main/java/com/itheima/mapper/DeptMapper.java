package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询全部部门数据
     * @return
     */

    @Select("select id, name, create_time, update_time from dept")
    List<Dept> list();

    /**
     * 根据id删除部门
     * @param id
     */
    @Delete("delete from dept where id=#{id}")
    void deleteByID(Integer id);

//    插入部门信息
    @Insert("insert into dept(name, create_time, update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

//    根据id查询
    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept select(Integer id);


//    更新部门
    @Update("update dept set name=#{name} where id=#{id}")
    void update(Dept dept);


}
