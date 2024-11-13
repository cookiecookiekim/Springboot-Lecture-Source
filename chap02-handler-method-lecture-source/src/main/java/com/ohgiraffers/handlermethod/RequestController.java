package com.ohgiraffers.handlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

// 24-11-13 (수) 1교시 요청을 처리할 컨트롤러 클래스
@Controller
@RequestMapping("/request/*") // /request 이하 모든 요청 매핑
public class RequestController {

    /* title. 요청 시 값을 전달받는 방법 */

    // view 페이지를 반환하는 다른 방법 (이전에는 String 문자열로 페이지 네임 리턴)
    /* comment. view 페이지를 보여주는 방식
     *   1. String 타입 반환값에 view 파일 이름 작성 (chap01)
     *   2. 메서드의 반환 타입을 void로 설정
     *   → 반환 타입을 void로 하게 되면 요청 url이 view의 이름이 된다. */

    @GetMapping("regist") // view 파일 생성 → templates에 regist.html 구성
    // 버튼 클릭 시 /request/* 하위의 "regist" 동작
    public void regist() {
    }

    /* comment.
     *   1. WebRequest 객체로 요청 파라미터 전달받기
     *   매개변수 선언부에 WebRequest 객체를 선언하면
     *   해당하는 핸들러 메서드 호출 시 인자로 값을 전달해준다.
     *   (예 : 서블릿 doPost 메서드 내부에 HttpServletRequest)
     *   ------------------------------------------------
     *   스프링 프레임워크는 내부적으로 Servlet을 사용하고 있기 때문에
     *   HttpServletRequest도 사용 가능
     *   사용하지 않는 이유는 WebRequest 객체는 Servlet에 종속적이지 않으나
     *   기능은 모두 포함하고 있기 때문에 추후에 Servlet 기능이 사라졌을 시
     *   적은 수정 범위로 교체할 수 있다는 장점이 있음 (고급 스킬)
     *   -
     *   → WebRequest 객체는 Spring 측에서 제공하기 때문에
     *   Spring 친화적으로 많이 사용하는 편이다. */
    @PostMapping("regist")
    public String registMenu(Model model, WebRequest request) { // String 타입으로 해보기

        // 입력한 값 꺼내기
        String menuName = request.getParameter("name");
        int menuPrice = Integer.parseInt(request.getParameter("price"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

        String message = menuName + "을(를) 신규 메뉴 목록" +
                categoryCode + " 번 카테고리에 " + menuPrice +
                "원으로 등록 했습니다.";              // 번역 과정

        model.addAttribute("message", message); // 모델 객체에 키,밸류 설정

        return "request/printResult"; // view → Controller → view 리턴
        // printResult.html 파일 생성 (view)
    }

    @GetMapping("modify")
    public void modify() { // 이 메서드를 처리할 modify.html 파일 생성
    }

    /* comment. @RequestParam
    *   화면에서 요청하는 값을 담아주는 어노테이션
    *   담을 매개변수 앞에 작성하게 되며 form의 name속성과 매개변수 명 일치시켜야 함.
    *   일치하기 싫다면 '@RequestParam("폼name속성") String 사용하고 싶은 변수명'
    *   ------------------------------------
    *   name 속성이 일치하지 않을 때 400-bad request 에러 발생
    *   → required 속성의 기본 값이 true이기 때문 (값이 없다면 에러)
    *   이 때 required 속성 값을 false로 바꿔주면 해당 name 속성이 일치하지
    *   않더라도 error 발생시키지 않고, null로 처리 하게됨. */
    @PostMapping("modify") // modify.html 의 요청을 처리할 Post 메서드 생성
    //WebRequest 객체로 전달하면, 꺼내기가 귀찮음 (입력값이 10개라고 가정)
    // → RequestParam으로 전달하면 간단
    // 사용 위한 규칙 → name 속성의 값과 매개변수 명을 일치시켜야 한다
    public String modifyMenu(Model model,
                             @RequestParam String modifyName,
                             @RequestParam int modifyPrice) {
        String message = modifyName + "의 가격을 " + modifyPrice + " 로 수정";

        model.addAttribute("message", message);
        return "request/printResult"; // 경로 지정
    }
}
