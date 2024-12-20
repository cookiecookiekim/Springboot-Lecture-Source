package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionContoller {

    @GetMapping("nullpointer")
    public String nullpointerException () {

        String str = null; // 일부러 에러 발생시키기
        // null 값에 참조하게되면 NullPointerException이 발생됨.
        System.out.println(str.charAt(0));
        return "/"; // 리턴 구문으로 메인페이지 보여주기
        // 버튼 클릭 시 nullPointerException 발생 , 에러 페이지 핸들링 해보기
        /* @ExceptionHandler(NullPointerException.class) 작성 이후,
          return구문 가기도 전에 @ExceptionHandler가 낚아 챔 */
    }

    /* comment. @ExceptionHandler 어노테이션
    *     처리하고 싶은 예외 클래스를 정의하면 정의한 클래스의
    *     예외 발생 시 nullHandler 메서드가 동작하게 됨*/
    // null포인트 에러 발생 시, 이 메서드가 낚아챔
    @ExceptionHandler(NullPointerException.class)
    public String nullHandler(NullPointerException exception){
        System.out.println("controller 레벨에서 NullPointer 예외 처리");
        return "error/nullPointer";
        // 전역 레벨에 정의한 메서드가 호출되지 않고, 현재 메서드 호출
        // 이유 : 가까운 곳(클래스 내)부터 훑기 때문. 만약 클래스 외부에 정의했을때엔
            // 전역인 메서드 호출
    }

    @GetMapping("userexception")  // 시그니처에 throws MemberNotFoundException 추가
    public String userException() throws MemberNotFoundException {
        boolean check = true;
        if (check) {
            throw new MemberNotFoundException("NotFoundException 에러 예외 처리!");
        } // MemberNotFoundException 클래스 생성.
        return "/";
    }

    @ExceptionHandler(MemberNotFoundException.class) // 직접 생성한 클래스 전달
    public String userException(Model model, MemberNotFoundException exception){
        model.addAttribute("exception", exception);
        return "error/memberNotFound"; // error 하위에 memberNotFound 생성
    }

}
