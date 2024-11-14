package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// ③ 리턴 전, 처리할 메서드 클래스
@Controller
public class ResolverController {

    /* comment.
    *   Handler method에서 마지막으로 해야할 일은 client에게
    *   응답해야 할 페이지를 리턴하는 것이다.
    *   스프링에서는 다양한 전략에 맞게 view를 해석할 수 있는데
    *   ViewResolver 구현체가 존재한다.
    *   Thymleaf 의존성을 추가하면 viewResolver는 Thymleaf 문법을
    *   해석할 수 있는 ThymleafViewResolver로 전환되며
    *   이는 prefix로 resource/templates/suffix로
    *   .html을 자동으로 붙여주게 된다. */

    @GetMapping("string") // "string" 요청 처리 하기 위한 메서드
    public String stringReturn(Model model){

        model.addAttribute("forwardMessage"
                , "문자열로 view 반환");  // key, value 설정
        return "result";
        // comment에서 확인했듯, 풀경로가 아닌, "result"로만 작성해도 된다.
    }

    @GetMapping("string-redirect")
    public String stringRedirect(Model model){
        /* comment.
        *   지금까지 view를 반환하는 방식은 default로 forward 방식.
        *   redirect 방식은 접두사로 redirect:를 붙이면 된다. */

        return "redirect:/"; // : 는 redirect , 이후 /는 메인 페이지로 이동하기
            // f12 로 확인 시 string-redirect / localhost 찍힘 (갔다가 다시 옴)
    }
}
