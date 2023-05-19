package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    //    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        Long count= empMapper.count();
//
//        Integer start=(page-1)*pageSize;
//        List<Emp> empList=empMapper.page(start,pageSize);
//        PageBean pageBean=new PageBean(count,empList);
//
//
//        return pageBean;
//    }

//    分页查询
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page, pageSize);//第一个参数是页数。第二个参数是条数，每页查询的条数。
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;

//        将数据进行分页
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());


        return pageBean;
    }


//    批量删除员工
    @Override
    public void delete(List<Integer> ids) {

        empMapper.delete(ids);
    }

//    新增员工
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());


        empMapper.insert(emp);
    }


//    根据id查询员工
    @Override
    public Emp getById(Integer id) {

        return empMapper.getById(id);
    }


//    更新员工
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

//    登录操作
    @Override
    public Emp login(Emp emp) {
        Emp e= empMapper.getByUsernameAndPassword(emp);
        return e;
    }
}
