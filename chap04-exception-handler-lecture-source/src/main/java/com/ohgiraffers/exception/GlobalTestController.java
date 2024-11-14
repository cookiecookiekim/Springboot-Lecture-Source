package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalTestController {

    /* comment.
    *   다른 클래스에서 @ExceptionHandler 메서드를 작성해 두었다고,
    *   현 클래스에서 발생하는 예외를 처리할 수는 없다.
    *   이유 : 전혀 연관 없는 클래스이기 때문 */

    @GetMapping("global-nullpointer")
    public String globalNull(){ // 이 메서드를 ExceptionController에 옮기면 적용이 된다.
                            // 이유는 클래스 레벨로 (@Controller) 묶기 때문

        String str = null;
        System.out.println(str.charAt(0)); // 강제로 에러 발생시키기
        //ExceptionController에서 이미 null포인터 에러를 정의해놨는데,
        // global-nullpointer를 클릭 시에는 정의한 에러가 아니라 기본 에러가 뜬다.
        // 이유 : 각기 다른 bean이고 의존성 연결한 적이 없으므로 위 comment 확인.

        return "/";
         // → 전역으로 에러를 다룰 GlobalExceptionHandler 클래스 생성
    }
    @GetMapping("global-userexception")
    public String globalUserException () throws MemberNotFoundException {
        boolean check = true;
        if (check) {
            throw new MemberNotFoundException("찾는 회원이 없습니다.");
        }
        return "/";
    }

    @GetMapping("global-default")
    public String manyException (){
        double[] array = new double[0];
        // 강제 에러 발생하기
        // 배열의 크기를 0으로 만들어 두고, 1번째 데이터 출력하려고 할 때
        System.out.println(array[0]);

        return "/";
    }
}
