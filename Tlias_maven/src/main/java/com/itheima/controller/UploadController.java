package com.itheima.controller;



import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 上传图片
 */
@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOSSUtils aliOSSUtils;

//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传：{},{},{}",name,age,image);
//        String originalFilename = image.getOriginalFilename();
//        int index=originalFilename.lastIndexOf(".");
//        String extname=originalFilename.substring(index);
//        String newFilename= UUID.randomUUID().toString()+extname;
//        log.info("新的文件名：{}",newFilename);
//        image.transferTo(new File("/Users/tingfenghuang/Study/ITheima/temp/" + newFilename));
//
//
//        return Result.success();
//    }


    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("上传文件，文件名为：{}",image.getOriginalFilename());

//        通过工具类上传并且获取上传的地址
        String url= aliOSSUtils.upload(image);
        log.info("文件上传完成，文件的url为:{}",url);

        return Result.success(url);
    }


}
