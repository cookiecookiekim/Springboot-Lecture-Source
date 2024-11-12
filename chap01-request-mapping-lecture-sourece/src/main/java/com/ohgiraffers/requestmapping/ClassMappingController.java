package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 2. 클래스의 요청 매핑을 위한 클래스 (4개의 요청 처리)
// 4개의 요청은 모두 /order로 시작

@Controller
/* comment. 클래스 레벨에 RequestMapping 어노테이션 사용 가능.
*       공통되는 URL을 설정해두고 *(와일드카드)를 이용하면
*       조금 더 포괄적인 URL 패턴 설정 가능. */
@RequestMapping("/order/*") // /order로 시작하는 모든 요청을 처리하겠다.
public class ClassMappingController {

    /* 1. Class 레벨에 매핑하기 (중복 및 반복 방지) */
    @GetMapping("/regist")
    public String resgistOrder(Model model) {
        model.addAttribute("message"
                ,"GET 방식의 주문 등록 핸들러 메서드 호출됨..");
        return "mappingResult";
    }

    /* 2. 여러 URL 매핑하기 */
    // modify , delete 동시에 처리
//    @RequestMapping(value = {"modify", "delete"}, method = RequestMethod.POST)
}
