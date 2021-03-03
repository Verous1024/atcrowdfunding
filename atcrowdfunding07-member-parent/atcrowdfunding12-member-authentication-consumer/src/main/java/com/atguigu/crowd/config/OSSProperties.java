package com.atguigu.crowd.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-23 上午 09:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix="aliyun.oss")
public class OSSProperties {

    private String endPoint;

    private String bucketName;

    private String accessKeyId;

    private String accessKeySecert;

    private String bucketDomain;
}
