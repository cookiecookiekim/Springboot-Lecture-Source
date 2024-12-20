package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuAndCategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

// ⑤ menu의 관한 요청을 핸들링할 메서드 생성
@Controller
@RequestMapping("/menu/*")
public class MenuController {

    /* comment. 24-11-22 (금) Logging
    *   어플리케이션이 실행 중 발생하는 이벤트 (정보, 경고, 오류) 등 기록하는 과정
    *   이는 사용자 화면을 위해 만드는 기능이 아닌, 개발자가 어플리케이션의
    *   상태를 추적하고, 모니터링 하는 데 사용할 수 있다. */
    // 아파치 제공 로거 사용 , 인터페이스이므로 LogManager를 이용해 객체 생성
    private static final Logger logger = LogManager.getLogger(MenuController.class);
                                                 // 어떤 클래스에 대해 로그 처리 할 건지?


    // ⑧ MenuService의 메서드를 사용하기 위해 관계를 형성해야 함.
    private final MenuService menuService; // 생성자 주입으로 형성

    /* bean으로 등록한 메세지 소스 사용 */
    private final MessageSource messageSource;


    @Autowired
    public MenuController(MenuService menuService, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.menuService = menuService;
    }

    @GetMapping("list")
    public String findMenuList(Model model) { // String 설정

        // 전체 메뉴 조회는 MenuDTO 타입이 여러개이기 때문에 List
        List<MenuDTO> menuList = menuService.findAllMenus(); // ⑦ MenuService 클래스 생성
        // ⑫ 전체 조회 결과를 menuList에 담음

        // DB 조회 값이 잘 들어있는지 확인용
        for (MenuDTO menu : menuList) {
            System.out.println("menu = " + menu);
        }

        model.addAttribute("menuList", menuList);
        return "menu/list"; // view page 작성
    }

    @GetMapping("regist") // 신규 메뉴 등록 핸들러 메서드
    public String registPage() {

        return "menu/regist";
    }


    /* comment. @ResponseBody 어노테이션
     *   기존의 컨트롤러는 view를 마지막에 리턴하는 것이 의무이지만
     *   해당 어노테이션이 붙으면 view를 리턴해야 하는 의무가 아닌,
     *   데이터만 리턴할 수 있게 만든다.
     *   json → 자바스크립트 객체 표기법을 의미 */
    // category 데이터를 미리 가져오기 위한 fetch 처리 메서드
    // regist.html의 fetch절은 자바스크립트 공간이므로 다르게 보내야한다.
    @GetMapping(value = "category", produces = "application/json; charset=UTF-8")
    // 두 번째 인자로 자바스크립트 식으로 설정
    @ResponseBody
    // 원래 컨트롤러는 view 리턴 의무가 있지만, ResponseBody가 붙으면 view까지 return할 의무는 없다 (데이터만)
    public List<CategoryDTO> findcategoryList() {
        return menuService.findAllCategory();
    }

    // 5교시
    @PostMapping("regist") // 아무것도 작성 안 하면 forward가 디폴트
    public String registMenu(@ModelAttribute MenuDTO newMenu, RedirectAttributes rttr, Locale locale){
        /* comment.
        *      @ModelAttribute : form 태그로 묶어서 넘어오는 값을 클래스 자료형에 담기 위해 작성
        *      @RedirectAttributes : 리다이렉트 시 저장할 값이 있으면 사용하는 객체 */
        // resources/ messages/ message_ko_KR.properties 파일 생성
        // message_ko_KR.properties : ko_KR은 한국어 기본 설정
        menuService.regirstMenu(newMenu);

        /* comment.
        *   TRACE : 상세한 디버깅 정보 (매우 세밀한 로그)
        *   DEBUG : 개발 중 디버깅용 정보
        *   INFO : 일반적인 실행 정보
        *   WARN : 잠재적인 문제 경고
        *   ERROR : 실행중 발생한 오류 */
        // 추후에 sout 말고 logger 처리로 정보를 확인해야 함. (안에 뭐 들어있는지 확인)
        logger.info("Locale : {}" , locale);
        logger.info("newMenu : {}" , newMenu);

        // 사용자에게 등록 잘 됐다는 viwe 설정
        rttr.addFlashAttribute("successMessage"
,messageSource.getMessage("regist", new Object[]{newMenu.getName(), newMenu.getPrice()}, locale)); // key값으로 꺼내오기

        return "redirect:/menu/list";
    }

    @GetMapping("codeSelect")
    public String codeSelect(){

        return "menu/menuCodeList";
    }

    @PostMapping("codeSelect")
    public String codeSelect (Model model, @RequestParam int selectCode) {

        System.out.println("selectCode = " + selectCode);
        List<MenuDTO> menuCode = menuService.selectMenuCode(selectCode);

        System.out.println("menuCode = " + menuCode);

        model.addAttribute("menuCode" , menuCode);

        return "menu/resultMenuCode";
    }

    @GetMapping("click")
    public String click (Model model , @RequestParam int code) {

        List<MenuDTO> result = menuService.clickMenuSelect(code);

        model.addAttribute("result" , result);

        return "menu/clickResult";
    }

    // 24-11-22 (금) Join 조회
    @GetMapping("join/list")
    public String menuAndCategoryList (Model model){
        // 값 담기 위한 Model 객체 전달 (MenuAndCategoryDTO 구성)

        // 메뉴 정보 뿐 아니라 카테고리 정보도 받아 와야 함 List<MenuAndCategoryDTO>
        List<MenuAndCategoryDTO> joinList = menuService.findAllMenuAndCategory();

        model.addAttribute("joinList", joinList);
        return "menu/join";
    }

    /* comment. DELETE 구문 생성 (미션)
    *       인덱스 페이지에서 DELETE 버튼 누르면
    *       메뉴 코드를 입력할 수 있는 input 태그와
    *       전송 버튼을 보여주는 view 페이지로 이동.
    *       -
    *       이후 값 전달 받아, 삭제하는 기능 생성
    *       전송버튼 누르면 menu/list 페이지로 redirect 진행
    *       리다이렉트 시 사용자에게 alert 창으로 '~ 번 메뉴 삭제 완료되었습니다' 출력 */

    @GetMapping ("delete")
    public String menuDelete(){
        return "menu/delete";
    }

    @PostMapping ("delete")
    public String menuDelete(@RequestParam("deleteName") int menuCode, RedirectAttributes rttr, Locale locale){

        menuService.deleteMenuCode(menuCode);

        rttr.addFlashAttribute("deleteMessage"
                ,messageSource.getMessage("delete",new Object[]{menuCode}, locale));

        return "redirect:/menu/list";
    }

    @PostMapping("modify")
    public String menuModify () {

        return "menu/modify";
    }
}
