package com.ohgiraffers.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 24-11-12 (화) 스프링부트 1교시,
// 컴포넌트 스캔 경로 지정안 하면 해당 파일의 페키지만 스캔.

@SpringBootApplication
public class Chap00BootStarterLectureSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chap00BootStarterLectureSourceApplication.class, args); // 스캔한 정보를 전달
		// 컨테이너 생성 구문

	}

}
