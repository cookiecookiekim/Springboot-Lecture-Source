package com.ohgiraffers.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 전역 예외처리 핸들러 생성

    /* comment.
    *   AOP 기능 중 Advice는 반복적으로 동작해야 할 코드들을
    *   뭉쳐둔 메서드를 의미하는 단어.
    *   ControllerAdvice 어노테이션은 마찬가지로 공통적으로
    *   발생하는 예외처리를 담당하는 어노테이션을 의미한다. */

@ControllerAdvice // 핸들링 (전역 처리 용)
public class GlobalExceptionHandler {

    /* comment. @ExceptionHandler의 우선 순위
    *   해당 클래스에 핸들러가 있으면 클래스 레벨이 우선순위를 갖음
    *   만약 해당 클래스에 핸들러가 없으면 이후 전역의 핸들러가 동작*/

    @ExceptionHandler(NullPointerException.class) // null 에러 처리 정의
    public String globalNull(NullPointerException exception){
        System.out.println("전역 레벨의 exception 처리");
        return "error/nullPointer";
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public String globalUser(Model model, MemberNotFoundException exception){

        model.addAttribute("exception", exception);

        return "error/memberNotFound";
    }

    // array out of bound 에러 처리
    // 부모 타입인 Exception 이용
    // 부모 타입을 이용하면 구체적인 예외 상황을 지정하지 않아도 처리 가능
    // → 기본적으로 동작할 예외 상황을 처리할 수 있다.
    @ExceptionHandler(Exception.class)
    public String defaultException(Exception exception){
        return "error/default";
    }

}
