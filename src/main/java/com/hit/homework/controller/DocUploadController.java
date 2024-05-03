package com.hit.homework.controller;

import com.hit.homework.conmon.AliYun;
import com.hit.homework.conmon.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "阿里云文件上传")
@Slf4j
@RequiredArgsConstructor
@RestController
public class DocUploadController {

    //本地上传
//    @RequestMapping(method = RequestMethod.POST)
//    public Result upload(String username, String age, @RequestParam("image")MultipartFile multipartFile ) throws IOException {
//        log.info("文件上传:{},{},{}",username,age,multipartFile);
//
//        //直接获取原有文件的名称
//        String originalFilename = multipartFile.getOriginalFilename();
//
//        //获取最后一个.的索引
//        int i = originalFilename.lastIndexOf(".");
//        String suffix = originalFilename.substring(i);
//        String finalName = UUID.randomUUID()+suffix;
//        log.info("文件名为:{}",finalName);
//
//        multipartFile.transferTo(new File("E://images//"+finalName));
//        return Result.success();
//    }

    //阿里云上传
    final private AliYun aliYun;

    @PostMapping("/upload")
    public Result uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        log.info("输入的文件为:{}",file.getOriginalFilename());

        //交给oss并且返回文件阿里云地址
        String url = aliYun.upload(file);
        log.info("文件上传成功:{}",url);

        return Result.success(url);
    }
}
