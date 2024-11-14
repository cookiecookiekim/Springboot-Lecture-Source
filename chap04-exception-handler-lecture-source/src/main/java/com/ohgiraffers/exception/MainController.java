package com.ohgiraffers.exception;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // ② main.html을 다룰 컨트롤러 클래스 생성
public class MainController {

    @GetMapping("/") // / 기본 페이지 요청 들어 왔을때 메인페이지 렌더링
    public String main(){
        return "main";
    }
}
