package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {


    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

//    列出所有部门
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

//要么两步操作同时成功要么同时失败
    @Transactional(rollbackFor =Exception.class)//    事务回滚所有异常
    @Override
    public void delete(Integer id){

//        删除部门和部门id对应的员工
        deptMapper.deleteByID(id);

        empMapper.deleteByDepId(id);
    }

//    添加部门信息
    @Override
    public void add(Dept dept){
//        设置更新时间和创建时间
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

//    根据id查询部门信息
    @Override
    public Dept select(Integer id){
        return deptMapper.select(id);
    }

//    更新部门信息
    @Override
    public void update(Dept dept){
        deptMapper.update(dept);
    }
}
