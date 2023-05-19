package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页对象实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean {
    private Long total;//数据总条数
    private List rows;//数据内容
}
