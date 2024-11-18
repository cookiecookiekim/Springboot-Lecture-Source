package com.ohgiraffers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// Interceptor는 Controller 앞단에서 실행
@Component // Interceptor 만드는 방법 → HandlerInterceptor 상속받기
/* @Component 탈아 놨어도, 이 클래스가 Interceptor라고 명시를 해줘야 한다
 → WebConfig 클래스 */
public class HandleInterceptor implements HandlerInterceptor {

    private final MenuService menuService; // 의존성 주입하기

    /* comment.
    *   Interceptor는 Spring에서 제공하는 기능이기 때문에
    *   우리가 Bean으로 등록해둔 객체들을 언제든
    *   참조(의존성 주입) 할 수 있다. */

    @Autowired
    public HandleInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }

    // ctrl + o 로 오버라이드 받기
    /* 전처리 메서드 (이전에 동작한다) */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandler 호출됨...");

        // 현재 시간을 startTime에 담기
        long startTime = System.currentTimeMillis();

        request.setAttribute("startTime", startTime);

        /* comment.
        *   true 값을 리턴하게 되면 Interceptor의
        *   다음 계층인 Controller를 호출하게 된다.
        *   false 값을 리턴하게 되면 컨트롤러의
        *   핸들러 메서드 호출하지 않음. */
        return true; // → Controller 동작
    }

    /* 후처리 메서드 (이후에 동작한다) */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /* Controller 동작 이후, 화면으로 쏴주기 전에
            이 메서드가 한 번 더 낚아채서 동작! */
        System.out.println("postHandler 호출됨...");

        // setAttribute에 담아놨떤 key값을 다시 get으로 꺼내기
        long startTime = (long) request.getAttribute("startTime");

        long endTime = System.currentTimeMillis();

        // modelAndView에 값 담아주기
        modelAndView.addObject("interval", endTime - startTime);
    }

    /* 가장 마지막 view가 랜더링 된 이후 동작하는 메서드*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion 호출됨...");

        menuService.method(); // menuService 빈에 접근 가능한지 확인
    }
}
