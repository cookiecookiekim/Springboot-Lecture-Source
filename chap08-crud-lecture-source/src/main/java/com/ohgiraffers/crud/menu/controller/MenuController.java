package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

// ⑤ menu의 관한 요청을 핸들링할 메서드 생성
@Controller
@RequestMapping("/menu/*")
public class MenuController {

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
}
