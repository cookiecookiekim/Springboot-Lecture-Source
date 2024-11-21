package com.ohgiraffers.crud.config;

// BeanFactrory == IOC 컨테이너 == ApplicationContext 모두 같은 말

// 24-11-21 (목) 1교시 , ② Bean들을 정의할 클래스 생성

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Application과 ContextConfig 클래스가 같은 경로에 있기 때문에
// ContextCongig 클래스는 자동으로 Bean 등록

@Configuration // Bean 등록을 위한 Configuration
@ComponentScan(basePackages = "com.ohgiraffers.crud") // crud 까지 읽을 수 있게 작성

// ⑩ import → ibatis Mapper (스프링 제공이 아니라 mybatis 이므로 MapperScan으로 다시 spring에 알려줘야한다)
@MapperScan(basePackages = "com.ohgiraffers.crud", annotationClass = Mapper.class)
public class ContextConfig {



}
