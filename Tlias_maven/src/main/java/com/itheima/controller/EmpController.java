package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


/**
 * 员工管理
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

//    分页查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1")Integer page,//开始的位置
                       @RequestParam(defaultValue = "10")Integer pageSize,//一页的数据条数
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,//开始时间
                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){//结束时间
        log.info("分页查询，参数:{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageBean pageBean= empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }


//    根据id可批量删除员工
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除员工：{}",ids);
        empService.delete(ids);
        return Result.success();
    }


//    新增员工
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}",emp);
        empService.save(emp);
        return Result.success();
    }


//    根据员工的id查询员工的所有信息
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询员工信息，id为{}",id);
        Emp emp= empService.getById(id);
        return Result.success(emp);
    }

//    修改员工信息
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }

}
