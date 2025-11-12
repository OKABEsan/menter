package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * メンターアプリケーションのメインクラス
 */
@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class Menter01Application {
//メンターアプリケーションのメインメソッド
	public static void main(String[] args) {
		SpringApplication.run(Menter01Application.class, args);
		
	}

}
