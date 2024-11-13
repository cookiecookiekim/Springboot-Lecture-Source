package com.ohgiraffers.handlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    *   Spring 친화적으로 많이 사용하는 편이다 */
    @PostMapping("regist")
    public String registMenu(Model mosel , WebRequest webRequest){ // String 타입으로 해보기

//        return
    }
}
