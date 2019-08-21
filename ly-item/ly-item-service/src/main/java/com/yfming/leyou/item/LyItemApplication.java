package com.yfming.leyou.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Date 2019/8/20 16:26
 * @Created by minfy
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.yfming.leyou.*.mapper") // 扫描mapper包
public class LyItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyItemApplication.class, args);
    }
}
