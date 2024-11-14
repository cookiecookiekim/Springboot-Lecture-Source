package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                                // MainController 의 "/" 동작 → main
            // f12 로 확인 시 string-redirect / localhost 찍힘 (갔다가 다시 옴)
    }

    @GetMapping("string-redirect-attr")
    public String redirectAttr(RedirectAttributes attr){
        /* comment.
        *   redirect는 최초 요청 받게되면 응답 진행 후 다시 재요청하는 방식
        *   따라서 request가 2번 일어나기 때문에 값을 유지할 방법이 없다.
        *   그러므로 Servlet에서 Session, Cookie라는 개념을 학습했음.
        *   Session과 Cookie는 별도의 공간을 할당하기 때문에
        *   관리할 데이터가 많아지면 서버의 부담이 생길 수 있다.
        *   스프링에서는 RedirectAttributes라는 타입을 통해
        *   redirect 시 값을 저장할 수 있는 기능을 제공해 준다. */
        attr.addFlashAttribute("flashMessage"
                        , "리다이렉트시 값 유지!");
        // Flash 해석 : 반짝.. 잠깐 넣었다 뺀다는 의미
        /* comment. RedirectAttributes는 Session의 기능을 사용하지만,
        *       Flash → 잠깐 값이 담겼다가 소멸되는 방식을 사용.
        *       Session처럼 소멸될 때까지 공간을 차지하는 것이 아닌
        *       잠시 데이터를 썼다가 사라지는 구조이기 때문에 서버의 부담이 없다. */
        // 반환하면 데이터 없어짐. 무궁무진하게 사용 가능.

        return "redirect:/";
    }

    /* comment. Model과 View를 합친 개념이다.
    *   값을 집어 넣을수도 있고, 화면을 결정 지을수도 있다. */
    @GetMapping("modelandview")
    public ModelAndView modelAndViewReturn (ModelAndView mv) {
        // Model 객체처럼 화면에 쓰일 데이터를 넣을 수 있다.
        mv.addObject("forwardMessage",
            "ModelAndView를 이용하여 반환");

        // 문자열로 이동하고 싶은 view 페이지 지정
        mv.setViewName("result");
        return mv;
    }

    @GetMapping("modelandview-redirect-attr")
    public ModelAndView moRedirectAttr (ModelAndView mv, RedirectAttributes attr){
        attr.addFlashAttribute("flashMessage2",
                        "ModelAndView 리다이렉트 시 값 유지!");

        mv.setViewName("redirect:/");
        return mv;
    }
}
