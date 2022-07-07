package com.vrms.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**这是一个springboot应用程序*/
@MapperScan("com.vrms.app.dao")
@SpringBootApplication
public class VrmsApp {
    //boot项目已经内置了tomcat服务器，所以不需要配置tomcat
    public static void main(String[] args) {
        //运行boot工程启动tomcat服务器
        SpringApplication.run(VrmsApp.class);
    }
}
