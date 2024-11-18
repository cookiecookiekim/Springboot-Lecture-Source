package com.ohgiraffers.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /* comment. <HandleInterceptor 클래스가 Interceptor라고 알려주기>
    *   WebMvcConfigurer를 통해서 만든 인터셉터를 등록하는 과정이 필요. */
    @Autowired
    private HandleInterceptor handleInterceptor; // 필드 주입으로 해보기

    // 인터셉터를 (Registry = 저장소)에 등록하는 역할
    @Override // addInterceptors만 오버라이딩
    public void addInterceptors(InterceptorRegistry registry) {

        // 인터셉터가 어떤 요청에 동작할 것인지 정의
      registry.addInterceptor(handleInterceptor)
              // /* : 어떠한 요청에도 동작하게 설정
              .addPathPatterns("/*")
             /* static(정적인 요소) 파일을 불러오는 것도 하나의 요청.
              인터셉터가 단순 화면 꾸미기 위한 요청에도 동작하는 것은
              비효율적이기 때문에 제외할 경로 지정하는 것이 중요 */
              .excludePathPatterns("/css/*")
              .excludePathPatterns("/asset/*")
              .excludePathPatterns("/js/*")
              .excludePathPatterns("/images/*");
              // 제외할 것 추가.
    }
}
