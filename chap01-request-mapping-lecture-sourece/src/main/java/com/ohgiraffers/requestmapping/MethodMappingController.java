package com.ohgiraffers.requestmapping;

// 24-11-12 (화) 2교시, index 파일 컨트롤할 컨트롤러 클래스

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* comment.
*   Spring Boot는 Servlet이 내장되어 있음.
*   지금까지는 요청을 처리할 서블릿이 동작했다면
*   이제 Servlet은 요청을 받는 즉시 @Controller 어노테이션이
*   달린 클래스에 처리를 위임한다. */

@Controller // App가 인식할 수 있도록 어노테이션 설정
// 조금 더 구체적인 Controller 생성
public class MethodMappingController {

    /* 1. 메서드 방식 미지정 */
    // 사용자의 요청을 매칭시킬 메서드 : 버튼을 누른다 → request
    @RequestMapping ("/menu/regist") // button의 onclick 속성
    /* RequestMapping는 URL만 일치하면 모든 방식의 요청을 처리하는,
       Servlet으로 치면 service 메서드 */
    public String registMenu(Model model){ // 스프링 프레임워크 Model 임포트

        /* comment. Model 객체
        *   - 값을 담을 수 있는 공간.
        *   - key와 value 형식으로 값을 담으며 추후 응답 시
        *     view에서 사용할 수 있게 됨.
        *     → 추후에 더 다룰 예정!
        *   addAttribute라는 메서드를 사용하여 값을 담을 수 있음. */
        model.addAttribute("message", "메뉴 등록용 핸들러 메서드 동작 확인..");
        // key와 value 설정

        // 이제 사용자에게 보여줄 return 구문 작성
        /* comment.
        *   Controller 계층의 마지막 임무는 사용자에게 어떻게 응답할건지?
        *   메서드의 반환타입을 String으로 문자열 전달 시,
        *   resources/templates 하위 경로의 파일을 탐색하게 됨. */
        return "mappingResult.html"; // mappingresult라는 문자열을 반환
        // templates의 mappingResult 파일 탐색하여 반환

    }

    /* 2. 메서드 방식 지정 */
    // 요청 URL을 value 속성, 요청 방식을 method 속성에 지정
    // Get방식의 요청만 받게 지정했기 때문에 URL이 같더라도 Post방식의 요청은 받을 수 없음.
    @RequestMapping(value = "/menu/modify", method = RequestMethod.GET)
    // 메서드 방식을 GET으로 지정하겠다.
    public String modifyMenu(Model model) {
        model.addAttribute("message", "GET 방식만 허용하는 메뉴 수정 핸들러 메서드 호출됨..");
        return "mappingResult.html";
    }

    /* comment. @RequestMapping 어노테이션만 쓰게 되면
    *   항상 메서드 방식을 지정해두고 value도 작성해야 하는 번거로움이 있다.
    *   따라서 요청 메서드 전용 어노테이션을 제공하여 번거로운
    *   작업을 안 하게 도와준다.
    *   <요청 메서드>         <어노테이션>
    *     POST               @POSTMapping
    *     GET                @GetMapping
    *     PUT                @PutMapping
    *     DELETE             @DeleteMapping
    *   → 특성화된 어노테이션 제공하고 있음. */

    @GetMapping("/menu/delete")
    public String getDeleteMenu(Model model){
        model.addAttribute("message"
                ,"GET 방식의 메뉴 삭제용 핸들러 메서드 호출됨...");
        return "mappingResult";
    }

    @PostMapping("/menu/delete")
    public String postDeleteMenu(Model model){
        model.addAttribute("message"
                ,"POST 방식의 메뉴 삭제용 핸들러 메서드 호출됨...");
        return "mappingResult";
    }

}

