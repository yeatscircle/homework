package com.hit.homework.conmon;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.occ")
public class AliOSSProperties {
    //根据自己的节点填写
    @Value("${aliyun.occ.endpoint}")
    private String endpoint;

    //api的id和密钥
    @Value("${aliyun.occ.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.occ.accessKeySecret}")
    private String accessKeySecret;

    // 填写Bucket名称
    @Value("${aliyun.occ.bucketName}")
    private String bucketName;
}
