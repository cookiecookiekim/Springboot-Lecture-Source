package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

// ⑤ menu의 관한 요청을 핸들링할 메서드 생성
@Controller
@RequestMapping("/menu/*")
public class MenuController {

    // ⑧ MenuService의 메서드를 사용하기 위해 관계를 형성해야 함.
    private final MenuService menuService; // 생성자 주입으로 형성

    @Autowired
    public MenuController(MenuService menuService) {
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
    public String registPage () {
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
    public List<CategoryDTO> findcategoryList () {

        return menuService.findAllCategory();
    }
}
