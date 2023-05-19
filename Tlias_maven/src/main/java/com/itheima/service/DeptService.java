package com.itheima.service;


import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> list();


    /**
     * 删除部门
     * @param id
     */
    void delete(Integer id);

    void add(Dept dept);

    Dept select(Integer id);

    void update(Dept dept);
}
