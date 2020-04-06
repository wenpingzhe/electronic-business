package com.agri;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(value = "com.agri.study.wen.dao")
public class ApplicationStudy {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStudy.class);
    }
}
