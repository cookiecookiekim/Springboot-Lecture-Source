package com.ohgiraffers.handlermethod;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

// 24-11-13 (수) 1교시 요청을 처리할 컨트롤러 클래스
@Controller
@RequestMapping("/request/*") // /request 이하 모든 요청 매핑
@SessionAttributes("id") // key: "id" 설정 (sessionTest2 메서드 처리용)
public class RequestController {

    /* title. 요청 시 값을 전달받는 방법 */

    // view 페이지를 반환하는 다른 방법 (이전에는 String 문자열로 페이지 네임 리턴)
    /* comment. view 페이지를 보여주는 방식
     *   1. String 타입 반환값에 view 파일 이름 작성 (chap01)
     *   2. 메서드의 반환 타입을 void로 설정
     *   → 반환 타입을 void로 하게 되면 요청 url이 view의 이름이 된다. */

    @GetMapping("regist") // view 파일 생성 → templates에 regist.html 구성
    // 버튼 클릭 시 /request/* 하위의 "regist" 동작
    public void regist() {  // 페이지 넘어가는 역할
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

    /* comment.
     *   요청 파라미터가 여러개인 경우, 각각 담는 것이 아닌
     *   Map을 사용하여 한 번에 담을 수 있다.
     *   맵의 키는 form 태그의 name 속성 값이 된다. */
    @PostMapping("modifyAll") // modifyAll 을 처리할 메서드 생성
    public String modifyAll(Model model,
                            @RequestParam Map<String, String> parameters) {

        // map에 담긴 값 꺼내기
        String menuName = parameters.get("modifyName2"); // form 태그의 name
        int mennuPrice = Integer.parseInt(parameters.get("modifyPrice2"));

        String message = menuName + "의 가격을 " + mennuPrice + " 로 수정";

        model.addAttribute("message", message);

        return "request/printResult";
    }

    @GetMapping("search")  // search.html 생성
    public void search() { // search.html로 보내주는 메서드
    }

    /* comment. ★ 요청 파라미터가 몇 개 안 되면 @RequestParam
     *        어노테이션을 사용해도 간단하게 작성이 가능하다.
     *   하지만 받아올 데이터가 많아진다면 관리할 변수나,
     *   키 값이 많아질 수 밖에 없다.
     *   -------------------------------------
     *   @ModelAttribute 객체를 생성하여 요청되는 값을
     *   필드와 form 태그의 name 속성과 비교하여 값을 넣어준다.
     *   ---------------------
     *   @ModelAttribute 담은 값은 view 페이지에서 타입(자료형)
     *   앞글자를 소문자로 한 네이밍 규칙으로 사용 가능하다 (menuDTO)
     *   -------------
     *   다른 이름을 사용하고 싶다면 @ModelAttribute("사용할값")
     *   이렇게 지정도 가능하다. */
    @PostMapping("search") // search.html에서 넘어온 결과 처리 메서드
    public String searchMenu(@ModelAttribute MenuDTO menu) { // 여기서 MenuDTO 생성

        System.out.println("menu = " + menu); // 담긴 거 확인 용으로 출력
//menu = MenuDTO{name='1231234', price=7, categoryCode=1, orderableStatus='Y'}
// DTO 상의 필드 이름(변수) 명대로 알아서 담겨 출력
        // → DTO의 필드값과 search.html 의 name 속성 필드 이름 맞춰 줘야함!!!!

        // 이번엔 키와 밸류를 설정하지 않았음.
        // 하지만 ModelAttribute 자체가 Model 속성이므로 key가 이미 설정되어 있음.
        // 키값은 menu가 아닌 MenuDTO인데, 앞글자를 소문자로 바꿔서
        // key : menuDTO

        return "request/searchResult";
    }

    @GetMapping("login")
    public void login() {
    } // login.html 파일 생성

    /* comment. HttpSession 객체 이용해서 요청 값 저장하기 */
    @PostMapping("login1")
    public String loginTest(HttpSession session,
                            @RequestParam String id) { // form 태그의 id 꺼내오기

        session.setAttribute("id", id); // 키와 밸류 설정
        // model에 add 할 필요 없는 이유 : session을 사용한다는 건
        // 한 브라우저 내에서 항상 사용 가능 (세션이 만료될 때 까지)
        return "request/loginResult";
    }

    @GetMapping("logout1") // loginResult에서의 로그아웃 처리 메서드 생성
    public String logout1(HttpSession session) {
        // 세션 만료 위해 매개변수로 session 받기
        session.invalidate(); // session을 강제로 만료 시키는 메서드
        return "request/loginResult";
    }

    /* comment. @SessionAttributes를 이용한 session에 값 담기
     *   클래스 레벨에 @SessionAttributes를 사용하여
     *   Session에 담을 key 값을 설정해두면 Model 영역에
     *   해당 key로 값이 추가되는 경우 자동으로 session에 등록해준다. */
    @PostMapping("login2")
    public String sessionTest2(Model model,
                               @RequestParam String id) {
        model.addAttribute("id", id);
        // key가 상위 @SessionAttributes("id") id와 일치해야함
        return "request/loginResult";
        // session 객체가 아니므로 session.invalidate(); 동작하지 않음
        // 로그아웃 눌러도 반응 없음.
    }

    /* comment.
    *   SessionAttributes 방식은 session의 상태를 관리하는
    *   SessionStatus 객체의 setComplete() 메서드를 사용해야 만료 가능 */
    // session이 아닌 model에 담긴 id 정보 삭제위한 로그아웃 메서드
    @GetMapping("logout2")// 많이 사용하지 않음, 이런 게 있구나하고 넘어가기
    public String logout2 (SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "request/loginResult"; // 로그아웃2 → 로그아웃 가능!
    }

    @GetMapping("body")
    public void body(){}

    /* comment. @RequestBody
    *   해당 어노테이션은 http 본문 자체를 읽는 부분을 모델로 변환
    *   출력해보니 쿼리스트링 형태로 문자열 전달된다.
    *   → key 와 value 형태로 값이 전달되고 있다.
    *   추후에 나올 개념인 JSON(자바스크립트객체표현식)으로 전달되면
    *   Jackson 컨버터 → 자바스크립트 객체 ↔ 자바 객체
    *   자동 변환해주어 프론트엔드 서버(js 기반) 백엔드 서버(java기반)
    *   간의 데이터 전송을 가능케 해준다.
    *   ----------------------
    *   주로 Rest API를 사용하여 만들 때 많이 사용하며
    *   일반적인 form 테그에서 거의 사용하지 않는다. */
    @PostMapping("body")
    public void bodyTest(@RequestBody String body) throws UnsupportedEncodingException {
        System.out.println("body = " + body);
//body = name=%EC%BD%9C%EB%94%B1%EC%A7%80&price=3311&categoryCode=1&orderableStatus=Y
        // post이므로 데이터가 숨겨져 있으나, RequestBody 사용 시, 가려진 채로 url 출력
        /* comment.
        *   'encoding 되어 있다.(암호화 처리)' 되어 있다고 말하고,
        *   해석하기 위해 URLDecoder로 decoding 해야 한다. */

        // 예외처리 강제화, UTF-8 : 한글 안 깨지게
        System.out.println(URLDecoder.decode(body, "UTF-8"));
        // name=쿠딱지&price=33231&categoryCode=1&orderableStatus=Y


    }
}
