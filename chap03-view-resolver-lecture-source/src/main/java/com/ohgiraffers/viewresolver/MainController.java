package com.ohgiraffers.viewresolver;

// ② main.html을 보여줄 메인 컨트롤러 생성
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // / 요청에 대한 처리를 직접 처리.
    // 다시 run 시 에러 발생하지 않고 main.html 페이지 보여짐
    @GetMapping("/")
    public String main(){
        return "main";
    }
}
