package com.ohgiraffers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// ② 컨트롤러 클래스
@Controller
@RequestMapping("/lecture/*") // lecture 하위 모두 매핑하겠다.
public class LectureController {

    @GetMapping("expression")
    public ModelAndView expression (ModelAndView mv){

        // 한 명의 정보를 DTO 클래스를 이용하여 mv에 Key 와 Value로 값 집어 넣기
        mv.addObject("member", new MemberDTO("김규남", 20, '남', "서울시"));
        // member로만 출력을 하면 ToString으로 출력되기 때문에 사용자 입장에서 당황할 수 있음.
        // 오브젝트(member)가 가지고 있는 필드에 접근해서 가져올 수 있다.

        mv.addObject("hello", "hi~<h2>타임리프</h2>");

        mv.setViewName("lecture/expression"); // view에 대한 경로 지정 (앞에 templates와 뒤에 html 생략)

        return mv;
    }

}
