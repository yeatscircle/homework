package com.hit.homework.conmon;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

//交给ioc容器会更加方便,并且不使用static可以减少内存使用
@Component
@RequiredArgsConstructor
public class AliYun {
//    //根据自己的节点填写
//    @Value("${aliyun.occ.endpoint}")
//    private String endpoint;
//
//    //api的id和密钥
//    @Value("${aliyun.occ.accessKeyId}")
//    private String accessKeyId;
//    @Value("${aliyun.occ.accessKeySecret}")
//    private String accessKeySecret;
//
//    // 填写Bucket名称
//    @Value("${aliyun.occ.bucketName}")
//    private String bucketName;

    final private AliOSSProperties aliOSSProperties;
    /**
     * 实现上传文件到阿里云
     */
    public String upload(MultipartFile file) throws IOException {
        String endpoint = aliOSSProperties.getEndpoint();
        String accessKeyId = aliOSSProperties.getAccessKeyId();
        String accessKeySecret = aliOSSProperties.getAccessKeySecret();
        String bucketName = aliOSSProperties.getBucketName();

        //获取文件上传的输出流
        InputStream inputStream = file.getInputStream();

        //避免文件被覆盖
        String originalFilename = file.getOriginalFilename();
        String newName = UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传到oss
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, newName, inputStream);
        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "."
                + endpoint.split("//")[1] + "/" + newName;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }
}
