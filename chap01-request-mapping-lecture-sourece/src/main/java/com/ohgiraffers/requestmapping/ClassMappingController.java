package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String registOrder(Model model) {
        model.addAttribute("message"
                ,"GET 방식의 주문 등록 핸들러 메서드 호출됨..");
        return "mappingResult";
    }

    /* 2. 여러 URL 매핑하기 */
    // modify , delete 동시에 처리
    @RequestMapping(value = {"modify", "delete"}, method = RequestMethod.POST) // 여러가지도 동시에 가능하다라는 것만 알기.
    public String modifyAndDelete(Model model){
        model.addAttribute("message"
                , "POST 방식의 수정, 삭제 둘 다 처리하는 핸들러 메서드 호출됨..");
        return "mappingResult";
    }

    /* 3. path variable (url 경로를 타고 오는 값) → 추후에 많이 사용함 */
    /* comment. @PathVariable 어노테이션을 통해 요청 URL로부터 변수를 받아올 수 있음.
    *   path variable로 전달되는 {변수명} 값은 반드시 매개변수 명과 일치해야 함.
    *   만약, 동일하지 않으면 @PathVariable("이름")을 설정해야 한다. */
    @GetMapping("/detail/{orderNo}") // 숫자로 고정(예,3)하면 값이 고정되므로 변수처리 해야 여러 값 대입 가능
    public String orderDetail(Model model, @PathVariable("orderNo") int no) {
                                    //@PathVariable 받을 매개변수 생성 int orderNo
        model.addAttribute("message"
                , no + "번 주문 상세 조회 핸들러 메서드 호출됨..");
        return "mappingResult";
    }
}
