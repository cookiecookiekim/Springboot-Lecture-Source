package com.ohgiraffers.crud.config;

// BeanFactrory == IOC 컨테이너 == ApplicationContext 모두 같은 말

// 24-11-21 (목) 1교시 , ② Bean들을 정의할 클래스 생성

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

// Application과 ContextConfig 클래스가 같은 경로에 있기 때문에
// ContextCongig 클래스는 자동으로 Bean 등록

@Configuration // Bean 등록을 위한 Configuration
@ComponentScan(basePackages = "com.ohgiraffers.crud") // crud 까지 읽을 수 있게 작성

// ⑩ import → ibatis Mapper (스프링 제공이 아니라 mybatis 이므로 MapperScan으로 다시 spring에 알려줘야한다)
@MapperScan(basePackages = "com.ohgiraffers.crud", annotationClass = Mapper.class)
public class ContextConfig {

    // 5교시
    @Bean // java 방식으로 Bean 생성
    public ReloadableResourceBundleMessageSource messageSource (){
        /* comment.
        *   message.properties 파일을 자바 객체 형식으로 읽어 들일 수 있게 만듦. */
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        // classpath: → src/main/resource, src/main/java를 의미한다.
        source.setBasename("classpath:/messages/message");
        source.setDefaultEncoding("UTF-8");
        /* 제공하지 않는 언어로 요청 시에 사용할 메세지 설정 */
        Locale.setDefault(Locale.KOREA);
        return source;
    }

}
