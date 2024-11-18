package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Service;

@Service
public class MenuService {

    /* comment. Interceptor가 Bean에 개입할 수 있다는 것을 보여주는 예시 클래스 */

    // Autowired를 통해 bean들과의 연관성을 만들지 않았는데,
    // Interceptor를 통해 강제 연관을 만들 예정 → HandleInterceptor
    public void method(){
        System.out.println("Service 계층의 메서드 호출됨...");
    }

}
