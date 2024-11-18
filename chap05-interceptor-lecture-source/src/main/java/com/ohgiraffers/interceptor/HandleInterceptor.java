package com.ohgiraffers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component // Interceptor 만드는 방법 → HandlerInterceptor 상속받기
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
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
