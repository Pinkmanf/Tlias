package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理
 * controller主要是与前端交互，接收响应数据
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

//    依赖注入
    @Autowired
    private DeptService deptService;

//    private static Logger log= LoggerFactory.getLogger(DeptController.class);
//    @RequestMapping("/depts")


//    查询部门数据
    @GetMapping
    public Result list(){
        List<Dept> deptList=deptService.list();
        log.info("查询全部部门数据");
        return Result.success(deptList);
    }


//    删除部门数据
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){

        log.info("根据id删除部门:{}",id);
        deptService.delete(id);
        return Result.success();
    }

//    新增部门
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){

        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

//查询根据id查询部门数据
    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id){
        log.info("查询部门：{}",id);
        Dept dept=deptService.select(id);
        return Result.success(dept);
    }

//    修改部门数据
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门");
        deptService.update(dept);
        return Result.success();
    }
}
